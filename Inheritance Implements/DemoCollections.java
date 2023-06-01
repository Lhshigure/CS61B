import java.util.*;

public class DemoCollections{

    public static String cleanString(String s){
        return s.toLowerCase().replaceAll("[^a-z]","");
    }
    public static List<String> getWords(String inputFilename){
        List<String> words = new ArrayList<>();
        In in = new In(inputFilename);
        while(! in.isEmpty()){
            String nextword = cleanString(in.readString());
            words.add(nextword);
        }
        return words;
    }
    public static int countUniqueWords(List<String> words){
        Set<String> wordSet = new HashSet<>();
       /*for(int i = 0; i < words.size(); i ++){
            String ithWord = words.get(i);
            wordSet.add(ithWord);
        }*/
        for (String ithword : words){
            wordSet.add(ithword);
        }
        return wordSet.size();
    }

    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets){
        Map<String, Integer> counts = new HashMap<>();
        for(String t: targets){
            counts.put(t, 0);
        }
        for(String s: words){
            if(counts.containsKey(s)){
                int oldcount = counts.get(s);
                counts.put(s, oldcount + 1);
            }
        }
        return counts;
    }
    public static void main(String[] args){
        List<String> w = getWords("testFile.txt");
        System.out.println(w);
        System.out.println(countUniqueWords(w));

        List<String> targets = new ArrayList<>();
        targets.add("student");

        System.out.println(collectWordCount(w, targets));
    }

}
