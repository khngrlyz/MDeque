package project3;

import java.util.ArrayList;

/**
 * 
 *	This class represents a double ended queue that supports element insertion and removal at three points: front, middle and back. 
 *  MDeque has no fixed limits on the number of elements it contains.The remove operations all return null values if the MDeque is empty. 
 *  The structure does not allow null as an element.
 *  All pop, push, and peek operations (from all three points of access) are constant time operations
 * @param <E>
 * @author Chloe Baatar * @version 11/10/2022
 */
public class MDeque<E> extends java.lang.Object implements java.lang.Iterable<E> {
	private  Node front, end;
	private int size;

	/**
	 * No argument constructor that constructs empty queue.
	 */
	public MDeque() {
		front = null;
		end = null;
		size = 0;
	}

	/**
	 * Inserts an item of generic type at the back end of the MDeque and increments the size by one.
	 * @param item will be pushed at the back of the MDeque unless it is null.
	 * @throws IllegalArgumentException if the item is null
	 */
	public void pushBack(E item) throws IllegalArgumentException{
		if(item == null){
			throw new IllegalArgumentException();
		}
		if (size == 0) {
			front = new Node(item);
			end = front;
		} else {
			Node newNode = new Node(item, end, null);
			end.setNext(newNode);
			end = newNode;
		}
		size++;
	}

	/**
	 * Inserts an item at the front end of the MDeque and increments the size by one.
	 * @param item will be added the the front unless it is null.
	 * @throws IllegalArgumentException
	 */
	public void pushFront(E item) throws IllegalArgumentException{
		if(item == null){
			throw new IllegalArgumentException();
		}
		if (size == 0) {
			front = new Node(item);
			end = front;
		} else {
			Node newNode = new Node(item, null, front);
			front.setPrev(newNode);
			front = newNode;
		}
		size++;
	}
	/**
	 * Inserts an item at the middle index of the MDeque and increments the size by one.
	 * @param item will be added at the (size+1)/2 index of the MDeque unless it is null.
	 * @throws IllegalArgumentException if the item is null.
	 * @
	 */
	public void pushMiddle(E item) throws IllegalArgumentException{
		if(item == null){
			throw new IllegalArgumentException();
		}
		else if (size == 0) {
			Node newNode = new Node(item);
			front = newNode;
			front.setNext(null);
			front.setPrev(null);
			end = front;
		} 
		else if(size == 1) {
			Node newNode = new Node(item);
			front.setNext(newNode);
			end = newNode;
			end.setPrev(front);
			end.setNext(null);
		}
		else {
			Node newNode = new Node(item);
			int midIndex = (size+1)/2;
			Node middle = front;
			for (int i = 0; i < midIndex; i++) {
				middle = middle.getNext();
			}

			newNode.setNext(middle);
			newNode.setPrev(middle.getPrev());
			middle.getPrev().setNext(newNode);
			middle.setPrev(newNode);

		}
		size++;
	}
	/**
	 * 
	 * @return the element at the front end of the MDeque, returns null if the size is zero.
	 */
	public E peekFront() {
		if (size == 0) {
			return null;
		} else {
			return front.getData();
		}
	}
	/**
	 * 
	 * @return the element at the back end of the MDeque, returns null if the size is zero.
	 */
	public E peekBack() {
		if (size == 0) {
			return null;
		} else {
			return end.getData();
		}
	}
	/**
	 * 
	 * @return the element at the middle index, size/2,  of the MDeque, returns null if the size is zero.
	 */
	public E peekMiddle() {
		if (size == 0) {
			return null;
		}   
		else if(size == 1) {
			Node middle = front = end;
			return middle.getData();
		}
		else {
			Node middle = front;
			for (int i = 0; i < size / 2; i++) {
				middle = middle.getNext();
			}

			return middle.getData();

		}
	}

	/**
	 * Retrieves and returns the front element of this MDeque.
	 * @return null if the MDeque is empty.
	 */
	public E popFront() {
		if (size == 0) {
			return null;
		} else {
			E data = front.getData();
			front = front.getNext();
			if (front != null) {
				front.setPrev(null);
			} 
			else {
				end = null;
			}
			size--;
			return data;
		}
	}
	/**
	 * Retrieves and removes the back element of this MDeque.
	 * @return null if MDeque is empty.
	 */
	public E popBack() {
		if (size == 0) {
			return null;
		} else {
			E data = end.getData();
			end = end.getPrev();
			if (end != null) {
				end.setNext(null);
			} else {
				front = null;
			}
			size--;
			return data;
		}
	}
	/**
	 * Retrieves and removes the middle element of the MDeque.
	 * @return null if MDeque is empty.
	 */
	public E popMiddle() {
		E data;
		Node middle;
		if (size == 0) {
			return null;
		} 
		else if(size == 1) {
			middle = front;
			data = middle.getData();
			front = end = null;
			size--;
			return data;

		}
		else if(size == 2) {
			middle = end;
			data = middle.getData();
			front.setNext(null);
			end = front;
			size--;
			return data;
		}
		else {
			middle = front;
			int midIndex = size/2;
			for (int i = 0; i < midIndex; i++) {
				middle = middle.getNext();
			}

			data = middle.getData();
			middle.getNext().setPrev(middle.getPrev());
			middle.getPrev().setNext(middle.getNext());

			size--;
			return data; 

		}
	}


	// returns the size of the MDeque
	public int size() {
		return size;
	}

	/**
	 * Returns an iterator over the elements in this MDeque in proper sequence. 
	 * The elements will be returned in order from front to back.
	 */

	public java.util.Iterator<E> iterator(){
		ArrayList<E> list = new ArrayList<E>();
		Node current = front;
		while(current != null){
			list.add(current.getData());
			current = current.getNext();
		}
		return list.iterator();
	}
	/**
	 * Returns an iterator over the elements in this MDeque in reverse sequential order. 
	 * The elements will be returned in order from back to front.
	 */
	public java.util.Iterator<E> reverseIterator(){
		ArrayList<E> list = new ArrayList<E>();
		Node current = end;
		while(current != null){
			list.add(current.getData());
			current = current.getPrev();
		}
		return list.iterator();
	}

	@Override
	/**
	 * Returns a string representation of this MDeque. 
	 * The string representation consists of a list of the collection's elements in the order they are returned by its iterator,
	 * enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
	 */

	public String toString() {
		String result = "";
		Node n = front;
		result += ("[");
		while( n != null ) {
			result += (n.data);
			n= n.next;
			if(n != null)
				result += (", ");
		}	
		return result.toString() + "]";
	}

	/**
	 * Represents a single node in the MDeque.
	 * Contains constructors, accessors and mutators, and toString method.
	 * @author Chloe Baatar
	 *
	 */
	private class Node {
		private E data;
		private Node next;
		private Node prev;

		//constructs a node that stores data of type E
		public Node(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		//constructs a node that stores data of type E
		//specifies the previous and next node
		public Node(E data, Node prev, Node next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		//return data of this node
		public E getData() {
			return data;
		}
		//access to next node
		public Node getNext() {
			return next;
		}
		//access to previous node
		public Node getPrev() {
			return prev;
		}

		//sets the data of this node
		@SuppressWarnings("unused")
		public void setData(E data) {
			this.data = data;
		}
		//sets the next node of this node 
		//to the node specified in the argument
		public void setNext(Node next) {
			this.next = next;
		}
		//sets the previous node of this node 
		//to the node specified in the argument
		public void setPrev(Node prev) {
			this.prev = prev;
		}

		//returns the data of this node as a string
		public String toString() {
			return data.toString();
		}
	}
}