import java.util.*;

public class BubbleTest
{
   private static final int ROWS = 40;
   
   public static void main(String[] args)
   {
      System.out.println("Instructions go here. ");
      int[] a = getArray();
      System.out.println("Before sorting: ");
      showArray(a);
      System.out.println();
      Sort.mergeSort(a);
      System.out.println("\n\nAfter sorting:");
      showArray(a);
      System.out.println();
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
   
   public static double elepsedTime(Calendar start, Calendar end)
   {
      int hour1 = start.get(Calendar.HOUR_OF_DAY);
      int minute1 = start.get(Calendar.MINUTE);
      int second1 = start.get(Calendar.SECOND);
      int milli1 = start.get(Calendar.MILLISECOND);
      double startSeconds = 3600*hour1 + 60*minute1 + second1 + milli1 / 1000.0;
      int hour2 = end.get(Calendar.HOUR_OF_DAY);
      if (hour1 > hour2)
	 hour2 = hour2 + 24;
      int minute2 = end.get(Calendar.MINUTE);
      int second2 = end.get(Calendar.SECOND);
      int milli2 = end.get(Calendar.MILLISECOND);
      double endSeconds = 3600 * hour2 + 60 * minute2 + second2 + milli2 / 1000.0;
      double et = (int) (1000*(endSeconds - startSeconds))/1000.0;
      return et;
   }
}

