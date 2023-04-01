package ProjectProgredior;

import java.util.ArrayList;
import java.util.Comparator;

public class AVLTree <T extends Comparable<T>>{
    private class Node {
        T data;
        Node left;
        Node right;
        int height;
        int BFactor;
        Node(T data) {
            this.data = data;
            height = 0;
            BFactor = 0;
        }
    }
    private Node root;
    private int newHeight(Node node) {
        int left = node.left == null? -1: node.left.height;
        int right = node.right == null? -1 : node.right.height;
        return Math.max(left, right) + 1;
    }
    private int newBFactor(Node node) {
        int left = node.left == null? -1: node.left.height;
        int right = node.right == null? -1 : node.right.height;
        return left - right;
    }
    public Node rotateClockwise(Node node) {
        Node output = node.left;
        node.left = output.right;
        output.right = node;
        updateVals(output);
        updateVals(output.left);
        return output;
    }
    public Node rotateAntiClockwise(Node node) {
        Node output = node.right;
        node.right = output.left;
        output.left = node;
        updateVals(output);
        updateVals(output.right);
        return output;
    }
    public Node rotateAC(Node node) {
        node.left = rotateAntiClockwise(node.left);
        return rotateClockwise(node);
    }
    public Node rotateCA(Node node) {
        node.right = rotateClockwise(node.right);
        return rotateAntiClockwise(node);
    }
    public Node update(Node node) {
        if(Math.abs(node.BFactor) <= 1){
            return node;
        } else {

            if(node.BFactor > 1) {
                if(node.left.BFactor >= 0){
                    return rotateClockwise(node);
                } else {
                    return rotateAC(node);
                }
            } else {
                if(node.right.BFactor <= 0){
                    System.out.println("Rotating AntiClockwise: " + node.data);
                    return rotateAntiClockwise(node);
                } else {
                    System.out.println("Rotating: " + node.data);
                    return rotateCA(node);
                }
            }
        }
    }
    public void updateVals(Node node) {if (node == null)return;
        node.height = newHeight(node);
        node.BFactor = newBFactor(node);
    }
    private Node add(T data, Node node) {
        if(node == null)
            return new Node(data);
        else if(node.data.compareTo(data) < 0){
            node.right = add(data, node.right);
        } else if (node.data.compareTo(data) > 0) {
            node.left = add(data, node.left);
        }
        updateVals(node);
        if(node.left != null && node.right != null) {
        System.out.println(node.data + ": " + node.left.height + " - " + node.right.BFactor);}
        return update(node);
    }
    public void add(T data) {
        root = add(data, root);
    }
    public boolean search(T data) {
        return search(data, root);
    }
    private boolean search(T data, Node node) {
        if(node == null) return false;
        else if(node.data.compareTo(data) == 0){
            return true;
        } else {
            if(data.compareTo(node.data) > 0){
                return search(data, node.right);
            } else {
                return search(data, node.left);
            }
        }
    }
    public void remove(T data) {
        root = remove(data, root);
    }
    private Node remove(T data, Node node) {
        if(node == null) return null;
        else if(node.data.compareTo(data) == 0){
            Node left = node.left;
            Node right = node.right;
            if(left == null && right == null){
                return null;
            } else if(left == null ^ right == null){
                return left == null ? right: left;
            } else {
                Node dummy = node.left;
                while(dummy.right != null){
                    dummy = dummy.right;
                }
                remove(dummy.data);
                node.data = dummy.data;
                return node;
            }
        } else {
            if(node.data.compareTo(data) > 0){
                node.left = remove(data, node.left);
            } else {
                node.right = remove(data, node.right);
            }
        }
        return node;
    }
    public ArrayList<T> traverse(){
        ArrayList<T> output = new ArrayList<T>();
        traverse(root, output);
        return output;
    }
    private void traverse(Node node, ArrayList<T> output){
        if(node == null){
            return;
        } else {
            traverse(node.left, output);
            output.add(node.data);
            traverse(node.right, output);
        }
    }
    public ArrayList<T> levelTraverse(){
        ArrayList<T> output = new ArrayList<T>();
        levelTraverse(root, output);
        return output;
    }
    public void levelTraverse(Node node, ArrayList<T> output){
        DequeLinkedFIFO<Node> stack = new DequeLinkedFIFO<Node>();
        stack.enqueue(node);
        while(!stack.isEmpty()){
            Node current = stack.dequeue();
            if(current.left != null){
                stack.enqueue(current.left);
            }
            if(current.right != null){
                stack.enqueue(current.right);
            }
            output.add(current.data);
        }
    }
}
