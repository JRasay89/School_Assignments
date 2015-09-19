package PreFix;
/*
** PreFixToPostFixException.java
** John Rasay
** Honolulu Community College
** ICS  211 Spring 2010
** Last modified on January 13, 2010
*/

/**
  * This class is created for the PostFix class.
  */
public class PreFixToPostFixException extends RuntimeException
{
   /**
     * Constructs a new exception with a fixed detail message.
     */
   public PreFixToPostFixException()
   {
      super("Unspecified InFix Error.");
   }
   
   /**
     * Constructs a new exception with a specified detail message.
     * @param s The detail message.
     */
   public PreFixToPostFixException(String s)
   {
      super(s);
   }
}
