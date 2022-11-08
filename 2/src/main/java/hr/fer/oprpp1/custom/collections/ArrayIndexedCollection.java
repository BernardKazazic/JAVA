package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * This class represents a resizable list of elements.
 * It implements List methods and stores Object elements to its array.
 */
public class ArrayIndexedCollection implements List {
	
	private int size;
	private long modificationCount;
	private Object[] elements;

	/**
	 * Constructs ArrayIndexedCollection with initial size of 16 elements.
	 */
	public ArrayIndexedCollection() {
		this.size = 0;
		this.modificationCount = 0;
		this.elements = new Object[16];
	}

	/**
	 * Constructs ArrayIndexedCollection with given initial size.
	 *
	 * @param initialCapacity initial capacity of ArrayIndexedColleciton
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		if(initialCapacity < 1) 
			throw new IllegalArgumentException();
		this.modificationCount = 0;
		this.size = 0;
		this.elements = new Object[initialCapacity];
	}

	/**
	 * Constructs ArrayIndexedCollection that copies given collection.
	 *
	 * @param other collection that is copied into ArrayIndexedCollection
	 */
	public ArrayIndexedCollection(Collection other) {
		this();
		if(other == null) 
			throw new NullPointerException();
		if(other.size() > this.size) {
			this.size = other.size();
			this.elements = new Object[other.size()];
		}
		this.addAll(other);
	}

	/**
	 * Constructs ArrayIndexedCollection that copies given collection and
	 * has capacity of given initial size. If initial size is less than needed
	 * for copying other collection, default size will be set to capacity of
	 * other collection.
	 *
	 * @param other collection that is copied into ArrayIndexedCollection
	 * @param initialCapacity initial capacity of ArrayIndexedCollection
	 */
	public ArrayIndexedCollection(Collection other, int initialCapacity) {
		this(initialCapacity);
		if(other == null) 
			throw new NullPointerException();
		if(other.size() > this.size) {
			this.size = other.size();
			this.elements = new Object[other.size()];
		}
		this.addAll(other);
	}

	/**
	 * ArrayIndexedCollection implementation of ElementsGetter.
	 */
	private static class ArrayCollectionElementsGetter implements ElementsGetter{
		int index;
		long savedModificationCount;
		ArrayIndexedCollection parent;
		
		public ArrayCollectionElementsGetter(ArrayIndexedCollection arrayCollection) {
			this.index = 0;
			this.savedModificationCount = arrayCollection.modificationCount;
			this.parent = arrayCollection;
		}
		
		@Override
		public boolean hasNextElement() {
			if(this.savedModificationCount != this.parent.modificationCount) 
				throw new ConcurrentModificationException();
			if(parent.size - this.index == 0) 
				return false;
			return true;
		}
		
		@Override
		public Object getNextElement() {
			if(!this.hasNextElement()) 
				throw new NoSuchElementException();
			return parent.elements[index++];
		}
		
	}
	
	@Override
	public ElementsGetter createElementsGetter() {
		return new ArrayCollectionElementsGetter(this);
	};
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public void add(Object value) {
		if(value == null) 
			throw new NullPointerException();
		if(size == elements.length) {
			Object[] other = new Object[size * 2];
			for(int i = 0; i < size; i++) {
				other[i] = elements[i];
			}
			other[size] = value;
			size += 1;
			elements = other;
		}
		else {
			size += 1;
			elements[size - 1] = value;
		}
		modificationCount++;
	}
	
	@Override
	public boolean contains(Object value) {
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(value)) 
				return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Object value) {
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(value)) {
				elements[i] = null;
				return true;
			}
		}
		modificationCount++;
		return false;
	}
	
	@Override
	public Object[] toArray() {
		Object[] other = new Object[size];
		for(int i = 0; i < size; i++) {
			other[i] = elements[i];
		}
		return other;
	}
	
	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
		modificationCount++;
	}
	
	@Override
	public Object get(int index) {
		if(index < 0 && index > size - 1) 
			throw new IndexOutOfBoundsException();
		return elements[index];
	}
	
	@Override
	public void remove(int index) {
		if(index < 0 && index > size - 1) 
			throw new IndexOutOfBoundsException();
		for(int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[size - 1] = null;
		modificationCount++;
		size -= 1;
	}
	
	@Override
	public void insert(Object value, int position) {
		if(position < 0 && position > size) 
			throw new IndexOutOfBoundsException();
		if(size == position) 
			add(value);
		else {
			if(size == elements.length) {
				Object[] other = new Object[size * 2];
				for(int i = 0; i < size; i++) {
					other[i] = elements[i];
				}
				elements = other;
			}
			for(int i = size; i > position; i--) {
				elements[i] = elements[i - 1];
			}
			elements[position] = value;
			size++;
			modificationCount++;
		}
	}
	
	@Override
	public int indexOf(Object value) {
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(value)) 
				return i;
		}
		return -1;
	}
	
	
}
