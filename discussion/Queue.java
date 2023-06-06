import java.util.Stack;

//first-in, first-out, by two stack;
public class Queue<T>{
    private Stack<T> inbox;
    private Stack<T> outbox;

    public Queue(){
        inbox = new Stack<>();
        outbox = new Stack<>();
    }

    public void push(T item){
        inbox.push(item);
    }
    public T poll(){
        if(!outbox.isEmpty()) {
            while (outbox != null) {
                outbox.push(inbox.pop());
            }
        }
        return outbox.pop();
    }
}
