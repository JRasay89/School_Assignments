import java.io.*;
import java.util.*;
import java.lang.ProcessBuilder;
/**
 * 
 * @author John Benedick Rasay
 * @school University Of Hawaii
 * @Class ICS332
 *
 */
public class JSH {

	public static void main(String[] args) throws IOException {
		String command;
		String[] tokens;
		ProcessBuilder pb = null; 
		Process p = null;
		String directory = System.getProperty("user.home");
		
		//Getting user inputs
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader console = new BufferedReader(isr);
		
		try {
			while(true) { //Loop until user types exit or ^D
				System.out.print("JSH> ");
				command = console.readLine(); //Read for user inputs
				
				//Check if input is null i.e when user types ^D
				if (command == null) {
					System.out.println("Exiting.....");
					System.exit(0);
				}
				
				command = command.trim(); //Trim for spaces
				tokens = command.split("[ \t\n]+");	//Store inputs in tokens
				
				//Exit when user types exit
				if (command.equals("exit")){
					System.out.println("Exiting.....");
					System.exit(0);
				}
				
				//Continue if user enters empty string
				if (command.equals("")) {
					continue;
				}
				
			    //Change current directory to home if user just types "cd"
				if (command.equals("cd")) {
					directory = System.getProperty("user.home");
					continue;
				}
				//Change current directory
				if (tokens[0].equals("cd")) {
					
					String path = tokens[1];
					//Check if its an absolute path
					if (path.contains("/")) {
					
						File filePath = new File(tokens[1]);
						if (!filePath.exists()) {
							System.out.println("Error: No such file or directory");
							continue;
						}
						else {
							directory = tokens[1];
						}
					}
					//Checks if its a relative path
					else {
						System.out.println(directory + "/" + tokens[1]);
						File filePath = new File(directory + "/" + tokens[1]);
						if(!filePath.exists())
						{
							System.out.println("Error: No such file or directory");
							continue;
						} else {
							directory = directory + "/" + tokens[1];
						}
					}
					continue;
				
				}


				System.err.println("Starting command "+tokens[0]+"...");
				try {
					pb = new ProcessBuilder(tokens); //Creates new process builder
					pb.directory(new File(directory)); //Sets the directory to the current directory
					p = pb.start(); //Starts process builder
					//Input Streams
					InputStream  sstderr = p.getErrorStream();
					InputStream  sstdout = p.getInputStream();
		      
					//Print out the output
					BufferedReader stdout = new BufferedReader(new InputStreamReader(sstdout));
					String line;
					while ((line = stdout.readLine()) != null){
	                  System.out.println(line);
					}
	              
					//Print out the error
					BufferedReader stderr = new BufferedReader(new InputStreamReader(sstderr));
					while ((line = stderr.readLine()) != null) {
						System.out.println(line);
					}
					
					//Closes the input streams
					sstderr.close();
					sstdout.close();
	              
			    } catch (IOException e) {
			      System.err.println(Arrays.toString(tokens) +": Command not found");
			      } //End of second the try catch block
				
				p.destroy(); //Destroy process
		    
			}//End of the while loop
		} catch (IOException e) {
			System.err.println (e.getMessage());
		  }  //End of first try catch block
	}//End of main

}//End class
