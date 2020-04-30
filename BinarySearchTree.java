/**
 * This class creates a binary search tree and supports methods to insert, delete, and find items as well as
 * methods to traverse the in Preorder, Postorder, and Inorder.
 *
 * @author James Dell'Alba
 * @version 4/3/2020
 */
public class BinarySearchTree {
	Node root = null;

	/**
	 * Constructor for the BinarySearchTree class.
	 * Creates a binary search tree with no nodes.
	 */
	public BinarySearchTree() {
	}

	/**
	 * Constructor for the BinarySearchTree class, creates a tree with one node as the root
	 * @param root the node which will become the root of the tree
	 */
	private BinarySearchTree(Node root) {
		this.root = root;
	}

	/**
	 * This class holds data for a node object and supports methods to print the contents of the node.
	 */
	private class Node {
		String name;
		double gdpPerCapita;
		Node leftChild;
		Node rightChild;

		/**
		 * Constructor which creates a new node
		 * @param name the name of the country the node is created from
		 * @param gdpPerCapita the gdp per capita of the country
		 */
		public Node(String name, double gdpPerCapita) {
			this.name = name;
			this.gdpPerCapita = gdpPerCapita;
		}

		/**
		 * This method prints the name and gdp per capita of the country inside the node that calls the method.
		 */
		public void printNode() {
			System.out.printf("%-33s%,-20.2f\n", name, gdpPerCapita);
		}
	}

	/**
	 * Creates a node and inserts it into the binary search tree.
	 * If the tree is empty, the node is inserted and becomes the root of the tree.
	 * @param name the name of the node to be created
	 * @param gdpPerCapita the gdp per capita of the node to be created
	 */
	public void insert(String name, double gdpPerCapita) {
		if (this.isEmpty()) {
			root = new Node(name, gdpPerCapita);
		} else {
			Node current = root;
			Node previous = current;
			while (current != null) {
				if (current.name.compareTo(name) > 0) {
					//move to the left
					previous = current;
					current = current.leftChild;
				} else {
					//move to the right
					previous = current;
					current = current.rightChild;
				}
			}
			if (previous.name.compareTo(name) > 0) {
				//insert to the left
				previous.leftChild = new Node(name, gdpPerCapita);
			} else {
				//insert to the right
				previous.rightChild = new Node(name, gdpPerCapita);
			}
		}
	}

	/**
	 * Given a string, this method searches for a country with a name that matches the string.
	 * Once the country is found or a leaf node it reached, it prints the number of nodes it visited while searching.
	 * @param name The name of the country to find
	 * @return the gdp per capita of the country if found, -1 if not found.
	 */
	public double find(String name) {
		Node current = this.root;
		int nodesChecked = 0;
		while (current != null) {
			if (current.name.compareTo(name) > 0) {
				nodesChecked++;
				current = current.leftChild;
			} else if (current.name.compareTo(name) < 0) {
				nodesChecked++;
				current = current.rightChild;
			} else if (current.name.compareTo(name) == 0) {
				nodesChecked++;
				System.out.println("Nodes Checked: " + nodesChecked);
				return current.gdpPerCapita;
			}
		}
		System.out.println(" Nodes Checked: " + nodesChecked);
		System.out.println("Country not found");
		return -1;
	}

	/**
	 * Searching for a node with the same name as the input string, and if found, deletes that node by adjusting
	 * the appropriate pointers.
	 * @param name the name of the node to be deleted
	 */
	public void delete(String name) {
		Node current = this.root;
		Node previous = current;
		Node deletedNode = null;
		//search for the node
		while (current != null) {
			if (current.name.compareTo(name) > 0) {
				previous = current;
				current = current.leftChild;
			} else if (current.name.compareTo(name) < 0) {
				previous = current;
				current = current.rightChild;
			} else if (current.name.compareTo(name) == 0) {
				//found it
				deletedNode = current;
				break;
			}
		}
		if (current == null) {
			System.out.println("Country not found");
			return;
		}
		if ((deletedNode.leftChild == null) && (deletedNode.rightChild == null)) { //if leaf node
			if (deletedNode == previous.leftChild) {
				previous.leftChild = null;
			} else if (deletedNode == previous.rightChild) {
				previous.rightChild = null;
			}
		} else if (deletedNode == this.root) { //if root node
			Node successor = deletedNode.rightChild;
			Node nodeAboveSuccessor = null;
			while (successor.leftChild != null) {
				//find successor
				nodeAboveSuccessor = successor;
				successor = successor.leftChild;
			}
			if (nodeAboveSuccessor == null) { //as in the case when deleting Chile
				successor.leftChild = this.root.leftChild;
				this.root = successor;
				return;
			}

			try {
				nodeAboveSuccessor.leftChild = successor.rightChild;
			} catch (NullPointerException ex) {
				//no action necessary
			}

			successor.rightChild = deletedNode.rightChild;
			successor.leftChild = deletedNode.leftChild;

			this.root = successor;
		} else { //middle node (not leaf or root)
			if (deletedNode.rightChild == null) { //there is no successor
				if (deletedNode == previous.leftChild) {
					previous.leftChild = deletedNode.leftChild;
				} else if (deletedNode == previous.rightChild) {
					previous.rightChild = deletedNode.leftChild;
				}
				return;
			}
			Node successor = deletedNode.rightChild;
			Node nodeAboveSuccessor = successor;
			while (successor.leftChild != null) {
				nodeAboveSuccessor = successor;
				successor = successor.leftChild;
			}
			try {
				nodeAboveSuccessor.leftChild = successor.rightChild;
			} catch (NullPointerException ex) {
				//no action necessary
			}

			if (deletedNode == previous.leftChild) {
				previous.leftChild = successor;
			} else if (deletedNode == previous.rightChild) {
				previous.rightChild = successor;
			}

			if (successor == deletedNode.rightChild) {
				successor.leftChild = deletedNode.leftChild;
			} else {
				successor.rightChild = deletedNode.rightChild;
				successor.leftChild = deletedNode.leftChild;
			}
		}
	}

