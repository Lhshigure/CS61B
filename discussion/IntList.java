public class IntList {
    private int first;
    private IntList rest;
    public IntList(int first, IntList rest){
        this.first = first;
        this.rest = rest;
    }
    public static IntList list(int... args) {
        IntList head = null;
        IntList tail = null;

        for (int i = 0; i < args.length; i++) {
            if (head == null) {
                head = new IntList(args[i], null);
                tail = head;
            } else {
                tail.rest = new IntList(args[i], null);
                tail = tail.rest;
            }
        }
        return head;
    }
    public void skippify(){
        IntList p = this;
        int n = 1;
        while(p != null){
            IntList next = p.rest; // 不能写成 next = this.rest
            for(int i = 0; i < n; i++){
                if (next != null) {
                    next = next.rest;
                }
                break;
            }
            p.rest = next;
            p = next;
            n ++;
        }
    }
    public static void removeDuplicates(IntList p){
        if(p == null){
            return;
        }
        IntList current = p.rest;
        IntList previous = p;
        while(current.rest != null){
            if(previous.first == current.first){
                previous.rest = previous.rest.rest;
            }else{
                previous = current;
            }
            current = current.rest;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first);

        IntList current = rest;
        while (current != null) {
            sb.append(" -> ");
            sb.append(current.first);
            current = current.rest;
        }
        return sb.toString();
    }

    public static void main(String[] args){
        IntList A = IntList.list(1, 2, 2, 2, 3);
        IntList.removeDuplicates(A);
        System.out.println(A);
    }



}
