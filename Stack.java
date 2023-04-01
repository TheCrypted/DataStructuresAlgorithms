package ProjectProgredior;
import java.util.Arrays;
import java.util.LinkedList;

public class Stack<T> {
    private T[] stackArray;
    private int top;
    Stack(int size){
        stackArray = (T[]) new Object[size];
        top = -1;
    }
    public boolean isFull(){
        return top == stackArray.length;
    }

    public void push(T data) {
        if(isFull()) {
            System.out.println("Stack overflow");
        } else {
            top++;
            stackArray[top] = data;
        }
    }
    public T pop() {
        T data;
        if(top == -1){
            return null;
        } else {
            data = stackArray[top];
            top--;
            return data;
        }
    }
    public T peek() {
        if(top == -1){
            return null;
        } else {
            return stackArray[top];
        }
    }
    public void prettyPrint(){
        System.out.println("Stack: " + Arrays.toString(stackArray));
    }
}
