package hr.fer.oprpp1.custom.collections;

/**
 * This interface provides method for testing if an object is acceptable or not.
 */
public interface Tester {

	/**
	 * Method is used for testing an object.
	 *
	 * @param obj object that is tested
	 * @return true if the object passed the test, false if not
	 */
	boolean test(Object obj);
}
