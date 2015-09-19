/*
 ** ListDemo.java
 ** John Rasay
 ** Honolulu Community College
 ** ICS211
 ** Last Modified: February, 1, 2010
 */
 
import java.util.*;

public class ListDemo
{
   public static void main(String[] args)
   {
      System.out.println("This program will tests the list class" +
                         " by creating a list of integers.");
      List<Integer> list = new List<Integer>();
      String s = "";
      Scanner keyboard = new Scanner(System.in);
      while (! s.equals("e")) {
         try {
            System.out.print("Enter a-add, r-remove, R-replace, g-retrieve, " +
	                          "s-search, d-display, e-exit: ");
            s = keyboard.nextLine().trim();
            if (s.length() == 0)
               System.out.println("Please type one of letters to continue. ");
            else if (s.charAt(0) == 'a') {
               System.out.print("Enter an integer to be added: ");
               Integer item = new Integer(keyboard.nextInt());
               keyboard.nextLine();
               System.out.println("y - Add at the end of the list");
               System.out.println("n - Enter a position where the item will " +
	                               "be added");
               System.out.print("Type y or n to continue:  ");
               s = keyboard.nextLine().trim();
               if (s.length() > 0 && s.charAt(0) == 'y')
                  list.add(item);
               else {
                  System.out.print("Enter the position: ");
                  int position = getPosition();
                  list.add(item, position);
               }
               System.out.println("The list now contains " + list.length() +
	                               " elements. ");
            }
            else if (s.charAt(0) == 'r') {
               System.out.print("Remove by position? (y/n) ");
               s = keyboard.nextLine().trim();
               if (s.length() > 0 && s.charAt(0) == 'y') {
                  System.out.print("Enter the position: ");
                  int position = getPosition();
                  list.remove(position);
               }
               else {
                  System.out.print("Enter the integer to be removed: ");
                  Integer item = new Integer(keyboard.nextInt());
                  keyboard.nextLine();
                  list.remove(item);
               }
               System.out.println("The list now contains " + list.length() + 
	                               " elements.");
            }
            else if (s.charAt(0) == 'R') {
               System.out.print("Enter the position: ");
               int position = getPosition();
               System.out.print("Enter the new integer to be added: ");
               Integer item = new Integer(keyboard.nextInt());
               keyboard.nextLine();
               list.replace(item, position);
               System.out.println("The list now contains " + list.length() +
	                               " elements.");
            }
            else if (s.charAt(0) == 'g') {
               System.out.print("Enter the position: ");
               int position = getPosition();
               Integer item = (list.retrieve(position));
               System.out.println("The integer in that position is " + item);
            }
            else if (s.charAt(0) == 's') {
               System.out.print("Enter an integer to be searched for: ");
               Integer item = new Integer(keyboard.nextInt());
               keyboard.nextLine();
               int position = list.contains(item);
               if (position > 0)
                  System.out.println(item + " in the list is in position " +
	                                  position + ".");
               else
                  System.out.println(item + " could not be found in the" +
	                                  " list.");
            }
            else if (s.charAt(0) == 'd')
               for (int i = 1; i <= list.length(); i++)
                  System.out.println(i + ": " + list.retrieve(i));
         }
         catch (ListException e) {
            System.out.println("Error: " + e.getMessage());
         }
         catch (InputMismatchException e) {
            System.out.println("Error, you should have entered an integer!");
         }
      }
      System.out.println("Now let's test the equals() method. ");
      System.out.println("I'm going to create a second list and fill it with" +
                         " the same integers in the first list. Then I'm going"
			                + " to see if two of those list are equal.");
      List<Integer> list2 = new List<Integer>();
      try {
         for (int i = 1; i <= list.length(); i++)
            list2.add(list.retrieve(i));
      }
      catch (ListException e) {
         System.out.println("Something's wrong. This should work.");
      }
      if (list2.equals(list))
         System.out.println("The two list are equal.");
      else
         System.out.println("That's strange. They should be equal.");
      System.out.println("Now let's add something to the second list and check"+
                          " again.");
      list2.add(2);
      if (list2.equals(list))
         System.out.println("The two lists are equal, but they shouldn't be.");
      else
         System.out.println("The two list are not equal.");
      Date today = new Date();
      if (list.equals(today))
         System.out.println("It shouldn't be equal. Somethings wrong.");
      else
         System.out.println("Yes a list and a date cannot be equal.");
      System.out.println("Thank you for using the program, good bye.");
   }
   
   static int getPosition()
   {
      Scanner keyboard = new Scanner(System.in);
      while (true) {
         try {
            int pos = keyboard.nextInt();
            keyboard.nextLine();
            return pos;
         }
         catch (InputMismatchException e) {
            System.out.print("Error, please enter an integer:  ");
         }
      }
   }
}
