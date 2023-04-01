package ProjectProgredior;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
    private class NodeBinary {
        private T data;
        private NodeBinary left;
        private NodeBinary right;
        NodeBinary(T data, NodeBinary left, NodeBinary right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        NodeBinary(T data) {
            this.data = data;
        }
        public T getData() {
            return data;
        }
    }
    private NodeBinary root;
    public NodeBinary add(T data, NodeBinary root) {
        if(root == null){
            return new NodeBinary(data);
        } else {
            if(data.compareTo(root.data) < 0){
                root.left = add(data, root.left);
            } else if(data.compareTo(root.data) > 0){
                root.right = add(data, root.right);
            }
        }
        return root;
    }
    public void add(T data){
        root = add(data, root);
    }
    public ArrayList<T> traverseIn(){
        ArrayList<T> BackingArray = new ArrayList<T>();
        traverseIn(root, BackingArray);
        return BackingArray;
    }
    public ArrayList<T> traversePre(){
        ArrayList<T> BackingArray = new ArrayList<T>();
        traversePre(root, BackingArray);
        return BackingArray;
    }
    public ArrayList<T> traversePost(){
        ArrayList<T> BackingArray = new ArrayList<T>();
        traversePost(root, BackingArray);
        return BackingArray;
    }
    public void traverseIn(NodeBinary root, ArrayList<T> BackingArray){
        if(root == null){
            return;
        } else {
            traverseIn(root.left, BackingArray);
            BackingArray.add(root.data);
            traverseIn(root.right, BackingArray);
        }
    }
    public void traversePre(NodeBinary root, ArrayList<T> BackingArray){
        if(root == null){
            return;
        } else {
            BackingArray.add(root.data);
            traversePre(root.left,  BackingArray);
            traversePre(root.right,  BackingArray);
        }
    }
    public void traversePost(NodeBinary root, ArrayList<T> BackingArray){
        if(root == null){
            return;
        } else {
            traversePost(root.left, BackingArray);
            traversePost(root.right, BackingArray);
            BackingArray.add(root.data);
        }
    }
    public ArrayList<T> traverseLevel(){
        ArrayList<T> BackingArray = new ArrayList<T>();
        DequeLinkedFIFO<NodeBinary> stack = new DequeLinkedFIFO<NodeBinary>();
        if(root == null){
            return null;
        } else {
            stack.enqueue(root);
            while(!stack.isEmpty()){
                NodeBinary node = stack.dequeue();
                BackingArray.add(node.data);
                if(node.right != null) {
                    stack.enqueue(node.right);
                } if (node.left != null) {
                    stack.enqueue(node.left);
                }
            }
        }
        return BackingArray;
    }
    public ArrayList<NodeBinary> traverseLevelNode(){
        ArrayList<NodeBinary> BackingArray = new ArrayList<NodeBinary>();
        DequeLinkedFIFO<NodeBinary> stack = new DequeLinkedFIFO<NodeBinary>();
        if(root == null){
            return null;
        } else {
            stack.enqueue(root);
            while(!stack.isEmpty()){
                NodeBinary node = stack.dequeue();
                BackingArray.add(node);
                if(node.right != null) {
                    stack.enqueue(node.left);
                } if (node.left != null) {
                    stack.enqueue(node.right);
                }
            }
        }
        return BackingArray;
    }
    public NodeBinary search(T data){
        return searchHelp(root, data);
    }
    public NodeBinary searchHelp(NodeBinary root, T data){
        if(root == null){
            return null;
        } else if(root.data.compareTo(data) == 0){
            return root;
        } else {
            if(data.compareTo(root.data) > 0){
                return searchHelp(root.right, data);
            } else {
                return searchHelp(root.left, data);
            }
        }
    }
    public void remove(T data){
        root = removeRec(root, data);
    }

    public NodeBinary removeRec(NodeBinary node, T data){
        if(node == null){
            return null;
        } else if(node.data.compareTo(data) > 0){
            node.left = removeRec(node.left, data);
        } else if(node.data.compareTo(data) < 0){
            node.right = removeRec(node.right, data);
        } else {
            if(node.left == null && node.right == null){
                return null;
            } else if(node.left != null && node.right != null){
                NodeBinary minNode = findMinNode(node.right);
                node.data = minNode.data;
                node.right = removeRec(node.right, node.data);
            } else {
                return node.left != null ? node.left : node.right;
            }
        }
        return node;
    }
    public NodeBinary findMinNode(NodeBinary node) {
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    public void prettyPrint(){
        prettyPrintParam(root);
    }
    public void prettyPrintParam(NodeBinary root){
        int lvl = 1;
        ArrayList<NodeBinary> arr = traverseLevelNode();
        int levels = (int)Math.log(arr.size() + 1);
        for(int i = 0; i < arr.size(); i++){

            if(i + 1 > (Math.pow(2, lvl))-1){
                System.out.print("\n");
                lvl++;
                levels--;
            }
            if(i + 2 > (Math.pow(2, lvl))-1){
                for(int j = 0; j < levels; j++) {
                    System.out.print("  ");
                }
//                lvl++;
            }
            prettyPrintHelperA(arr.get(i));
        }
    }
    public void prettyPrintHelperA(NodeBinary root){
        NodeBinary current = root;
        while(current !=null){
            System.out.print("    ");
            if(current.right == null){
                current = current.left;
            } else {
                current = current.right;
            }
        }
        System.out.printf("%2d",root.data);
    }
}
