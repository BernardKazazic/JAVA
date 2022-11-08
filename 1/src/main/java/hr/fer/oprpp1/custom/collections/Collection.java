package hr.fer.oprpp1.custom.collections;

/**
 * This represents some general collection of objects.
 */
public class Collection {
	
	protected Collection() {}
	
	/**
	 * This method is used for checking if collection is empty. It checks the size of collection and
	 * uses that to determine if it is empty.
	 * 
	 * @return true if this is empty or false if it isn't
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	/**
	 * This method gives the number of currently stored objects in this collection.
	 * 
	 * @return number of currently stored objects
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * This method adds given object to the collection.
	 * 
	 * @param value object that will be added into collection
	 */
	public void add(Object value) {}
	
	/**
	 * Method that checks if this contains given object.
	 * 
	 * @param value object that method is looking for
	 * @return false
	 */
	public boolean contains(Object value) {
		return false;
	}
	
	/**
	 * This method removes object that is equal to passed one.
	 * 
	 * @param value object that will be removed if it exists
	 * @return true if element is found and removed, false if not
	 */
	public boolean remove(Object value) {
		return false;
	}
	
	/**
	 * This method allocates new array with size equals to the size of this collections, fills it with collection content and
	 * returns the array.
	 * @return object array that contains all values of this collection and it's size is the same as number of objects
	 * @throws UnsupportedOperationException method is unimplemented
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Processes each value of this collection with the given processor.
	 * 
	 * @param processor that is used to process values of collection
	 */
	public void forEach(Processor processor) {}
	
	/**
	 * Method adds into the current collection all elements from the given collection. This other collection
	 * remains unchanged. A local processor class is implemented whose method process will add each item into the current collection
	 * by calling method add, and the forEach method is called on the other collection with this processor as argument.
	 * 
	 * @param other collection whose values will be added into this one
	 */
	public void addAll(Collection other) {
		class addProcessor extends Processor {
			
			public addProcessor() {
				super();
			}
			
			@Override
			public void process(Object value) {
				add(value);
			}
		}
		
		addProcessor processor = new addProcessor();
		
		other.forEach(processor);
	}
	
	/**
	 * Removes all elements from this collection.
	 */
	public void clear() {}
}
