import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 *
 *Last Updated: 10/20/2012
 */
public class MainSortTest {

	public static void main(String[] args) {
		List<String> wordList = new ArrayList<String>();
		TablePrinter table = new TablePrinter(" Method   ","  Time  ","  First Key "," Last Key ");
		long start = 0;
		long end = 0;
		long result = 0;
		
		//Reads in a file and store it in an ArrayList
		try {
			File myFile = new File(args[0]);
			FileReader fileReader = new FileReader(myFile);
			
			BufferedReader reader = new BufferedReader(fileReader);
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				wordList.add(line.trim());
			}
			reader.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// Copy the contents of the list to an array
		String[] toSortArray = wordList.toArray(new String[wordList.size()]);
		System.out.println("Sort Test: " + toSortArray.length);
		
		//Testing the time for Heap Sort.
		start = System.nanoTime();
		heapSort(toSortArray);
		end = System.nanoTime();
		result = end - start;
		table.addRow("Heap Sort ", result +"ns", "" + toSortArray[0], "" + toSortArray[toSortArray.length - 1]);
		
		//Refill The array
		toSortArray = wordList.toArray(new String[wordList.size()]);
		//Testing the time for Insertion Sort.
		start = System.nanoTime();
		insertionSort(toSortArray);
		end = System.nanoTime();
		result = end - start;
		table.addRow("Insertion Sort ", result +"ns", "" + toSortArray[0], "" + toSortArray[toSortArray.length - 1]);
		
		
		
		//Refill The array
		toSortArray = wordList.toArray(new String[wordList.size()]);
		//Testing the time for Merge Sort.
		start = System.nanoTime();
		mergeSort(toSortArray);
		end = System.nanoTime();
		result = end - start;
		
		table.addRow("Merge Sort ", result +"ns", "" + toSortArray[0], "" + toSortArray[toSortArray.length - 1]);
		
		//Refill The array
		toSortArray = wordList.toArray(new String[wordList.size()]);
		//Testing the time for Quick Sort.
		start = System.nanoTime();
		quickSort(toSortArray);
		end = System.nanoTime();
		result = end - start;
		table.addRow("Quick Sort ", result +"ns", "" + toSortArray[0], "" + toSortArray[toSortArray.length - 1]);
		
		//Prints the table
		table.print();

	}//End of main
	

													/*************************************************
													 ** 									        **
													 ** 			   Sorting Methods		        **
													 ** 											**
													 *************************************************/
	

/*************************************************
 * 												 *
 * 			 	   The HeapSort					 *
 * 												 *
 *************************************************/
	/**
	 *  This is the heapSort.
	 * 
	 * @param a is the array to be sorted.
	 */
	public static <T extends Comparable<T>> void heapSort(T[] a) {
		
		for (int i = (a.length - 2)/2; i >= 0; --i)
			heapify(a, i, a.length -1);
		swap(a, 0, a.length - 1);
		for (int i = a.length - 2; i > 0; i--) {
			heapify(a,0,i);
			swap(a,0,i);
		}
	}
	
	/*
	 * Private method of the heapSort.
	 */
	private static <T extends Comparable<T>> void heapify(T[] a,int start, int end) {
		int left = 2 * start+ 1;
		if (left <= end) {
			int right = left + 1;
			int larger = left;
			if (right <= end && a[right].compareTo(a[left]) >= 0)
				larger = right;
			if(a[start].compareTo(a[larger]) < 0) {
				swap(a,start,larger);
				heapify(a, larger, end);
			}
		}
		
	}

/*****************************************
 *										 *
 *  			The InsertionSort		 *
 * 										 *
 *****************************************/
	
	/**
	 *  This is the insertion sort.
	 * 
	 * @param a is the array to be sorted.
	 */
	public static <T extends Comparable<T>> void insertionSort(T a[]) {
		
		for (int i = 1; i < a.length; i++) {
			T temp = a[i];
			int j = i - 1;
			while (j >= 0 && a[j].compareTo(temp) > 0) {
				a[j+1] = a[j];
				j = j - 1;
			}
			a[j+1] = temp;
		}
	}
	
/***************************************
 * 									   *
 * 				The MergeSort          *
 * 								       *
 ***************************************/
	
	/**
	 *  This is the mergeSort.
	 * 
	 * @param a is the array to be sorted.
	 */
	public static <T extends Comparable<T>> void mergeSort(T[] a) {
		
		T[] temp = (T[]) new Comparable[a.length];
		mergeSort(a, temp, 0, a.length - 1);
	}
	/*
	 *  Private method of the mergeSort
	 */
	private static <T extends Comparable<T>> void mergeSort(T[] a, T[] temp, int left, int right) {
		
		if (left < right) {
			int middle = (left + right)/2;
			mergeSort(a, temp, left, middle);
			mergeSort(a, temp, middle + 1, right);
			merge(a, temp, left, middle, right);
		}
	}
	
	/*
	 * Private method of the mergeSort
	 */
	private static <T extends Comparable<T>> void merge(T[] a, T[] temp, int left, int middle, int right) {
		int k = 0;
		int i = left;
		int j = middle + 1;
		while (i <= middle && j <= right) {
			if (a[i].compareTo(a[j]) <= 0)
				temp[k++] = a[i++];
			else
				temp[k++] = a[j++];
		}
		while (j <= right)
			temp[k++] = a[j++];
		while (i <= middle)
			temp[k++] = a[i++];
		for (k = 0, i = left; i <= right; i++, k++)
			a[i] = temp[k];
	}
	
	
/********************************************************************************************
 * 									 														*
 * 				The QuickSort        														*
 * 																							*
 *              																			*
 * @author     Original Author     http://www.algolist.net/Algorithms/Sorting/Quicksort     *                            
 * @author     Derivative Author   John Rasay                                 				*
 *                                   														*
 ********************************************************************************************/
	
	/**
	 *  This is the quickSort.
	 *  
	 * @param a is the array to be sorted.
	 */
	public static <T extends Comparable<T>> void quickSort(T a[]) {
		
		quickSort(a, 0, a.length - 1);
	}
	
	/*
	 * Private method of the quickSort, which takes the array, and the left and right position fo the array 
	 */
	private static <T extends Comparable<T>> void quickSort(T a[], int left, int right) {
	      int middle = partition(a, left, right);
	      if (left < middle - 1)
	            quickSort(a, left, middle - 1);
	      if (middle < right)
	            quickSort(a, middle, right);
	}
	
	/*
	 * Private method of the quickSort, partitions the array into two array.
	 */
	private static <T extends Comparable<T>> int partition(T a[], int left, int right)
	{
	      int i = left;
	      int j = right;
	      T pivot = a[(left + right) / 2];
	      while (i <= j) {
	            while (a[i].compareTo(pivot) < 0)
	                  i++;
	            while (a[j].compareTo(pivot) > 0)
	                  j--;
	            if (i <= j) {
	                  swap(a,i,j);
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	
	 /**
	  * This is for swapping elements in an array.
	  * 
	  * @param a The array to be work with
	  * @param i The position of the array
	  * @param j The position of the array
	  */
	private static <T extends Comparable<T>> void swap(T[] a, int i, int j)	{
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}//End of class
