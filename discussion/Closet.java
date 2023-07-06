import java.util.*;

public class Closet {
    Set<Clothing> clothes = new HashSet<>();
    Map<String, List<Clothing>> clothesColor = new HashMap<>();
    public Closet(List<Clothing> clothes){
        for(Clothing c: clothes){
            clothes.add(c);
        }
        for(Clothing uniquec : clothes){
            String color = uniquec.color;
            if(!clothesColor.containsKey(color)){
                clothesColor.put(color, new ArrayList<>());
            }
            clothesColor.get(color).add(uniquec);
        }
    }
    public List<Clothing> getItemsByDay(Map<String, String> daysToColors, String currentDay){
        String color = daysToColors.get(currentDay);
        return clothesColor.get(color);
    }
    public void enterEmoPhase(List<String> happyColor){
        for(String s : happyColor){
            for(Clothing c: clothesColor.get(s)){
                c.dyeColor("BLACK");
            }
        }
    }
}
