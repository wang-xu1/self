package algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by xu on 2020/6/13
 */
@Data
@AllArgsConstructor
public class Array {

    private int[] array;

    private int size;

    public Array(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 根据value查找i
     */

    public int getVal(int val) {
        for (int i = 0; i < size; i++) {
            if (array[i] == val) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 头插入
     */

    public void insertHead(int val) {
        if (size > 0) {
            for (int i = size - 1; i <0; i--) {
                array[i + 1] = array[i];
            }
        }
        array[0] = val;
        size++;
    }


    /**
     * 尾插入
     */

    public void insertTail(int val) {
        array[size + 1] = val;
        size++;
    }


    public void insert(int index, int val) {
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = val;
        size++;
    }


    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }


    /**
     * 删除
     */

    public static void main(String[] args) {

        Array array = new Array(10);

        array.insertTail(2);
        array.insertHead(1);
        array.insertHead(3);


        array.print();

    }

}
