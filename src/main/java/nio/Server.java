package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xu on 2020-05-07
 */
public class Server {

    private int port = 8888;

    private Charset charset = Charset.forName("UTF-8");

    private ByteBuffer rbuffer = ByteBuffer.allocate(1024);

    private ByteBuffer wbuffer = ByteBuffer.allocate(1024);

    private Map<String, SocketChannel> clientMap = new HashMap<>();

    // 用于监听通道事件
    private static Selector selector;

    public Server(int port) {
        this.port = port;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        //注册应答事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动,端口={}" + port);

    }

    /**
     * 服务器端轮询监听，select 方法会一直阻塞直到有相关事件发生或超时
     */
    public void listen() {
        while (true) {
            try {
                selector.select();   // 返回值为本次触发的事件数
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> handle(selectionKey));
                selectionKeys.clear(); // 清除处理过的事件
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void handle(SelectionKey selectionKey) {

        try {
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                SocketChannel accept = server.accept();
                accept.configureBlocking(false);
                accept.register(selector, SelectionKey.OP_READ);
                clientMap.put(getClientName(accept), accept);

            } else if (selectionKey.isReadable()) {
                SocketChannel client = (SocketChannel) selectionKey.channel();
                rbuffer.clear();
                int bytes = client.read(rbuffer);
                if (bytes > 0) {
                    rbuffer.flip();
                    String receiveText = String.valueOf(charset.decode(rbuffer));
                    System.out.println(client.toString() + ":" + receiveText);
                    dispatch(client, receiveText);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发消息给各个客户端
     */
    private void dispatch(SocketChannel client, String info) throws IOException {
        if (!clientMap.isEmpty()) {
            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                SocketChannel temp = entry.getValue();
                if (!client.equals(temp)) {
                    wbuffer.clear();
                    wbuffer.put(charset.encode(getClientName(client) + ":" + info));
                    wbuffer.flip();
                    temp.write(wbuffer);
                }
            }
        }
    }

    private String getClientName(SocketChannel client) {
        Socket socket = client.socket();
        return "[" + socket.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.listen();
    }


}
