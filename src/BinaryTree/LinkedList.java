package BinaryTree;

public class LinkedList<T> 
{
	private LinkedListNode<T> head;
	
	//constructor
	public LinkedList()
	{
		//set head to null
		head = null;
	}

	/**
	 * Get data stored in head node of list.
	 * return null if the head is empty
	 **/
	public T getFirst()
	{
		if (head != null)
			return head.getData();
		else
			return null;
	}
	
	
	/**
	 * Get the head node of the list.
	 * return null if head is empty
	 **/
	public LinkedListNode<T> getFirstNode()
	{
		if (head != null)
			return head;
		else
			return null;
	}
	
	 
	/**
	 * Get data stored in tail node of list.
	 **/
	public T getLast()
	{
		LinkedListNode<T> newNode = head; 
		//set the newNode to the last node in the list
		while (newNode.getNext() != null)
			newNode = newNode.getNext();
		
		return newNode.getData();
	}
	 
	/**
	 * Get the tail node of the list.
	 **/
	public LinkedListNode<T> getLastNode()
	{
		LinkedListNode<T> newNode = head; 
		//set the newNode to the last node in the list
		while (newNode.getNext() != null)
			newNode = newNode.getNext();
		
		return newNode;
	} 
	
	
	/**
	 * Insert a new node with data at the head of the list.
	 **/
	public void insertFirst( T data )
	{
		LinkedListNode<T> newNode = new LinkedListNode<T>(); 
		//give the new node the data
		newNode.setData(data);
		//set the new node to point to the head
		newNode.setNext(head);
		head = newNode;
		
	}
	
	
	/**
	 * Insert a new node with data after currentNode
	 **/
	public void insertAfter( LinkedListNode<T> currentNode, T data )
	{
		//create new node
		LinkedListNode<T> newNode = new LinkedListNode<T>();
		//give new node the data 
		newNode.setData(data);
		//set the new node to point to the node that used to be after the current node
		newNode.setNext(currentNode.getNext());
		//set the current node to point to the new node
		currentNode.setNext(newNode);	
	}
	
	
	/**
	 * Insert a new node with data at the tail of the list.
	 **/
	public void insertLast( T data )
	{
		if (isEmpty())
		{
			insertFirst(data);
		}
		else
		{
			LinkedListNode<T> newNode = new LinkedListNode<T>();
			newNode.setData(data);
			getLastNode().setNext(newNode);
			newNode.setNext(null);
		}
	}
	
	
	/**
	 * Remove the first node
	 **/
	public void deleteFirst()
	{
		if (head != null)
			//the head becomes the second node in the list
			head = head.getNext();
	}
	
	
	/**
	 * Remove the last node
	 **/
	public void deleteLast()
	{
		//initialize newLastNode to be the head
		LinkedListNode<T> newLastNode = head;
		
		//newLastNode is the second to last node
		while (newLastNode.getNext() != null  && newLastNode.getNext() != getLastNode())
		{
			newLastNode = newLastNode.getNext();
		}
			//set the new last node to point to null ( remove the last node)
			newLastNode.setNext(null);
	}
	
	
	/**
	 * Remove node following currentNode
	 * If no node exists (i.e., currentNode is the tail), do nothing
	 **/
	public void deleteNext( LinkedListNode<T> currentNode )
	{
		//make the current node to point to the node after the next node to remove the next node
		currentNode.setNext(currentNode.getNext().getNext());

	}
	
	
	/**
	 * Return the number of nodes in this list.
	 **/
	public int size()
	{
		//start counting from one because the head is in the beginning
		int count = 0;
		
		LinkedListNode<T> newNode = head;
		
		//increment count by one as the loop goes through each node in the list
		while (newNode != null)
		{
			newNode = newNode.getNext();
			count += 1;
		}
		return count;
			
	}
	
	
	/**
	 * Check if list is empty.
	 * @return true if list contains no items.
	 **/
	public boolean isEmpty()
	{
		//if the list is empty return true
		if (head == null)
			return true;
		else
			return false;
	}
	
	
	//Return a String representation of the list.

	public String toString()
	{

		LinkedListNode<T> currentNode = new LinkedListNode<T>();
		currentNode = head;

		String string = "";

			//if there is nothing in the string, return an empty string
			if(currentNode==null)
			{
				return "";
			}

			else
			{
				//perform the following while there is still node left in the list
				while(currentNode.getNext()!=null)
				{
					//keep adding the currentNode to the string during the looping
					string += currentNode.getData() + "->";
					//keep updating the current node before the tail
					currentNode = currentNode.getNext();
				}
			//add the tail to the string
			string+=currentNode.getData();

	}

	return string;

	}
	
}