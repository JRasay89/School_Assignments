package Infix;
/*
 ** John Rasay
 ** Infixtopostfix.java
 ** ICS211
 ** Last Modified: March 30, 2010
 */
 import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
 
 public class Infixtopostfix
 {
    public static void main(String[] args)
    {
       System.out.println("This program converts an infix expression, " +
                          "to a postfix expression.");
       Scanner keyboard = new Scanner(System.in);
       System.out.print("Enter an infix expression: ");
       String s = keyboard.nextLine();
       String answer = convert(s);
       System.out.println(s + " in postfix expression is " + answer);
       System.out.println("Would you like to convert another infix expression" +
                          " to postfix? y/n ");
       String response = keyboard.nextLine();
       while (response.trim().charAt(0) == 'y') {
          System.out.print("Enter an infix expression: ");
          s = keyboard.nextLine();
          answer = convert(s);
          System.out.println(s + " in postfix expression is " + answer);
          System.out.println("Enter another infix expression? y/n ");
          response = keyboard.nextLine();
       }
       System.out.println("Thank you, goodbye.");
    }
    
    public static String convert(String s)
    {
       Stack<String> theStack = new Stack<String>();
       StringTokenizer tokens = new StringTokenizer(s, "+-*/^() ", true);
       String post = "";
       String opperrator;
       while (tokens.hasMoreTokens()) {
          String token = tokens.nextToken();
          char op = token.charAt(0);
          if (op == '-' || op == '+') {
             if (theStack.isEmpty())
                theStack.push(new String(token));
             else if (theStack.peek().equals("*") || theStack.peek().equals("/") 
                      || theStack.peek().equals("^")) {
                while (! theStack.isEmpty() && ! theStack.peek().equals("(")) {
                   opperrator = theStack.pop();
                   post = post + " " + opperrator;
                }
                theStack.push(new String(token));
             }             
             else
                theStack.push(new String(token));
          }
          else if (op == '*' || op == '/') {
             if (theStack.isEmpty())
                theStack.push(new String(token));
             else if (theStack.peek().equals("*") || theStack.peek().equals("/") 
                      || theStack.peek().equals("^")) {
                while (! theStack.isEmpty() && ! theStack.peek().equals("(")) {
                   opperrator = theStack.pop();
                   post = post + " " + opperrator;
                }
                theStack.push(new String(token));
             }
             else
                theStack.push(new String(token));
          }
          else if (op == '^')
             theStack.push(new String(token));
          else if (op == ' ')
             ;
          else if (op == '(')
             theStack.push(new String(token));
          else if (op == ')') {
             while (!theStack.peek().equals("(")) {
                   opperrator = theStack.pop();
                   post = post + " " + opperrator;
             }
             theStack.pop();
          }
          else if (op >= '0' && op <= '9')
             post = post + " " + token;
          else
             throw new InFixException("Invalid Operator");
       }
       while (! theStack.isEmpty()) {
          opperrator = theStack.pop();
          post = post + " " + opperrator;
       }
       return post;
    }
 }
 
       
                  
                  
             
