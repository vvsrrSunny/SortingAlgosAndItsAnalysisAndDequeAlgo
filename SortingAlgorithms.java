public class SortingAlgorithms {
	/**
	 * Sorts the given array using the selection sort algorithm. This should modify
	 * the array in-place.
	 *
	 * @param input    An array of comparable objects.
	 * @param reversed If false, the array should be sorted ascending. Otherwise, it
	 *                 should be sorted descending.
	 * @requires input != null
	 * 
	 */
	static <T extends Comparable> void selectionSort(T[] input, boolean reversed) {

		int n = input.length;
// we are using same code because to reduce the number of comparations 

		// One by one move boundary of unsorted subarray

		for (int i = 0; i < n - 1; i++) {
			// finding the minimum element in the array
			int base = i;
			for (int j = i + 1; j < n; j++)
				if (input[j].compareTo(input[base]) < 0 && reversed == false)
					base = j;
				else if (input[j].compareTo(input[base]) > 0 && reversed == true)
					base = j;

			// Swap the found minimum element with the first
			T temperaryVariable = input[base];
			input[base] = input[i];
			input[i] = temperaryVariable;
		}

		
	}
	
	
	

	/**
	 * Sorts the given array using the insertion sort algorithm. This should modify
	 * the array in-place.
	 *
	 * @param input    An array of comparable objects.
	 * @param reversed If false, the array should be sorted ascending. Otherwise, it
	 *                 should be sorted descending.
	 * @requires input != null
	 */
	static <T extends Comparable> void insertionSort(T[] input, boolean reversed) {

		int n = input.length;

		for (int i = 1; i < n; ++i) {
			T currentValue = input[i];
			int j = i - 1;

			/*
			 * Move elements of input from index 0 to i-1, which are greater than
			 * currentValue to the one position ahead of their current position
			 */
			while (j >= 0 && input[j].compareTo(currentValue) > 0 && reversed == false) {
				input[j + 1] = input[j];
				j = j - 1;
			}
			/*
			 * Move elements of input from index 0 to i-1, which are lesser than
			 * currentValue to the one position ahead of their current position
			 */
			while (j >= 0 && input[j].compareTo(currentValue) < 0 && reversed == true) {
				input[j + 1] = input[j];
				j = j - 1;
			}
			input[j + 1] = currentValue;
		}

	}

	/**
	 * Sorts the given array using the merge sort algorithm. This should modify the
	 * array in-place.
	 * 
	 * @param input    An array of comparable objects.
	 * @param reversed If false, the array should be sorted ascending. Otherwise, it
	 *                 should be sorted descending.
	 * @requires input != null
	 */
	static <T extends Comparable> void mergeSort(T[] input, boolean reversed) {

		int size = input.length;
		// as we are not supposed to modify the standard methods, we are using an other
		// helper method
		mergeSortWithProperinputs(input, 0, size - 1, reversed);
	}

	private static <T extends Comparable> void mergeSortWithProperinputs(T[] input, int left, int right,
			boolean reversed) {
		// TODO Auto-generated method stub
		if (left < right) {

			// Same as (left + right) / 2, but avoids overflow
			// for large left and right
			int mid = left + (right - left) / 2;

			// Sort first and second halves by making method calls
			mergeSortWithProperinputs(input, left, mid, reversed);
			mergeSortWithProperinputs(input, mid + 1, right, reversed);

			merge(input, left, mid, right, reversed);
		}

	}

	private static <T extends Comparable> void merge(T[] input, int startIndexOfFirstArray, int mid, int end,
			boolean reversed) {
		// TODO Auto-generated method stub
		int startIndexOfSecondArray = mid + 1;

		// If the direct merge is already sorted

		if (input[mid].compareTo(input[startIndexOfSecondArray]) <= 0 && reversed == false) {
			return;
		} else if (input[mid].compareTo(input[startIndexOfSecondArray]) >= 0 && reversed == true) {
			return;
		}

		// Two pointers to maintain startIndexOfFirstArray
		// of both arrays to merge
		while (startIndexOfFirstArray <= mid && startIndexOfSecondArray <= end) {

			// If element 1 is in right place

			if (input[startIndexOfFirstArray].compareTo(input[startIndexOfSecondArray]) <= 0 && reversed == false) {
				startIndexOfFirstArray++;
			} else if (input[startIndexOfFirstArray].compareTo(input[startIndexOfSecondArray]) >= 0
					&& reversed == true) {
				startIndexOfFirstArray++;
			} else {
				T temp = input[startIndexOfSecondArray];
				int counterIndexValue = startIndexOfSecondArray;

				// Shift all the elements between element 1
				// element 2, right by 1.
				while (counterIndexValue != startIndexOfFirstArray) {
					input[counterIndexValue] = input[counterIndexValue - 1];
					counterIndexValue--;
				}
				input[startIndexOfFirstArray] = temp;
				// Update all the pointers. Here we are updating all of the pointers we are
				// considering
				startIndexOfFirstArray++;
				mid++;
				startIndexOfSecondArray++;
			}
		}
	}

	/**
	 * Sorts the given array using the quick sort algorithm. This should modify the
	 * array in-place.
	 * 
	 * You should use the value at the middle of the input array(i.e. floor(n/2)) as
	 * the pivot at each step.
	 * 
	 * @param input    An array of comparable objects.
	 * @param reversed If false, the array should be sorted ascending. Otherwise, it
	 *                 should be sorted descending.
	 * @requires input != null
	 */
	static <T extends Comparable> void quickSort(T[] input, boolean reversed) {
		int n = input.length;
		quickSortForProperInputs(input, 0, n-1, reversed);
	}

	private static <T extends Comparable> void quickSortForProperInputs(T[] input, int start, int end,
			boolean reversed) {
		// TODO Auto-generated method stub
		if (start < end) {
			int dividingIndex = quickSortinActionForPartion(input, start, end, reversed);

			// here we are dividing the array in to two halfs, form start of the array to mid
			quickSortForProperInputs(input, start, dividingIndex - 1, reversed);
			//here we are dividing the array in to two halfs, form mid of the array to last
			quickSortForProperInputs(input, dividingIndex + 1, end, reversed);
		}
	}

	private static <T extends Comparable> int quickSortinActionForPartion(T[] input, int start, int end,
			boolean reversed) {
		// TODO Auto-generated method stub
		// Picking the mid value of the array as pivot element.
		int xmid = (start+end)/2;
		T pivot = input[xmid];
	    int i = (start-1);
	 
	    for (int j = start; j < end; j++) {
	        
	    	if (input[j].compareTo(pivot) <= 0 && reversed == false) {
	            i++;
	 
	            T TemperaryVariableToRepace = input[i];
	            input[i] = input[j];
	            input[j] = TemperaryVariableToRepace;
	        }
	        else if (input[j].compareTo(pivot) >= 0 && reversed == true) {
	            i++;
	 
	            T TemperaryVariableToRepace = input[i];
	            input[i] = input[j];
	            input[j] = TemperaryVariableToRepace;
	        }
	    }
	 
	    T TemperaryVariableToRepace = input[i+1];
	    input[i+1] = input[end];
	    input[end] = TemperaryVariableToRepace;
	 
	    return i+1;
	}

}
