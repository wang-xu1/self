package dataStructure;


/**
 * Created by xu on 2020-06-30
 * 仿写Java ArrayList
 */
public class MyArray {


    private int[] array;

    private int size;

    public MyArray(int capcatity) {
        this.array = new int[capcatity];
        this.size = 0;
    }


    /**
     * 按index查找
     * o(1)
     */

    public int gitByindex(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("数组下标越界");
        }

        return array[index];
    }


    /**
     * 按value查找index
     * o(n)
     */

    public int gitIndex(int val) {
        for (int i = 0; i < size - 1; i++) {
            if (array[i] == val) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 尾部插入
     */

    public int inster(int val) {
        if (size >= array.length) {
            resize();
        }
        array[size] = val;
        size++;
        return size;
    }

    /**
     * 中间插入
     */

    public int insterMid(int index, int val) {
        if (index < 0 || index > size) {
            throw new RuntimeException("数组访问越界");
        }

        if (size >= array.length) {
            resize();
        }

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = val;
        size++;
        return val;
    }


    /**
     * 删除元素
     */


    public int remove(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("数组访问越界");
        }

        int delectVal = array[index];

        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }

        return delectVal;

    }


    /**
     * 扩容
     */
    private void resize() {
        int[] arrayNew = new int[2 * array.length];
        System.arraycopy(array, 0, arrayNew, 0, array.length);
        array = arrayNew;
    }


    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }


    public static void main(String[] args) {
        MyArray myArray = new MyArray(10);
        myArray.inster(1);
        myArray.inster(2);
        myArray.inster(3);
        myArray.inster(4);
        myArray.inster(5);
        myArray.inster(6);
        myArray.inster(7);
        myArray.inster(8);
        myArray.inster(9);


        myArray.insterMid(2, 10);
        myArray.print();
        System.out.println(myArray.gitByindex(9));
        System.out.println("==============扩容==============");
        myArray.inster(11);
        myArray.print();


    }

}
