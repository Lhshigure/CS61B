public class SLListTest {
    public static void main(String[] args){
        SLList L = new SLList();
        L.addFirst(2);
        L.addFirst(1);
        L.addFirst(0);
        System.out.println(L);
        L.reverse();
        System.out.println(L);

    }
}
