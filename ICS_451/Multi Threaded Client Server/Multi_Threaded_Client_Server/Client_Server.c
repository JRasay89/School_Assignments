/*
 * John Rasay
 * ICS 451
 * HW 9 Client Program
 * Prof. Ravi Narayan
*/
#include <stdio.h> //For input and output such as perror()
#include <stdlib.h> //Standard library
#include <sys/socket.h> //For creating socket, binding, listening and accepting, reading and writing to socket functions
#include <arpa/inet.h> //For creating internet address structure
#include <string.h> //For functions such as strcpy, memset

int main(int argc, char* argv[])
{
	//If number of given argument is 3
	if (argc == 3) {
		int port; //The port number of the server to connect to.
		port = atoi(argv[1]); //Convert the given argument into an int.
		char addr[256]; //Will contain the address of the server to connect to
		strcpy(addr, argv[2]); //Convert argv to string
		char buff[256]; //Will contain the data sent from server
		//Creating the socket for client
		int s = socket(PF_INET, SOCK_STREAM, 0);
		//Creating the address struct for the server to connect to
		struct sockaddr_in si;
		memset(&si, 0, sizeof(si)); //Make sure that the struct is empty
		si.sin_family = PF_INET; //Setting the address family of the socket address
		si.sin_addr.s_addr = inet_addr(addr); //Setting the address
		si.sin_port = htons(port); //Setting the Port number
		
		//Connect to the server and print error if connection fails
		if(connect(s, (struct sockaddr *) &si, sizeof(si)) < 0) {
		    perror("Connect Error: ");
		    return 1;
		}
		
		//Loop and read from the socket until there is no more data being sent 
		while ( read(s, buff, sizeof(buff)) > 0) {
			printf("%s\n", buff); //print the data that is read from the socket
		}


    }
    
    //Print an error if number of given arguments is incorrect.
    else {
    	fprintf(stderr,"Error: Incorrect number of arguments.\n");
    }

    return 0;
}
