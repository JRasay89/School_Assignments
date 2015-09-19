/*
 * SearchSort.java
 *
 * John Rasay
 * ICS 111
 * Last modified: December 9, 2009
 */
 
import java.util.*;
 
public class SearchSort
{
   private static final int ROWS = 40;
   
   public static boolean sorted = false;
   
   public static void main(String[] args)
   {
      System.out.print("This program will create an array of a size with a ");
      System.out.print("minimum and maximum value of your choice if not then ");
      System.out.print("array with a size of 100 with a minimum of -1000 and ");
      System.out.print("a maximum of 1000 random integers will be created. ");
      System.out.print("Once the array has been created and filled with ");
      System.out.print("random integers you can display, sort, or search ");
      System.out.println("the contents of the array.");
      Scanner keyboard = new Scanner(System.in);
      int[] a = new int[100];
      int[] b = new int[a.length];
      int min = -1000;
      int max = 1000;
      for (int i = 0; i < a.length; i++)
        a[i] = (int) ((max - min + 1)*Math.random()) + min;
      int choice = 0;
      while (choice != 5) {
          System.out.println("What would you like to do? ");
	  System.out.println("1 - Create a new array ");
	  System.out.println("2 - Search the array ");
	  System.out.println("3 - Sort the array");
	  System.out.println("4 - Display the array");
	  System.out.println("5 - Quit ");
	  System.out.print("Enter the appropriate number to pick a task:  ");
	  choice = keyboard.nextInt();
	  keyboard.nextLine();
	  while (choice < 1 || choice > 5) {
		System.out.print("Invalid choice, please enter the correct ");
		System.out.print("number:  ");
		choice = keyboard.nextInt();
		keyboard.nextLine();
	  }
	  if (choice == 1)
	     a = getArray();
          else if (choice == 2) {
	     System.out.print("Enter a number to be searched ");
	     System.out.print("in the array:  ");
	     int number = keyboard.nextInt();
	     keyboard.nextLine();
	     if (!sorted) {
		int result = Searches.linearSearch(a,number);
	        if (result == -1)
		   System.out.println("A linear search was used and " +
		                      number + " was not found in the array");
		else
	           System.out.println(number + " was found in " 
		                      + result + "\n");
	     }
	     if (sorted) {
		System.out.print("Would you like to search the original or ");
	        System.out.println("the sorted array?  ");
		System.out.println("1 - Original \n2 - Sorted");
		System.out.print("Enter number here to pick which array "
		                  + "you would like to search:  ");
		int arraychoice = keyboard.nextInt();
		keyboard.nextLine();
		while (arraychoice < 1 || arraychoice > 2) {
		      System.out.print("Invalid number, enter again:  ");
		      arraychoice = keyboard.nextInt();
		      keyboard.nextLine();
		}
		if(arraychoice == 1) {
		   int result = Searches.linearSearch(a,number);
		   if (result == -1) 
		      System.out.println("A linear search was use and " +
		                         number + " was not found "
					 + "in the array\n");
		   else
		      System.out.println(number + " was found in " + result);
		}
	        else {
		   System.out.print("Would you like to search with a linear ");
		   System.out.println("or a binary search?");
		   System.out.println("1 Linear Search \n2 Binary Search");
		   System.out.print("Enter a number to pick which searching "
		                    + "method you would like to use:  ");
		   int searchchoice = keyboard.nextInt();
		   keyboard.nextLine();
		   while (searchchoice < 1 || searchchoice > 2) {
		       System.out.print("Invalid number, enter again:  ");
		       searchchoice = keyboard.nextInt();
		       keyboard.nextLine();
		   }
		   if (searchchoice == 1) {
		      int result = Searches.linearSearch(a,number);
		      if (result == -1) {
		        System.out.println(" A linear search was use and " 
		                           + number + " was not found "
					   + "in the array\n");
		      }
		      else
		        System.out.println("A linear search was use and "
		                          + number + " was found in " 
					  + result + "\n");
		   }
		   else if (searchchoice == 2) {
		      int result = Searches.binarySearch(b,number);
		      if (result == -1)
		         System.out.print("A binary search was use and "
			                  + number + " was not found in "
					  + "the array\n");
		      else
		         System.out.println("A binary search was use and " +
		                           number + " was found in " 
					   + result + "\n");
		   }
		      
		}
	     }
		   
	  }
	  else if (choice == 3) {
	      sorted = true;
	      for (int i = 0; i < a.length; i++)
		b[i] = a[i];
	      System.out.println("Which sorting method do you want to use? ");
	      System.out.println("1. Bubble Sort");
	      System.out.println("2. Selection Sort");
	      System.out.println("3. Insertion Sort");
	      System.out.println("4. Heap Sort");
	      System.out.println("5. Quick Sort");
	      System.out.println("6. Merge Sort");
	      System.out.print("Enter a number to pick which sorting method "
	                       + "to use:  ");
	      int sortchoice = keyboard.nextInt();
	      keyboard.nextLine();
	      while (sortchoice < 1 || sortchoice > 6) {
		  System.out.print("Invalid choice, please enter the");
		  System.out.print("correct number.  ");
		  sortchoice = keyboard.nextInt();
		  keyboard.nextLine();
	      }
	      if (sortchoice == 1)
		 Sort.bubbleSort(b);
	      else if (sortchoice == 2)
		 Sort.selectionSort(b);
	      else if (sortchoice == 3)
		 Sort.insertionSort(b);
	      else if (sortchoice == 4)
		 Sort.heapSort(b);
	      else if (sortchoice == 5)
		 Sort.quickSort(b);
	      else if (sortchoice == 6)
		 Sort.mergeSort(b);
	  }
	      
	  else if (choice == 4) {
	      if (!sorted)
		 showArray(a);
	      else {
		 System.out.print("Would you like to see the original or ");
	         System.out.println("the sorted array?");
		 System.out.println("1 Original \n2 Sorted");
		 System.out.print("Enter a number to pick an array to use:  ");
                 int arraychoice = keyboard.nextInt();
		 while (arraychoice < 1 || arraychoice > 2) {
		       System.out.print("Invalid choice, please enter the ");
		       System.out.print("correct number.  ");
		       arraychoice = keyboard.nextInt();
		       keyboard.nextLine();
		 }
		 if (arraychoice == 1)
		    showArray(a);
		 else
		    showArray(b);
	      }
	  }
	  
	  else if (choice == 5)
	      choice = 5;
	  
      }
      System.out.print("Good bye and thank you for using this program");
   }
   
