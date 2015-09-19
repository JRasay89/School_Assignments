package ics321;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

	/**
	 * @param args
	 */
	 public static void main(String[] args) {
	 	 try {
	 	 	 Assignment1 a1 = new Assignment1();
	 	 	 Timing stopwatch = new Timing();
			 BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			 String line = null;
			 stopwatch.start();
			 if((line = reader.readLine())!=null)
			 {
			 	 String[] arr = line.split(" ");
			 	 if (arr[0].equalsIgnoreCase("load") && arr.length==2)
			 	 {
			 	 	 a1.load(arr[1]);
			 	 }
			 	 else
			 	 {
			 	 	 System.out.println("Error: First line of commands must be a load and not "+line);
					 return;
				 }
			 }
			 stopwatch.stop();
			 System.out.println( stopwatch.print("Total load time") );
			 stopwatch.start();
			 while ((line = reader.readLine())!=null)
			 {
			 	 String[] arr = line.split(" ");
				
			 	 if (arr[0].equalsIgnoreCase("eq") && arr.length==3)
				 {
				 	 try
					 {
					 	 a1.searchEq(Integer.parseInt(arr[1]), arr[2]);
					 }
					 catch (NumberFormatException e) {
						 System.out.println("Skipped line: "+line);
					 }
				 }
				 else if (arr[0].equalsIgnoreCase("gtr") && arr.length==3)
				 {
				 	 try
					 {
						 a1.searchGtr(Integer.parseInt(arr[1]), Float.parseFloat(arr[2]));
					 }
					 catch (NumberFormatException e) {
						 System.out.println("Skipped line: "+line);
					 }
				 }
				 else
				 {
					 System.out.println("Skipped line: "+line);
				 }
			 }
			 stopwatch.stop();
			 System.out.println( stopwatch.print("Total query time") );
		 } catch (ArrayIndexOutOfBoundsException e) {
			 System.out.println("Path to command file required");
		 } catch (FileNotFoundException e) {
			 System.out.println("Command file "+ args[0] +" not found");
		 } catch (IOException e) {
			 System.out.println("IOException");
		 } 
	 }
}
