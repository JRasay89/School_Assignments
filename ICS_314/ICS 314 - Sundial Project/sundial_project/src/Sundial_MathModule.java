import java.util.GregorianCalendar;
import java.util.*;

/* Master Class Sundial Project-clr
 * Sundial MathModule 
 * Computes the angles with correction of longitude and  
 * the correction of planetary orbit for a sundial
 * 
 * ICS 314 
 * @author Chris Rickett
 */
public class Sundial_MathModule {
	
	// Angles for hours 6pm-12am-5am held in 0-6-12 indexes 
	double[] angleArray = new double[12];
	
	// empty constructor to enable connections between interface
	public Sundial_MathModule() {
	}
	/*
	 * Computes the angles of the hours 6-12, placing them into an array
	 * as a return value for the visualization of the Sundial
	 * Uses the equation 
	 * Changed the initial equation to use arctan2 which takes out the inside 
	 * Tangent and replaces it with sin(15deg * #hoursfrom12) and dividing it 
	 * by cos(15deg * #hoursfrom12)
	 * hour = arctan(sin(latitude) * tan(15deg * #hoursfrom12))
	 * Each hour is placed into an index: hour - 1
	 */
	public double[] computeAngles(double lat, double lon, int day, int month, int year) {
		/*
		* Calculating for Error of Time
		* The EOT can be approximated by the following formula:
		* E = 9.87 * sin (2B) - 7.53 * cos (B) - 1.5 * sin (B)
		* Where: B = 360 * (N - 81) / 365
		* Where: N = day number, January 1 = day 1
		*/
		//creates new calendar
	   GregorianCalendar gc = new GregorianCalendar();
	  
	   //sets calendar according to input parameters
	   gc.set(GregorianCalendar.DAY_OF_MONTH, day);
	   gc.set(GregorianCalendar.MONTH, month - 1);
	   gc.set(GregorianCalendar.YEAR, year);
	   //returns day of year, or N
	   int dayOyear = gc.get(GregorianCalendar.DAY_OF_YEAR);
		
		//bojano is for the B in the equation since it was not 
		//specified in the explanation.
		double bojano = (360 * (dayOyear - 81)) / 365;
		
		//implementing the error of time equation
		double eot = (9.87 * Math.sin(Math.toRadians(2*bojano))) - 
			(7.53 * Math.cos(Math.toRadians(bojano))) - 
			(1.5 * Math.sin(Math.toRadians(bojano)));
		// testing code
		//System.out.println("EOT: " + eot);
		double eotDec = eot / 60;
		// Double mainly needed for the trigonomic equations 
		double doubLat = lat;
		double doubLon = lon;
		//Sin of the Lattitude
		double sinLat = Math.sin(Math.toRadians(doubLat));

		//For loop finds each of the angles and places them in the 
		//array for return.
		for (int i=-6; i<6; i++) {
		
			int[] standMeridian = {0, 15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 180};
			//Finds closest standardMeridian calculating the correction of longitude
			// and then calculates the hour angle according to the correction of 
			// longitude and the error of time as degrees, I.e. 15 * DecimalHours
			double standardMeridian = standMeridian[(int)Math.abs(doubLon)/15];
			double lonCorrected = (standardMeridian - doubLon);
			double hourAngle = (15 * i) + lonCorrected + (15 * eotDec);
			
			//converting the hour angle to radians and then finding the cos
			// and sin of said angle	
			double hourAndgleRad = Math.toRadians(hourAngle);
			double sinHourAngle = Math.sin(hourAndgleRad);
			double cosHourAngle = Math.cos(hourAndgleRad);
	
			//By setting i as the hour, then index 0 corresponds to 12 and index 6 corresponds to 6 
			double angle = Math.toDegrees(Math.atan2(sinLat * sinHourAngle, cosHourAngle));
			
			/*
			* Since a sundial is usually within the shape of a Protractor or half circle
			* no angles above 90 nor below -90 may be used for the sundial, if so adding
			* 180 degrees will bring it to the opposite side. This will happen a lot since
			* it checks for corrections and it is rare when none are needed.
			*/
			if (angle < -90) {
				angle = (angle + 180);
			} else if (angle > 90) {
				angle = (angle - 180);
			}
			angleArray[i+6] = angle;
			
			
		}
		return angleArray;
	}
	

}
