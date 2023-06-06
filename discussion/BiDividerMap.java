import java.util.HashMap;

public class BiDividerMap<K, V> {
    private HashMap<K, V> keyToValue;
    private HashMap<V, K> valueToKey;
    public BiDividerMap(){
        keyToValue = new HashMap<>();
        valueToKey = new HashMap<>();
    }
    public void put(K key, V value){
        keyToValue.put(key, value);
        valueToKey.put(value, key);
    }
    public V getByKey(K key){
        return keyToValue.get(key);
    }

    public K getByvalue(V value){
        return valueToKey.get(value);
    }

    public int numLessThan(K key){
        int count = 0;
        for(K k : keyToValue.keySet()){
            if(k instanceof Comparable && ((Comparable)k).compareTo(key) < 0){
                count ++;
            }
        }
        return count;
    }
}
