package ProjectProgredior;

import java.util.ArrayList;
import java.util.Arrays;

public class HashMap<K extends Object, V>{
    private static final float MAX_LOAD_CAPACITY = 0.67f;
    private Entry<K, V>[] backingArray;
    private int size = 0;
    private int capacity;
    private static class Entry<K,V>{
        K key;
        V value;
        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public void setKey(K key) {
            this.key = key;
        }
        public K getKey(){
            return key;
        }
        public V getValue() {
            return value;
        }
        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    public K DELMARKER;
    HashMap(int capacity, K delmarker){
        this.capacity = capacity;
        this.backingArray = new Entry[capacity];
        this.DELMARKER = delmarker;
    }
    public V put(K key, V value) {
        if((size+1)/(float)backingArray.length > 0.67){
            backingArray = resize();
            capacity*=2;
        } else {
            int hash = key.hashCode();
            int index = Math.abs(hash % backingArray.length)-1;
            index = probeDoubleHashingAlpha(index, key, backingArray);
            if(backingArray[index] != null && backingArray[index].getKey().equals(key)){
                V temp = backingArray[index].getValue();
                backingArray[index] = new Entry<>(key, value);
                size++;
                return temp;
            }
            backingArray[index] = new Entry<>(key, value);
            size++;
        }
        return null;
    }
    private Entry<K, V>[] resize() {
        Entry<K, V>[] temp = new Entry[capacity*2 +1];
        for (int i = 0; i < backingArray.length; i++) {
            if(backingArray[i]!= null){
                int hash = backingArray[i].getKey().hashCode();
                int index = Math.abs(hash% temp.length)-1;
                index = probeDoubleHashingAlpha(index, backingArray[i].getKey(), temp);
                temp[index] = new Entry<>(backingArray[i].getKey(), backingArray[i].getValue());
            }
        }
        return temp;
    }
    private int hashingFunction(K key){
        return (17-key.hashCode());
    }
    private int probeDoubleHashingAlpha(int index, K key, Entry<K, V>[] backingArray){
        int i = 1;
        while(backingArray[index] != null && !backingArray[index].getKey().equals(key)){
            index = Math.abs((key.hashCode() + hashingFunction(key)*i)% backingArray.length)-1;
            i++;
            if(i >= backingArray.length){
                return -1;
            }
        }
        return index;
    }
    private int probeDoubleHashing(int index, K key, Entry<K, V>[] backingArray){
        int i = 1;
        while(backingArray[index] != null && !backingArray[index].getKey().equals(key)){
            index = Math.abs((key.hashCode() + hashingFunction(key)*i)% backingArray.length)-1;
            i++;
            if(i >= backingArray.length || backingArray[index] == null){
                return -1;
            }
        }
        return index;
    }
    public V get(K key){
        int index = Math.abs(key.hashCode()% backingArray.length)-1;
        index = probeDoubleHashing(index, key, backingArray);
        return index == -1 ? null : backingArray[index].getValue();
    }
    public V remove(K key){
        int index = Math.abs(key.hashCode()% backingArray.length)-1;
        if(backingArray[index] == null){
            return null;
        } else {
            int i = 0;
            while(!backingArray[index].getKey().equals(key)){
                index = Math.abs((backingArray[index].getKey().hashCode() + hashingFunction(backingArray[index].getKey())*i)% backingArray.length)-1;
                i++;
                if(i > backingArray.length || backingArray[index] == null){
                    return null;
                }
            }
            V value = backingArray[index].getValue();
            backingArray[index].setKey(DELMARKER);
            return value;
        }

    }
    @Override
    public String toString() {
        return "HashMap{" +
                "backingArray=" + Arrays.toString(backingArray) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}
