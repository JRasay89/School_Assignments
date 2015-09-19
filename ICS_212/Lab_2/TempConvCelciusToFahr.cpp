#include <stdio.h>
#include <stdlib.h>

/*
 * Fall 2011
 * Professor: Julia Patriarche
 * This programs takes 3 command line arguments, which will be used in converting Celsius values into Fahrenheit
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
	upper = atoi (argv[2]); //upper = 30, it is the number to be converted
	step = atoi (argv[3]); //step = 20, subtract from upper until upper is less than lower

	//step 3: convert from farenheit to celcius using a while loop

    printf("\n*******************************\n");
    printf("* Celcius To Fahrenheit Table *\n");
    printf("*******************************\n\n");
    printf("Celsius   Fahr\n");
    printf("_______________\n\n");

    celsius = upper;
	while (celsius >= lower) {
		fahr = 9 *celsius /5 +32;  //formula
		printf("%3.0f\t%6.2f\n", celsius, fahr); //print the result
        if(celsius == 0)
            printf("________________\n");
		celsius = celsius - step;
	}
	printf("\nPress any key to continue.");
	getchar();
	exit(0);

}
