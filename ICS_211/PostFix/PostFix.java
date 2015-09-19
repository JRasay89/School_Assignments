package PostFix;

/*
 ** John Rasay
 ** PostFix.java
 ** ICS211
 **Last Modified; March 9, 2010
 */
 
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

 
 public class PostFix
 {
    public static void main(String[] args)
    {
       boolean response = true;
       while (response) {
          try {
             System.out.println("This program evaluates a postfix expression.");
             Scanner keyboard = new Scanner(System.in);
             System.out.print("Enter a postfix expression: ");
             String s = keyboard.nextLine();
             double answer = evaluate(s);
             System.out.println("The answer is :  " + answer);
             System.out.println("Enter a another postfix expression? y/n ");
             String ans = keyboard.nextLine();
             while (ans.trim().charAt(0) == 'y') {
                System.out.print("Enter a postfix expression: ");
                s = keyboard.nextLine();
                answer = evaluate(s);
                System.out.println("The answer is:  " + answer);
                System.out.println("Enter another postfix expression? y/n");
                ans = keyboard.nextLine();
             }
             response = false;
             System.out.println("Thank you for using the program.");
          }
          catch (NumberFormatException s) {
             throw new PostFixException("Invalid Opperand or Operator");
          }
       }
    }
    public static double evaluate(String s)
    {
       Stack<Double> theStack = new Stack<Double>();
       StringTokenizer tokens =  new StringTokenizer(s, "+-*/^ ", true);
       if (tokens.countTokens() == 1)
          throw new PostFixException("PostFix Expression is invalid. Need" +
                                     " atleast two operator followed by " +
                                     " an operand.");
       while (tokens.hasMoreTokens()) {
          String token = tokens.nextToken();
          char op = token.charAt(0);
          if (op == '+' || op == '-' || op == '*' || op == '/' || op == '^') {
             if (theStack.size() == 1)
                throw new PostFixException("Expression has too many operator.");
             double opperrand2 = theStack.pop();
             double opperrand1 = theStack.pop();
             switch (op) {
                case '+':
                  theStack.push(opperrand1 + opperrand2);
                  break;
                case '-':
                  theStack.push(opperrand1 - opperrand2);
                  break;
                case '*':
                  theStack.push(opperrand1 * opperrand2);
                  break;
                case '/':
                  theStack.push(opperrand1 / opperrand2);
                  break;
                case '^':
                  theStack.push(Math.pow(opperrand1,opperrand2));
                  break;
                default:
                   break;
             }
          }
          else if (op == ' ')
             ;
          else if (op == '~') {
             token = "-" + token.substring(1);
             theStack.push(new Double(token));
          }
          else {
             theStack.push(new Double(token));
          }
          
       }
       if (theStack.size() == 2)
          throw new PostFixException("Invalid Expression, too many opperands.");
       else {
          double answer = theStack.pop();
          return answer;
       }
    }
 }             
