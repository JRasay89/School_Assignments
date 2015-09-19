#include <stdlib.h>
#include <stdio.h>

int main(){
    int arrayofints[] = {2,3,6,8,10,20,5,6,7,9}; //An array of integers
    int *intptr;
    for (int i = 0; i < 10; i++){
        intptr = &arrayofints[i];
        printf("The address of arrayofints[%d] is %d\n", i, intptr);
        printf("The value of arrayofints[%d] is %d\n", i, *intptr);
    }
    printf("\n\npress any key to exit.");
	getchar();
	exit(0);
}
