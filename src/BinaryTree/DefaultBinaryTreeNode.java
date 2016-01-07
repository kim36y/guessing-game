package BinaryTree;

/**
 * 
 * DefaultBinaryTreeNode implements methods defined in the interface for a basic binary tree node,
 * with data of type T and pointers to left and right children.
 * 
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> 
{
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	//constructor
	public DefaultBinaryTreeNode()
	{
	}
	
	/**
	* Get the data stored at this node.
	* @return Object data.
	*/
	public T getData()
	{
		if (data != null)
			return data;
		else //if the data is null
			return null;
	}

	/**
	* Set the data stored at this node.
	*/
	public void setData(T data)
	{
		this.data = data;
	}

	/**
	* Get the left child.
	* @return BinaryTreeNode that is left child,
	* or null if no child.
	*/
	public BinaryTreeNode<T> getLeftChild()
	{
		if (left != null)
			return left;
		else
			return null;
	}

	/**
	 * Get the right child.
	* @return BinaryTreeNode that is right child,
	* or null if no child.
	*/
	public BinaryTreeNode<T> getRightChild()
	{
		if (right != null)
			return right;
		else
			return null;
	}

	/**
	* Set the left child.
	*/
	public void setLeftChild( BinaryTreeNode<T> left )
	{
		this.left = left;
	}

	/**
	* Set the right child.
	*/
	public void setRightChild( BinaryTreeNode<T> right )
	{
		this.right = right;
	}

	/**
	* Tests if this node is a leaf (has no children).
	* @return true if leaf node.
	*/
	public boolean isLeaf()
	{
		 return (left == null && right == null); 
	}
}
