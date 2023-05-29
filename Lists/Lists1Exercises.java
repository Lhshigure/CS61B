public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        /* Your code here. */
        IntList lst = new IntList(L.first + x, null);
        IntList p = lst; // 记录lst的地址,用于在while中操作lst
        while(L.rest != null){
            p.rest = new IntList(L.rest.first + x, null);
            p = p.rest;
            L = L.rest;
        }
        return lst;        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        /* Your code here. */
        // base case
        if (L.rest == null){
            L.first += x;
        }else{
            L.first += x; 
            dincrList(L.rest, x);
        }
        return L;
    }

    public static IntList dSquareList(IntList L){
        // base case
        if (L.rest == null){
             L.first = L.first * L.first;
        }else{
            L.first = L.first * L.first;
            dSquareList(L.rest);
        }

        return L;
    }

    public static IntList nonSquareList(IntList L){
        IntList lst = new IntList(L.first * L.first, null);
        IntList ptr = lst;
        while(L.rest != null){
            ptr.rest = new IntList(L.rest.first * L.rest.first, null);
            ptr = ptr.rest;
            L = L.rest;
        }        
        return lst;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        //System.out.println(L.size());
        //System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        // System.out.println(L.get(1));
        
        System.out.println(incrList(L, 3));
        System.out.println(dincrList(L, 3));        
        System.out.println(nonSquareList(L));
        System.out.println(dSquareList(L));
        
    }
        
}