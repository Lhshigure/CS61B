public class VengefulSLList <T> extends SLList<T>{
    SLList<T> deletedItems;
    public VengefulSLList(){
        super();
        deletedItems = new SLList<T>();
    }
    public VengefulSLList(T x){
        super(x);
        deletedItems = new SLList<T>();
    }

    public void printLostItems(){
        deletedItems.print();
    }

    @Override
    public T removeLast(){
        T x =super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    public static void main(String[] args){
        VengefulSLList<Integer> vs1 = new VengefulSLList<>(0);
        vs1.addLast(1);
        vs1.addLast(5);
        vs1.addLast(10);
        vs1.addLast(11);
        vs1.print();
        vs1.removeLast();
        vs1.removeLast();
        vs1.removeLast();
        System.out.print("The fallen are: ");
        vs1.printLostItems();
    }
}
