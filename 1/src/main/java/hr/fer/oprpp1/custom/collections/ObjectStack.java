package hr.fer.oprpp1.custom.collections;

/**
 * Adaptor class that adapts interface of ArrayIndexedCollection for use as object stack.
 */
public class ObjectStack {
	
	private ArrayIndexedCollection arrayCollection;
	
	public ObjectStack() {
		this.arrayCollection = new ArrayIndexedCollection();
	}
	
	/**
	 * This method checks if stack is empty. It checks the size of arrayCollection and
	 * uses that to determine if it is empty.
	 * 
	 * @return true if this is empty or false if it isn't.
	 */
	public boolean isEmpty() {
		return this.arrayCollection.isEmpty();
	}
	
	/**
	 * Returns the number of currently stored objects in stack.
	 * 
	 * @return parameter size of arrayCollection
	 */
	public int size() {
		return this.arrayCollection.size();
	}
	
	/**
	 * Pushes the given object on top of the stack. It uses ArrayIndexedCollection.add() to execute its function.
	 * 
	 * @param value object that is added to the end of the collection elements array
	 * @exception NullPointerException when parameter value is null
	 */
	public void push(Object value) {
		this.arrayCollection.add(value);
	}
	
	/**
	 * Returns value on top of the stack and removes it from stack. Uses ArrayIndexedCollection.get() and ArrayIndexedCollection.remove().
	 *
	 * @return value on top of the stack
	 * @exception EmptyStackException if the stack is empty
	 */
	public Object pop() {
		if(this.isEmpty()) throw new EmptyStackException();
		Object temp = this.arrayCollection.get(this.size() - 1);
		this.arrayCollection.remove(this.size() - 1);
		return temp;
	}
	
	/**
	 * Returns value on top of the stack and does not remove it. Uses ArrayIndexedCollection.get().
	 *
	 * @return value on top of the stack
	 * @exception EmptyStackException if the stack is empty
	 */
	public Object peek() {
		if(this.isEmpty()) throw new EmptyStackException();
		return this.arrayCollection.get(this.size() - 1);
	}
	
	/**
	 * Removes all values from stack. Uses ArrayIndexedCollection.clear().
	 */
	public void clear() {
		this.arrayCollection.clear();
	}
}
