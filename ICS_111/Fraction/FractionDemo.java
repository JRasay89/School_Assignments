/*
**
** FractionDemo.java
**
** John Rasay
** ICS 111
** Last modified: November 1, 2009
**
*/

import java.util.*;

public class FractionDemo
{ 
   public static void main(String[] args)
   {
      System.out.println("This program demonstrates and test the Fraction class.");
      System.out.print("Enter the numerator and denominator for a fraction; ");
      Scanner keyboard = new Scanner(System.in);
      int n = keyboard.nextInt();
      int d = keyboard.nextInt();
      Fraction first = new Fraction(); 
      try {
          first = new Fraction(n, d);
      }
      catch (ArithmeticException e) {
		System.out.println(e.getMessage());
        System.out.println("Default fraction created. ");
      }
      System.out.println("Your Fraction is " + first);
      System.out.println("When reduced, " + first + " is " + first.reduce());
      System.out.print("Enter the numerator and "); 
      System.out.print("denominator for another fraction:  ");
      n = keyboard.nextInt();
      d = keyboard.nextInt();
      Fraction second = Fraction.ONE;
      try {
		second = new Fraction(n, d);
      }
      catch (ArithmeticException e) {
          System.out.println(e.getMessage());
          System.out.println("Default fraction created.");
      }
      System.out.println("Your Fraction is " + second);
      System.out.println("When reduced " + second + " is " + second.reduce());
      Fraction sum = first.plus(second);
      System.out.println("The sum of " + first + " and " + second +
                         " is " + sum);
      Fraction difference = first.minus(second);
      System.out.println("The difference between " + first + " and " + second + 
                         " is " + difference);
      Fraction product = first.times(second);
      System.out.println("The product of " + first + " and " + second + 
                         " is " + product);
      Fraction quotient = first.dividedBy(second);
      System.out.println("The quotient of " + first + " and " + second +
                         " is " + quotient);
      double decimal = first.valueOf();
      System.out.println("The decimal value of " + first + " is " + decimal); 
      if (first.equals(second))
		System.out.println(first + " and " + second + " are equal.");
      else
        System.out.println(first + " and " + second + " are not equal.");
      if (first.isLessThan(second))
		System.out.println(first + " is less than " + second);
      if (first.isGreaterThan(second))
        System.out.println(first + " is greater than " + second);
      Fraction power = first.powerOf(2);
      System.out.println(first + "  raised to the power of 2 is  " + power);
      Fraction power2 = second.powerOf(-4);
      System.out.println(second + " raised to the power of -4 is " + power2);
      System.out.println("Mixed form of " + first + " is " + first.reduce().mixedNumber());
   }
}
