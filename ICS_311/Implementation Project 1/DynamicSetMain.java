import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DynamicSetMain {
	public static void main(String[] args) {
		List<String> wordList = new ArrayList<String>();
		BSTDynamicSet theTree = new BSTDynamicSet();
		DLLDynamicSet theDLList = new DLLDynamicSet();
		//SkipListDynamicSet theList = new SkipListDynamicSet();
		
		//Reads in a file and store it in a ArrayList
		try {
			File myFile = new File(args[0]);
			FileReader fileReader = new FileReader(myFile);
			
			BufferedReader reader = new BufferedReader(fileReader);
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				wordList.add(line.trim());
			}
			reader.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		////////////////////////////////////////////////////////////
		///					Main Menu							 ///
		////////////////////////////////////////////////////////////
		
		String s = "";
		Scanner keyboard = new Scanner(System.in);
		while (! s.equals("e")) {
	            System.out.print("Enter r = runtest, i = individual test, e-exit: \n");
	            s = keyboard.nextLine().trim();
	            if (s.length() == 0)
	                System.out.println("Please type one of letters to continue. ");
	            else if (s.charAt(0) == 'r') {
	            	runtest(wordList,theTree,theDLList);
	            }
	            else if (s.charAt(0) == 'i') {														//If use picks other, show another menu
	            	String s2 = "";
	            	while (! s2.equals("q")) {
	            		System.out.print("Enter d = Doubly Linked List, b = Binary Search Tree,  s = Skip List, q = quit: \n");
	            		s2 = keyboard.nextLine().trim();
	            		if (s.length() == 0) {
	            			System.out.println("Please type one of the letters to continue. ");
	            		}
	            		
	            		//Linked List
	            		else if (s2.charAt(0) == 'd') {											//If user picks linked list
	            			for (String element : wordList)									//Fill up the array for testing
	            			{
	            				theDLList.insert(element);
	            			}
	            			String item;
	            			String s3 = "";
	            			while (! s3.equals("q")) {
	            				System.out.print("Enter d = delete, i = insert,  s = search, l = maximum, m = minimum, p = predecessor, u = successor, q = quit: \n");
	            				s3 = keyboard.nextLine().trim();
	    	            		if (s.length() == 0) {
	    	            			System.out.println("Please type one of the letters to continue. ");
	    	            		}
	    	            		//Adding an item to the Linked List
	    	            		else if (s3.charAt(0) == 'i') {
	    	            			System.out.println("Enter an item to be inserted: ");
	    	            			item = keyboard.nextLine();
	    	            			theDLList.insert(item);
	    	            		}
	    	            		//Deleting an item to the Linked List
	    	            		else if (s3.charAt(0) == 'd') {
	    	            			System.out.println("Enter an item to be deleted: ");
	    	            			item = keyboard.nextLine();
	    	            			try {
	    	            				theDLList.delete(item);
	    	            			}catch (DynamicSetException e) {
	    	            				System.out.println(e.getMessage());
	    	            			}
	    	            		}
	    	            		//Search for an item
	    	            		else if (s3.charAt(0) == 's') {
	    	            			System.out.println("Enter an item to be searched: ");
	    	            			item = keyboard.nextLine();
	    	            			String searched = (String)theDLList.search(item);
	    	            			if (searched == null) {
	    	            				System.out.println("Item is was not found in the list.");
	    	            			}
	    	            			else {
	    	            				System.out.println(searched);
	    	            			}
	    	            		}
	    	            		//Finds the maximum
	    	            		else if (s3.charAt(0) == 'l') {
	    	            			String maximum = (String)theDLList.maximum();
	    	            			System.out.println("The maximum element in the List is "+ maximum);
	    	            		}
	    	            		//Finds the minimum
	    	            		else if (s3.charAt(0) == 'm') {
	    	            			String minimum = (String)theDLList.minimum();
	    	            			System.out.println("The minimum element in the List is "+ minimum);
	    	            		}
	    	            		//Finds the predecessor of the item
	    	            		else if (s3.charAt(0) == 'p') {
	    	            			System.out.println("Enter an item to find it's predecessor: ");
	    	            			item = keyboard.nextLine();
	    	            			String predecessor = null;
	    	            			try {
	    	            				predecessor = (String) theDLList.predecessor(item);
	    	            			}catch (DynamicSetException e) {
	    	            				System.out.println(e.getMessage());
	    	            			}
	    	            			System.out.println("The items predecessor is " + predecessor);
	    	            		}
	    	            		//Finds the successor of an item
	    	            		else if (s3.charAt(0) == 'u') {
	    	            			System.out.println("Enter an item to find it's successor: ");
	    	            			item = keyboard.nextLine();
	    	            			String successor = null;
	    	            			try {
	    	            				successor = (String) theDLList.successor(item);
	    	            			}catch (DynamicSetException e) {
	    	            				System.out.println(e.getMessage());
	    	            			}
	    	            			System.out.println("The items successor is " + successor);
	    	            			
	    	            		}
	    	            		
	            			}//End of Linked List While Loop
	            			theDLList.clear();//Clears the Linked List
	            			
	            		}//End of Linked List If
	            		
	            		
	            		//Binary Search Tree
	            		else if (s2.charAt(0) == 'b') {											//If user picks Binary Search Tree
	            			for (String element : wordList)									//Fill up the array for testing
	            			{
	            				theTree.insert(element);
	            			}
	            			String item;
	            			String s3 = "";
	            			while (! s3.equals("q")) {
	            				System.out.print("Enter d = delete, i = insert,  s = search, l = maximum, m = minimum, p = predecessor, u = successor, q = quit: \n");
	            				s3 = keyboard.nextLine().trim();
	    	            		if (s.length() == 0) {
	    	            			System.out.println("Please type one of the letters to continue. ");
	    	            		}
	    	            		//Adding an item to the Tree
	    	            		else if (s3.charAt(0) == 'i') {
	    	            			System.out.println("Enter an item to be inserted: ");
	    	            			item = keyboard.nextLine();
	    	            			theTree.insert(item);
	    	            		}
	    	            		//Deleting an item to the Tree
	    	            		else if (s3.charAt(0) == 'd') {
	    	            			System.out.println("Enter an item to be deleted: ");
	    	            			item = keyboard.nextLine();
	    	            			try {
	    	            				theTree.delete(item);
	    	            			}catch (DynamicSetException e) {
	    	            				System.out.println(e.getMessage());
	    	            			}
	    	            		}
	    	            		//Search for an item
	    	            		else if (s3.charAt(0) == 's') {
	    	            			System.out.println("Enter an item to be searched: ");
	    	            			item = keyboard.nextLine();
	    	            			String searched = (String)theTree.search(item);
	    	            			if (searched == null) {
	    	            				System.out.println("Item is was not found in the list.");
	    	            			}
	    	            			else {
	    	            				System.out.println(searched);
	    	            			}
	    	            		}
	    	            		//Prints the maximum element in the Tree
	    	            		else if (s3.charAt(0) == 'l') {
	    	            			String maximum = (String)theTree.maximum();
	    	            			System.out.println("The maximum element in the List is "+ maximum);
	    	            		}
	    	            		//Prints the minimum element in the Tree
	    	            		else if (s3.charAt(0) == 'm') {
	    	            			String minimum = (String)theTree.minimum();
	    	            			System.out.println("The minimum element in the List is "+ minimum);
	    	            		}
	    	            		//Prints the predecessor of the give item
	    	            		else if (s3.charAt(0) == 'p') {
	    	            			System.out.println("Enter an item to find it's predecessor: ");
	    	            			item = keyboard.nextLine();
	    	            			String predecessor = null;
	    	            			try {
	    	            				predecessor = (String) theTree.getPredecessor(item);
	    	            			}catch (DynamicSetException e) {
	    	            				System.out.println(e.getMessage());
	    	            			}
	    	            			System.out.println("The items predecessor is " + predecessor);
	    	            		}
	    	            		//Prints the successor of the give item
	    	            		else if (s3.charAt(0) == 'u') {
	    	            			System.out.println("Enter an item to find it's successor: ");
	    	            			item = keyboard.nextLine();
	    	            			String successor = null;
	    	            			try {
	    	            				successor = (String) theTree.getSuccessor(item);
	    	            			}catch (DynamicSetException e) {
	    	            				System.out.println(e.getMessage());
	    	            			}
	    	            			System.out.println("The items successor is " + successor);
	    	            			
	    	            		}
	    	            		
	            			}//End of Binary Tree While Loop
	            			theTree.clear();//Clears the Binary Tree
	            			
	            		}//End of Binary Tree If

	            		
	            	}//end of other while loop
	            		
	            }
		}//end of while loop
	}//end of main

	
	
	
	/*
	 * 
	 * Takes 4 Parameters an arrayList, Binary Search Tree, Doubly Linked List and SkipList.
	 * 
	 */
	public static void runtest(List<String> wordList,BSTDynamicSet theTree,DLLDynamicSet theDLList) {
		long Start = 0;
		long End = 0;
		long results = 0;
		///////////////////////////////////////////////////////////////////////////////////
		//																				//
		//							Doubly Linked List Runtimes						    //
		//////////////////////////////////////////////////////////////////////////////////
		//Insertion runtime
		long DLLInsertionMin = 0;
		long DLLInsertionMax = 0;
		long DLLInsertionAvg = 0;
		for (String element : wordList)
		{	Start = System.nanoTime();
			theDLList.insert(element);
			End = System.nanoTime();
			results = End - Start;
			//Calculate the runtime for each insertion
			DLLInsertionAvg = DLLInsertionAvg + results;
			if (DLLInsertionMin == 0)
				DLLInsertionMin = results;
			if (DLLInsertionMax == 0)
				DLLInsertionMax= results;
			if (DLLInsertionMin > results)
				DLLInsertionMin = results;
			if (DLLInsertionMax < results)
				DLLInsertionMax= results;
		}
		
		//Calculate Search,successor, and predecessor runtime
		long DLLSearchMin = 0;
		long DLLSearchMax = 0;
		long DLLSearchAvg = 0;
		long DLLSuccessorMin = 0;
		long DLLSuccessorMax = 0;
		long DLLSuccessorAvg = 0;
		long DLLPredecessorMin = 0;
		long DLLPredecessorMax = 0;
		long DLLPredecessorAvg = 0;
		for (int i = 0; i < 10; i++){
			int searchkey = (int)(Math.random()*wordList.size());
			//Calculate the runtime for search
			String elem = wordList.get(searchkey);
			Start = System.nanoTime();
			String Dllelem = (String) theDLList.search(elem);
			End = System.nanoTime();
			results = End - Start;
			DLLSearchAvg = DLLSearchAvg + results;
			if (DLLSearchMin == 0)
				DLLSearchMin = results;
			if (DLLSearchMax == 0)
				DLLSearchMax= results;
			if (DLLSearchMin > results)
				DLLSearchMin = results;
			if (DLLSearchMax < results)
				DLLSearchMax= results;
			
			//Calculate the runtime for successor
			try {
				Start = System.nanoTime();
				String Dllelem2 = (String) theDLList.successor(elem);
				End = System.nanoTime();
				results = End - Start;
				DLLSuccessorAvg = DLLSuccessorAvg + results;
				if (DLLSuccessorMin == 0)
					DLLSuccessorMin = results;
				if (DLLSuccessorMax == 0)
					DLLSuccessorMax= results;
				if (DLLSuccessorMin > results)
					DLLSuccessorMin = results;
				if (DLLSuccessorMax < results)
					DLLSuccessorMax= results;
			}catch (DynamicSetException e) {
				System.out.println(e.getMessage());
			}
			
			//Calculate the runtime for predecessor
			try {
				Start = System.nanoTime();
				String Dllelem3 = (String) theDLList.predecessor(elem);
				End = System.nanoTime();
				results = End - Start;
				DLLPredecessorAvg = DLLPredecessorAvg + results;
				if (DLLPredecessorMin == 0)
					DLLPredecessorMin = results;
				if (DLLPredecessorMax == 0)
					DLLPredecessorMax= results;
				if (DLLPredecessorMin > results)
					DLLPredecessorMin = results;
				if (DLLPredecessorMax < results)
					DLLPredecessorMax= results;
			}catch (DynamicSetException e) {
				System.out.println(e.getMessage());
			}
		}
		
		//Calculate Minimum
		long DLLMinimumAvg = 0;
		Start = System.nanoTime();
		String Dllelem = (String)theDLList.minimum();
		End = System.nanoTime();
		DLLMinimumAvg = End - Start;
		
		//Calculate Maximum
		long DLLMaximumAvg = 0;
		Start = System.nanoTime();
		String Dllelem2 = (String)theDLList.maximum();
		End = System.nanoTime();
		DLLMaximumAvg = End - Start;
		
		
		theDLList.clear();
		///////////////////////////////////////////////////////////////////
		//						End of the Doubly Linked List Runtest   //
		///////////////////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////////
		//						BST Runtest      					   //
		////////////////////////////////////////////////////////////////
		//Insertion runtime
		long BSTInsertionMin = 0;
		long BSTInsertionMax = 0;
		long BSTInsertionAvg = 0;
		for (String element : wordList)
		{	Start = System.nanoTime();
			theTree.insert(element);
			End = System.nanoTime();
			results = End - Start;
			//Calculate the runtime for each insertion
			BSTInsertionAvg = BSTInsertionAvg + results;
			if (BSTInsertionMin == 0)
				BSTInsertionMin = results;
			if (BSTInsertionMax == 0)
				BSTInsertionMax= results;
			if (BSTInsertionMin > results)
				BSTInsertionMin = results;
			if (BSTInsertionMax < results)
				BSTInsertionMax= results;
		}
		
		//Calculate Search,successor, and predecessor runtime
		long BSTSearchMin = 0;
		long BSTSearchMax = 0;
		long BSTSearchAvg = 0;
		long BSTSuccessorMin = 0;
		long BSTSuccessorMax = 0;
		long BSTSuccessorAvg = 0;
		long BSTPredecessorMin = 0;
		long BSTPredecessorMax = 0;
		long BSTPredecessorAvg = 0;
		for (int i = 0; i < 10; i++){
			int searchkey = (int)(Math.random()*wordList.size());
			//Calculate the runtime for search
			String elem = wordList.get(searchkey);
			Start = System.nanoTime();
			String BSTelem = (String) theTree.search(elem);
			End = System.nanoTime();
			results = End - Start;
			BSTSearchAvg = BSTSearchAvg + results;
			if (BSTSearchMin == 0)
				BSTSearchMin = results;
			if (BSTSearchMax == 0)
				BSTSearchMax= results;
			if (BSTSearchMin > results)
				BSTSearchMin = results;
			if (BSTSearchMax < results)
				BSTSearchMax= results;
			
			//Calculate the runtime for successor
			Start = System.nanoTime();
			try {
				String BSTelem2 = (String) theTree.getSuccessor(elem);
			}catch (DynamicSetException e) {
				System.out.println(e.getMessage());
			}
			End = System.nanoTime();
			results = End - Start;
			BSTSuccessorAvg = BSTSuccessorAvg + results;
			if (BSTSuccessorMin == 0)
				BSTSuccessorMin = results;
			if (BSTSuccessorMax == 0)
				BSTSuccessorMax= results;
			if (BSTSuccessorMin > results)
				BSTSuccessorMin = results;
			if (BSTSuccessorMax < results)
				BSTSuccessorMax= results;
			
			//Calculate the runtime for predecessor
			Start = System.nanoTime();
			try {
				String Dllelem3 = (String) theTree.getPredecessor(elem);
			}catch (DynamicSetException e) {
				System.out.println(e.getMessage());
			}
			End = System.nanoTime();
			results = End - Start;
			BSTPredecessorAvg = BSTPredecessorAvg + results;
			if (BSTPredecessorMin == 0)
				BSTPredecessorMin = results;
			if (BSTPredecessorMax == 0)
				BSTPredecessorMax= results;
			if (BSTPredecessorMin > results)
				BSTPredecessorMin = results;
			if (BSTPredecessorMax < results)
				BSTPredecessorMax= results;
		}
		
		//Calculate Minimum
		long BSTMinimumAvg = 0;
		Start = System.nanoTime();
		String BSTelem = (String)theTree.minimum();
		End = System.nanoTime();
		BSTMinimumAvg = End - Start;
		
		//Calculate Maximum
		long BSTMaximumAvg = 0;
		Start = System.nanoTime();
		String BSTelem2 = (String)theTree.maximum();
		End = System.nanoTime();
		BSTMaximumAvg = End - Start;
		
		theTree.clear();
		/////////////////////////////////////////////////////////////////////
		//					End of BST Runtest							   //
		/////////////////////////////////////////////////////////////////////
		
		//Prints the table
		int n = wordList.size() + 1;
		System.out.println("N = " + n);
		String Test = wordList.get(50);
		TablePrinter table = new TablePrinter("    ","   Insert   ","   Search   ","   Successor   ", "   Predecessor   ", "   Minimum   ", "   Maximum   ");
		//FIRST ROW
		table.addRow("DLL","Min:"+ DLLInsertionMin/10E9 +"s" +" Max:"+DLLInsertionMax/10E9 +"s"+ " AVG: " +(DLLInsertionAvg/10000)/10E9+"s"
						  ,"Min: "+ DLLSearchMin/10E9 +"s"+" Max: "+DLLSearchMax/10E9+ "s"+ " AVG: " +(DLLSearchAvg/10000)/10E9+"s"
						  ,"Min: "+ DLLSuccessorMin/10E9 +"s"+" Max: "+DLLSuccessorMax/10E9+"s"+ " AVG: " +(DLLSuccessorAvg/10000)/10E9+"s"
						  ,"Min: "+ DLLPredecessorMin/10E9 +"s"+" Max: "+DLLPredecessorMax/10E9+"s"+ " AVG: " +(DLLPredecessorAvg/10000)/10E9+"s"
						  ,""+DLLMinimumAvg/10E9+"s"
						  ,""+DLLMaximumAvg/10E9+"s");
		//Second ROW
		table.addRow("      " ,"    ","   ",  "   ",  "   ",  "   ",  "   ");
		table.addRow("BST","Min:"+ BSTInsertionMin/10E9 +"s" +" Max:"+BSTInsertionMax/10E9 +"s"+ " AVG: " +(BSTInsertionAvg/10000)/10E9+"s"
				  ,"Min: "+ BSTSearchMin/10E9 +"s"+" Max: "+BSTSearchMax/10E9+"s"+ " AVG: " +(BSTSearchAvg/10000)/10E9+"s"
				  ,"Min: "+ BSTSuccessorMin/10E9+"s" +" Max: "+BSTSuccessorMax/10E9 +"s"+ " AVG: " +(BSTSuccessorAvg/10000)/10E9+"s"
				  ,"Min: "+ BSTPredecessorMin/10E9+"s" +" Max: "+BSTPredecessorMax/10E9+ "s"+ " AVG: " +(BSTPredecessorAvg/10000)/10E9+"s"
				  ,""+BSTMinimumAvg/10E9 +"s"
				  ,""+BSTMaximumAvg/10E9 +"s");
		
		table.print();
		
	}

}
