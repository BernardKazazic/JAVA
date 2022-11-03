package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListIndexedCollectionTest {

	@Test
	void testSize() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		assertEquals(3, a1.size(), "Size should be 3.");
	}

	@Test
	void testAdd() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		Object[] a2 = new Object[] {1, 2, 3};
		a1.add(1);
		a1.add(2);
		a1.add(3);
		for(int i = 0; i < a1.size(); i++) {
			assertEquals(true, a1.get(i).equals(a2[i]), "All elements should be equal.");
		}
	}
	
	@Test
	void testAddNull() {
		assertThrows(NullPointerException.class, () -> (new ArrayIndexedCollection()).add(null), "It should throw NullPointerException.");
	}

	@Test
	void testContains() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		assertEquals(true, a1.contains(2), ".contains() should find value 2.");
	}
	
	@Test
	void testContainsNull() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		assertEquals(false, a1.contains(null), ".contains() should return false.");
	}

	@Test
	void testRemoveObject() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		LinkedListIndexedCollection a2 = new LinkedListIndexedCollection();
		a1.add("a");
		a1.add("b");
		a1.add("c");
		a2.add("a");
		a2.add("c");
		a1.remove("b");
		for(int i = 0; i < a1.size(); i++) {
			assertEquals(true, a1.get(i).equals(a2.get(i)), "It should be equal.");
		}
	}

	@Test
	void testToArray() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		Object[] a2 = new Object[] {1, 2, 3};
		Object[] other;
		a1.add(1);
		a1.add(2);
		a1.add(3);
		other = a1.toArray();
		for(int i = 0; i < a1.size(); i++) {
			assertEquals(true, other[i].equals(a2[i]), "All elements should be equal.");
		}
	}

	@Test
	void testForEach() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		Object[] a2 = new Object[] {2, 4, 6};
		Object[] a3 = new Object[3];
		
		class MulitplyProcessor extends Processor {
			
			int i = 0;
			
			@Override
			public void process(Object value) {
				a3[i] = (Integer) value * 2;
				i++;
			}
		}
		
		a1.add(1);
		a1.add(2);
		a1.add(3);
		a1.forEach(new MulitplyProcessor());
		for(int i = 0; i < a1.size(); i++) {
			assertEquals(true, a3[i].equals(a2[i]), "All elements should be equal.");
		}
	}

	@Test
	void testClear() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		
		a1.clear();
		a1.add(5);
		assertEquals(1, a1.size(), "Should be equal.");
	}
	

	@Test
	void testGet() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		assertEquals(2, a1.get(1), "It should be equal.");
	}
	
	@Test
	void testGetBounds() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		assertThrows(IndexOutOfBoundsException.class, () -> a1.get(5), "Index is out of bounds.");
	}

	@Test
	void testInsert() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		Object[] a2 = new Object[] {"a", "d", "b", "c"};
		a1.add("a");
		a1.add("b");
		a1.add("c");
		a1.insert("d", 1);
		for(int i = 0; i < a1.size(); i++) {
			assertEquals(true, a1.get(i).equals(a2[i]), "It should be equal.");
		}
	}
	
	@Test
	void testIndexOfTrue() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add("a");
		a1.add("b");
		a1.add("c");
		assertEquals(1, a1.indexOf("b"), "B should be on index 1");
	}
	
	@Test
	void testIndexOfFalse() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		a1.add("a");
		a1.add("b");
		a1.add("c");
		assertEquals(-1, a1.indexOf("d"), "D should not be found");
	}

	@Test
	void testRemoveInt() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		LinkedListIndexedCollection a2 = new LinkedListIndexedCollection();
		a1.add("a");
		a1.add("b");
		a1.add("c");
		a2.add("a");
		a2.add("c");
		a1.remove(2);
		for(int i = 0; i < a1.size(); i++) {
			assertEquals(true, a1.get(i).equals(a2.get(i)), "It should be equal.");
		}
	}

	@Test
	void testIsEmpty() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		assertEquals(true, a1.isEmpty(), "It should be empty");
	}

	@Test
	void testAddAll() {
		LinkedListIndexedCollection a1 = new LinkedListIndexedCollection();
		LinkedListIndexedCollection a2 = new LinkedListIndexedCollection();
		a2.add(1);
		a2.add(5);
		a2.add(10);
		a1.addAll(a2);
		for(int i = 0; i < a1.size(); i++) {
			assertEquals(true, a1.get(i).equals(a2.get(i)), "It should be equal.");
		}
	}

}
