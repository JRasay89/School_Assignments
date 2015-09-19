/*
 *John Rasay
 *Homework 5
 *ICS 451
 *Prof. Ravi Narayan
*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    //TCP header struct
    typedef struct tcp_header {
        unsigned short int tcph_srcport; //16bit
        unsigned short int tcph_dstport; //16bit
        unsigned int tcph_seqnum; //32bit
        unsigned int tcph_acknum; //32 bit
        unsigned char tcph_offset:4; //4bit
        unsigned char tcph_reserved:6; //6bit
        unsigned char tcph_flags:6; //6bit
        unsigned char
                tcph_URG:1, //1bit
                tcph_ACK:1,
                tcph_PSH:1,
                tcph_RST:1,
                tcph_SYN:1,
                tcph_FIN:1;
        unsigned short int tcph_win; //16bit
        unsigned short int tcph_chksum; //16bit
        unsigned short int tcph_urgptr; //16bit
    } tcp_header;


    //Creating the tcp header struct
    tcp_header tcph;
    tcp_header tcpr; //Response header struct
    tcp_header temp; //Temp struct used for reading response header


    //Function to print the header
    int printHeader() {\
        printf("\n*****REQUEST TCP HEADER*****\n");
        printf("Source Port: %d\n",tcph.tcph_srcport);
        printf("Destination Port: %d\n",tcph.tcph_dstport);
        printf("Sequence Number: %d\n",tcph.tcph_seqnum);
        printf("Acknowledgment Number: %d\n",tcph.tcph_acknum);
        printf("Data Offset: %d\n",tcph.tcph_offset);
        printf("Reserved: %d\n",tcph.tcph_reserved);
        printf("Flags: "); //Printing the Flags if they are set to 1
        if (tcph.tcph_URG == 1) {
            printf("URG ");

        }
        if (tcph.tcph_ACK == 1) {
            printf("ACK ");

        }
         if (tcph.tcph_PSH == 1) {
            printf("PSH ");

        }
         if (tcph.tcph_RST == 1) {
            printf("RST ");
        }
         if (tcph.tcph_SYN == 1) {
            printf("SYN ");
        }
         if (tcph.tcph_FIN == 1) {
            printf("FIN ");
        }
        printf("\n");
        printf("Window: %d\n",tcph.tcph_win);
        printf("Checksum: %02x\n",tcph.tcph_chksum); //Print the checksum in hex
        printf("Data Urgent Pointer: %d\n",tcph.tcph_urgptr);

        return 0; //Return 0 if successful
    }

    //Read the response file created and print the header to the screen
    //Takes in a file name to read
    //return 0 if success
    int readFile(char x[]) {
        FILE *fileptr;
        fileptr = fopen(x,"rb");
        fread(&temp,sizeof(tcp_header),1,fileptr);
        printf("\n*****RESPONSE TCP HEADER*****\n");
        printf("Source Port: %d\n",temp.tcph_srcport);
        printf("Destination Port: %d\n",temp.tcph_dstport);
        printf("Sequence Number: %d\n",temp.tcph_seqnum);
        printf("Acknowledgment Number: %d\n",temp.tcph_acknum);
        printf("Data Offset: %d\n",temp.tcph_offset);
        printf("Reserved: %d\n",temp.tcph_reserved);
        printf("Flags: "); //Printing the Flags if they are set to 1
        if (temp.tcph_URG == 1) {
            printf("URG ");

        }
        if (temp.tcph_ACK == 1) {
            printf("ACK ");

        }
         if (temp.tcph_PSH == 1) {
            printf("PSH ");

        }
         if (temp.tcph_RST == 1) {
            printf("RST ");
        }
         if (temp.tcph_SYN == 1) {
            printf("SYN ");
        }
         if (temp.tcph_FIN == 1) {
            printf("FIN ");
        }
        printf("\n");
        printf("Window: %d\n",temp.tcph_win);
        printf("Checksum: %02x\n",temp.tcph_chksum); //Print the checksum in hex
        printf("Data Urgent Pointer: %d\n",temp.tcph_urgptr);
        fclose(fileptr); //closing file
        return 0;
    }

    //Write to a file
    //Takes in a filename to write into, if no file is found then create it.
    //return 0 if success
    int writeFile(char x[]) {
        FILE *fileptr;
        fileptr = fopen(x,"wb");
        fwrite(&tcpr,sizeof(tcp_header),1,fileptr);
        fclose(fileptr); //closing file

        return 0;
    }

    //Opening the file given to be used to create a response header
    //File pointer
    FILE *f;
    //Use for index
    int i;
    //Use to hold binary data from the given bin file
    unsigned char buffer[20];
    //Open the bin file, rb is for reading binary file
    f = fopen("test2.bin","rb");
    //Read the bin file
    fread(buffer,sizeof(buffer),1,f);
    //Fill the struct
    //Getting the bit numbers for srcport, shifting the bits by 8 places to the left, and adding the other bytes to get 16 bit
    tcph.tcph_srcport = (buffer[0] << 8) | buffer[1];
    //Getting the bit numbers for dstport
    tcph.tcph_dstport = (buffer[2] << 8) | buffer[3];
    //Getting the bit number for seqnum
    tcph.tcph_seqnum = (buffer[4] << 24) | (buffer[5] << 16) | (buffer[6] << 8) | buffer[7];
    //Getting the bit number for acknum
    tcph.tcph_acknum = (buffer[8] << 24) | (buffer[9] << 16) | (buffer[10] << 8) | buffer[11];
    //Getting the bit number for offset
    tcph.tcph_offset = (buffer[12] >> 4);
    //Getting the bit number for reserved
    tcph.tcph_reserved = (buffer[12] <<2) | (buffer[13] >> 6);
    //Getting the bit number for the flags
    tcph.tcph_flags = buffer[13];
    tcph.tcph_URG = (tcph.tcph_flags >> 5);
    tcph.tcph_ACK = (tcph.tcph_flags >> 4);
    tcph.tcph_PSH = (tcph.tcph_flags >> 3);
    tcph.tcph_RST = (tcph.tcph_flags >> 2);
    tcph.tcph_SYN = (tcph.tcph_flags >> 1);
    tcph.tcph_FIN = tcph.tcph_flags;
    //Getting the bit number for win
    tcph.tcph_win = (buffer[14] << 8) | buffer[15];
    //Getting the bit number for chksum
    tcph.tcph_chksum = (buffer[16] << 8) | buffer[17];
    //Getting the bit number for urgptr
    tcph.tcph_urgptr = (buffer[18] << 8) | buffer[19];

    //Creating the Response header
    tcpr.tcph_srcport = tcph.tcph_dstport;
    tcpr.tcph_dstport = tcph.tcph_srcport;
    tcpr.tcph_seqnum = tcph.tcph_seqnum + 1; //Adding 1 to the seqnum
    tcpr.tcph_acknum = tcph.tcph_seqnum;
    tcpr.tcph_offset = tcph.tcph_offset;
    tcpr.tcph_reserved = tcph.tcph_reserved;
    tcpr.tcph_flags = tcph.tcph_flags;
    tcpr.tcph_URG = tcph.tcph_URG;
    tcpr.tcph_ACK = tcph.tcph_ACK;
    tcpr.tcph_PSH = tcph.tcph_PSH;
    tcpr.tcph_RST = tcph.tcph_RST;
    tcpr.tcph_SYN = tcph.tcph_SYN;
    tcpr.tcph_FIN = tcph.tcph_FIN;
    tcpr.tcph_win = tcph.tcph_win;
    tcpr.tcph_chksum = tcph.tcph_chksum;
    tcpr.tcph_urgptr = tcph.tcph_urgptr;

    //Calling the functions
    //Prints the TCP HEADER data
    printHeader();
    //Write response header to a new file using the name given
    writeFile("file.bin");
    //Read the response header from a file name given
    readFile("file.bin");

    //Prints the content of the given file
    printf("\n\nPrinting contents of the original file. \n");
    for (i = 0; i < 20; i++) {
        printf("%02x ",buffer[i]);
    }
    printf("\n");

    //Closing the file
    fclose(f);
    return 0;
}
