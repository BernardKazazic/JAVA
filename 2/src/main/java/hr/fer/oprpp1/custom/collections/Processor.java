package hr.fer.oprpp1.custom.collections;

/**
 * This interface provides a method capable of performing some operation on the passed object
 */
public interface Processor {
	
	/**
	 * Method that is used to process passed object.
	 * 
	 * @param value object that will be processed in this method
	 */
	void process(Object value);
}
