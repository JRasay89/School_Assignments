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

	public static void main(String[] args) throws IOException, InterruptedException {
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
				
				//This is for programming Assignment 4 
				if ((command.contains("|")) && !(tokens[tokens.length - 1].equals("&"))) {
					tokens = command.split("[|\t\n]+");	//Store inputs in tokens
					String[] commandTokens; //For the first command in the pipe
					String[] commandTokens2; //For the second command in the pipe
					Process p2 = null;
					Process p3 = null;
					boolean first_pipe = true; // Use for multiple pipes
					boolean switch_pipe = true; //Use for multiple pipes, for keeping track of previous process outputstream
					if (tokens.length == 0) {
						System.err.println("Syntax Error occured");
						continue;
					}
					else {
						//Get the first command
						try {
							commandTokens = tokens[0].trim().split("[ \t\n]+");	//Store inputs in tokens
							pb = new ProcessBuilder(commandTokens); //Creates new process builder
							pb.directory(new File(directory)); //Sets the directory to the current directory
							p = pb.start(); //Starts process builder
						}catch (IOException e) {
							System.err.println(tokens[0] +": Command not found\n");
							continue;
				      		} //End of second try catch block
					}
					
						if (tokens.length == 2) { //If there is only one pipe
							for(int i = 0; i < tokens.length; i++) {
								try {
									commandTokens2 = tokens[i+1].trim().split("[ \t\n]+");
									p2 = new ProcessBuilder(commandTokens2).start();
									InputStream  sstderr = p.getErrorStream();
									PrintStream ps = new PrintStream(System.out);
					        
					        
									PipeRedirect pipethread1 = new PipeRedirect(p.getInputStream(), p2.getOutputStream());
									PipeThread2 errorThread = new PipeThread2(sstderr, ps);
									PipeThread2 pipethread2 = new PipeThread2(p2.getInputStream(), ps);
									pipethread1.start();
									errorThread.start();
									pipethread2.start();
									
									try {
										pipethread1.join();
										errorThread.join();
										pipethread2.join();
										
						     			} catch (InterruptedException e) {
						     			}
									}catch (IOException e) {
										System.err.println(tokens[i + 1] +": Command not found");
									}
									break;
							}
						}
						else if (tokens.length > 2){ // If more than one pipe
							for(int i = 1; i < tokens.length; i++) {
								if (first_pipe) { //For the first pipe
									try {
										commandTokens2 = tokens[i].trim().split("[ \t\n]+");
										p2 = new ProcessBuilder(commandTokens2).start();
					       
										PipeRedirect pipethread1 = new PipeRedirect(p.getInputStream(), p2.getOutputStream());
										pipethread1.start();
										try {
											pipethread1.join();
										}catch (InterruptedException e) {
										}
										first_pipe = false;
										}catch (IOException e) {
											System.err.println(tokens[i] +": Command not found");
											break;
										}
								}
								else { // For the other pipes
									if (switch_pipe) { //If this is the last command 
										try {

											commandTokens2 = tokens[i].trim().split("[ \t\n]+");
											p3 = new ProcessBuilder(commandTokens2).start();
										
											//InputStream  sstderr = p.getErrorStream();
											//PrintStream ps = new PrintStream(System.out);
										
										
											PipeRedirect pipethread1 = new PipeRedirect(p2.getInputStream(), p3.getOutputStream());
										
											//PipeThread2 pipethread2 = new PipeThread2(p3.getInputStream(), ps);
											pipethread1.start();
											//pipethread2.start();
											try {
											pipethread1.join();
											//pipethread2.join();

								       	
											} catch (InterruptedException e) {
											}
											
											if ((i + 1) == tokens.length){ //If this is the last command then print output
												InputStream  sstderr = p.getErrorStream();
												PrintStream ps = new PrintStream(System.out);
												PipeThread2 pipethreadError = new PipeThread2(p.getErrorStream(), ps);
												PipeThread2 pipethread2 = new PipeThread2(p3.getInputStream(), ps);
												pipethreadError.start();
												pipethread2.start();
												try {
													pipethreadError.join();
													pipethread2.join();
													
													} catch (InterruptedException e) {
													}
												break;
											}
											switch_pipe = false;

										}catch (IOException e) {
											System.err.println(tokens[i] +": Command not found");
											break;
										}
									}
									else if (!switch_pipe) {
										try {
											commandTokens2 = tokens[i].trim().split("[ \t\n]+");
											p2 = new ProcessBuilder(commandTokens2).start();
											PipeRedirect pipethread1 = new PipeRedirect(p3.getInputStream(), p2.getOutputStream());
											pipethread1.start();
											
											try {
											
												pipethread1.join();
												
												} catch (InterruptedException e) {
												}
											
											if ((i + 1) == tokens.length){ //If this is the last command then print output
												InputStream  sstderr = p.getErrorStream();
												PrintStream ps = new PrintStream(System.out);
												PipeThread2 pipethreadError = new PipeThread2(p.getErrorStream(), ps);
												PipeThread2 pipethread2 = new PipeThread2(p2.getInputStream(), ps);
												pipethread2.start();
												try {
													pipethreadError.join();
													pipethread2.join(); 	
													} catch (InterruptedException e) {
													}
												break;
											}
											switch_pipe = true;
											
											
										}catch (IOException e) {
											System.err.println(tokens[i] +": Command not found");
											break;	
										}
									}
								}
							}//End of for loop
						
						}//End of else if
					continue;
				}//End of main if block for pipes
				

				
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
						if(command.contains("|")) { //Check If the command being sent to the background is a piped command
							String[] tokens2 = new String[tokens.length - 1];
							tokens = command.split("[ \t\n]+");
							stringArrayCopy(tokens, tokens2);
							new MyDaemonThread(p,pb,directory,tokens2,daemonThreadList,command).start();
						}
						else {
							String[] tokens2 = new String[tokens.length - 1];
							stringArrayCopy(tokens, tokens2);
							new MyDaemonThread(p,pb,directory,tokens2,daemonThreadList,command).start();
						}
					}
				}
				else {
					System.err.println("Starting command "+tokens[0]+"...\n");
					try {
						pb = new ProcessBuilder(tokens); //Creates new process builder
						pb.directory(new File(directory)); //Sets the directory to the current directory
						p = pb.start(); //Starts process builder
						//Input Streams
						InputStream  sstderr = p.getErrorStream();
						InputStream  sstdout = p.getInputStream();
						PrintStream ps = new PrintStream(System.out);
						
						//Creates a new thread for input and error
						PipeThread2 pipe1 = new PipeThread2(sstdout, ps);
						PipeThread2 pipe2 = new PipeThread2(sstderr, ps);
						pipe1.start();
						pipe2.start();
						try { 
							pipe1.join();
					       	pipe2.join();
					     	} catch (InterruptedException e) {
					     	}
	              
			    	} catch (IOException e) {
			      	System.err.println(Arrays.toString(tokens) +": Command not found");
			      	} //End of second try catch block
				
					//p.destroy(); //Destroy process
				}
			}//End of the while loop
		} catch (IOException e) {
			System.err.println (e.getMessage());
		}  //End of first try catch block
	}//End of main
	
	//Copy Array of strings to a new Array of strings
	public static void stringArrayCopy(String[] x, String[] y) {
		for (int i = 0; i < (x.length -1); i++) {
			y[i] = x[i];
		}
	}
	

}//End class
