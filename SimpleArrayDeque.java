
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// memory complexity is O(n+1+1+1) = O(n+3) , n is the capacity 
public class SimpleArrayDeque<T> implements SimpleDeque<T> {

	T[] dequeCircularArray;
	int left;
	int right;
	int capacity;

	/**
	 * Constructs a new array based deque with limited capacity.
	 * 
	 * @param capacity the capacity
	 * @throws IllegalArgumentException if capacity <= 0
	 */
	
	//memory complexity O(1)   
	// time complexity O(5)
	public SimpleArrayDeque(int capacity) throws IllegalArgumentException {
		
		if (capacity <= 0)
			throw new IllegalArgumentException("capacity was provided zero");
		else {
			dequeCircularArray = (T[]) new Object[capacity];
			left = -1;
			right = 0;
			this.capacity = capacity;
		}

	}

	/**
	 * Constructs a new array based deque with limited capacity, and initially
	 * populates the deque with the elements of another SimpleDeque.
	 *
	 * @param otherDeque the other deque to copy elements from. otherDeque should be
	 *                   left intact.
	 * @param capacity   the capacity
	 * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is >
	 *                                  capacity
	 */
	
	//memory complexity O(1+n)  // n is the capacity of the other queue  
	// time complexity O(7+2n+1) // n is the size of the other queue
	public SimpleArrayDeque(int capacity, SimpleDeque<? extends T> otherDeque) throws IllegalArgumentException {
		if ((otherDeque.size() > capacity) || (capacity <= 0))
			throw new IllegalArgumentException("capacity <= 0 or size of otherDeque is > capacity");
		else {
			dequeCircularArray = (T[]) new Object[capacity];
			left = 0;
			right = 0;
			this.capacity = capacity;
			Iterator itr = otherDeque.iterator();
			while (itr.hasNext()) {
				dequeCircularArray[right] = (T) itr.next();
				right++;
				
			}
			right--;

		}

	}

	//memory complexity O(0)  this method do not need any space to store the variables  
	// time complexity O(2)
	@Override
	public boolean isEmpty() {
		if (left == -1)
			return true;
		else
			return false;
	}

	//memory complexity O(0) this method do not need any space to store the variables    
	// time complexity 0(4)  
	@Override
	public boolean isFull() {
		if ((left == 0 && right == capacity - 1) || (left == right + 1))
			return true;
		else
			return false;
	}

	//memory complexity O(0) // this method do not need any space to store the variables    
	// time complexity 0(4)  
	@Override
	public int size() {
		if (isEmpty())
			return 0;
		else if (isFull())
			return capacity;
		else if (right >= left)
			return (right - left) + 1;
		else
			return (capacity - left) + (right + 1);
	}

	
	//memory complexity O(1)  
	// time complexity O(6)  
	@Override
	public void pushLeft(T e) throws RuntimeException {

		if (isFull()) {
		throw new RuntimeException("Queue is full");
		}

		// If queue is initially empty
		if (left == -1) {
			left = 0;
			right = 0;
		}

		// left is at first location of the queue
		else if (left == 0)
			left = capacity - 1;

		else // decrement left end by '1'
			left = left - 1;

		// insert current element into Deque
		dequeCircularArray[left] = e;
	}

	
	//memory complexity O(1)  
	// time complexity 0(6) 
	@Override
	public void pushRight(T e) throws RuntimeException {
		if (isFull()) {
			throw new RuntimeException("Deque is full");
		}

		// If queue is initially empty
		if (left == -1) {
			left = 0;
			right = 0;
		}

		// right is at last position of queue
		else if (right == capacity - 1)
			right = 0;

		// increment right end by '1'
		else
			right++;

		// present variable is added to the deque
		dequeCircularArray[right] = e;
	}

	//memory complexity O(1)  
	// time complexity 0(2) 
	@Override
	public T peekLeft() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException ("Queue is empty");
		}
		return dequeCircularArray[left];
	}

	//memory complexity O(1)  
		// time complexity 0(2)
	@Override
	public T peekRight() throws NoSuchElementException {
		if (isEmpty() || right < 0) {
			
			throw new NoSuchElementException("Queue is empty");
		}
		return dequeCircularArray[right];
	}

	// time complexity 0(6)
	//memory complexity O(2)  
	@Override
	public T popLeft() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}

		int popingElement = left;
		// Deque is holding single variable
		if (left == right) {
			left = -1;
			right = -1;
		} else
		// back to initial position
		if (left == capacity - 1)
			left = 0;

		else // increment left by '1' to remove current
				// left value from Deque
			left = left + 1;

		return dequeCircularArray[popingElement];

	}

	// time complexity 0(6)
	//memory complexity O(2) 
	@Override
	public T popRight() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		int popingElement = right;
		// Deque is holding single variable
		if (left == right) {
			left = -1;
			right = -1;
		} else if (right == 0)
			right = capacity - 1;
		else
			right = right - 1;
		return dequeCircularArray[popingElement];
	}

	// time complexity O(10+n) where n is the size
	//memory complexity O(4+n+n+1)  where n is the capacity
	@Override
	public Iterator<T> iterator() {
		if (isEmpty())
			return null;
		int i = 0;
		int size = size();
		int tempL = left;
		int tempR = right;
		T[] tempArryForIterator = (T[]) new Object[size];
		if (left <= right) {
			while (tempL <= tempR) {
				tempArryForIterator[i] = dequeCircularArray[tempL];
				tempL++;
				i++;
			}

		} else {
			while (tempL <= (capacity - 1)) {
				tempArryForIterator[i] = dequeCircularArray[tempL];
				tempL++;
				i++;
			}
			tempL = 0;
			while (tempL <= tempR) {
				tempArryForIterator[i] = dequeCircularArray[tempL];
				tempL++;
				i++;
			}
		}
		Iterator<T> iterator = Arrays.stream(tempArryForIterator).iterator();

		return iterator;
	}

	// time complexity O(10+n) where n is the size
		//memory complexity O(4+n+n+1)  where n is the capacity
	@Override
	public Iterator<T> reverseIterator() {
		if (isEmpty())
			return null;
		int i = 0;
		int size = size();
		int tempL = left;
		int tempR = right;
		T[] tempArryForIterator = (T[]) new Object[size];
		if (left <= right) {
			while (tempL <= tempR) {
				tempArryForIterator[i] = dequeCircularArray[tempR];
				tempR--;
				i++;
			}

		} else {
			// iterating from right to index 0
			while (0 <= tempR) {
				tempArryForIterator[i] = dequeCircularArray[tempR];
				tempR--;
				i++;
			}
			// iterating for last element in the array to right
			tempR = capacity - 1;
			while (tempL <= (tempR)) {
				tempArryForIterator[i] = dequeCircularArray[tempR];
				tempR--;
				i++;
			}
		}
		Iterator<T> revIterator = Arrays.stream(tempArryForIterator).iterator();

		return revIterator;
	}
}
