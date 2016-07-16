import java.util.*;

public class Upvotes {
	public static void main (String [] args) {
		int[] arr = {1, 2, 3, 1, 1};
		int k = 3;

		calculate2(arr, k);	
	}

	public static void calculate2(int[] arr, int k) {
		ArrayList<Node> nonDecTrees;
		ArrayList<Node> nonIncTrees;
		int windows = arr.length - k + 1;
		int currentMin;
		int currentMax;

		for (int x = 0; x < windows; x++) {
			nonDecTrees = new ArrayList<Node>();
			nonIncTrees = new ArrayList<Node>();
			currentMin = 2147483647;
			currentMax = -1;


			System.out.println("**Window: " + x);

			for(int y = 0; y < k; y++) {
				if (arr[x + y] <= currentMin) {
					currentMin = arr[x + y];
					Node nonDecRoot = new Node(null, currentMin);

					for (int z = x + y + 1; z < x + k; z++) 
						nonDecRecurse(nonDecRoot, arr[z]);

					if (nonDecRoot.getChildren().size() > 0)
						nonDecTrees.add(nonDecRoot);
				}

				if (arr[x + y] >= currentMax) {
					currentMax = arr[x + y];
					Node nonIncRoot = new Node(null, currentMax);

					for (int z = x + y + 1; z < x + k; z++) 
						nonIncRecurse(nonIncRoot, arr[z]);

					if (nonIncRoot.getChildren().size() > 0)
						nonIncTrees.add(nonIncRoot);
				}
			}


			// if (arr[x] < currentMin) {
			// 	currentMin = arr[x];
			// 	Node nonDecRoot = new Node(null, arr[x]);

			// 	for (int y = 1; y < k; y++) 
			// 		nonDecRecurse(nonDecRoot, arr[x + y]);

			// 	nonDecTrees.add(nonDecRoot);
			// } 

			// if (arr[x] > currentMax) {
			// 	currentMax = arr[x];
			// 	Node nonIncRoot = new Node(null, arr[x]);

			// 	for (int y = 1; y < k; y++) 
			// 		nonIncRecurse(nonIncRoot, arr[x + y]);

			// 	nonIncTrees.add(nonIncRoot);
			// }


			System.out.println("NonDecTree");
			output(nonDecTrees);
			System.out.println("NonIncTree");
			output(nonIncTrees);
			System.out.println();

		}
		
	}

	public static void nonDecRecurse (Node n, int val) {
		int childrenCnt = n.getChildren().size();
		if (childrenCnt != 0) {
			for (int x = 0; x < childrenCnt; x++)
				nonDecRecurse(n.getChildren().get(x), val);
			
		}

		if (n.getVal() <= val) {
			Node newNode = new Node(n, val);
			n.addChild(newNode);
		}
	}

	public static void nonIncRecurse (Node n, int val) {
		int childrenCnt = n.getChildren().size();
		if (childrenCnt != 0) {
			for (Node x : n.getChildren())
				nonIncRecurse(x, val);
			
		}

		if (n.getVal() >= val) {
			Node newNode = new Node(n, val);
			n.addChild(newNode);
		}
	}

	public static void output(ArrayList<Node> in) {
		for (int x = 0; x < in.size(); x++) {
			System.out.println("Tree: " + x);
			printTree(in.get(x));
			System.out.println();
		}

	}

	public static void printTree(Node n) {
		System.out.print("Node { ");
		if (n.getParent() == null)
			System.out.println("Root");
		else
			System.out.println("Parent: " + n.getParent().getVal());

		System.out.println("Val: " + n.getVal() + " }\n");

		for(Node x : n.getChildren())
			printTree(x);
	}

}

class Node {

	public Node parent;
	public ArrayList<Node> children;
	public int val;

	public Node(Node p, int val) {
		parent = p;
		this.val = val;
		children = new ArrayList<Node>();
	}

	public int getVal() {
		return val;
	}

	public Node getParent() {
		return parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void addChild(Node c) {
		children.add(c);
	}

	public void changeParent(Node p) {
		parent = p;
	}

}

