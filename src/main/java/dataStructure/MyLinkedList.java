package dataStructure;



import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by xu on 2020-07-02
 */
public class MyLinkedList {


    private Node head;

    private Node last;

    private int size;


    @Data
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }


    /**
     * 插入
     */

    public void insert(int index, int v) {
        if (index < 0 || index > size) {

        }

        Node node = new Node(v);
        //空链表
        if (size == 0) {
            head = node;
            last = node;
        } else if (index == 0) {
            //头节点
            node.next = head;
            head = node;
        } else if (size == index) {
            //尾节点
            last.next = node;
            last = node;
        } else {
            Node node1 = get(index - 1);
            node.next = node1.next;
            node1.next = node;
        }

        size++;

    }


    /**
     * 删除
     */

    public Node remove(int index) {
        if (index < 0 || index > size) {

        }


        Node removeNode = null;

        //头节点
        if (index == 0) {
            removeNode = head;
            head = head.next;
        } else if (index == size - 1) {
            Node node = get(index - 1);
            removeNode = node.next;
            node.next = null;
            last = node;
        } else {
            Node node = get(index - 1);
            removeNode = node.next;
            node.next = node.next.next;

        }
        size--;
        return removeNode;

    }


    /**
     * 查询
     *
     * @param index
     * @return
     */
    public Node get(int index) {
        if (index < 0 || index > size) {

        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;

    }


    public void print() {

        Node temp = head;
        for (int i = 0; i < size; i++) {
            System.out.println(temp.data);
            temp = temp.next;

        }
    }


    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(4);

        BigDecimal bigDecimal1 = BigDecimal.valueOf(0);


        BigDecimal bigDecimal2 = BigDecimal.valueOf(3);


        System.out.println(bigDecimal1.compareTo(bigDecimal)>0);

        System.out.println(bigDecimal.compareTo(bigDecimal2)<0);
    }

}
