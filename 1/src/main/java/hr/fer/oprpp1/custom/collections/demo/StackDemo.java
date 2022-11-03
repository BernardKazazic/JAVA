package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.EmptyStackException;
import hr.fer.oprpp1.custom.collections.ObjectStack;

/**
 * This class is used to demonstrate use of ObjectStack class.
 */
public class StackDemo {
	
	public static void main(String[] args) {
		String argumentLine = args[0];
		String[] arguments = argumentLine.split(" ");
		Integer operator1;
		Integer operator2;
		int result;

		try {
			ObjectStack stack = new ObjectStack();
			for(String s : arguments) {
				switch(s) {
				case "+" -> {
					operator2 = (Integer) stack.pop();
					operator1 = (Integer) stack.pop();
					result = operator1 + operator2;
					stack.push(result);
				}
				case "-" -> {
					operator2 = (Integer) stack.pop();
					operator1 = (Integer) stack.pop();
					result = operator1 - operator2;
					stack.push(result);
				}
				case "*" -> {
					operator2 = (Integer) stack.pop();
					operator1 = (Integer) stack.pop();
					result = operator1 * operator2;
					stack.push(result);
				}
				case "/" -> {
					operator2 = (Integer) stack.pop();
					operator1 = (Integer) stack.pop();
					result = operator1 / operator2;
					stack.push(result);
				}
				case "%" -> {
					operator2 = (Integer) stack.pop();
					operator1 = (Integer) stack.pop();
					result = operator1 % operator2;
					stack.push(result);
				}
				default -> {
					stack.push(Integer.parseInt(s));
				}
				}
			}
			if(stack.size() != 1) throw new Error("There is more than 1 value in stack after all operations are finished.");
			else System.out.println(stack.pop());
		}
		catch(Exception e) {
			if(e instanceof NumberFormatException) {
				System.out.println("Input was something that is not operator or a number");
				e.printStackTrace();
			}
			else if (e instanceof EmptyStackException) {
				System.out.println("There is not enough operators left to do the operation");
				e.printStackTrace();
			}
			else {
				e.printStackTrace();
			}
		}
		
	}
	
}
