package Map61B;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ArrayMap<K, V> implements Map61B<K, V> {
    private K[] keys;
    private V[] values;
    int size;
    public ArrayMap(){
        keys = (K[])new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }
    private int keyIndex(K key){
        for (int i = 0; i < size; i++){
            if(keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private int valueIndex(V value){
        for (int i = 0; i < size; i++){
            if(values[i].equals(value)){
                return i;
            }
        }
        return -1;
    }
    public int size(){
        return size;
    }
    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    public void put(K key, V value){
        int index = keyIndex(key);
        if(index == -1){
            keys[size] = key;
            values[size] = value;
            size += 1;
        }else{
            values[index] = value;
        }

    }
    public K getKey(V value){
        int index = valueIndex(value);
        return keys[index];
    }
    public V get(K key){
        int index = keyIndex(key);
        return values[index];
    }

    public List<K> keys(){
        List<K> keyList = new ArrayList<>();
        for(int i = 0; i < keys.length; i++){
            keyList.add(keys[i]);
        }
        return keyList;
    }
    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        int expected = 5;
        assertEquals((Integer) expected, am.get(2));
    }


}
