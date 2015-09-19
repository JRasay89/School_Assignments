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
		ArrayList<MyDaemonThread> daemonThreadList = new ArrayList<MyDaemonThread>(); 
		
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
				
				//If user enters job command
				if (command.equals("jobs") && !(tokens[tokens.length - 1].equals("&"))) {
					Iterator iterator = daemonThreadList.iterator();
					int i = 0;
					for (MyDaemonThread threadIt: daemonThreadList) {
					    System.out.println("[ " + i + " ]  " + threadIt.getThreadName());
					    i++;
					}
					continue;
				}
				
				//If User enters fg command
				if (tokens[0].equals("fg") && !(tokens[tokens.length - 1].equals("&"))) {
					try {
						try {
							daemonThreadList.get(Integer.parseInt(tokens[1])).join();
						} catch (InterruptedException e) {
				     	}
					
					} catch ( IndexOutOfBoundsException e ) {
					    System.err.println("Error: No Such Job\n");
					}
				
					continue;
				}
				
			    //Change current directory to home if user just types "cd"
				if (command.equals("cd") ) {
					directory = System.getProperty("user.home");
					continue;
				}
				//Change current directory
				if (tokens[0].equals("cd") && !(tokens[tokens.length - 1].equals("&"))) {
					
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


				System.err.println("Starting command "+tokens[0]+"...\n");
				//put the process in the foreground.
				if (tokens[tokens.length - 1].equals("&")) {
					if (tokens[0].equals("cd")) { //Prints an error if user put cd in background
						System.err.println("Error: cannot put " + tokens[0] + " in the background");
						continue;
					}
					else if (tokens[0].equals("jobs")) { //Prints an error if user put jobs in background
						System.err.println("Error: cannot put " + tokens[0] + " in the background");
						continue;
					}
					else if (tokens[0].equals("fg")) { //Prints an error if user put fg in background
						System.err.println("Error: cannot put " + tokens[0] + " in the background");
						continue;
					}
					else {
						String[] tokens2 = new String[tokens.length - 1];
						stringArrayCopy(tokens, tokens2);
						new MyDaemonThread(p,pb,directory,tokens2,daemonThreadList).start();
					}
				}
				else {
					try {
						pb = new ProcessBuilder(tokens); //Creates new process builder
						pb.directory(new File(directory)); //Sets the directory to the current directory
						p = pb.start(); //Starts process builder
						//Input Streams
						InputStream  sstderr = p.getErrorStream();
						InputStream  sstdout = p.getInputStream();
						PrintStream ps = new PrintStream(System.out);
						
						//Creates a new thread for input and error
						PipeThread2 p1 = new PipeThread2(sstdout, ps);
						PipeThread2 p2 = new PipeThread2(sstderr, ps);
						p1.start();
						p2.start();
						try { 
					       	p2.join();
					     	} catch (InterruptedException e) {
					     	}
	              
			    	} catch (IOException e) {
			      	System.err.println(Arrays.toString(tokens) +": Command not found");
			      	} //End of second try catch block
				
				   p.destroy(); //Destroy process
				}
			}//End of the while loop
		} catch (IOException e) {
			System.err.println (e.getMessage());
		}  //End of first try catch block
	}//End of main
	
	public static void stringArrayCopy(String[] x, String[] y) {
		for (int i = 0; i < (x.length -1); i++) {
			y[i] = x[i];
		}
	}

}//End class
