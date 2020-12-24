import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


//memory complexity is O(n + 2) + 2 * big O of node class, n is the size of deque
	// memory complexity of node class O(1+1+1) = O(3)
	//memory complexity is O(n+2+ 2*3) = O(n+8) = O(n)
public class SimpleLinkedDeque<T> implements SimpleDeque<T> {
	
	
	SimpleLinkedDeque<T> simplelinkedDequeObject;

	private class Node<T> {
		// data variable is data
		protected T data;
		// right is right pointer linked to next node and left is the left pointer
		// pointing to the previous node
		protected Node right, left;

		// initilising all the parameters of node
		public Node() {
			right = null;
			left = null;
			data = null;
		}

		// initilising all the parameters of node 
		public Node(T d, Node r, Node l) {
			data = d;
			right = r;
			left = l;
		}

		// setters and getters of class parameters
		public void setRight(Node r) {
			right = r;
		}

		public void setLeft(Node l) {
			left = l;
		}

		public Node getRight() {
			return right;
		}

		public Node getLeft() {
			return left;
		}

		public void setData(T d) {
			data = d;
		}

		public T getData() {
			return data;
		}
	}

	// start is the first node in the list
	protected Node start;
	// last node in the list
	protected Node end;
	// side of the list is maintained
	public int size;
	// capacity of the list is stored
	public int capacity = -1;

	/**
	 * Constructs a new linked list based deque with unlimited capacity.
	 */
	//memory complexity O(0)  this method do not need any space to store the variables  
		// time complexity O(1)
	public SimpleLinkedDeque() {
		start = null;
		end = null;
		size = 0;
	}

	/**
	 * Constructs a new linked list based deque with limited capacity.
	 *
	 * @param capacity the capacity
	 * @throws IllegalArgumentException if capacity <= 0
	 */
	//memory complexity O(1)  
	// time complexity O(5)
	public SimpleLinkedDeque(int capacity) throws IllegalArgumentException {
		if (capacity > 0) {
			start = null;
			end = null;
			size = 0;
			this.capacity = capacity;
		} else
			throw new IllegalArgumentException("capacity <= 0");
	}

	/**
	 * Constructs a new linked list based deque with unlimited capacity, and
	 * initially populates the deque with the elements of another SimpleDeque.
	 *
	 * @param otherDeque the other deque to copy elements from. otherDeque should be
	 *                   left intact.
	 * @requires otherDeque != null
	 */
	
	//memory complexity O(n)  
	// time complexity O(5)
	public SimpleLinkedDeque(SimpleDeque<? extends T> otherDeque) {
		simplelinkedDequeObject = (SimpleLinkedDeque<T>) otherDeque;
		start = simplelinkedDequeObject.start;
		end = simplelinkedDequeObject.end;
		this.size = otherDeque.size();
		simplelinkedDequeObject = null;

	}

	/**
	 * Constructs a new linked list based deque with limited capacity, and initially
	 * populates the deque with the elements of another SimpleDeque.
	 *
	 * @param otherDeque the other deque to copy elements from. otherDeque should be
	 *                   left intact.
	 * @param capacity   the capacity
	 * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is >
	 *                                  capacity
	 */
	//memory complexity O(n+1)  
	// time complexity O(14)
	public SimpleLinkedDeque(int capacity, SimpleDeque<? extends T> otherDeque) throws IllegalArgumentException {
		if ((otherDeque.size() > capacity) || (capacity <= 0))
			throw new IllegalArgumentException("capacity <= 0 or size of otherDeque is > capacity");
		else {
			// this = otherDeque;
			this.capacity = capacity;
			simplelinkedDequeObject = (SimpleLinkedDeque<T>) otherDeque;
			start = simplelinkedDequeObject.start;
			end = simplelinkedDequeObject.end;
			this.size = otherDeque.size();
			simplelinkedDequeObject = null;

		}

	}
	//memory complexity O(0)  
	// time complexity O(2)
	@Override
	public boolean isEmpty() {
		return start == null;
	}

	//memory complexity O(0)  
	// time complexity O(3)
	@Override
	public boolean isFull() {
		if (capacity == -1)
			return false;
		else if (capacity == size)
			return true;
		else
			return false;

	}

	
	//memory complexity O(0)  
	// time complexity O(1)
	@Override
	public int size() {
		return size;
	}

