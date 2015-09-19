package PostFix;
/*
** PostFixException.java
** John Rasay
** Honolulu Community College
** ICS  211 Spring 2010
** Last modified on January 13, 2010
*/

/**
  * This class is created for the PostFix class.
  */
public class PostFixException extends RuntimeException
{
   /**
     * Constructs a new exception with a fixed detail message.
     */
   public PostFixException()
   {
      super("Unspecified PostFix Error.");
   }
   
   /**
     * Constructs a new exception with a specified detail message.
     * @param s The detail message.
     */
   public PostFixException(String s)
   {
      super(s);
   }
}
