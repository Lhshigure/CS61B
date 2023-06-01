public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
        @Override
        public String toString() {
            return String.valueOf(item);
        }
    }
    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }
    public void insert(int item, int position){
        if(first == null){
            first = new IntNode(item, null);
        }
        IntNode ptr = first;
        while (position != 1) {
            ptr = ptr.next;
            position --;
        }
        ptr.next = new IntNode(item, ptr.next);
    }
    /*
    // recursion
    private static IntNode reverse(IntNode p){
        // base case
        if(p == null || p.next == null){
            return p;
        }
        IntNode reversed = reverse(p.next);
        p.next.next = p;
        p.next = null;
        return reversed;
    }
    public void reverse(){
        first = reverse(first);
    }
    */
    //iterative
    public void reverse(){
        if (first == null){
            return;
        }else{
            IntNode pptr = null;
            IntNode ptr = first;
            while(ptr != null){
                IntNode nextNode = ptr.next;
                ptr.next = pptr;
                pptr = ptr;
                ptr = nextNode;

            }
            first = pptr;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntNode current = first;
        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.next;
        }
        return sb.toString();
    }

}
