package KD_tree;

import java.util.ArrayList;

public class Node {
    ArrayList<Double> k_vals;
    public int currentLevel;

    public Node right_child;
    public Node left_child;

    public Node (ArrayList<Double> k) {
        this.k_vals = k;
        this.left_child = null;
        this.right_child = null;
    }

    public Node (ArrayList<Double> k, Node right, Node left) {
        this.k_vals = k;
        this.right_child = right;
        this.left_child = left;
    }
}
