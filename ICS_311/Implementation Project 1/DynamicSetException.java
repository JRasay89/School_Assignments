/*
** DynamicSetException.java
** John Rasay
** UH
**
*/

/**
  * This class is created for the DynamicSet classess.
  */
public class DynamicSetException extends Exception
{
   /**
     * Constructs a new exception with a fixed detail message.
     */
   public DynamicSetException()
   {
      super("Unspecified List Error.");
   }
   
   /**
     * Constructs a new exception with a specified detail message.
     * @param s The detail message.
     */
   public DynamicSetException(String s)
   {
      super(s);
   }
}
