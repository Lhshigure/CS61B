public class LinkedListDeque<T> {
    private class Node{
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(T item){
       sentinel = new Node(sentinel.prev, null, sentinel.next);
       sentinel.prev = sentinel.next;
       sentinel.next = new Node(sentinel, item, sentinel);
       size = 1;
    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel.prev = sentinel;
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.next = sentinel;
        size = 0;

        for(int i = 0; i < other.size(); i ++){
            addFirst((T)other.get(i));
        }
    }
    public void addFirst(T item){
        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;

    }
    public void addLast(T item){
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
       Node ptr = sentinel;
       while(ptr.next != sentinel){
           System.out.print(ptr.next.item + " ");
           ptr = ptr.next;
       }
       System.out.println();
    }
    public T removeFirst(){
       if (size == 0){
           return null;
       }else{
           Node ptr = sentinel.next;
           ptr.next.prev = sentinel;
           sentinel.next = ptr.next;
           size -= 1;
           return ptr.item;
       }
    }
    public T removeLast(){
        if(size == 0){
            return null;
        }else{
            Node ptr = sentinel.prev;
            ptr.prev.next = sentinel;
            sentinel.prev = ptr.prev;
            size -= 1;
            return ptr.item;
        }
    }
    public T get(int index){
        if( index < size){
            return null;
        }else{
            Node ptr = sentinel.next;
            while(index != 0){
                ptr = ptr.next;
                index -= 1;
            }
            return ptr.item;
        }
    }
}
