package PreFix;

/*
 ** John Rasay
 ** PreFixToPostFix.java
 ** ICS211
 ** Last Modified: March 15, 2010
 */
 import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class PreFixToPostFix
 {
    public static void main(String[] args)
    {
       System.out.println("This program converts prefix expression, " +
                          "to a postfix expression.");
       Scanner keyboard = new Scanner(System.in);
       System.out.print("Enter a prefix expression: ");
       String s = keyboard.nextLine();
       String answer = prefixtopost(s);
       System.out.println(s + " in postfix expression is " + answer);
       System.out.println("Would you like to convert another prefix expression"+
                          " to postfix? y/n ");
       String response = keyboard.nextLine();
       while (response.trim().charAt(0) == 'y') {
          System.out.print("Enter a prefix expression: ");
          s = keyboard.nextLine();
          answer = prefixtopost(s);
          System.out.println(s + " in postfix expression is " + answer);
          System.out.println("Enter another prefix expression? y/n ");
          response = keyboard.nextLine();
       }
       System.out.println("Thank you for using this program, goodbye.");
    }
    
    public static String prefixtopost(String s)
    {
       Stack<String> theStack = new Stack<String>();
       StringTokenizer tokens = new StringTokenizer(s, "+-*/^ ", true);
       String post = "";
       int opercount = 0;
       int opercount2 = 0;
       while (tokens.hasMoreTokens()) {
          String token = tokens.nextToken();
          char op = token.charAt(0);
          if (op == '+' || op == '-' || op == '*' || op == '/' || op == '^') {
             opercount++;
             theStack.push(new String(token));
             theStack.push(new String("off"));
          }
          else if (op == ' ')
             ;
          else if (op >= '0' && op <= '9' || op == '~') {
             opercount2++;
             if (theStack.isEmpty())
                throw new PreFixToPostFixException("Invalid expression, " +
                                                   "too many Operands.");
             else if (op == '~') {
                token = "-" + token.substring(1);
                post = post + " " + token;
             }
             else
                post = post + " " + token;
             while (!theStack.isEmpty() && theStack.peek().equals("on")) {
                theStack.pop();
                String operator = theStack.pop();
                post = post + " " + operator;
             }
             if (!theStack.isEmpty()) {
                theStack.pop();
                theStack.push(new String("on"));
             }
          }
          else
             throw new PreFixToPostFixException("Invalid Operator " + op);
       }
       if (opercount >= opercount2)
          throw new PreFixToPostFixException("Invalid expression, " +
                                              "too many operators.");
       while (!theStack.isEmpty()) {
          if (theStack.peek().equals("on") || theStack.peek().equals("off"))
             theStack.pop();
          else {
             String operator = theStack.pop();
             post = post + " " + operator;
          }
       }
       return post;
    }
 }
             
             
