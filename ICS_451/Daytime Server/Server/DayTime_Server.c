/*
 * John Rasay
 * ICS 451
 * HW 8
 * Prof. Ravi Narayan
*/
#include <stdio.h>	//For input and output such as perror(), snprintf()
#include <stdlib.h> //Standard library
#include <time.h> //For functions getting the time
#include  <string.h> //For functions such as strlen()
#include <unistd.h> //For write() function
#include <sys/socket.h> //For creating socket, binding, listening and accepting
#include <arpa/inet.h> //For creating internet address structure


int main(int argc, char* argv[])
{
	//argc contains the number of arguments given, argv contains the arguments
	//If there is a port number given
	if (argc > 1) {
	
		int port; //The port number of this program.
		port = atoi(argv[1]); //Convert the given argument into an int.
		char buff[128]; //Holds the date and time.
		time_t dt; //The date and time.
		
		//Creating the socket.
		int listener_d = socket(PF_INET, SOCK_STREAM, 0);
		//Check for error with the socket.
		if (listener_d < 0) {
			perror("Socket Error");
			return 1;
		}
		//Create the address stucture.
		struct sockaddr_in name;
		name.sin_family = PF_INET;
		name.sin_port = (in_port_t)htons(port);
		name.sin_addr.s_addr = htonl(INADDR_ANY);
		//Bind the address structure with the socket.
		//Print a bind error if something goes wrong.
		if (bind(listener_d, (struct sockaddr *) &name, sizeof(name)) < 0) {
			perror("Bind Error");
			return 1;
		}
		
		//Set the socket to listen with a max queue of 10.
		//10 clients can try and connect to the server at once.
		//Print a listen error if something goes wrong.
		if (listen(listener_d, 10) < 0) {
			perror("Listen Error");
			return 1;
		}
		//Print the port number that the server is listening from.
		printf("Listening for connection at port: %d\n",port);
		puts("Waiting for connection...");
    	
    	//Wait for clients to connect.
    	//Loop until program is ended by user.
		while(1) {
			//Creating structure to hold the address of client.
			struct sockaddr_storage client_addr;
		    unsigned int address_size = sizeof(client_addr);
		    //Accept the client connection, and get the secondary socket for conversation.
		    int connect_d = accept(listener_d, (struct sockaddr *) &client_addr, &address_size);
		    //Print an error if cannot accept connection.
		    if (connect_d < 0) {
		    	perror("Accept Error");
		    	return 1;
		    }
		     
		    dt = time(NULL); //Get the current calendar time
		    snprintf(buff, sizeof(buff), "%.24s\r\n", ctime(&dt)); //Redirect the print output to buff
		    write(connect_d, buff, strlen(buff)); //Write the time contained in buff into the socket
		    close(connect_d);//Close the connection
		}
    }
    
    //Print an error if no port number was given.
    else {
    	puts("Error: No Port number was given.");
    }
    
    return 0; 
}
