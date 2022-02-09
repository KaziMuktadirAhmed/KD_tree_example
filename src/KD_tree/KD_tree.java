package KD_tree;

import java.util.ArrayList;
import java.util.Objects;

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
        if (root == null) {
            return;
        }
        traverseTree(root);
    }

    public void traverseTree (Node startNode) {
        System.out.println(startNode.k_vals + " level:" + startNode.currentLevel);

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
        Node returnNode;

        if (root == null)
            returnNode = null;
        else
            returnNode = searchNode(root, node);

        return returnNode;
    }

    public Node searchNode (Node startNode, Node node) {
        Node returnNode = null;
        boolean isMatch = true;

        for (int i=0; i<nodeSize; i++) {
            if (!Objects.equals(startNode.k_vals.get(i), node.k_vals.get(i))) {
               isMatch = false;
               break;
            }
        }

        if (isMatch) {
            returnNode = startNode;

        } else {
            int comparableIndex = startNode.currentLevel%nodeSize;
            if (startNode.k_vals.get(comparableIndex) > node.k_vals.get(comparableIndex)) {
                if (startNode.left_child != null)
                    returnNode = searchNode(startNode.left_child, node);

            } else {
                if (startNode.right_child != null)
                    returnNode = searchNode(startNode.right_child, node);
            }
        }

        return returnNode;
    }

    public ArrayList<Node> searchNode (Node startNode, Node lowerBound, Node upperBound) {
        ArrayList<Node> nodes = new ArrayList<>();

        if (root == null) return nodes;

        correctBounds(lowerBound, upperBound);
        searchNode(startNode, lowerBound, upperBound, nodes);

        return nodes;
    }

    private void searchNode (Node startNode, Node lowerBound, Node upperBound, ArrayList<Node> nodes) {
        int comparableIndex = startNode.currentLevel%nodeSize;
        if (startNode.k_vals.get(comparableIndex) > lowerBound.k_vals.get(comparableIndex)) {
            if (startNode.left_child != null)
                searchNode(startNode.left_child, lowerBound, upperBound, nodes);
        }

        boolean isMatch = true;
        for (int i=0; i<nodeSize; i++) {
            if (startNode.k_vals.get(i) < lowerBound.k_vals.get(i) || startNode.k_vals.get(i) > upperBound.k_vals.get(i)) {
                isMatch = false;
                break;
            }
        }
        if (isMatch) nodes.add(startNode);

        if (startNode.k_vals.get(comparableIndex) <= upperBound.k_vals.get(comparableIndex)){
            if (startNode.right_child != null)
                searchNode(startNode.right_child, lowerBound, upperBound, nodes);
        }
    }

    private void correctBounds (Node lowerBound, Node upperBound) {
        for (int i=0; i<nodeSize; i++) {
            if (lowerBound.k_vals.get(i) > upperBound.k_vals.get(i)) {
                double temp = lowerBound.k_vals.get(i);
                lowerBound.k_vals.set(i, upperBound.k_vals.get(i));
                upperBound.k_vals.set(i, temp);
            }
        }
    }


}



































