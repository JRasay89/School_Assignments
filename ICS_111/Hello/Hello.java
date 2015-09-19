/*
**  Hello.java
*/

import java.util.*;

public class Hello
{
   public static void main(String[] args)
   {
      System.out.print("Hello, what's your name?  ");
      Scanner keyboard = new Scanner (System.in);
      String name = keyboard.nextLine();
      System.out.println("Hi " + name + ", it's nice to meet you.");
   }
}