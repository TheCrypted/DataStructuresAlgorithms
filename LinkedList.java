package ProjectProgredior;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable{
    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator{
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(hasNext()){
                T data = current.data;
                current = current.next;
                return data;
            }
            throw new NoSuchElementException();
        }
    }
    public class Node{
        private T data;
        private Node next;
        private Node previous;

        Node(T data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
        Node(T data){
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public Node getHead() {
        return head;
    }

    private Node head;
    private Node tail;
    public void addBack(T data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = tail.next;
        }
    }
    public void addFront(T data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        }
    }
    public void removeBack(){
        Node current = head;
        if(head == null){
            return;
        } else if (head.next == null){
            head = null; tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
    }
    public void removeFront(){
        if(head == null){
            return;
        } else {
            head = head.next;
        }
    }

    public Node getTail() {
        return tail;
    }

    public void prettyPrint() {
        System.out.println("Doubly Linked List: ");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}