/*
 * Arrays.java
 *
 * John Rasay
 * ICS111
 * Last modified: November 15, 2009
 */
 
import java.util.*;

public class Arrays
{
   public static void main(String[] args)
   {
      System.out.print("Enter the size of the array being created: ");
      Scanner  keyboard = new Scanner(System.in);
      int n = keyboard.nextInt();
      keyboard.nextLine();
      while (n < 1) {
	    System.out.println("You cannot make an array of that size. " );
	    System.out.print("Try again. ");
	    n = keyboard.nextInt();
      }
      int [] a = new int[n];
      for (int i = 0; i < a.length; ++i)
        a[i] = (int)(1000*Math.random()+ 1;
      String answer = "y";
      while (answer.trim().toLowerCase().charAt(0) == 'y') {
	   System.out.print("Enter a number to be search:  ");
	   int search = keyboard.nextInt();
	   keyboard.nextLine();
	   for (int i = 0; i < a.length; ++i)
              if (a[i] == search)
                 System.out.println(search + " is in cell, " + i);
           System.out.print("Would you like to search another number? ");
           answer = keyboard.nextLine();
           
      }
   }
}
