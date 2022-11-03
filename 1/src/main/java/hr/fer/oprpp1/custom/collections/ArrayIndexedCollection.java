package hr.fer.oprpp1.custom.collections;

/**
 * This class implements Collection and uses Object array to store elements.
 * It has integer size that counts current elements in Object array.
 */
public class ArrayIndexedCollection extends Collection {
	
	private int size;
	private Object[] elements;
	
	public ArrayIndexedCollection() {
		this.size = 0;
		this.elements = new Object[16];
	}
	
	public ArrayIndexedCollection(int initialCapacity) {
		if(initialCapacity < 1) throw new IllegalArgumentException();
		this.size = 0;
		this.elements = new Object[initialCapacity];
	}
	
	public ArrayIndexedCollection(Collection other) {
		this();
		if(other == null) throw new NullPointerException();
		if(other.size() > this.size) {
			this.size = other.size();
			this.elements = new Object[other.size()];
		}	
	}
	
	public ArrayIndexedCollection(Collection other, int initialCapacity) {
		this(initialCapacity);
		if(other == null) throw new NullPointerException();
		if(other.size() > this.size) {
			this.size = other.size();
			this.elements = new Object[other.size()];
		}	
	}

	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Adds the given object into this collection. Reference is added into first empty place in the elements array.
	 * If the elements array is full, it is reallocated by doubling its size.
	 * 
	 * @param value object that is added to the end of the collection
	 * @exception NullPointerException when parameter value is null
	 */
	@Override
	public void add(Object value) {
		if(value == null) throw new NullPointerException();
		if(this.size == this.elements.length) {
			Object[] other = new Object[this.size * 2];
			for(int i = 0; i < this.size; i++) {
				other[i] = this.get(i);
			}
			other[this.size] = value;
			this.size += 1;
			this.elements = other;
		}
		else {
			this.size += 1;
			this.elements[this.size - 1] = value;
		}
	}
	
	/**
	 * This method checks if given value exists in collection.
	 * 
	 * @param value object that collection elements are compared with
	 * @return true if collection contains given value, false if not
	 */
	@Override
	public boolean contains(Object value) {
		for(int i = 0; i < this.size; i++) {
			if(this.get(i) == value) return true;
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
		for(int i = 0; i < this.size; i++) {
			if(this.elements[i].equals(value)) {
				this.elements[i] = null;
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
		Object[] other = new Object[this.size];
		for(int i = 0; i < this.size; i++) {
			other[i] = this.elements[i];
		}
		return other;
	}
	
	/**
	 * Method calls processor.process() for each element of this collection.
	 */
	@Override
	public void forEach(Processor processor) {
		for(int i = 0; i < this.size; i++) {
			processor.process(this.elements[i]);
		}
	}
	
	/**
	 * Removes all elements from the collection. The allocated array is left at current capacity. Size is set to 0.
	 * Null references are written into the backing array.
	 */
	@Override
	public void clear() {
		for(int i = 0; i < this.size; i++) {
			this.elements[i] = null;
		}
		this.size = 0;
	}
	
	/**
	 * Returns the object that is stored in backing array at position index.
	 *
	 * @param index position of element we are looking for
	 * @return element at given index
	 * @exception IndexOutOfBoundsException if given index is not between 0 and size-1
	 */
	public Object get(int index) {
		if(index < 0 && index > this.size() - 1) throw new IndexOutOfBoundsException();
		return this.elements[index];
	}

	 /**
	  * Removes element at specified index from collection. Element that was previously at location index+1
	  * after this operation is on location index, etc.
	  *
	  * @param index position of element that will be removed
	  * @exception IndexOutOfBoundsException if given index is not between 0 and size-1
	  */
	public void remove(int index) {
		if(index < 0 && index > this.size() - 1) throw new IndexOutOfBoundsException();
		if(index == this.size() - 1) {
			this.elements[index] = null;
			this.size--;
		}
		else {
			for(int i = index; i < this.size - 1; i++) {
				this.elements[i] = this.elements[i + 1];
			}
			this.elements[this.size - 1] = null;
			this.size -= 1;
		}

	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in array. Elements at the position and at
	 * greater positions are shifted one place toward the end. If elements array is full it doubles its size and then inserts given value.
	 *
	 * @param value object that will be inserted
	 * @param position index at which object needs to be inserted
	 * @exception IndexOutOfBoundsException if position is not between 0 and size
	 */
	public void insert(Object value, int position) {
		if(position < 0 && position > this.size) throw new IndexOutOfBoundsException();
		if(this.size == position) this.add(value);
		else {
			if(this.size == this.elements.length) {
				Object[] other = new Object[this.size * 2];
				for(int i = 0; i < this.size; i++) {
					other[i] = this.get(i);
				}
				this.elements = other;
			}
			for(int i = this.size + 1; i > position; i--) {
				this.elements[i] = this.elements[i - 1];
			}
			this.elements[position] = value;
		}
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of the given value.
	 * The equality is determined using the equals method.
	 *
	 * @param value element that will be looked for
	 * @return index of given object if it is found or -1 if object is not found
	 */
	public int indexOf(Object value) {
		for(int i = 0; i < this.size; i++) {
			if(value.equals(this.elements[i])) return i;
		}
		return -1;
	}
}