	//memory complexity O(3)  
	// time complexity O(8)
	@Override
	public void pushLeft(T e) throws RuntimeException {
		if (capacity > size || capacity == -1) {

			Node nptr = new Node(e, null, null);
			if (start == null) {
				start = nptr;
				end = start;
			} else {
				start.setLeft(nptr);
				nptr.setRight(start);
				start = nptr;
			}
			size++;
		} else
			throw new RuntimeException("its running at maximum memory capacity");
	}

	//memory complexity O(3)  
	// time complexity O(8)
	@Override
	public void pushRight(T e) throws RuntimeException {
		if (capacity > size || capacity == -1) {
			Node nptr = new Node(e, null, null);
			if (start == null) {
				start = nptr;
				end = start;
			} else {
				nptr.setLeft(end);
				end.setRight(nptr);
				end = nptr;
			}
			size++;
		} else
			throw new RuntimeException("its running at maximum memory capacity");
	}

	//memory complexity O(1)  
	// time complexity O(3)
	@Override
	public T peekLeft() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("its empty, could not return elements");
		} else
			return (T) start.getData();
	}

	//memory complexity O(1)  
	// time complexity O(3)
	@Override
	public T peekRight() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("its empty, could not return elements");
		} else
			return (T) end.getData();
	}

	
	//memory complexity O(1)  
	// time complexity O(8)
	@Override
	public T popLeft() throws NoSuchElementException {

		if (isEmpty()) {
			throw new NoSuchElementException("its empty, could not return elements");
		} else {
			T tempData;
			if (size == 1) {
				tempData = (T) start.getData();
				start = null;
				end = null;
				size = 0;
				return tempData;
			}
			tempData = (T) start.getData();
			start = start.getRight();
			start.setLeft(null);
			size--;
			return tempData;
		}

	}

	//memory complexity O(1)  
	// time complexity O(8)
	@Override
	public T popRight() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("its empty, could not return elements");
		} else {
			T tempData;
			if (size == 1) {
				tempData = (T) end.getData();
				start = null;
				end = null;
				size = 0;
				return tempData;
			}
			tempData = (T) end.getData();
			end = end.getLeft();
			end.setRight(null);
			size--;
			return tempData;
		}
	}

	//memory complexity O(n + n)   n is the size 
	// time complexity O(10+n+2)
	@Override
	public Iterator<T> iterator() {
		// return new Iterator<T>(start);
		T[] tempArryForIterator = (T[]) new Object[size];

		// System.out.print("\nDoubly Linked List = ");
		if (size == 0) {
			// System.out.print("empty\n");
			return null;
		} else if (size == 1) {
			tempArryForIterator[0] = (T) start.getData();

		} else {
			Node tempStart = start;
			tempArryForIterator[0] = (T) start.getData();
			tempStart = start.getRight();
			int i = 1;
			while (tempStart.getRight() != null) {
				tempArryForIterator[i] = (T) tempStart.getData();
				i++;
				tempStart = tempStart.getRight();
			}
			tempArryForIterator[i] = (T) tempStart.getData();

		}
		Iterator<T> iterator = Arrays.stream(tempArryForIterator).iterator();
		return iterator;
	}

	//memory complexity O(n+ 3 + n) n is size  
	// time complexity O(10+ n + 1 + 2)
	@Override
	public Iterator<T> reverseIterator() {
		T[] tempArryForIterator = (T[]) new Object[size];

		// System.out.print("\nDoubly Linked List = ");
		if (size == 0) {
			// System.out.print("empty\n");
			return null;
		} else if (size == 1) {
			tempArryForIterator[0] = (T) start.getData();
		} else {
			Node tempStart = start;
			int i = size - 1;
			tempArryForIterator[i] = (T) start.getData();
			tempStart = start.getRight();
			i--;
			while (tempStart.getRight() != null) {
				tempArryForIterator[i] = (T) tempStart.getData();
				i--;
				tempStart = tempStart.getRight();
			}
			tempArryForIterator[i] = (T) tempStart.getData();

		}
		Iterator<T> revIterator = Arrays.stream(tempArryForIterator).iterator();
		return revIterator;
	}
}