	/**
	 * Traverses the tree in order (LNR), printing each node by calling the printNode method as it visits it.
	 */
	public void printInOrder() {
		Node current = this.root;
		BinarySearchTree leftSubTree = new BinarySearchTree(current.leftChild);
		BinarySearchTree rightSubTree = new BinarySearchTree(current.rightChild);
		if ((current.leftChild == null) && (current.rightChild == null)) {
			current.printNode();
		} else {
			try {
				leftSubTree.printInOrder();
			} catch (NullPointerException ex) {
				//no action necessary
			}
			current.printNode();
			try {
				rightSubTree.printInOrder();
			} catch (NullPointerException ex) {
				//no action necessary
			}
		}
	}

	/**
	 * Traverses the tree Preorder (NLR), and prints each node by calling the printNode method as it visits it.
	 */
	public void printPreOrder() {
		Node current = this.root;
		BinarySearchTree leftSubTree = new BinarySearchTree(current.leftChild);
		BinarySearchTree rightSubTree = new BinarySearchTree(current.rightChild);
		current.printNode();
		if ((current.leftChild == null) && (current.rightChild == null)) {
			return;
		} else {
			try {
				leftSubTree.printPreOrder();
			} catch (NullPointerException ex) {
				//no action necessary
			}
			try {
				rightSubTree.printPreOrder();
			} catch (NullPointerException ex) {
				//no action necessary
			}
		}
	}

	/**
	 * Traverses the tree PostOrder (LRN), and prints each node by calling the printNode method as it visits it.
	 */
	public void printPostOrder() {
		Node current = this.root;
		BinarySearchTree leftSubTree = new BinarySearchTree(current.leftChild);
		BinarySearchTree rightSubTree = new BinarySearchTree(current.rightChild);
		if ((current.leftChild == null) && (current.rightChild == null)) {
			current.printNode();
		} else {
			try {
				leftSubTree.printPostOrder();
			} catch (NullPointerException ex) {
				//no action necessary
			}
			try {
				rightSubTree.printPostOrder();
			} catch (NullPointerException ex) {
				//no action necessary
			}
			current.printNode();
		}
	}

	/**
	 * Traverses the tree (inorder), and prints 5 times starting from the lowest node.
	 */
	public void printBottomFive() {
		BinarySearchTree gdpTree = new BinarySearchTree();

		/**
		 * This class is necessary to support recursively traversing the tree
		 */
		class TreeMaker {
			/**
			 * This method traverses a tree in order
			 * @param treeToTraverse the tree which will be traversed
			 */
			void traverseTree(BinarySearchTree treeToTraverse) {
				Node current = treeToTraverse.root;
				BinarySearchTree leftSubTree = new BinarySearchTree(current.leftChild);
				BinarySearchTree rightSubTree = new BinarySearchTree(current.rightChild);
				if ((current.leftChild == null) && (current.rightChild == null)) {
					gdpTree.insertByGDPperCapita(current.name, current.gdpPerCapita);
				} else {
					try {
						traverseTree(leftSubTree);
					} catch (NullPointerException ex) {
						//no action necessary
					}
					gdpTree.insertByGDPperCapita(current.name, current.gdpPerCapita);
					try {
						traverseTree(rightSubTree);
					} catch (NullPointerException ex) {
						//no action necessary
					}
				}
			}
		}
		TreeMaker traverser = new TreeMaker();
		traverser.traverseTree(this);

		//do the printing
		/**
		 * This class is similar to the previous class, except that it is used to recursively traverse the tree
		 * while printing the bottom 5 nodes.
		 */
		class traverseAnotherTree {
			int i = 0;

			/**
			 * The method traverses a tree in order
			 * @param treeToTraverse the tree which will be traversed
			 * @param numberOfCountries the number of nodes to print (starting at the bottom)
			 * @return the number of nodes that remain to be printed
			 */
			int printTree(BinarySearchTree treeToTraverse, int numberOfCountries) {
				if (numberOfCountries > 0) {
					Node current = treeToTraverse.root;
					BinarySearchTree leftSubTree = new BinarySearchTree(current.leftChild);
					BinarySearchTree rightSubTree = new BinarySearchTree(current.rightChild);
					if ((current.leftChild == null) && (current.rightChild == null)) {
						current.printNode();
					} else {
						try {
							return printTree(leftSubTree, numberOfCountries);
						} catch (NullPointerException ex) {
							//no action necessary
						}
						current.printNode();
						//numberOfCountries--;
						try {
							return printTree(rightSubTree, numberOfCountries);
						} catch (NullPointerException ex) {
							//no action necessary
						}
					}
					numberOfCountries--;
				}
				return numberOfCountries;
			}
		}
		traverseAnotherTree treePrinter = new traverseAnotherTree();
		treePrinter.printTree(gdpTree, 5);
	}

