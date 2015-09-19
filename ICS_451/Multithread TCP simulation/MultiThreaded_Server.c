/*
 * John Rasay
 * ICS 451
 * HW 10 MultiThreaded Server
 * Prof. Ravi Narayan
*/

/*  Version 2
	Currently The three way handshake is implemented and sending and saving a small text file and images
	Still need to implement the timer and dropping packets
*/
#include <stdio.h>	//For input and output such as perror(), snprintf()
#include <stdlib.h> //Standard library
#include <time.h> //For functions getting the time
#include  <string.h> //For functions such as strlen()
#include <sys/socket.h> //For creating socket, binding, listening and accepting, reading and writing to socket functions
#include <arpa/inet.h> //For creating internet address structure
#include <pthread.h> //Library for creating threads 

#define MSS 1524 //The Maximum Size of Segments
//The tcp header struct
typedef struct tcpheader {
	unsigned short int tcph_srcport; //16bit
    unsigned short int tcph_dstport; //16bit
    unsigned int tcph_seqnum; //32bit
    unsigned int tcph_acknum; //32 bit
    unsigned char tcph_offset:4; //4bit
    unsigned char tcph_reserved:6; //6bit
    unsigned char tcph_flags:6; //6bit
    unsigned short int tcph_win; //16bit
    unsigned short int tcph_chksum; //16bit
    unsigned short int tcph_urgptr; //16bit
} tcph;

/*
 *Function to be run in a thread
 *@param s is the socket to be used
 *@return null
*/
void* funcThread(void* s) {
	int sock = *(int*)s; //Typecast void pointer to int and dereference it
	char packetbuff[MSS];
	char recvBuff[MSS];
	tcph *header = (tcph *) packetbuff; //Store the tcp header in the packet
	tcph *recvHeader = (tcph *) recvBuff;
	
	//char buff[256]; //Holds the date and time.
	//time_t dt; //The date and time.
	//dt = time(NULL); //Get the current calendar time
    //snprintf(buff, sizeof(buff), "%.24s\r\n", ctime(&dt)); //Redirect the print output to buff
    //write(sock, buff, strlen(buff)); //Write the time contained in buff into the socket
    
    recv(sock, recvBuff, sizeof(recvBuff),0); //Receive the Clients SYN
    while(1) {
    	//First step of the handshake, receive a SYN from client
		if (recvHeader->tcph_flags == 2) {
			srand((unsigned)time(NULL));
			int randomNum = (rand() % (70000 - 60000 )) + 60000;
			header->tcph_acknum = htonl(ntohl(recvHeader->tcph_seqnum)+1); //Set the ack number to equal to the seqnum + 1
			header->tcph_seqnum = htonl(randomNum);
			header->tcph_flags = 18; //Set both the Syn and Ack flags to 1
			printf("\nSecond Packet To Send\n");
			printf("Flag Number: %d\n", header->tcph_flags);
			printf("Sequence Numer: %d\n", ntohl(header->tcph_seqnum));
			printf("Ack Number: %d\n", ntohl(header->tcph_acknum));
			send(sock,packetbuff,sizeof(packetbuff),0); //Send the SYN and ACK to client
			int s = recv(sock, recvBuff, sizeof(recvBuff),0); //Receive the clients ACK 
			
		}
		
		//The final part of the handshake, If the client sends the ack and server receives it
		else if (recvHeader->tcph_flags == 16) {		
			//Gets the next packet from client
			printf("\nThird Packet Receieved\n");
			printf("Flag Number: %d\n", recvHeader->tcph_flags);
			printf("Sequence Numer: %d\n", ntohl(recvHeader->tcph_seqnum));
			printf("Ack Number: %d\n", ntohl(recvHeader->tcph_acknum));
			printf("\nConnected Established!\n"); //The handshake is finish
			//recv(sock, recvBuff, sizeof(recvBuff),0); //Get the clients ACK packet
			
			FILE *fp = fopen("sfile.gif","w"); //Open a textfile to store data receive from client
			char fileSize[1024]; //Holds the size of the file
			recv(sock, fileSize, sizeof(fileSize),0); //Receive the first packet size from client
			int size = atoi(fileSize); //Convert fileSize into int
			printf("\nSize of file is: %d\n", size);
			char *data = recvBuff + sizeof(tcph);
			
			//Loop if there are more than one data packet to be received
			while(recvHeader->tcph_flags == 16) {
				//Simulating a drop packet to be implemented
				
				recv(sock, recvBuff, sizeof(recvBuff),0); //Receives the data
				printf("\nWRITING FILE!\n");			
				fwrite(data,1,size,fp);
				//Printing a message when the packet with the data receive
				printf("\nHeader of the packet receive with the data!\n");
				printf("Flag Number: %d\n", recvHeader->tcph_flags);
				printf("Sequence Numer: %d\n", ntohl(recvHeader->tcph_seqnum));
				printf("Ack Number: %d\n", ntohl(recvHeader->tcph_acknum));
	
				
				//Send the ack to the Client
				header->tcph_acknum = htonl(ntohl(recvHeader->tcph_seqnum)+1); //Set the ack number to equal to the seqnum + 1
				header->tcph_seqnum = recvHeader->tcph_acknum;
				header->tcph_flags = 16; //Set the Ack flag

				send(sock,packetbuff,sizeof(packetbuff),0); //Send an ack after receiving data
				
				//If the size is less than the maximum size of the data buffer, it means there are no more data to be read and so exit the loop
				if (size < (MSS-sizeof(tcph))) {
					break;
				}
				recv(sock, fileSize, sizeof(fileSize),0); //Receives the size of the next data to write 
				size = atoi(fileSize);
				printf("\nSize of file is: %d\n", size); //Prints the size of the data to be written
				
			}
			printf("\nFILE Written\n");
			fclose(fp);
			recv(sock, recvBuff, sizeof(recvBuff),0); //Receives the FIN from client

		}
		//IF client sends fin close the connection
		else if (recvHeader->tcph_flags == 1) {	
			header->tcph_acknum = htonl(ntohl(recvHeader->tcph_seqnum)+1); //Set the ack number to equal to the seqnum + 1
			header->tcph_seqnum = recvHeader->tcph_acknum;
			header->tcph_flags = 17; //Set both the Syn and Ack flags to 1
			printf("\nSending Packet With ACK+FIN\n");
			printf("Flag Number: %d\n", header->tcph_flags);
			printf("Sequence Numer: %d\n", ntohl(header->tcph_seqnum));
			printf("Ack Number: %d\n", ntohl(header->tcph_acknum));
			send(sock,packetbuff,sizeof(packetbuff),0); //Send the Servers Header
			recv(sock, recvBuff, sizeof(recvBuff),0); //Get the clients response header
			printf("\nFinal Packet Received\n");
			printf("Flag Number: %d\n", recvHeader->tcph_flags);
			printf("Sequence Numer: %d\n", ntohl(recvHeader->tcph_seqnum));
			printf("Ack Number: %d\n", ntohl(recvHeader->tcph_acknum));
			printf("\nCLOSING CONNECTION!\n");
			return 0;
		}
	}
    close(sock);//Close the connection
	return NULL;
}

