package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.LinkedListIndexedCollection;

public class LinkedListTester {

	public LinkedListTester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.remove(4);
		
		list.forEach((Object value) -> System.out.println(value));
		
	}

}
