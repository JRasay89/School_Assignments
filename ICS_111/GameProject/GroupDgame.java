/*
** GroupDgame.java
** ICS 111 Introduction to Computer Science I
** Honolulu Community College
*/

import java.util.*;

public class GroupDgame
{
   public static void main(String[] args)
   {
      System.out.print("Lets play a game. I will think of a number between 1-100");
      System.out.print(" and you have 7 chances to guess correctly.");
      int number = (int) (100*Math.random())+ 1;
      Scanner keyboard = new Scanner(System.in);
      keyboard.nextLine();
      System.out.println("What is your first Guess? ");
      int guess = keyboard.nextInt();
      int totalguesses = 6;
      while (guess != number && totalguesses != 0) {
	  	  if (guess < number)
	   	      System.out.println("You need to guess higher. You have "  + totalguesses +  " guesses left.");
	      else
	          System.out.println ("You need to guess lower. You have " + totalguesses +  " guesses left.");
	  	  if (totalguesses == 1)
	          System.out.println("This is your last guess.");
	      System.out.println("What is your next guess. ");
	      guess = keyboard.nextInt();
	      totalguesses = totalguesses - 1;
      }
      if (guess == number)
          System.out.print("You are correct.");
      else
	      System.out.print("You are out of guesses. My number was, " + number);
   }

}







