public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private double usage;
    public ArrayDeque(){
        items = (T[])new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        usage = 0.0;
    }
    private void resize(int capacity){
        T[] a = (T[])new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    public ArrayDeque(ArrayDeque other){
        T[] newarray = (T[])new Object[other.size];
        System.arraycopy(newarray, 0, other,0,other.size);
    }
    public void addFirst(T item){
        if(nextFirst == 0){
            items[nextFirst] = item;
            nextFirst = size - 1;
            size ++;
        }
        items[nextLast] = item;
        nextLast ++;
        size ++;
    }
    public void addLast(T item){
        if(nextLast == items.length - 1){
            items[nextLast] = item;
            nextLast = 0;
            size ++;
        }
        items[nextFirst] = item;
        nextLast --;
        size ++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public T removeFirst(){
        size --;
        return items[nextFirst + 1];
    }
    public T removeLast() {
        size --;
        return items[nextLast - 1];
    }
    public T get(int index){
        index = (index + nextFirst) % items.length;
        return items[index];
    }
}
