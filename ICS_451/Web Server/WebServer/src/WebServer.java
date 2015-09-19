import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * WebServer.java is the main program of this web server
 * @author John Rasay
 * @class ICS451
 */
public class WebServer {

		private ServerSocket serverSock; //The server socket
		private static int countAccess = 0; //For counting the number of access
	
	/**
	 *  This method is used to start the server application
	 * @param portNum is the port number that the server will be listening in to.
	 */
	public static void go(int portNum) {
		try {
			//Creates a new server socket, makes it listen to a port number which is the value from portNum
			ServerSocket serverSock = new ServerSocket(portNum);
			//Socket variable that will hold the socket connection between server and client
			Socket sock;
			
		//Loop until application is stop
		while (true) {
			//Waits for a client socket connection, and returns a socket connection once a client connects
			sock = serverSock.accept();
			countAccess++; //Increment whenever the server makes a connection
			//Prints to the screen that a client has connected with their IP address
			System.out.println("Client " + sock.getRemoteSocketAddress() + " has connected.");
			//Create a new runnable instance and pass socket to the instance along with the number of access the server received
			RequestHandler handlerThread = new RequestHandler(sock,countAccess);
			//Create a new thread and pass the runnable instance
			Thread t = new Thread(handlerThread);
			//Starts the thread
			t.start();
			
		}
		//Catch any I/O errors, and print the stack trace 
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	//The main program
	public static void main(String[] args) {
		//Takes the value of args[0], and convert it to an int
		//to be used for the port number for this server application.
		int portNum = Integer.parseInt(args[0]);
		//Prints a waiting for connection message with new line
		System.out.println("Waiting For Connection....");
		//Starts the Webserver, and a port number is pass which
		//will be used to listen for client request
		WebServer.go(portNum);
	}
}
