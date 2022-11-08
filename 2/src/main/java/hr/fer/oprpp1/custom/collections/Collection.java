package hr.fer.oprpp1.custom.collections;

/**
 * Interface which offers methods for working with a collection of objects.
 */
public interface Collection {

	/**
	 * This method gives the number of currently stored objects in the collection.
	 *
	 * @return number of currently stored objects
	 */
	int size();

	/**
	 * This method adds given object to the collection.
	 *
	 * @param value object that will be added into collection
	 */
	void add(Object value);

	/**
	 * Method that checks if a collection contains given object.
	 *
	 * @param value object that method is looking for
	 * @return false
	 */
	boolean contains(Object value);

	/**
	 * This method removes object that is equal to passed one.
	 *
	 * @param value object that will be removed if it exists
	 * @return true if element is found and removed, false if not
	 */
	boolean remove(Object value);

	/**
	 * Removes all elements from the collection.
	 */
	void clear();

	/**
	 * This method allocates a new array with size equals to the size of the collection, fills it with collection content and
	 * returns the array.
	 * @return object array that contains all values of the collection, and it's size is the same as number of objects
	 */
	Object[] toArray();

	/**
	 * This method creates ElementsGetter for a collection that called this function.
	 *
	 * @return ElementsGetter that can read elements of the collection
	 */
	ElementsGetter createElementsGetter();

	/**
	 * Method adds all elements into the collection from a given collection. Other collection
	 * remains unchanged. A local processor class is implemented whose method process will add each item into the current collection
	 * by calling method add, and the forEach method is called on the other collection with this processor as argument.
	 *
	 * @param other collection whose values will be added into the collection that called the method
	 */
	default void addAll(Collection other) {
		class addProcessor implements Processor {
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
	 * This method is used for checking if collection is empty. It checks the size of collection and
	 * uses that to determine if it is empty.
	 *
	 * @return true if this is empty or false if it isn't
	 */
	default boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * This method is used for adding all elements from another collection that
	 * meet testers conditions in the collection that called the method.
	 *
	 * @param col collection whose elements will be added
	 * @param tester tester that tests if elements from other collection are acceptable
	 */
	default void addAllSatisfying(Collection col, Tester tester) {
		ElementsGetter getter = col.createElementsGetter();
		while(getter.hasNextElement()) {
			Object element = getter.getNextElement();
			if(tester.test(element)) 
				this.add(element);
		}
	}

	/**
	 * Processes each value of the collection with the given processor.
	 *
	 * @param processor that is used to process values of collection
	 */
	default void forEach(Processor processor) {
		ElementsGetter getter = this.createElementsGetter();
		while(getter.hasNextElement()) {
			processor.process(getter.getNextElement());
		}
	}
}
