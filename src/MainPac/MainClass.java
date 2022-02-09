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
        int nodeSize = 3;
        KD_tree tree = new KD_tree(nodeSize);
        takeInput(tree);
        tree.traverseTree();

        ArrayList<Double> dbl = new ArrayList<Double>();
        dbl.add(6.0); dbl.add(6.0); dbl.add(1.0);
        Node node = tree.searchNode(new Node(dbl));
        System.out.println(node.k_vals);
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
