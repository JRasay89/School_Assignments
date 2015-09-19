/*
** ListException.java
** John Rasay
** Honolulu Community College
** ICS  211 Spring 2010
** Last modified on January 13, 2010
*/

/**
  * This class is created for the ListInterface interface and the List class.
  */
public class ListException extends Exception
{
   /**
     * Constructs a new exception with a fixed detail message.
     */
   public ListException()
   {
      super("Unspecified List Error.");
   }
   
   /**
     * Constructs a new exception with a specified detail message.
     * @param s The detail message.
     */
   public ListException(String s)
   {
      super(s);
   }
}
