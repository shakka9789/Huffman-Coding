# Huffman Coding Implementation in Java

This project implements Huffman Coding using a linked list and binary tree structure to compress and decompress files. The implementation includes the following classes:

1. **treeNode**: Represents a node in the binary tree.
2. **LinkedList**: Manages a linked list of `treeNode` objects.
3. **BinaryTree**: Manages the binary tree used in Huffman coding.
4. **HuffmanCoding**: Contains methods to compute character frequencies, construct the Huffman tree, and encode/decode files.
5. **Shaxzod_Karimov_Main**: The main class that drives the program.

## treeNode Class

The `treeNode` class represents a node in the binary tree used for Huffman coding.

### Attributes
- `String chStr`: The character string represented by the node.
- `int frequency`: The frequency of the character string.
- `String code`: The Huffman code assigned to the character string.
- `treeNode left, right, next`: Pointers to the left and right children and the next node in the linked list.

### Constructor
- `treeNode(String chStr, int frequency, String code, treeNode left, treeNode right, treeNode next)`: Initializes the node with the given values.

### Method
- `void printNode(treeNode Node, FileWriter outFile)`: Prints the node's details to the specified file.

## LinkedList Class

The `LinkedList` class manages a linked list of `treeNode` objects.

### Attributes
- `treeNode listHead`: The head of the linked list, initialized with a dummy node.

### Methods
- `void insertNewNode(treeNode listHead, treeNode newNode)`: Inserts a new node in the list.
- `treeNode findSpot(treeNode listHead, treeNode newNode)`: Finds the appropriate spot for a new node based on frequency.
- `void printList(FileWriter outFile)`: Prints the linked list to the specified file.

## BinaryTree Class

The `BinaryTree` class manages the binary tree used in Huffman coding.

### Attributes
- `treeNode Root`: The root of the binary tree, initialized with a dummy node.

### Methods
- `static boolean isLeaf(treeNode Node)`: Checks if a node is a leaf node.
- `void preOrderTraversal(treeNode T, FileWriter outFile)`: Preorder traversal of the tree.
- `void inOrderTraversal(treeNode T, FileWriter outFile)`: Inorder traversal of the tree.
- `void postOrderTraversal(treeNode T, FileWriter outFile)`: Postorder traversal of the tree.

## HuffmanCoding Class

The `HuffmanCoding` class handles the Huffman coding process.

### Attributes
- `int[] charCountAry`: An array to count the frequency of characters.
- `String[] charCode`: An array to store Huffman codes for characters.

### Methods
- `void computeCharCounts(FileReader inFile)`: Computes the frequency of each character in the input file.
- `void printCountAry(FileWriter outFile)`: Prints the character frequencies.
- `void constructHuffmanLList(LinkedList LL, FileWriter outFile)`: Constructs a linked list of tree nodes based on character frequencies.
- `void constructHuffmanBinTree(BinaryTree BT, LinkedList LL, FileWriter outFile)`: Constructs the Huffman binary tree.
- `void constructCharCode(BinaryTree BT, treeNode node, String code)`: Constructs Huffman codes for each character.
- `void userInterface(BinaryTree BT, FileWriter outFile)`: Provides a user interface for encoding and decoding files.
- `void encode(FileReader File1, FileWriter File2, FileWriter File3)`: Encodes the input file using Huffman codes.
- `void decode(FileReader File1, FileWriter File2, BinaryTree BT)`: Decodes the compressed file back to the original.

## Shaxzod_Karimov_Main Class

The `Shaxzod_Karimov_Main` class contains the `main` method, which serves as the entry point for the program.

### main Method

The `main` method:
1. Reads the input and output file names from the command line arguments.
2. Initializes the `HuffmanCoding`, `LinkedList`, and `BinaryTree` objects.
3. Computes character counts and constructs the Huffman linked list and binary tree.
4. Prints the Huffman codes and provides an interface for encoding and decoding files.
