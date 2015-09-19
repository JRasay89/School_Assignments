#include <stdlib.h>
#include <stdio.h>
#include <conio.h>

int main(){
	int birthYear;
	double height;
	double weight;
	int age;
	double meterheight;
	double weightkg;

	printf("Please enter your birth year, height(inches) and weight(lbs):");
	scanf("%d %lf %lf", &birthYear, &height, &weight);

	printf("\nYour birth year is: %d\n", birthYear);
    printf("Your height is: %6.2f inches \n", height);
	printf("Your weight is: %6.2f pounds\n", weight);
	
	age = 2011 - birthYear;
	meterheight = height * .0254;
	weightkg = weight*0.45359237;

	printf("\nYou are %d years old, your height is %2.2f meters and your weight is %2.2f kg\n", age, meterheight, weightkg);

    printf("\n\nPress any key to exit.");
	getch();
	return EXIT_SUCCESS;
}

