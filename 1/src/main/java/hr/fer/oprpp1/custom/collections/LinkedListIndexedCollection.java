package hr.fer.oprpp1.custom.collections;

/**
 * This is a class that extends Collection and uses nodes point to other nodes and store value.
 * It also has field size that counts current number of elements in collection. Node cannot contain null value.
 */
public class LinkedListIndexedCollection extends Collection {
	
	/**
	 * LinkedListIndexedCollection uses ListNode to create a list of nodes that store its values.
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
	private ListNode first;
	private ListNode last;
	
	public LinkedListIndexedCollection() {
		this.size = 0;
		this.first = null;
		this.last = null;
	}
	
	public LinkedListIndexedCollection(Collection other) {
		this();
		this.addAll(other);
	}
	
	/**
	 * This method returns current number of elements in this collection.
	 *
	 * @return field size of this collection
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Adds the given object into this collection at the end of collection. Newly added element becomes the
	 * element at the biggest index.
	 *
	 * @param value object that will be added at the end of the collection
	 * @exception NullPointerException if given value is null
	 */
	@Override
	public void add(Object value) {
		if(value == null) throw new NullPointerException();
		if(this.size == 0) {
			this.first = new ListNode(value);
			this.last = this.first;
			this.size++;
		}
		else {
			this.last.next = new ListNode(this.last, value);
			this.last = this.last.next;
			this.size++;
		}

	}
	
	/**
	 * Returns true only if the collection contains given value, as determined by equals method.
	 * 
	 * @param value object that collection elements are compared with
	 * @return true if collection contains it, false if not
	 */
	@Override
	public boolean contains(Object value) {
		ListNode temp = this.first;
		if(temp.value.equals(value)) return true;
		for(int i = 1; i < this.size; i++) {
			temp = temp.next;
			if(temp.value.equals(value)) return true;
		}
		return false;
	}
	
	/**
	 * Returns true only if the collection contains given value as determined by equals method and removes
	 * first occurrence of it.
	 * 
	 * @param value first element equal to this object will be removed from collection
	 * @return true if element is removed, false if not
	 */
	@Override
	public boolean remove(Object value) {
		ListNode temp = this.first;
		if(temp.value.equals(value)) {
			this.first = this.first.next;
			this.first.previous = null;
			return true;
		}
		for(int i = 1; i < this.size; i++) {
			temp = temp.next;
			if(temp.value.equals(value)) {
				temp.previous.next = temp.next;
				temp.next.previous = temp.previous;
				size--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Allocates new array with size equals to the size of this collections, fills it with collection content and
	 * returns the array. This method never returns null.
	 * 
	 * @return object array with this collection elements
	 */
	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[this.size];
		if(this.size == 0) return newArray;
		ListNode temp = this.first;
		for(int i = 0; i < this.size; i++) {
			newArray[i] = temp.value;
			temp = temp.next;
		}
		return newArray;
	}
	
	/**
	 * Method calls processor.process() for each element of this collection.
	 */
	@Override
	public void forEach(Processor processor) {
		ListNode temp = this.first;
		for(int i = 0; i < this.size; i++) {
			processor.process(temp.value);
			temp = temp.next;
		}
	}
	
	/**
	 * Removes all elements from the collection. Collection “forgets” about current linked list.
	 * Size is set to 0, first = second = null.
	 */
	@Override
	public void clear() {
		this.size = 0;
		this.first = null;
		this.last = null;
	}
	
	/**
	 * Returns the object that is stored in ListNode at position index.
	 *
	 * @param index position of element we are looking for
	 * @return element at given index
	 * @exception IndexOutOfBoundsException if given index is not between 0 and size-1
	 */
	public Object get(int index) {
		if(index < 0 && index > this.size - 1) throw new IndexOutOfBoundsException();
		ListNode temp;
		if(index < this.size / 2) {
			temp = this.first;
			for(int i = 1; i <= index; i++) {
				temp = temp.next;
			}
			return temp.value;
		}
		else {
			temp = this.last;
			for(int i = this.size - 2; i >= index; i--) {
				temp = temp.previous;
			}
			return temp.value;
		}
	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position. ListNode with the given value is
	 * inserted at the given position.
	 *
	 * @param value object that will be inserted
	 * @param position index at which object needs to be inserted
	 * @exception IndexOutOfBoundsException if position is not between 0 and size
	 */
	public void insert(Object value, int position) {
		if(position < 0 && position > this.size) throw new IndexOutOfBoundsException();
		ListNode previousNew = this.first;
		for(int i = 1; i <= position; i++) {
			previousNew = previousNew.next;			
		}
		ListNode nextNew = previousNew.next;
		ListNode insertNode = new ListNode(previousNew, value);
		insertNode.next = nextNew;
		previousNew.next = insertNode;
		nextNew.previous = insertNode;
		this.size++;
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of the given value.
	 * The equality is determined using the equals method.
	 *
	 * @param value element that will be looked for
	 * @return index of given object if it is found or -1 if object is not found
	 */	
	public int indexOf(Object value) {
		ListNode temp = this.first;
		if(temp.value.equals(value)) return 0;
		for(int i = 1; i < this.size; i++) {
			temp = temp.next;
			if(temp.value.equals(value)) return i;
		}
		return -1;
	}
	
	/**
	  * Removes element at specified index from collection. Element that was previously at location index+1
	  * after this operation is on location index, etc. When found ListNode at the given index is unlinked.
	  * Previous and next node are then linked.
	  *
	  * @param index position of element that will be removed
	  * @exception IndexOutOfBoundsException if given index is not between 0 and size-1
	  */
	public void remove(int index) {
		if(index < 0 && index > size - 1) throw new IndexOutOfBoundsException();
		ListNode temp = this.first;
		for(int i = 1; i <= index; i++) {
			temp = temp.next;
		}
		ListNode prev = temp.previous;
		ListNode next = temp.next;
		prev.next = next;
		next.previous = prev;
		this.size--;
	}
}
