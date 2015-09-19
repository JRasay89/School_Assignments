/*
** numbers.java
*/

import java.util.*;

public class numbers
{
   public static void main(String[] args)
   {

      System.out.print("Please enter first number: ");
	  Scanner keyboard = new Scanner (System.in);
	  int first = keyboard.nextInt();
      System.out.print("Please enter second number: ");
      Scanner in = new Scanner (System.in);
      int second = in.nextInt();
      int sum = first + second;
      System.out.println("The sum of the two numbers is " + sum);
      int difference = first - second;
      System.out.println("The diffrence of the two numbers " + difference);
      int product = first * second;
      System.out.println("The product of the two numbers is " + product);
      int qoutient = first / second;
      int remainder = first % second;
      System.out.println("The qoutient of the two numbers is " + qoutient + ", with a remainder of " + remainder);
   }
}