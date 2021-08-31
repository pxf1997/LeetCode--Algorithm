package exer;

import java.util.ArrayList;
import java.util.Arrays;
public class MaxHeap<E extends Comparable<E>> {
    private final ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<E>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<E>();
    }

    //heapify操作:将数组转化为堆
    public MaxHeap(E[] arrs) {
        data = new ArrayList<>(Arrays.asList(arrs));
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }

    public void swap(int i, int j) {
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int index) {  //k不能是根节点，并且k的值要比父节点的值大
        int parentIndex = parent(index);
        while (index > 0 && data.get(index).compareTo(data.get(parentIndex)) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    //看堆中最大的元素
    public E findMax() {
        if (data.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        return data.get(0);
    }

    //取出堆中最大的元素
    public E extractMax() {
        E ret = findMax();
        swap(0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {
        //leftChild存在
        while (leftChild(k) < data.size()) {
            int j = leftChild(k);
            //rightChild存在,且值大于leftChild的值
            if (j + 1 < data.size() &&
                    data.get(j).compareTo(data.get(j + 1)) < 0)
                j = rightChild(k);
            //data[j]是leftChild和rightChild中最大的

            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            swap(k, j);
            k = j;
        }
    }

    //取出堆中最大的元素,替换为元素e
    public E replace(E e) {
        E ret = findMax();

        data.set(0, e);
        siftDown(0);
        return ret;
    }


}