	/**
	 * Traverses the tree (inorder), and prints 5 times starting from the highest node.
	 */
	public void printTopFive() {
		BinarySearchTree gdpTree = new BinarySearchTree();

		/**
		 * This class is necessary to support recursively traversing the tree
		 */
		class TreeMaker {
			/**
			 * This method traverses a tree in order
			 * @param treeToTraverse the tree which will be traversed
			 */
			void traverseTree(BinarySearchTree treeToTraverse) {
				Node current = treeToTraverse.root;
				BinarySearchTree leftSubTree = new BinarySearchTree(current.leftChild);
				BinarySearchTree rightSubTree = new BinarySearchTree(current.rightChild);
				if ((current.leftChild == null) && (current.rightChild == null)) {
					gdpTree.insertByGDPperCapita(current.name, current.gdpPerCapita);
				} else {
					try {
						traverseTree(leftSubTree);
					} catch (NullPointerException ex) {
						//no action necessary
					}
					gdpTree.insertByGDPperCapita(current.name, current.gdpPerCapita);
					try {
						traverseTree(rightSubTree);
					} catch (NullPointerException ex) {
						//no action necessary
					}
				}
			}
		}
		TreeMaker traverser = new TreeMaker();
		traverser.traverseTree(this);

		//do the printing
		/**
		 * This class is similar to the previous class, except that it is used to recursively traverse the tree
		 * while printing the bottom 5 nodes.
		 */
		class traverseAnotherTree {
			int i = 0;

			/**
			 * This method traverses a tree in order and prints the top 5 nodes
			 * @param treeToTraverse the tree which will be traversed
			 * @param numberOfCountries the number of nodes that will be printed
			 * @return the number of nodes that have yet to be printed
			 */
			int printTree(BinarySearchTree treeToTraverse, int numberOfCountries) {
				if (numberOfCountries > 0) {
					Node current = treeToTraverse.root;
					BinarySearchTree leftSubTree = new BinarySearchTree(current.leftChild);
					BinarySearchTree rightSubTree = new BinarySearchTree(current.rightChild);
					if ((current.leftChild == null) && (current.rightChild == null)) {
						current.printNode();
					} else {
						try {
							return printTree(rightSubTree, numberOfCountries);
						} catch (NullPointerException ex) {
							//no action necessary
						}
						current.printNode();
						//numberOfCountries--;
						try {
							return printTree(leftSubTree, numberOfCountries);
						} catch (NullPointerException ex) {
							//no action necessary
						}
					}
					numberOfCountries--;
				}
				return numberOfCountries;
			}
		}
		traverseAnotherTree treePrinter = new traverseAnotherTree();
		treePrinter.printTree(gdpTree, 5);
	}

	/**
	 * This method checks to see if the tree is empty.
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * This is a variant of the insert method, except instead of inserting a country based on alphabetical order
	 * the country is inserted based on the value of gdp per capita.
	 * @param name the name of the country that will be inserted
	 * @param gdpPerCapita the gdp per capita of the country that will be inserted.
	 */
	private void insertByGDPperCapita(String name, double gdpPerCapita) {
		if (this.isEmpty()) {
			root = new Node(name, gdpPerCapita);
		} else {
			Node current = root;
			Node previous = current;
			while (current != null) {
				if (current.gdpPerCapita > gdpPerCapita) {
					//move to the left
					previous = current;
					current = current.leftChild;
				} else {
					//move to the right
					previous = current;
					current = current.rightChild;
				}
			}
			if (previous.gdpPerCapita > gdpPerCapita) {
				//insert to the left
				previous.leftChild = new Node(name, gdpPerCapita);
			} else {
				//insert to the right
				previous.rightChild = new Node(name, gdpPerCapita);
			}
		}
	}
}

