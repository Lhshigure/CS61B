import com.sun.source.doctree.SeeTree;

import java.util.*;

public class ADTdemos {
    public static boolean sumset(int[] A, int k){
        Set<Integer> sumSet = new HashSet<>();
        for(int a : A){
            int diff = k - a;
            if(sumSet.contains(diff)){
                return true;
            }
        }
        return false;
    }

    public static void kMostCommonWords(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for(String w: words){
            if(counts.containsKey(w)){
                int oldcount = counts.get(w);
                counts.put(w, oldcount + 1);
            }else{
                counts.put(w, 1);
            }
        }
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return counts.get(b) - counts.get(a);
            }
        }); //这里没搞懂？？？
        for (String word : counts.keySet()) {
            pq.add(word);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(pq.poll());
        }
    }
}
