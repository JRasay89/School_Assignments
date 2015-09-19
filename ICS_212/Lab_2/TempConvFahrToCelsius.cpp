#include <stdio.h>
#include <stdlib.h>
/*
 * Fall 2011
 * Professor: Julia Patriarche
 * This programs takes 3 command line arguments, which will be used in converting Fahrenheit values into Celsius
*/


int main(int argc, char *argv[])
{
	//step 0: get user parameters
	printf ("We have been passed %d parameters \n", argc);
	for (int i = 0; i < argc; i++) {
		printf("argument %d : %s \n", i, argv[i]);
	}
	//step 1: define variables
	float fahr, celsius;
	int lower, upper, step;

	//step2: inialize variables
	lower = atoi (argv[1]); //lower = 0
	upper = atoi (argv[2]); //upper = 300
	step = atoi (argv[3]); //step = 20

	//step 3: convert from farenheit to celcius using a while loop

    printf("\n*******************************\n");
    printf("* Fahrenheit To Celcius Table *\n");
    printf("*******************************\n\n");
    printf("Fahr   Celcius\n");
    printf(".............\n\n");
    fahr = lower;
	while (fahr <= upper) {
		celsius = 5 * (fahr-32) / 9;  //formula
        if(fahr == 300 && celsius == 148){
            printf("..............\n");
        }
		printf("%3.0f\t%6.2f\n", fahr, celsius); //print the result
		fahr = fahr + step;
	}
	printf("\nPress any key to continue.");
	getchar();
	exit(0);

}
