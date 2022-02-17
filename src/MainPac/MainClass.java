package MainPac;

import KD_tree.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("jello world in jelly");
//        printInput();
        test();
    }

    public static void test() throws FileNotFoundException {
        int nodeSize = 2;
        KD_tree tree = new KD_tree(nodeSize);
        takeInput(tree);
        tree.traverseTree();

        ArrayList<Double> dbl = new ArrayList<>();
        ArrayList<Double> dbl1 = new ArrayList<>();
        dbl.add(0.0); dbl.add(0.0);
        dbl1.add(100.0); dbl1.add(100.0);
        Node node = tree.searchNode(new Node(dbl));

        if (node != null)
            System.out.println("Single node search result " + node.k_vals);
        else
            System.out.println("node not found");

        System.out.println("Ranged search result between " + dbl + " and " + dbl1);
        ArrayList<Node> nodes = tree.searchNode(tree.root, new Node(dbl), new Node(dbl1));
        for (Node value : nodes) {
            System.out.print(value.k_vals + " ");
        }
    }

    public static void takeInput(KD_tree tree) throws FileNotFoundException {
        File inputFile = new File("inputFile.txt");
        Scanner scn = new Scanner(inputFile);

        while (scn.hasNextLine()) {
            ArrayList<Double> arr = new ArrayList<>();
            String[] ks = scn.nextLine().split(" ");

            for (String s: ks) {
                arr.add(Double.parseDouble(s));
            }

            tree.insert(new Node(arr));
        }
    }

    public static void printInput() throws FileNotFoundException {
        File inputFile = new File("inputFile.txt");
        Scanner scn = new Scanner(inputFile);

        while (scn.hasNextLine()) {
            ArrayList<Double> arr = new ArrayList<>();
            String[] ks = scn.nextLine().split(" ");

            for (String s: ks) {
                arr.add(Double.parseDouble(s));
            }

            System.out.println(arr);
        }
    }
}
