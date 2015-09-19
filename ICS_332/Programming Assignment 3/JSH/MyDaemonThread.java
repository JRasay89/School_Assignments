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
	
	
	public MyDaemonThread(Process subprocess, ProcessBuilder pb, String directory, String[] tokens, ArrayList<MyDaemonThread> daemonThreadList) {
		this.subprocess = subprocess;
		this.pb = pb;
		this.directory = directory;
		this.tokens = tokens;
		this.daemonThreadList = daemonThreadList;
		this.setDaemon(true);
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
		try {
			pb = new ProcessBuilder(tokens); //Creates new process builder
			pb.directory(new File(directory)); //Sets the directory to the current directory
			subprocess = pb.start(); //Starts process builder
			//Input Streams
			InputStream  sstderr = subprocess.getErrorStream();
			InputStream  sstdout = subprocess.getInputStream();
			PrintStream ps = new PrintStream(System.out);
			//Creates new thread for output and error
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
		 daemonThreadList.remove(this);
	}
}
