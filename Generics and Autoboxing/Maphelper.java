package Map61B;

import Map61B.Map61B;

import java.util.List;
import java.util.Map;

public class Maphelper {
    public static <K extends Comparable<K>, V> V get(Map61B<K,V> map, K key){
        if (map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }

    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map){
        List<K> keylist = map.keys();
        K kk = keylist.get(0);
        V vv = map.get(kk);
        K largest = map.getKey(vv);//get return V type, getkey return K type
        for (K k: keylist){
            if(k.compareTo(largest) > 0){
                largest = k;
            }
        }
        return largest;
    }

}