//This program should be run with the following cmd arguments in the following order
//
//	./MuliThreaded_Server <destport>
//
int main(int argc, char* argv[])
{
	//argc contains the number of arguments given, argv contains the arguments
	//If there is a port number given
	if (argc == 2) {
	
		int port; //The port number of this program.
		port = atoi(argv[1]); //Convert the given argument into an int.
		pthread_t thread; //Creating the thread
		//Creating the socket.
		int listener_d = socket(PF_INET, SOCK_STREAM, 0);
		//Check for error with the socket.
		if (listener_d < 0) {
			perror("Socket Error: ");
			return 1;
		}
		//Create the address stucture.
		struct sockaddr_in name;
		name.sin_family = PF_INET; //Setting the address family of the socket address
		name.sin_port = (in_port_t)htons(port);
		name.sin_addr.s_addr = htonl(INADDR_ANY); //Setting the address
		//Bind the address structure with the socket.
		//Print a bind error if something goes wrong.
		if (bind(listener_d, (struct sockaddr *) &name, sizeof(name)) < 0) {
			perror("Bind Error: ");
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
		    	perror("Connect Error: ");
		    	return 1;
		    }
		    //Starting the thread and return error if fails to start
		    if (pthread_create(&thread, NULL,funcThread, (void*) &connect_d) < 0) {
		    	perror("Pthread Create Error: ");
		    	return 1;
		    } 
		   
		}
    }
    
    //Print an error if number of given arguments is incorrect.
    else {
    	fprintf(stderr,"Error: Incorrect number of arguments.\n");
    }
    
    return 0; 
}
