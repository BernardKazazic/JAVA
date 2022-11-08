package hr.fer.oprpp1.custom.collections;

/**
 * Extends collection's functionality so the collection can act as a list of elements.
 */
public interface List extends Collection {

	/**
	 * This method is used for getting an element from a specific index.
	 *
	 * @param index position of element we want in a list
	 * @return element at a position of index value
	 */
	Object get(int index);

	/**
	 * This method is used for inserting a value to a specific position in a list.
	 *
	 * @param value value that is inserted
	 * @param position position of the value that is inserted
	 */
	void insert(Object value, int position);

	/**
	 * This method is used for getting position of an object in a list.
	 *
	 * @param value value whose position is looked for
	 * @return position of the value in a list
	 */
	int indexOf(Object value);

	/**
	 * This method is used for removing an object at a specific position.
	 *
	 * @param index position of the value in a list that will be removed
	 */
	void remove(int index);

}
