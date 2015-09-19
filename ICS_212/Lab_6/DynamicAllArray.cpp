#include <stdio.h>
#include <stdlib.h>

//////////////////////////////////////////
//     Dynamically Allocated Array      //
/////////////////////////////////////////

int main(){
    int size = 10;
    int *arraypt;
    arraypt = (int *) malloc(size * sizeof(int));
    for (int elem = 0; elem < 10; elem++) //adds random ints
        arraypt[elem] = rand();
    for (int i = 0; i < size; i++){
        printf("The address of arraypt[%d] is %d\n", i, &arraypt[i]);
        printf("The value of arraypt[%d] is %d\n", i, arraypt[i]);
    }
    free(arraypt);

    printf("\n\npress any key to exit.");
	getchar();
	exit(0);
}