package BinaryTree;

/**
 * DefaultBinaryTree implements the methods defined in the interface for a basic binary tree. 
 * @author yesungkim
 *
 * @param <T>
 */
public class DefaultBinaryTree<T> implements BinaryTree<T> 
{
	//instance of the BinaryTreeNode class
	private BinaryTreeNode<T> root;

	//constructor
	public DefaultBinaryTree()
	{
	}
	
	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<T> getRoot()
	{
		if (root != null)
			return root;
		else
			return null;
	}

	/**
	 * Set the root node for this tree.
	 */
	public void setRoot(BinaryTreeNode<T> root)
	{
		this.root = root;
	}

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	public boolean isEmpty()
	{
		if (root == null)
			return true;
		else 
			return false;
	}

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorderList.
	 */
	public LinkedList<T> inorderTraversal()
	{
		//instantiate a new list
		LinkedList<T> inorderList = new LinkedList<T>();
		//call the recursive helper method starting from the root
		inorderTraversal(root, inorderList);
		
		return inorderList;
	}
	
	/**
	 * a private recursive method used in public inorderTraversal method
	 * @param node
	 * @param traversal
	 */
	private void inorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal)
	{
		//base case 1
		//if the node is empty return nothing
		if (node == null)
			return;
		//base case 2
		//if the node is a leaf insert the data of the node at the tail of the list(traversal)
		if (node.isLeaf())
			traversal.insertLast(node.getData());
		
		else //if the node is not a leaf or not empty
		{
			//first visit the left child then node then the right child
			inorderTraversal(node.getLeftChild(), traversal);
			traversal.insertLast(node.getData());
			inorderTraversal(node.getRightChild(), traversal);
		}
	}

	/**
	 * Get the data of this tree using preorder traversal.
	 * 
	 * @return preorderList.
	 */
	public LinkedList<T> preorderTraversal()
	{
		//instantiate a new list
		LinkedList<T> preorderList = new LinkedList<T>();
		//call the recursive helper method starting from the root
		preorderTraversal(root, preorderList);
		
		return preorderList;
	}
	
	/**
	 * a private recursive method used in public preorderTraversal method
	 * @param node
	 * @param traversal
	 */
	private void preorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal)
	{
		//base case 1
		//if the node is empty return nothing
		if (node == null)
			return;
		//base case 2
		//if the node is the leaf insert the data of the node at the tail of the list(traversal)
		else if (node.isLeaf())
			traversal.insertLast(node.getData());
		
		else //if the node is not a leaf or not empty
		{
			//visit node then left child then right child
			traversal.insertLast(node.getData());
			preorderTraversal(node.getLeftChild(), traversal);
			preorderTraversal(node.getRightChild(), traversal);
		}
	}	

	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorderList.
	 */
	public LinkedList<T> postorderTraversal()
	{
		//instantiate a new list
		LinkedList<T> postorderList = new LinkedList<T>();
		//call the recursive helper method for postorder starting from the root
		postorderTraversal(root, postorderList);
		
		return postorderList;		
	}
	
	
	/**
	 * a private recursive method used in public inorderTraversal method
	 * @param node
	 * @param traversal
	 */
	private void postorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal)
	{
		//base case 1
		//if the node is null return nothing
		if (node == null)
			return;
		
		//base case 2
		//if the node is the leaf insert the data of the node at the tail of the list(traversal)
		if (node.isLeaf())
			traversal.insertLast(node.getData());
		
		else //if the node is not a leaf or not empty
		{
			//visit left child then right child then the node
			postorderTraversal(node.getLeftChild(), traversal);
			postorderTraversal(node.getRightChild(), traversal);
			traversal.insertLast(node.getData());
		}
	}		
	

	/**
	 * Print the tree using inorder traversal.
	 * 
	 * @return inorder String
	 */
	public String inorderString()
	{
		return (inorderTraversal().toString());
	}

	/**
	 * Print the tree using preorder traversal.
	 * 
	 * @return preorder String
	 */
	public String preorderString()
	{
		return (preorderTraversal().toString());
	}

	/**
	 * Print the tree using postorder traversal.
	 * @return postorder String
	 */
	public String postorderString()
	{
		return (postorderTraversal().toString());
	}
	
	
	/**
	 * main method creates a DefaultBinaryTree instance, and manually creates a tree corresponding to the Seven Dwarves
	 * @param args
	 */
	public static void main(String [] args)
	{
		//instantiate the dwarves tree
		DefaultBinaryTree<String> dwarves = new DefaultBinaryTree<String>();
		//create a DefaultBinaryTreeNode of type <String> for each Dwarf
		DefaultBinaryTreeNode<String> Happy = new DefaultBinaryTreeNode<String>();
		Happy.setData("Happy");
		DefaultBinaryTreeNode<String> Doc = new DefaultBinaryTreeNode<String>();
		Doc.setData("Doc");
		DefaultBinaryTreeNode<String> Bashful = new DefaultBinaryTreeNode<String>();
		Bashful.setData("Bashful");
		DefaultBinaryTreeNode<String> Grumpy = new DefaultBinaryTreeNode<String>();
		Grumpy.setData("Grumpy");
		DefaultBinaryTreeNode<String> Sleepy = new DefaultBinaryTreeNode<String>();
		Sleepy.setData("Sleepy");
		DefaultBinaryTreeNode<String> Sneezy = new DefaultBinaryTreeNode<String>();
		Sneezy.setData("Sneezy");
		DefaultBinaryTreeNode<String> Dopey = new DefaultBinaryTreeNode<String>();
		Dopey.setData("Dopey");
		
		//set the root and children of the dwarves tree		
		dwarves.setRoot(Happy);
		Happy.setLeftChild(Doc);
		Doc.setLeftChild(Bashful);
		Doc.setRightChild(Grumpy);
		Happy.setRightChild(Sleepy);
		Grumpy.setLeftChild(Dopey);
		Sleepy.setRightChild(Sneezy);
		
		//print them out in inorder, preorder, and postorder
		System.out.println(dwarves.inorderString());
		System.out.println(dwarves.preorderString());
		System.out.println(dwarves.postorderString());

	}
}
