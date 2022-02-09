package KD_tree;

import java.util.ArrayList;

public class KD_tree {
    public Node root;
    private final int nodeSize;

    public KD_tree (int nodeSize) {
        this.nodeSize = nodeSize;
        this.root = null;
    }

    public KD_tree (int nodeSize, ArrayList<Double> k) {
        this.nodeSize = nodeSize;
        this.root = new Node(k);
    }

    public void traverseTree () {
        traverseTree(root);
    }

    public void traverseTree (Node startNode) {
        System.out.println(startNode.k_vals);

        if (startNode.left_child != null) {
            for (int i=0; i<startNode.left_child.currentLevel; i++) System.out.print("\t");
            System.out.print("l: ");
            traverseTree(startNode.left_child);
        }

        if (startNode.right_child != null) {
            for (int i=0; i<startNode.right_child.currentLevel; i++) System.out.print("\t");
            System.out.print("r: ");
            traverseTree(startNode.right_child);
        }
    }

    private Node findParentNode (Node node) {
        Node parentNode;

        if (root.left_child == root.right_child)
            parentNode = root;
        else
            parentNode = findParentNode(root, node);

        return parentNode;
    }

    private Node findParentNode (Node startNode, Node node) {
        Node parentNode = startNode;

        if (parentNode.left_child == null && parentNode.right_child == null)
            return parentNode;

        int comparableIndex = parentNode.currentLevel%nodeSize;
        if (parentNode.k_vals.get(comparableIndex) > node.k_vals.get(comparableIndex)) {
            if (parentNode.left_child != null)
                parentNode = findParentNode(parentNode.left_child, node);

        } else {
            if (parentNode.right_child != null)
                parentNode = findParentNode(parentNode.right_child, node);
        }

        return parentNode;
    }

    public void insert (Node node) {
        if (root == null) {
            root = node;
            root.currentLevel = 0;
            return;
        }

        Node parentNode = findParentNode(node);
        node.currentLevel = parentNode.currentLevel+1;

        int comparableIndex = parentNode.currentLevel%nodeSize;
        if (parentNode.k_vals.get(comparableIndex) > node.k_vals.get(comparableIndex))
            parentNode.left_child = node;
        else
            parentNode.right_child = node;
    }

    public Node searchNode (Node node) {
        Node returnNode = null;
        return returnNode;
    }

}



































