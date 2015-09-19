/*
 * John Rasay
 * ICS 451
 * HW 10 Client Program
 * Prof. Ravi Narayan
*/

/*  Version 2
	Currently The three way handshake is implemented and sending and saving a small text file and images
	Still need to implement the timer and dropping packets
*/
#include <stdio.h> //For input and output such as perror()
#include <stdlib.h> //Standard library
#include <sys/socket.h> //For creating socket, binding, listening and accepting, reading and writing to socket functions
#include <arpa/inet.h> //For creating internet address structure
#include <string.h> //For functions such as strcpy, memset
#include <sys/time.h> //For functions and structs such as timeval
#include <unistd.h> 

#define MSS 1524 //The Maximum Size of Segment File size + Header size

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

//This program should be run with the following cmd arguments in the following order
//
//	./Client_Server <destport> <destIP> <name of file>
//
int main(int argc, char* argv[])
{
	
	//If number of given argument is 3
	if (argc > 3) {
		
		char filename[128]; //Holds the filename that is going to be sent to the server
		strcpy(filename, argv[3]); //Convert argv to string
		//Create a tcp header
		char packetbuff[MSS]; //Holds the tcp request header
		char recvBuff[MSS];
		tcph *header = (tcph *) packetbuff;
		tcph *recvHeader = (tcph *) recvBuff;
		int handShakeSteps = 1; //To keep track of the handhshake steps
		int port; //The port number of the server to connect to.
		port = atoi(argv[1]); //Convert the given argument into an int.
		char addr[256]; //Will contain the address of the server to connect to
		strcpy(addr, argv[2]); //Convert argv to string
		char buff[256]; //Will contain the data sent from server
		//Creating the socket for client
		int s = socket(PF_INET, SOCK_STREAM, 0);
		int length;
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
		
		length = sizeof(si);
		getpeername(s, (struct sockaddr *)&si, &length);
		printf("Connecting to Server Port: %d \n", ntohs(si.sin_port));
		getsockname(s, (struct sockaddr *)&si, &length);
		printf("This Client's Port is: %d \n", ntohs(si.sin_port));
		
		//Creating random number for syn
    	srand((unsigned)time(NULL));
    	int randomNum = (rand() % (90000 - 80000 )) + 80000;
		//Filling the tcp header
		getsockname(s, (struct sockaddr *)&si, &length); //Getting the client source port
		header->tcph_srcport = si.sin_port;
		header->tcph_dstport = htons(port);
		header->tcph_seqnum = htonl(randomNum);
		header->tcph_acknum = 0;
		header->tcph_offset = 0;
		header->tcph_reserved = 0;
		header->tcph_flags = 2; // Set SYN to 1
		header->tcph_win = htons(1500);
		header->tcph_chksum = 0; //Setting the checksum to 0
		header->tcph_urgptr = 0; //Setting the urgent pointer to 0
		
		//Printing the header information of the SYN packet				
		printf("Initial Header to be send with SYN flag on\n");
		printf("Flag Number: %d\n", header->tcph_flags);
		printf("Sequence Numer: %d\n", ntohl(header->tcph_seqnum));
		printf("Ack Number: %d\n\n", ntohl(header->tcph_acknum));
    	//Loop until connection finish
		while(1) {
			//The initial part of the handshake, send SYN to server
			if (header->tcph_flags == 2) {
				//Sending the packet with SYN flag
				if (send(s,packetbuff,sizeof(packetbuff),0) < 0) {
					printf("send error\n");
				}
				//Printing the ACK header receive from the Server
				printf("SYN and ACK Packet Receive From Server\n");
				recv(s, recvBuff, sizeof(recvBuff),0); //Receive the SYN and ACK from Server
				header->tcph_flags = recvHeader->tcph_flags; //Change the clients header
				printf("Flag Number: %d\n", recvHeader->tcph_flags);
				printf("Sequence Numer: %d\n", ntohl(recvHeader->tcph_seqnum));
				printf("Ack Number: %d\n", ntohl(recvHeader->tcph_acknum));
				
			}
			
			//IF the SYN+ACK is received from the server, Now send an ack
			else if(recvHeader->tcph_flags == 18) {
				header->tcph_acknum = htonl(ntohl(recvHeader->tcph_seqnum)+1); //Set the ack number to equal to the seqnum + 1
				header->tcph_seqnum = recvHeader->tcph_acknum;
				header->tcph_flags = 16; //Set ack flag only
				printf("\nSending the ACK to the Server To finish handshake\n");
				printf("Flag Number: %d\n", header->tcph_flags);
				printf("Sequence Numer: %d\n", ntohl(header->tcph_seqnum));
				printf("Ack Number: %d\n", ntohl(header->tcph_acknum));
				send(s,packetbuff,sizeof(packetbuff),0); //Sends the ack to finish the handshake
				printf("\nConnected Established!\n");
				
				//Start sending the data and set a timer
				struct timeval tv;
				tv.tv_sec = 10;
				//tv.tv_usec = 0;
				fd_set readfds;

				char *data = packetbuff + sizeof(tcph);
				FILE *fp = fopen(filename,"r");
				char fileSize[1024]; //Send the file size info to the server
				int size = 0; //Holds the size info of the file as an int
				int returnedVal = 0; //Holds the return value of select
				do {
					size = fread(data,1, (MSS-sizeof(tcph)), fp);
					sprintf(fileSize,"%d", size); //Store size into a char buffer
					send(s,fileSize,sizeof(fileSize),0); //Send the file size to the server
					printf("\nFILE SIZE IS: %d\n", size);
					//printf("\nMSS - sizeof tcph is: %d\n", (int)(MSS-sizeof(tcph)));
					if (size <= 0) { //Stops the loop when there is no more data to be read
						break;
					}
					//Loop until data is receive and an ack is sent
					do {
						FD_ZERO(&readfds);
						FD_SET(s, &readfds);
						send(s,packetbuff,sizeof(packetbuff),0);
						returnedVal = select(s+1,&readfds, NULL,NULL, &tv); //If no data is received
						if (returnedVal == 0) { 
							printf("\nCONNECTION HAS TIMED OUT, RESENDING!\n");
						}
					} while (returnedVal == -1 || returnedVal == 0);
					if (returnedVal > 0) {
						recv(s, recvBuff, sizeof(recvBuff),0); //Receive the ack from Server
						header->tcph_acknum = htonl(ntohl(recvHeader->tcph_seqnum)+1); //Set the ack number to equal to the seqnum + 1
						header->tcph_seqnum = recvHeader->tcph_acknum;
						//Print the ACK received from the server when it receive the data
						printf("\nACK PACKET RECEIVED FROM THE SERVER\n");
						printf("Flag Number: %d\n", header->tcph_flags);
						printf("Sequence Numer: %d\n", ntohl(header->tcph_seqnum));
						printf("Ack Number: %d\n", ntohl(header->tcph_acknum));
					}
				} while (size == (MSS-sizeof(tcph)));
				printf("\nFINISH SENDING\n");
				fclose(fp); //Close the file
				
				//Send a FIN to the server to close the connection
				header->tcph_flags = 1; //Set the flag to only have FIN
				send(s,packetbuff,sizeof(packetbuff),0); //Sends FIN flag to server
				
				//Receiving the SYN+ACK
				recv(s, recvBuff, sizeof(recvBuff),0);
				printf("Packet Receive from Server with FIN+ACK\n");
				printf("Flag Number: %d\n", recvHeader->tcph_flags);
				printf("Sequence Numer: %d\n", ntohl(recvHeader->tcph_seqnum));
				printf("Ack Number: %d\n", ntohl(recvHeader->tcph_acknum));

			}
			//Close the connection
			else if (recvHeader->tcph_flags = 17) {
				header->tcph_acknum = htonl(ntohl(recvHeader->tcph_seqnum)+1); //Set the ack number to equal to the seqnum + 1
				header->tcph_seqnum = recvHeader->tcph_acknum;
				header->tcph_flags = 16; //Set ack flag only
				printf("\nFinal Packet To Be Sent\n");
				printf("Flag Number: %d\n", header->tcph_flags);
				printf("Sequence Numer: %d\n", ntohl(header->tcph_seqnum));
				printf("Ack Number: %d\n", ntohl(header->tcph_acknum));
				send(s,packetbuff,sizeof(packetbuff),0); //Sends the final ack to the server to close the connection
				printf("\nCLOSING CONNECTION!\n");
				return 0;
			}
		}
		

    }
    
    //Print an error if number of given arguments is incorrect.
    else {
    	fprintf(stderr,"Error: Incorrect number of arguments.\n");
    }

    return 0;
}
