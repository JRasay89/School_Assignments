package ics321;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Assignment1 {
	
	//load takes a fileName as an argument and
	//reads in the CSV file at the given path.
	//The data may not all fit in memory
	public void load(String fileName)
	{
		try {
			CSVReader reader =  new CSVReader(new FileReader(fileName),'|');
		   
			System.out.println("load "+fileName);

			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				// TODO Load CSV
				// nextLine[] is an array of values from the line
				// ex: System.out.println(nextLine[0] + nextLine[1] + "etc...");
				System.out.println(nextLine[0]);
				for( int i=0; i<nextLine.length-1; i++) {
					// System.out.print(nextLine[i] + "|");
					System.out.print(nextLine[i] + "|");
					System.out.print(".");
				}
				//System.out.println();
			}
			System.out.println();
	
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//searchEq takes a columnNumber and a value and prints
	//tuples that match the given value on the given column.
	//More points will be given for faster return of this method
	public void searchEq(int columnNumber, String value)
	{
		System.out.println("searchEq col #"+columnNumber+"="+value);
		System.out.println("   Running your code here ...");
		
	}
	
	//searchGtr takes a columnNumber and a value and prints
	//tuples where the given column is greater than the given value.
	//More points will be given for faster return of this method
	public void searchGtr(int columnNumber, float value)
	{
		System.out.println("searchGtr col #"+columnNumber+">"+value);
		System.out.println("   Running your code here ...");
	}

}
