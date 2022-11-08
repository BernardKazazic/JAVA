package hr.fer.oprpp1.custom.collections;

/**
 * This interface provides methods for accessing collection elements.
 */
public interface ElementsGetter{

	/**
	 * Method checks if there are any elements that were not already read.
	 *
	 *  @return true if there are unread elements, false if not
	 */
	boolean hasNextElement();

	/**
	 * This method is used for reading next unread element.
	 *
	 * @return next element of collection
	 */
	Object getNextElement();

	/**
	 * This method processes all unread elements in a collection with given processor.
	 *
	 * @param p processor that will process elements
	 */
	default void processRemaining(Processor p) {
		while(hasNextElement()) {
			p.process(getNextElement());
		}
	}
}
