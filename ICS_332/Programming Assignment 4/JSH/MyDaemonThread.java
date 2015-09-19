import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;


public class MyDaemonThread extends Thread {
	private Process subprocess;
	private ProcessBuilder pb;
	String directory;
	String[] tokens;
	String threadName = "";
	ArrayList<MyDaemonThread> daemonThreadList;
	String command;
	
	
	public MyDaemonThread(Process subprocess, ProcessBuilder pb, String directory, String[] tokens, ArrayList<MyDaemonThread> daemonThreadList, String command) {
		this.subprocess = subprocess;
		this.pb = pb;
		this.directory = directory;
		this.tokens = tokens;
		this.daemonThreadList = daemonThreadList;
		this.setDaemon(true);
		this.command = command;
		for (int i = 0; i < tokens.length; i++) {
			threadName = threadName + tokens[i] + " ";
		}
	}
	
	public String getThreadName() {
		return threadName;
	}
	
	public void setForeground() {
		this.setDaemon(false);
	}
	public void start() {
	      daemonThreadList.add(this);
	      super.start();
	}
	
	
	public void run() {
		if (command.contains("|")) { //If it is a pipe command
			tokens = command.split("[&|\t\n]+");	//Store inputs in tokens
			String[] commandTokens; //For the first command in the pipe
			String[] commandTokens2; //For the second command in the pipe
			Process p2 = null;
			Process p3 = null;
			boolean first_pipe = true; // Use for multiple pipes
			boolean switch_pipe = true; //Use for multiple pipes, for keeping track of previous process outputstream
			if (tokens.length == 0) {
				System.err.println("Syntax Error occured");
			}
			else {
				//Get the first command
				try {
					commandTokens = tokens[0].trim().split("[ \t\n]+");	//Store inputs in tokens
					pb = new ProcessBuilder(commandTokens); //Creates new process builder
					pb.directory(new File(directory)); //Sets the directory to the current directory
					subprocess = pb.start(); //Starts process builder
				}catch (IOException e) {
					System.err.println(tokens[0] +": Command not found\n");
		      		} //End of second try catch block
			}
			
				if (tokens.length == 2) { //If there is only one pipe
					for(int i = 0; i < tokens.length; i++) {
						try {
							commandTokens2 = tokens[i+1].trim().split("[ \t\n]+");
							p2 = new ProcessBuilder(commandTokens2).start();
							InputStream  sstderr = subprocess.getErrorStream();
							PrintStream ps = new PrintStream(System.out);
			        
			        
							PipeRedirect pipethread1 = new PipeRedirect(subprocess.getInputStream(), p2.getOutputStream());
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
			       
								PipeRedirect pipethread1 = new PipeRedirect(subprocess.getInputStream(), p2.getOutputStream());
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
										InputStream  sstderr = subprocess.getErrorStream();
										PrintStream ps = new PrintStream(System.out);
										PipeThread2 pipethreadError = new PipeThread2(subprocess.getErrorStream(), ps);
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
										InputStream  sstderr = subprocess.getErrorStream();
										PrintStream ps = new PrintStream(System.out);
										PipeThread2 pipethreadError = new PipeThread2(subprocess.getErrorStream(), ps);
										PipeThread2 pipethread2 = new PipeThread2(p2.getInputStream(), ps);
										pipethread2.start();
										try {
											pipethreadError.join();
											pipethread2.join(); 	
											} catch (InterruptedException e) {
											}
										break;
									}//End of if loop
									switch_pipe = true;
									
									
								}catch (IOException e) {
									System.err.println(tokens[i] +": Command not found");
									break;	
								}
							}//End of inner else if
						}
					}//End of for loop
				
				}//End of outer else if
		}//End of main if block for pipes
		
		else {//If is a normal single command
			try {
				pb = new ProcessBuilder(tokens); //Creates new process builder
				pb.directory(new File(directory)); //Sets the directory to the current directory
				subprocess = pb.start(); //Starts process builder
				subprocess.waitFor();
				//Input Streams
				InputStream  sstderr = subprocess.getErrorStream();
				InputStream  sstdout = subprocess.getInputStream();
				PrintStream ps = new PrintStream(System.out);
	
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
			  catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			  }
		}
	  daemonThreadList.remove(this);
	}
}
