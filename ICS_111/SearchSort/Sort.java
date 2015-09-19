/*
 * Sort.java
 *
 * John Rasay
 * ICS 111
 * Last modified: December 7, 2009
 */

 public class Sort
 {
   /**
     * This method repeatedly swaps adjacent elements and when no swaps is 
     * needed then the array is sorted.
     * @param a an array containing integer values.
     */
    public static void bubbleSort(int[] a)
    {
       for (int i = 0; i < a.length - 1; i++) {
          int j = i;
          while (j >= 0 && a[j] > a[j+1]) {
               int temp = a[j];
               a[j] = a[j+1];
               a[j+1] = temp;
               --j;
	  }
       }
    }
    
    /**
      * This method finds the smallest value in the array and swaps it 
      * with the value of the first cell in the array, then it finds the second
      * smallest and swaps it in the second cell, and repeats.
      * @param a the array containing integer values.
      */
    public static void selectionSort(int[] a)
    {
       for (int i = 0; i < a.length -2; ++i) {
         int location = i;
         for (int j = i + 1; j < a.length -1; ++j)
	    if (a[j] < a[location])
	      location = j;
       int temp = a[i];
       a[i] = a[location];
       a[location] = temp;
       }
    }
    
    /**
      * This is the insertion sort, it takes a value from the array
      * one by one and insert them in their correct position.
      * @param a the array containing integer values.
      */
    public static void insertionSort(int[] a)
    {
       for (int i = 1; i < a.length; ++i) {
	 int temp = a[i];
	 int j = i - 1;
         while (j >= 0 && a[j] > temp) {
	      a[j+1] = a[j];
	      j = j - 1;
	 }
	 a[j+1] = temp;
       }
    }
    
    /**
      * This method turns the entire array into a heap.
      * @param a the array containing integer values.
      * @param start the left most part of the array.
      * @param end the right most part of the array.
      */
     private static void heapify(int[] a, int start, int end)
     {
        int left = 2*start + 1;
        if (left <= end) {
           int right = left + 1;
           int larger = left;
           if (right <= end && a[right] >= a[left])
              larger = right;
           if (a[start] < a[larger]) {
              int temp = a[start];
              a[start] = a[larger];
              a[larger] = temp;
              heapify(a, larger, end);
           }
        }
     }
     
     /** 
       * This is the heap sort method.
       * @param a the array containing integer values.
       */
     public static void heapSort(int[] a)
     {
        for (int i = (a.length-2)/2; i >= 0; --i)
	  heapify(a, i, a.length - 1);
        int temp = a[0];
        a[0] = a[a.length-1];
        a[a.length-1] = temp;
        for (int i = a.length-2; i > 0; i--) {
	   heapify(a, 0, i);
	   temp = a[0];
	   a[0] = a[i];
	   a[i] = temp;
	}
     }
     
     /**
       * This method partitions the array.
       * @param a an array that contains integer values.
       * @param left the left most position in the array.
       * @param right the right most position in the array.
       */
     public static int partition(int[] a, int left, int right)
     {
        int pivot = a[left];
        int i = left + 1;
        int j = right;
        while (i < j) {
            while (a[i] <= pivot && i < j)
                i++;
            while (a[j] > pivot)
                j--;
            if (i < j)
               swap(a, i, j);
	}
        swap(a, left, j);
        return j;
     }


     /**
       * This method partitions the array by calling the partition method
       * and then sort the array recursively.
       * @param a the array containing integer values.
       * @param left the left most part of the array.
       * @param right the right most part of the array.
       */
     public static void quickSort(int [] a, int left, int right)
     {
	if (left < right) {
	  int middle = partition(a, left, right);
	  quickSort(a, left, middle - 1);
	  quickSort(a, middle + 1, right);
	}
     }
     
     /** 
       * This is the Quick Sort method.
       * @param a the array containing integer values.
       */
     public static void quickSort(int[]a)
     {
	quickSort(a, 0, a.length - 1);
     }
     
     /**
       * This is the Merge Sort method.
       * @param a the array containing integer values.
       */
     public static void mergeSort(int [] a)
     {
	mergeSort(a, 0, a.length -1);
     }
     
     /**
       * This is the Merge Sort, it divides the array and sort it
       * then it merge them together.
       * @param a the array containing integer values.
       * @param left the left most part of the array.
       * @param right the right most part of the array.
       */
     public static void mergeSort(int [] a,int left, int right)
     {
        if (left < right) {
           int middle = (left + right)/2;
           mergeSort(a, left, middle);
           mergeSort(a, middle + 1, right);
           merge(a, left, middle, right);
	}
     }

     /**
       * This method merges the array together.
       * @param a the array containing integer values.
       * @param left the left most part of the array.
       * @param middle the middle part of the array.
       * @param right the right most part of the array.
       */
     public static void merge(int [] a,int left, int middle, int right)
     {
	 int [] b = new int[a.length];
         for (int i = left; i <= right; i++)
           b[i] = a[i];
	 int i = left;
	 int j = middle + 1;
	 int k = left;
         while (i <= middle && j <= right) {
	     if (b[i] <= b[j])
                a[k++]=b[i++];
	     else
                 a[k++] = b[j++];
	     
	 }
         while (i <= middle) {
             a[k++]=b[i++];
	 }
     }    
     
     /**
       * This method swaps two values.
       * @param a an array that holds integer values.
       * @param i an index.
       * @param j the second index
       */
     public static void swap(int [] a, int i, int j)
     {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
     }
	    
 }
     
     
