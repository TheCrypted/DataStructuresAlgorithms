package ProjectProgredior;

import org.w3c.dom.Node;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> {
    public class ArrayListHeap{
        int size = 1;
        T[] backingArray = (T[]) Array.newInstance(Comparable.class, 16);
        public void add(T data){
            if(size < backingArray.length){
                backingArray[size] = data;
                size++;
            } else {
                T[] newArray = (T[])Array.newInstance(Comparable.class, 2*backingArray.length);
                System.arraycopy(backingArray, 0, newArray, 0, backingArray.length);
                backingArray = newArray;
                backingArray[size] = data;
                size++;
            }
        }
        public boolean isEmpty() {
            return size == 1;
        }
        public void remove(){
            if(isEmpty()){
                return;
            } else {
                size--;
                backingArray[size] = null;
            }
        }
        public T get(int index){
            return backingArray[index];
        }
        public void set(int index, T data){
            backingArray[index] = data;
        }
        public String toString() {
            return Arrays.toString(backingArray);
        }
    }
    public ArrayListHeap backingArray = new ArrayListHeap();
    public void add(T data){
        backingArray.add(data);
        upHeap(backingArray.size-1);
    }
    public void upHeap(int index){
        if(index == 1){
            return;
        }
        if(backingArray.get(index).compareTo(backingArray.get(index/2))<0){
            T temp = backingArray.get(index);
            backingArray.set(index, backingArray.get(index/2));
            backingArray.set(index/2, temp);
            upHeap(index/2);
        }
    }
    private void swap(int index, int indexA){
        T temp = backingArray.get(index);
        backingArray.set(index, backingArray.get(indexA));
        backingArray.set(indexA, temp);
    }
    public T remove(){
        T data = backingArray.get(1);
        backingArray.set(1, backingArray.get(backingArray.size-1));
//        backingArray.set(backingArray.size, null);
        backingArray.size--;
        downHeap(1);
        return data;
    }
    private void downHeap(int index){
        if(childA(index) == null && childB(index) == null){
            return;
        } else if(childA(index) != null && childB(index) == null){
            if(childA(index).compareTo(backingArray.get(index)) < 0){
                swap(index, index*2);
            }
        } else if(childA(index) != null && childB(index) != null){
            T data = Collections.min(Arrays.asList(childA(index), childB(index)));
            if(backingArray.get(index).compareTo(data)>0){
                if(data == childA(index)){
                    swap(2*index, index);
                    downHeap(index*2);
                } else {
                    swap(2*index+1, index);
                    downHeap(index*2+1);
                }
            }
        }
    }
    private T childA(int index){
        if(2*index >= backingArray.size){
            return null;
        }
        return backingArray.get(2*index);
    }
    private T childB(int index){
        if(2*index+1 >= backingArray.size){
            return null;
        }
        return backingArray.get(2*index+1);
    }
}
