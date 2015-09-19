#include <stdio.h>
#include <stdlib.h>
void exercise1();
int main()
{
    printf("//////////////Exercise 1/////////////////\n");
    exercise1();
    
    printf("\n\n");
    printf("////////////Exercise 2//////////////\n");
	int arr[10]; //define an array
	for (int i = 0; i < 10; i++) //fill the array
		arr[i] = i;

	for (int i = 0; i < 10; i++){
		printf("// value: %i \t address: %i // \n", arr[i], &arr[i]);
	}
    printf("////////////////////////////////////\n");

	printf("\n\nPress any key to continue.");
	getchar(); getchar(); 
	exit(0);
}


void exercise1(){
	int x = 1;
	printf("Value of x: %i \n", x); // prints the content(value) of the variable
	printf("address of x: %i \n", &x); // print the address of x in decimal
	printf("address of x: %p \n", x); //print the address of x in hexadecimal

	int *ip; // ip is a pointer to int
	ip = &x; // ip now points to x

	printf("\n\n");

	printf("%-10s %-10s %-10s \n", "var", "address", "content");
	printf("%-10s %-10s %-10s \n", "---", "------", "------");
	printf("%-10s %-10i %-10i \n","x",     &x,        x);
	printf("%-10s %-10i %-10i \n", "ip",   &ip,       ip);
	printf("\nindirect access to the x using pointer: %i \n", *ip);

	printf("\nsize of int: %i \n", sizeof(x));
	printf("\nsize of pointer to int: %i \n\n", sizeof(ip));

}