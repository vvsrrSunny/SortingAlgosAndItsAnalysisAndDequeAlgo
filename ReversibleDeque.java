import java.util.Iterator;
import java.util.NoSuchElementException;



// memory complexity is O(n + 1) n is the size of the deque
public class ReversibleDeque<T> implements SimpleDeque<T> {
    
	SimpleLinkedDeque<T> simpleLinkedDequeObject;
	Boolean reverse = false;

	/**
	 * Constructs a new reversible deque, using the given data deque to store
	 * elements. The data deque must not be used externally once this
	 * ReversibleDeque is created.
	 * 
	 * @param data a deque to store elements in.
	 */
	//memory complexity O(n)  
	// time complexity O(1)
	public ReversibleDeque(SimpleDeque<T> data) {
		simpleLinkedDequeObject = (SimpleLinkedDeque<T>) data;
	}
	//memory complexity O(0)  
	// time complexity O(2)
	public void reverse() {
//		simpleLinkedDequeObject.tempNode = simpleLinkedDequeObject.start;
//		simpleLinkedDequeObject.start = simpleLinkedDequeObject.end;
//		simpleLinkedDequeObject.end = simpleLinkedDequeObject.tempNode;
		if (reverse == false)
			reverse = true;
		else
			reverse = false;
	}
	//memory complexity O(0)  
	// time complexity O(2)
	@Override
	public int size() {
		return simpleLinkedDequeObject.size();
	}

	//memory complexity O(0)  
	// time complexity O(3)
	@Override
	public boolean isEmpty() {
		return simpleLinkedDequeObject.isEmpty();
	}

	//memory complexity O(0)  
	// time complexity O(4)
	@Override
	public boolean isFull() {
		return simpleLinkedDequeObject.isFull();
	}

	//memory complexity O(3)  
	// time complexity O(1+(8))
	@Override
	public void pushLeft(T e) throws RuntimeException {
		if (reverse == false)
			simpleLinkedDequeObject.pushLeft(e);
		else
			simpleLinkedDequeObject.pushRight(e);
	}

	//memory complexity O(3)  
	// time complexity O(1+(8))
	@Override
	public void pushRight(T e) throws RuntimeException {
		if (reverse == false)
			simpleLinkedDequeObject.pushRight(e);
		else
			simpleLinkedDequeObject.pushLeft(e);
	}

	//memory complexity O(1)  
	// time complexity O(1+(3))
	@Override
	public T peekLeft() throws NoSuchElementException {
		if (reverse == false)
			return simpleLinkedDequeObject.peekLeft();
		else
			return simpleLinkedDequeObject.peekRight();
	}

	//memory complexity O(1)  
	// time complexity O(1+(3))
	@Override
	public T peekRight() throws NoSuchElementException {
		if (reverse == false)
			return simpleLinkedDequeObject.peekRight();
		else
			return simpleLinkedDequeObject.peekLeft();
	}

	
	//memory complexity O(1)  
	// time complexity O(1+(8))
	@Override
	public T popLeft() throws NoSuchElementException {
		if(reverse == false)
		return simpleLinkedDequeObject.popLeft();
		else
			return simpleLinkedDequeObject.popRight();
	}
	//memory complexity O(1)  
	// time complexity O(1+(8))
	@Override
	public T popRight() throws NoSuchElementException {
		if(reverse == false)
		return simpleLinkedDequeObject.popRight();
		else
			return simpleLinkedDequeObject.popLeft();
	}
	
	//memory complexity O(n + n)   n is the size 
	// time complexity O(1+(10+n+2))
	@Override
	public Iterator<T> iterator() {
		if(reverse = false)
		return simpleLinkedDequeObject.iterator();
		else 
			return simpleLinkedDequeObject.reverseIterator();
	}

	//memory complexity O(n + n)   n is the size 
	// time complexity O(1+(10+n+2))
	@Override
	public Iterator<T> reverseIterator() {
		if(reverse = false)
			return simpleLinkedDequeObject.reverseIterator();
			else 
				return simpleLinkedDequeObject.iterator();
	}
}