   public static void showArray(int[] a)
   {
      int min = a[0];
      int max = a[0];
      for (int i = 1; i < a.length - 1; i++) {
	 if (a[i] > max)
            max = a[i];
         if (a[i] < min)
       	    max = a[i];
      }
      min = ("" + min).length();
      max = ("" + max).length();
      int column = (min > max) ? min : max;
      int columns;
      if (column < 4)
         columns = 20;
      else if (column < 5)
	 columns = 16;
      else if (column < 8)
	 columns = 10;
      else
	 columns = 8;
      Scanner keyboard = new Scanner(System.in);
      for (int i = 0; i < a.length; i++) {
	 if (column < 4)
	    System.out.printf(" %3d", a[i]);
         else if (column < 5)
	    System.out.printf(" %4d", a[i]);
         else if (column < 8)
	    System.out.printf(" %7d", a[i]);
         else
	    System.out.printf(" %9d", a[i]);
         if ((i+1)%(ROWS*columns) == 0) {
           System.out.print("Press Enter ");
           keyboard.nextLine();
	 }
      }
      System.out.println();
      
   }
      
       
   public static int[] getArray()
   {
      System.out.print("How many elements do you want in the array?  ");
      Scanner keyboard = new Scanner(System.in);
      int n = keyboard.nextInt();
      while (n < 1) {
	  System.out.print("Sorry, please enter a positive value:  ");
	  n = keyboard.nextInt();
      }
      int[] a = new int[n];
      System.out.print("What is the smallest value you want in the array?  ");
      int min = keyboard.nextInt();
      System.out.print("What is the largest value you want in the array?  ");
      int max = keyboard.nextInt();
      while (max <= min) {
	   System.out.println("sorry, the largest value has to be bigger than");
	   System.out.print("the smallest value. Try again:  ");
	   max = keyboard.nextInt();
      }
      for (int i = 0; i < a.length; i++)
	a[i] = (int) ((max - min + 1)*Math.random()) + min;
      return a;
   }
}
                      
	 
