#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <conio.h>

#include "word_length.h"



int main()
{
    struct word_length months[] ={ {"January", 0}, {"February", 0}, {"March", 0},{"April",0}, {"May",0}, {"June",0}, {"July",0}, {"August", 0}, 
                                   {"September", 0}, {"October", 0}, {"November", 0}, {"December",0 }};

    //Updates the length of each month
    countlength(months);

    //Output the month, length and addresses of each element
    printmonth(months);

    
    printf("\n\nPress any key to exit");
    getch();
    return EXIT_SUCCESS;

}



//Find the length of the month and update the length
void countlength(struct word_length month[])
{

        
    for (int countlength = 0; countlength < 12; countlength++)
    {
        int mlength = strlen(month[countlength].word);
        month[countlength].length = mlength;
    }

}


//Prints the month, length and address of the element
void printmonth(struct word_length month[])
{
    for (int monthlength = 0; monthlength < 12; monthlength++)
    {
        printf("----%s----\n", month[monthlength].word);
        printf("Length: %d\n", month[monthlength].length);
        printf("Address: %d\n\n", &month[monthlength]);
    }

}