package BinaryTree;

/** The default binary search tree contains Comparable objects as data with
 * the invariant that all data in the left subtree of a node is
 * less than the data at the node, and all data in the right subtree
 * is greater than or equal to the data at the node.
 * 
 * this class implements the methods defined in the binary search tree interface.
 */
public class DefaultBinarySearchTree<T extends Comparable<T>> extends DefaultBinaryTree<T> implements BinarySearchTree<T>
{
	//constructor
	public DefaultBinarySearchTree()
	{
	}
	
	
	/**
	* Insert the data into the tree, maintaining the
	* search tree invariant.
	*/  
	public void insert( T data )
	{
		
		insert(this.getRoot(), data);
	}
	/**
	 * recursive helper method of insert method
	 * @param currentNode
	 * @param data
	 */
	private void insert(BinaryTreeNode<T> currentNode, T data)
	{	
		
		//if the current node is null
		//create the node with the given data
		//and set is as a root of the tree
		if (currentNode == null)
		{
			BinaryTreeNode<T> newNode = new DefaultBinaryTreeNode<T>();
			newNode.setData(data);
			this.setRoot(newNode);
		}
		
		//if the data is less than or equal to the currentNode
		else if (data.compareTo(currentNode.getData()) <= 0) 
		{		
				//if the left child is empty create the node with the given data and set it as the left child of the node
				if (currentNode.getLeftChild() == null)
				{
					BinaryTreeNode<T> newNode = new DefaultBinaryTreeNode<T>();
					newNode.setData(data);
					currentNode.setLeftChild(newNode);
				}
				//if the left child is not empty go to the its left child 
				else 
					insert(currentNode.getLeftChild(), data);
		}	
				
		
		//if the data is great than the currentNode
		else if (data.compareTo(currentNode.getData()) > 0)
		{
			//if the right child of the currentNode is empty create the node with the given data and set it as the right child of the node
			if (currentNode.getRightChild() == null)
			{
				BinaryTreeNode<T> newNode = new DefaultBinaryTreeNode<T>();
				newNode.setData(data);
				currentNode.setRightChild(newNode);
			}
			
			//if the right child of the currentNode is not empty go to the its right child 
			else 
				insert(currentNode.getRightChild(), data);
		}
		
		
	}
	

	/**
	* Search for data in the tree, finding the node
	* containing it, or null if the data is not present
	* in the tree.
	* @return the node containing data or null if none exists.
	*/
	public BinaryTreeNode<T> search( T data )
	{
		//set a new node as a root of the tree
		BinaryTreeNode<T> node = this.getRoot();
		//call the recursive method and return its returned value
		return search(node, data);
	}
	
	
	/**
	 * recursion helper method for search method
	 * @param node that possibly contains the right data (start from the root and get updated in order to find the right one)
	 * @param data that needs to be matched with the data of the node
	 * @return the node containing data or null if none exists.
	 */
	private BinaryTreeNode<T> search(BinaryTreeNode<T> node, T data )
	{
		//if the node is empty return null
		if (node == null)
			return null;
		
		//if the node is not empty
		else 
		{
		//if the data matches the data of the node return the node
		if (data.compareTo(node.getData()) == 0)
			return node;
		//if the data is less than the data of the node go to the left child of the node(update the node to be the left child)
		else if ( data.compareTo(node.getData()) < 0)
		{
			return search(node.getLeftChild(), data);
		}
		//if the data is greater than the data of the node go to the right child of the node(update the node to be the right child)
		else if (data.compareTo(node.getData()) > 0)
		{
			return search(node.getRightChild(), data);
		}
		//if the data cannot be found in the tree return null
		else
			return null;
		}
		
	}
	  
	/**
	* Find the minimum element in the tree.
	*/
	public T minElement()
	{
		//set the node to be the root of the tree
		BinaryTreeNode<T> node = this.getRoot();
		//create an element of type T and first set it to null
		T element = null;
		
		//while the node is not null
		while (node != null)
		{
			//set the element to be the data of the node
			element = node.getData();
			//update the data to be the left child
			node = node.getLeftChild();
		}
		//return the element (null if the node is null)
		return element;

	}

	/**
	* Find the maximum element in the tree.
	*/
	public T maxElement()
	{
		//set the node to be the root of the tree
		BinaryTreeNode<T> node = this.getRoot();
		//create an element of type T and first set it to null		
		T element = null;
		
		//while the node is not null
		while (node != null)
		{
			//set the element to be the data of the node
			element = node.getData();
			//update the data to be the right child			
			node = node.getRightChild();
		}
		
		//return the element (null if the node is null)
		return element;
	}
}
