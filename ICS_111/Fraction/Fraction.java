/*
**
** Fraction.java
**
** John Rasay
** ICS 111
** Last Modified: November 1, 2009
*/

/**
 * This class describes a normal fraction of mathematics. A fraction
 * has a numerator and a denominator, the denominator cannot equal 0.
 * Fractions can be reduced, added to one another, subtracted from one
 * another, etc. They can be compared and displayed as improper fractions
 * or as mixed numbers. Fractions are immutable; once instantiated, a
 * Fraction cannot be changed. When a Fraction is reduced, for example,
 * a new Fraction is returned, the original Fraction is left unchanged.
 * @author John Rasay
 */
public class Fraction
{
/**
 * The numerator of the Fraction.
 */
   private final int numerator;
   
/**
 * The denominator of the Fraction.
 */
   private final int denominator;
   
/**
 * This constant serves as the multiplicative identity, zero.
 */
   public static final Fraction ZERO = new Fraction(0, 1);

/**   
 * This constant serves as the multiplicative identity, one.
 */
   public static final Fraction ONE = new Fraction(1, 1);
   
/**
 * This is the default Fraction 1 / 2
 */
   public Fraction()
   {
      numerator = 1;
      denominator = 2;
   }

 /**
 * Instantiates a fraction with numerator n and denominator d.
 * If d equals zero, an ArithmeticException is thrown.
 * @param n The numerator of the new Fraction.
 * @param d The denominator of the new Fraction.
 * @throws ArithmeticException if d equals 0.
 */
   public Fraction(int n, int d)
   {
      numerator = n;
      denominator = d;
      if (d == 0)
	  throw new ArithmeticException("Error, zero denominator");
   }
   
/**
 * This method returns the numerator of the Fraction.
 /* @return the numerator of the Fraction.
 */
   public int getNumerator()
   {
      return numerator;
   }
   
/**
 * This method returns the denominator of the Fraction.
 * @return the denominator of the Fraction.
 */
   public int getDenominator()
   {
      return denominator;
   }

/**
 * This method returns a String representation of the Fraction.
 */
   public String toString()
   {
      int n = numerator;
      int d = denominator;
      if (d < 0){
	 d = -d;
         n = -n;
      }
      return n + "/" + d;
   }

/**
 * This method returns the greatest common denominator of the Fraction.
 * @return The value of y.
 * @param x The numerator of the Fraction.
 * @param y The denominator of the Fraction.
 */
   private static int gcd(int x, int y)
   {
     int r = x % y;
     while (r != 0) {
         x = y;
	 y = r;
	 r = x % y;
     }
     return y;
   }
   
/**
 * This method returns the reduce form of the Fraction.
 * @return the reduce form of the Fraction.
 */
   public Fraction reduce()
   {
       if (numerator == 0)
            return ZERO;
       if (numerator == denominator)
            return ONE;
   int gcd = gcd(numerator, denominator);
   int n = numerator / gcd;
   int d = denominator / gcd;
       if ( d < 0) {
            d = -d;
            n = -n;
       }
   return new Fraction(n, d);
   }
   
/**
 * This method adds the Fraction to a parameter and returns the sum.
 * @param g The fraction that is being added to the Fraction.
 * @return The sum of the Fraction and the parameter.
 */
   public Fraction plus(Fraction g)
   {
      Fraction first = reduce();
      Fraction second = g.reduce();
      int lcm = lcm(first.denominator, second.denominator);
      int n = (lcm/first.denominator)*first.numerator + 
              (lcm/second.denominator)*second.numerator;
      Fraction result = new Fraction(n, lcm);
      return result.reduce();
   }

/**
 * The least common multiple of the two parameters.
 * @return The least common multiple.
 * @param x The numerator of the Fraction.
 * @param y The denominator of the Fraction.
 */
   private static int lcm(int x, int y)
   {
      if (x < 0)
	 x = -x;
      if (y < 0)
	 y = -y;
      return x*(y/gcd(x, y));
   }
 
 /**
  * This method returns the inverse of the Fraction.
  * @return the inverse of the Fraction.
  */
   public Fraction inverseOf()
   {
      int n = denominator;
      int d = numerator;
      return new Fraction(n, d);
   }
   
/**
 * This method returns the opposite of the Fraction.
 * @return the opposite of the Fraction.
 */
   public Fraction negativeOf()
   {
       int n = numerator * -1;
       int d = denominator;
       return new Fraction(n, d);
   }
 
 /**
  * This method subtracts the Fraction by the parameter and returns the difference.
  * @return The difference of the Fraction and the parameter.
  * @param g The fraction being subtrated from Fraction.
  */
   public Fraction minus(Fraction g)
   {
      return plus(g.negativeOf());
   }
   
/**
 * This method multiplys the Fration by the parameter and returns the product.
 * @param g The Fraction being multiplied to the Fraction.
 * @return The product of the Fraction and the parameter.
 */
   
   public Fraction times(Fraction g)
   {
      Fraction first = reduce();
      Fraction second = g.reduce();
      int n = first.numerator * second.numerator;
      int d = first.denominator * second.denominator;
      Fraction result = new Fraction(n, d);
      return result.reduce();
   }
   
/**
 * This method divides the Fraction by the parameter and returns a quotient.
 * @param g The Fraction being divided to the Fraction.
 * @return The product of the  Fraction and the parameter.
 */
   public Fraction dividedBy(Fraction g)
   {
      return times(g.inverseOf().reduce());
   }
   
/** 
 * This method returns the Fraction into a decimal.
 * @return The decimal value of the Fraction.
 */
   public double valueOf()
   {
      return ((double)numerator)/denominator;
   }
   
/**
 * This method compares the Fraction and the parameter and returns if they are 
 * equal or not equal.
 * @param g The fraction being compared to the Fraction.
 * @return if Fraction and parameter are equal or not equal.
 */
   public boolean equals(Fraction g)
   {
     return this.valueOf() == g.valueOf();    
   }
   
/**
 * This method compares the Fraction and the parameter and returns if the Fraction
 * is less than the parameter.
 * @param g The Fraction being compared to the Fraction.
 * @return if the boolean expression is true or false.
 */
   public boolean isLessThan(Fraction g)
   {
      if (this.valueOf() < g.valueOf())
	  return true;
      else
          return false;
   }
   
/**
 * This method compares the Fraction and the parameter and returns if the Fraction
 * is greater than the parameter.
 * @param g The fraction being compared to the Fraction.
 * @return if g is less than the Fraction.
 */
   public boolean isGreaterThan(Fraction g)
   {
      return g.isLessThan(this);
   }
   
/**
 * This method returns the Fraction raised to the power of whatever number.
 * @param p The power value thats given to the fraction.
 */
   public Fraction powerOf(int p)
   {
    if (p == 0)
	return ONE;
    Fraction f = this.reduce();
    if (p > 0)
        return new Fraction((int)Math.pow(f.numerator, p),
                           (int)Math.pow(f.denominator, p));
        return new Fraction((int)Math.pow(f.denominator, -p),
	                    (int)Math.pow(f.numerator, -p));
   }
   
/**
 * This method returns the Fraction into mixed number form;
 * @return the mix form of the Fraction.
 */
   public String mixedNumber()
   { 
      int n = numerator;
      int d = denominator;
      int rem = n%d;
      int quo = n/d;
      if (d < 0){
	 d = -d;
	 n = -n;
      }
      if (rem < 0)
	 rem = -rem;
      if ( rem == 0)
	 return quo + "";
      if (quo == 0)
	 return n + "/" + d;
      else
	 return quo +"("+(rem + "/" + d)+")";
   } 
}
    
