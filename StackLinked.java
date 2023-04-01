package ProjectProgredior;

public class StackLinked<T> {
    private LinkedList<T> backend = new LinkedList<T>();
    public void push(T data) {
        backend.addFront(data);
    }
    public T pop() {
        if(backend.getHead() == null) {
            return null;
        }
        T data = backend.getHead().getData();
        backend.removeFront();
        return data;
    }
    public T peek() {
        if(backend.getHead() == null) {
            return null;
        }
        return backend.getHead().getData();
    }
    public boolean isEmpty() {
        return backend.getHead() == null;
    }

    public void prettyPrint() {
        backend.prettyPrint();
    }
}
