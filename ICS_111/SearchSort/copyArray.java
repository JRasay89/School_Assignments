import java.util.*;

public class copyArray
{
   private static final int ROWS = 40;
   
   public static void main(String[] args)
   {
      int [] a = {1,4,6,7,8,9,0};
      int [] b = new int[7];
      for (int i = 0; i < a.length; i++) {
	   b[i] = a[i];
	   System.out.println(b[i]);
      }
      Sort.quickSort(b);
      System.out.println("\n\nAfter sorting:");
      showArray(b);
      System.out.println("Before sorting: ");
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
}

  
