import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.*;

class treeNode {
    String chStr;
    int frequency;
    String code;
    treeNode left;
    treeNode right;
    treeNode next;

    treeNode(String chStr, int frequency, String code, treeNode left, treeNode right, treeNode next) {
        this.chStr = chStr;
        this.frequency = frequency;
        this.code = code;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    public void printNode(treeNode Node, FileWriter outFile) throws IOException {
        if (Node.next != null && Node.right != null && Node.left != null) {
            outFile.write("chStr: " + Node.chStr + ", Frequency: " + Node.frequency + ", Code: " + Node.code + ", NextChStr: " + Node.next.chStr + ", LeftChStr " + Node.left.chStr + ", RightChStr " + Node.right.chStr + " \n");
        } else if (Node.next == null) {
            outFile.write("chStr: " + Node.chStr + ", Frequency: " + Node.frequency + ", Code: " + Node.code + ", NextChStr: NULL " + ", LeftChStr " + Node.left.chStr + ", RightChStr " + Node.right.chStr + " \n");
        } else if (Node.right == null && Node.left == null) {
            outFile.write("chStr: " + Node.chStr + ", Frequency: " + Node.frequency + ", Code: " + Node.code + ", NextChStr: NULL " + ", LeftChStr: NULL " + ", RightChStr: NULL " + " \n");
        } else {
            outFile.write("chStr: " + Node.chStr + ", Frequency: " + Node.frequency + ", Code: " + Node.code + ", NextChStr: NULL " + ", LeftChStr: NULL " + ", RightChStr: NULL " + " \n");
        }
    }

}

class LinkedList {
    treeNode listHead;
    LinkedList() {
        listHead = new treeNode("dummy", 0, "", null, null, null);
    }
    public void insertNewNode(treeNode listHead, treeNode newNode) {
        treeNode Spot = findSpot(listHead, newNode);
        newNode.next = Spot.next;
        Spot.next = newNode;
    }
    public treeNode findSpot(treeNode listHead, treeNode newNode) {
        treeNode Spot = listHead;
        while (Spot.next != null && Spot.next.frequency < newNode.frequency) {
            Spot = Spot.next;
        }
        return Spot;
    }

    public void printList(FileWriter outFile) throws IOException {
        treeNode temp = listHead;
        while (temp.next.next != null) {
            temp.printNode(temp, outFile);
            temp = temp.next;
        }
        outFile.write("\n");
    }
}

class BinaryTree {
    treeNode Root;

    BinaryTree() {
        Root = new treeNode("dummy", 0, "", null, null, null);
    }

    static boolean isLeaf(treeNode Node) {
        if (Node.left == null && Node.right == null) {
            return true;
        }
        return false;
    }

    public void preOrderTraversal(treeNode T, FileWriter outFile) throws IOException {
        if (isLeaf(T)) {
            T.printNode(T, outFile);
        } else {
            T.printNode(T, outFile);
            preOrderTraversal(T.left, outFile);
            preOrderTraversal(T.right, outFile);
        }
    }
    public void inOrderTraversal(treeNode T, FileWriter outFile) throws IOException {
        if (isLeaf(T)) {
            T.printNode(T, outFile);
        } else {
            inOrderTraversal(T.left, outFile);
            T.printNode(T, outFile);
            inOrderTraversal(T.left, outFile);
        }
    }
    public void postOrderTraversal(treeNode T, FileWriter outFile) throws IOException {
        if (isLeaf(T)) {
            T.printNode(T, outFile);
        } else {
            postOrderTraversal(T.left, outFile);
            postOrderTraversal(T.right, outFile);
            T.printNode(T, outFile);
        }
    }

}

class HuffmanCoding {
    int[] charCountAry = new int[256];
    String[] charCode = new String[256];

    void computeCharCounts(FileReader inFile) throws IOException {
        BufferedReader inFileBuffer = new BufferedReader(inFile);
        int index = 0;
        while ((index = inFileBuffer.read()) != -1) {
            if (index != 8217) {
                charCountAry[index]++;
            }
        }


    }

    public void printCountAry(FileWriter outFile) throws IOException {
        for (int i = 0; i < charCountAry.length; i++) {
            if (charCountAry[i] != 0) {
                outFile.write("char[ " + (char) i + " ] = " + charCountAry[i] + " " + "\n");
            }
        }
    }


    public void constructHuffmanLList(LinkedList LL, FileWriter outFile) throws IOException {
        int index = 0;
        //outFile.write("\n");
        while (index < 256) {
            if (charCountAry[index] > 0) {
                String chStr = String.valueOf((char) index);
                int frequency = charCountAry[index];
                treeNode newNode = new treeNode(chStr, frequency, "", null, null, null);
                LL.insertNewNode(LL.listHead, newNode);
                LL.printList(outFile);
            }
            index++;
        }
        outFile.write("\n");

    }

