import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet(){
        items = (T[])new Object[100];
        size = 0;
    }
    public boolean contains(T x){
        if(size == 0){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(items[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return size;
    }
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("You can't add null");
        }
        if(contains(x)){
            return;
        }
        items[size] = x;
        size += 1;
    }
    private class ArraySetIterator implements Iterator<T>{
        private int wizPos;
        public ArraySetIterator(){
            wizPos = 0;
        }
        public boolean hasNext(){
            return wizPos < size;
        }
        public T next(){
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }
    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);
        Iterator<Integer> aseer = aset.iterator();
        while(aseer.hasNext()){
            int i = aseer.next();
            System.out.println(i);
        }

        for(int i : aset){
            System.out.println(i);
        }

        System.out.println(aset);

        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals("fish"));
        System.out.println(aset.equals(aset));
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i = 0; i < size; i++){
            sb.append(items[i]);
            if(i != size - 1){
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(other == null){
            return false;
        }
        if(other.getClass() != this.getClass()){
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if(o.size() != this.size()){
            return false;
        }
        for (T item: this){
            if(!o.contains(item)){
                return false;
            }
        }
        return true;
    }

    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> returnSet = new ArraySet<Glerp>();
        for (Glerp x : stuff) {
            returnSet.add(x);
        }
        return returnSet;
    }

}
