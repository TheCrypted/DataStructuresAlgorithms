package ProjectProgredior;

public class DequeLinkedFIFO<T> {
    LinkedList<T> backingList = new LinkedList<T>();
    public void enqueue(T data) {
        backingList.addFront(data);
    }
    public T dequeue() {
        if(backingList.getTail() == null) {
            return null;
        }
        T data = backingList.getTail().getData();
        backingList.removeBack();
        return data;
    }
    public T peek() {
        if(backingList.getTail() == null) {
            return null;
        }
        return backingList.getTail().getData();
    }
    public boolean isEmpty() {
        return backingList.getHead() == null;
    }
    public void prettyPrint() {
        backingList.prettyPrint();
    }
}
