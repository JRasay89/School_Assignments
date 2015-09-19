import java.io.*;
import java.util.*;
import java.net.Socket;

/**
 * RequesHandler.java handles the request from the client
 * @author John Rasay
 * @class ICS451
 */
public class RequestHandler implements Runnable {

	private Socket sock; //The socket and countAccess
	private int countAccess;
	
	//Constructor, takes a socket and the number of access the server has accepted
	public RequestHandler(Socket sock, int countAccess) {
		this.sock = sock;
		this.countAccess = countAccess;
		
	}
	
	public void run() {
		//Creating the readers, string variables, etc.
		InputStreamReader is;
		PrintStream os;
		BufferedReader reader;
		String request = "";
		String path = "";
		File file = null;
		StringTokenizer st;
		try { //Catch for any IOException
			is = new InputStreamReader(sock.getInputStream());
			os = new PrintStream(sock.getOutputStream());
			reader = new BufferedReader(is);
			request = reader.readLine(); //Get the request from client
			System.out.println("Server recieved request from client " + sock.getRemoteSocketAddress() + ": " + request);
			if (request != null) {
				st = new StringTokenizer(request); //tokenize the request 
				if (st.nextToken().equals("GET")) {
					path = st.nextToken(); //get the request path
				}
			}
			//If the request was date, print the date
			if (path.equals("/date")) {
				os.print("HTTP/1.1 200 OK \r\n");
	            os.print("Content-type: " + " " + getContentType(path) + "\r\n");
	            os.print("Server-name: Java Web Server \r\n");
				Date date = new Date();
				//Prints the date with a new line
				String response = ""+date+"";
				os.print("Content-length: " + response.length(  ) + "\r\n\r\n");
				os.println(response);
				os.flush();
				os.close(); //Closing the outputstream and socket
		        sock.close();
			}
			//If the request was count, print the number of times the server was access
			if (path.equals("/count")) {
				os.print("HTTP/1.1 200 OK \r\n");
	            os.print("Content-type: " + " " + getContentType(path) + "\r\n");
	            os.print("Server-name: Java Web Server \r\n");
	            String response = "Number of access to the server since started:  "+this.countAccess+"";
	            os.print("Content-length: " + response.length(  ) + "\r\n\r\n");
				os.println(response);
				os.flush();
				os.close(); //Closing the ouputstream and socket
		        sock.close();
			}
			//If no given path was given, send a default page index.html from home directory
			else if (path.equals("/")) {
				path = path.replace("/", File.separator); //Replace / with \ for windows path
				path = "home"+path+"index.html"; //home\index.html
				file = new File(path); //Get the file path
				int numOfBytes = (int) file.length(); //Get the length of the file
				FileInputStream fileInput; //Create a file input stream for sending the file
				byte[] fileInBytes = new byte[numOfBytes]; //create an array of bytes with size of the file
				fileInput = new FileInputStream(file); //Get the file to the input stream
				fileInput.read(fileInBytes); //Read the file in bytes
				os.print("HTTP/1.1 200 OK \r\n");
	            os.print("Content-type: " + " " + getContentType(path) + "\r\n");
	            os.print("Server-name: Java Web Server \r\n");
	            os.print("Content-length: " + numOfBytes + "\r\n\r\n");
	            os.write(fileInBytes,0,numOfBytes); //write the data to the client application
	            os.flush();
	            os.close(); //Closing the ouputstream and socket
	            sock.close();
			}
			//If a path is given
			else {
				path = path.replace("/", File.separator); //Replace / with \ for windows path
				path = "home"+path; //append home directory before the path
				file = new File(path); //get the file
				FileInputStream fileInput; //create fileinput stream
				byte[] fileInBytes; //create a byte array for holding the data to be sent in bytes
				int numOfBytes; //To hold length of the file to be sent
				try {
					if (path.endsWith(".html") || path.endsWith(".htm")) {
						numOfBytes = (int) file.length(); //Get the length of the file
						fileInBytes = new byte[numOfBytes];
						fileInput = new FileInputStream(file);//Create a file input stream for sending the file
						fileInput.read(fileInBytes);
						os.print("HTTP/1.1 200 OK \r\n");
			            os.print("Content-type: " + " " + getContentType(path) + "\r\n");
			            os.print("Server-name: Java Web Server \r\n");
			            os.print("Content-length: " + numOfBytes + "\r\n\r\n");
			            os.write(fileInBytes,0,numOfBytes);
			            os.flush();
			            os.close(); //Closing the ouputstream and socket
			            sock.close();
					}
					else if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
						numOfBytes = (int) file.length(); //Get the length of the file
						fileInBytes = new byte[numOfBytes];
						fileInput = new FileInputStream(file); //Create a file input stream for sending the file
						fileInput.read(fileInBytes);
						os.print("HTTP/1.1 200 OK \r\n");
			            os.print("Content-type: " + " " + getContentType(path) + "\r\n");
			            os.print("Server-name: Java Web Server \r\n");
			            os.print("Content-length: " + numOfBytes + "\r\n\r\n");
			            os.write(fileInBytes,0,numOfBytes);
			            os.flush();
			            os.close(); //Closing the ouputstream and socket
			            sock.close();
					}
					//Print error if file path is incomplete
					else {
						printError(os,"404","File Not Found");
					}
				} catch(FileNotFoundException fnfe) {      //When the file requested was not found.
					printError(os,"404","File Not Found"); //Print a 404 file not found when exception is caught.
				}
				
				
			}
		//Catch IOException
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Prints an error response message in html
	 * @param os is the output stream
	 * @param code is the error code number
	 * @param title is title of the error
	 */
	public static void printError(PrintStream os, String code, String title){
			os.print("HTTP/1.1 "+ code + " " + title+"\r\n");
			os.print("Content-type: text/html \r\n");
			os.print("Server-name: Java Web Server \r\n");
			String errorResponse = "<html>\n"+
								   "<head>\n"+
								   "<title> "+title+" </title>\n"+ 
								   "</head>\n"+
								   "<body>\n"+
								   "<h1> "+ code + " " + title +"</h1>\n"+
								   "</body>\n"+
								   "</html>\n";
	        os.print("Content-length: " + errorResponse.length()+"\r\n\r\n");
	        os.println(errorResponse);
	        os.flush();
	        os.close();
	}
	/**
	 *Gets the content type for the response
	 * @param path of the file
	 * @return the content type of the file data
	 */
	public static String getContentType(String path) {
		if (path.endsWith(".html") || path.endsWith(".htm")) {
			return "text/html";
		}
		else if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
			return "image/jpeg";
		}
		else {
			return "text/plain";
		}
	}

}
