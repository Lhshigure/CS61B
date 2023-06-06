import java.util.Stack;

public class SortedStack {
    private Stack<Integer> mainStack;
    private Stack<Integer> tempStack;

    public SortedStack(){
        mainStack = new Stack<>();
        tempStack = new Stack<>();
    }

    public void push(int t){
        while (!mainStack.isEmpty() && t > mainStack.peek()){
            tempStack.push(mainStack.pop());
        }
        mainStack.push(t);
        while(!tempStack.isEmpty()){
            mainStack.push(tempStack.pop());
        }
    }
    public int pop(){
        return mainStack.pop();
    }
    public boolean isEmpty() {
        return mainStack.isEmpty();
    }
    public static void main(String[] args){
        SortedStack sortedStack = new SortedStack();
        sortedStack.push(10);
        sortedStack.push(4);
        sortedStack.push(8);
        sortedStack.push(2);
        sortedStack.push(14);
        sortedStack.push(3);

        while (!sortedStack.isEmpty()) {
            System.out.print(sortedStack.pop() + " ");
        }
    }
}