    public void constructHuffmanBinTree(BinaryTree BT, LinkedList LL, FileWriter outFile) throws IOException {
        treeNode listHead = LL.listHead;
        //outFile.write("BEGINBIN \n");
        while (listHead.next.next != null) {

            int frequency = listHead.next.frequency + listHead.next.next.frequency;
            String chStr = listHead.next.chStr + listHead.next.next.chStr;
            treeNode newNode = new treeNode(chStr, frequency, "", listHead.next, listHead.next.next, null);
            //outFile.write(newNode.left.chStr + "MIDLL "+newNode.right.chStr +"\n");
            LL.insertNewNode(listHead, newNode);
            listHead.next = listHead.next.next.next;
            LL.printList(outFile);
            //outFile.write("BREAKLL \n");
        }
        BT.Root = listHead.next;

    }


    void constructCharCode(BinaryTree BT, treeNode node, String code) {
        if (BT.isLeaf(node)) {
            node.code = code;
            int index = Character.valueOf(node.chStr.charAt(0));
            charCode[index] = code;
        } else {
            constructCharCode(BT, node.left, code + "0"); //string concatenation
            constructCharCode(BT, node.right, code + "1"); //string concatenation
        }
    }


    void userInterface(BinaryTree BT, FileWriter outFile) throws IOException {
        String nameOrg;
        String nameCompress;
        String nameDecompress;
        char yesNo;

        Scanner myScanner = new Scanner(System.in);

        while (true) { //easier to loop
            System.out.println("do you want to encode a file: Y/N (capital *Letters only)");
            yesNo = myScanner.nextLine().charAt(0);
            if (yesNo == 'N') {
                myScanner.close(); //close all files
                outFile.close();
                System.out.println("Exiting The Program");
                System.exit(0);
            } else if (yesNo == 'Y') {
                System.out.println("Enter name of the file without the .txt: ");
                nameOrg = myScanner.nextLine();
                nameCompress = nameOrg + "_Compressed.txt";
                nameDecompress = nameOrg + "_DeCompressed.txt";
                nameOrg = nameOrg + ".txt";

                FileReader orgFile = new FileReader(nameOrg);
                FileWriter compFile = new FileWriter(nameCompress);
                FileWriter deCompFile = new FileWriter(nameDecompress);
                encode(orgFile, compFile, outFile);
                compFile.close();
                FileReader openCompFile = new FileReader(nameCompress);
                //System.out.println("working" +orgFile +deCompFile + " WORKS "+compFile); //debugger
                decode(openCompFile, deCompFile, BT);
                orgFile.close();
                openCompFile.close();
                deCompFile.close();
            }
            else {
                System.out.println("Please Enter Y for Yes or N For No");
            }
        }
    }

    void encode(FileReader File1, FileWriter File2, FileWriter File3) throws IOException {
        int charIn;
        BufferedReader orgFile = new BufferedReader(File1);
        while ((charIn = orgFile.read()) != -1) {
            int index = charIn;
            String code = charCode[index];
            File3.write("Index:" + index + " Code:" + code + "\n");
            File2.write(code);
            //System.out.println("working2"); //debugger
        }
        orgFile.close();
    }

    void decode(FileReader File1, FileWriter File2, BinaryTree BT) throws IOException {
        BufferedReader compFile = new BufferedReader(File1);
        treeNode Spot = BT.Root;
        int character;
        while ((character = compFile.read()) != -1) {
            if (BT.isLeaf(Spot)) {
                File2.write(Spot.chStr);
                Spot = BT.Root;
            }
            char oneBit = (char) character;
            if (oneBit == '0') {
                Spot = Spot.left;
            } else if (oneBit == '1') {
                Spot = Spot.right;
            } else {
                System.out.println("Error! The compress file contains invalid character!");
                System.out.print("Exiting The Program");
                System.exit(0);
            }
        }
        File2.write(Spot.chStr);
        if (!BT.isLeaf(Spot)) {
            System.out.println("Error: The compress file is corrupted!");
        }
        compFile.close(); // close file needed
    }
}


public class Shaxzod_Karimov_Main {
    public static void main(String[] args) throws IOException {
        FileReader inFile = new FileReader(args[0]);
        FileWriter outFile1 = new FileWriter(args[1]);
        HuffmanCoding huffmanCoding = new HuffmanCoding();
        LinkedList huffmanLinkedList = new LinkedList();
        BinaryTree huffmanBinaryTree = new BinaryTree();
        huffmanCoding.computeCharCounts(inFile);
        huffmanCoding.printCountAry(outFile1);
        huffmanCoding.constructHuffmanLList(huffmanLinkedList, outFile1);
        huffmanCoding.constructHuffmanBinTree(huffmanBinaryTree, huffmanLinkedList, outFile1);
        huffmanLinkedList.printList(outFile1);
        outFile1.write("\nPREORDER: \n");
        huffmanBinaryTree.preOrderTraversal(huffmanBinaryTree.Root, outFile1);
        outFile1.write("\nINORDER: \n");
        huffmanBinaryTree.inOrderTraversal(huffmanBinaryTree.Root, outFile1);
        outFile1.write("\nPOSTORDER: \n");
        huffmanBinaryTree.postOrderTraversal(huffmanBinaryTree.Root, outFile1);
        huffmanCoding.constructCharCode(huffmanBinaryTree, huffmanBinaryTree.Root, "");
        huffmanCoding.userInterface(huffmanBinaryTree, outFile1);
        inFile.close();
        outFile1.close();
    }
}