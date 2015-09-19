/**
 * ICS 311 Implementation Project 2
 * @author John Rasay
 * @School University OF Hawaii
 * @Class ICS 311
 * @Fall 2012
 *
 */
public class InvalidEdgeException extends Exception
{
   /**
     * Constructs a new exception with a fixed detail message.
     */
   public InvalidEdgeException()
   {
      super("Unspecified List Error.");
   }
   
   /**
     * Constructs a new exception with a specified detail message.
     * @param s The detail message.
     */
   public InvalidEdgeException(String s)
   {
      super(s);
   }
}
