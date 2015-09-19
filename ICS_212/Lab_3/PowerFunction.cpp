#include <stdio.h>
#include <stdlib.h>

//declaration of the function
int power(int base, int n);
int factorial(int n);

//main function
int main() {
	printf("Power Function\n");
	for (int i = 0; i < 20; i++)
		printf ("2 ^ %d = %d\n", i, power(2, i) );
	printf("\n");
	printf("Factorial Function\n");
    for (int fact = 0; fact <= 10; fact++)
        printf("%d! = %d\n", fact, factorial(fact));

	printf("\nPress any key to continue.");
	getchar(); getchar();
	exit(0);

}

// definition of th efunction
//power: raise base to the power of n
int power(int base, int n) {

	int p, i;
	p = 1;
	for ( i = 1; i <= n; ++i)
		p = p * base;
    return p;
}

int factorial(int n) {
    int i, fact1;
    
    fact1 = 1;
    for (i = 2; i <= n; ++i)
        fact1 = fact1 * i;
    return fact1;
}