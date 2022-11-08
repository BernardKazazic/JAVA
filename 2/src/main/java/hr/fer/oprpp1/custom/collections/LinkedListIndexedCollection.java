package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * This class represents linked list of elements.
 * It implements List methods and stores Object elements to its nodes that point to other nodes.
 * Node cannot contain null value.
 */
public class LinkedListIndexedCollection implements List {

	/**
	 * Internal class of LinkedListIndexedCollection that stores its values.
	 */
	private class ListNode {
		private ListNode previous;
		private ListNode next;
		private Object value;
				
		public ListNode(Object value) {
			this.value = value;
		}
		
		public ListNode(ListNode previous, Object value) {
			this(value);
			this.previous = previous;
		}
		
	}
	
	private int size;
	private long modificationCount;
	private ListNode first;
	private ListNode last;

	/**
	 * Constructs LinkedListIndexedCollection.
	 */
	public LinkedListIndexedCollection() {
		this.size = 0;
		this.modificationCount = 0;
		this.first = null;
		this.last = null;
	}

	/**
	 * Constructs LinkedListIndexedCollection that copies given collection.
	 *
	 * @param other collection that is copied into LinkedListIndexedCollection
	 */
	public LinkedListIndexedCollection(Collection other) {
		this();
		this.addAll(other);
	}

	/**
	 * LinkedListIndexedCollection implementation of ElementsGetter.
	 */
	private static class LinkedCollectionElementsGetter implements ElementsGetter{
		int index;
		long savedModificationCount;
		LinkedListIndexedCollection parent;
		
		public LinkedCollectionElementsGetter(LinkedListIndexedCollection linkedCollection) {
			this.index = 0;
			this.savedModificationCount = linkedCollection.modificationCount;
			this.parent = linkedCollection;
		}
		
		@Override
		public boolean hasNextElement(){
			if(this.savedModificationCount != this.parent.modificationCount) 
				throw new ConcurrentModificationException();
			if(parent.size - index > 0) 
				return true;
			return false;
		}
		
		@Override
		public Object getNextElement() {
			if(!this.hasNextElement()) 
				throw new NoSuchElementException();
			return parent.get(index++);
		}
	}
	
	@Override
	public ElementsGetter createElementsGetter() {
		return new LinkedCollectionElementsGetter(this);
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public void add(Object value) {
		if(value == null) 
			throw new NullPointerException();
		if(size == 0) {
			first = new ListNode(value);
			last = first;
			size++;
			modificationCount++;
		}
		else {
			last.next = new ListNode(last, value);
			last = last.next;
			size++;
			modificationCount++;
		}

	}
	
	@Override
	public boolean contains(Object value) {
		ListNode temp = first;
		for(int i = 0; i < size; i++) {
			if(temp.value.equals(value)) 
				return true;
			temp = temp.next;
		}
		return false;
	}
	
	@Override
	public boolean remove(Object value) {
		ListNode temp = first;
		for(int i = 0; i < size; i++) {
			if(temp.value.equals(value)) {
				if(i == 0) {
					first = first.next;
					first.previous = null;
				}
				else {
					if(temp.next != null) {
						temp.previous.next = temp.next;
						temp.next.previous = temp.previous;
					}
					else {
						temp.previous.next = null;
					}
				}
				size--;
				modificationCount++;
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	
	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[size];
		if(size == 0) 
			return newArray;
		ListNode temp = first;
		for(int i = 0; i < size; i++) {
			newArray[i] = temp.value;
			temp = temp.next;
		}
		return newArray;
	}
	
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
		modificationCount++;
	}
	
	@Override
	public Object get(int index) {
		if(index < 0 && index > size - 1) 
			throw new IndexOutOfBoundsException();
		ListNode temp;
		if(index < size / 2) {
			temp = first;
			for(int i = 1; i <= index; i++) {
				temp = temp.next;
			}
			return temp.value;
		}
		else {
			temp = last;
			for(int i = size - 2; i >= index; i--) {
				temp = temp.previous;
			}
			return temp.value;
		}
	}
	
	@Override
	public void insert(Object value, int position) {
		if(position < 0 && position > size) 
			throw new IndexOutOfBoundsException();
		ListNode previousNew = first;
		for(int i = 1; i < position; i++) {
			previousNew = previousNew.next;			
		}
		ListNode nextNew = previousNew.next;
		ListNode insertNode = new ListNode(previousNew, value);
		insertNode.next = nextNew;
		previousNew.next = insertNode;
		nextNew.previous = insertNode;
		size++;
		modificationCount++;
	}
	
	@Override
	public int indexOf(Object value) {
		ListNode temp = first;
		for(int i = 0; i < size; i++) {
			if(temp.value.equals(value)) 
				return i;
			temp = temp.next;
		}
		return -1;
	}
	
	@Override
	public void remove(int index) {
		if(index < 0 && index > size - 1) 
			throw new IndexOutOfBoundsException();
		ListNode temp = first;
		for(int i = 0; i < index; i++) {
			temp = temp.next;
		}
		if(index == 0) {
			first = first.next;
			first.previous = null;
		}
		else {
			if(temp.next != null) {
				temp.previous.next = temp.next;
				temp.next.previous = temp.previous;
			}
			else {
				temp.previous.next = null;
			}
		}
		size--;
		modificationCount++;
	}
}
