#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

//function declaration
int myatoi (char s[]);
double atof(char s[]);
void itoa(int n, char s[]);
void reverse(char s[]);

int main() {

    //Test to convert a string to an integer
	char strVariable[] = " 33.33";
	int iTemp = 1;
	iTemp *= myatoi(strVariable);
	printf("\nString converted to an integer result: %d \n", iTemp);
	
    //Test to convert a string to a double
    char dstrVariable[] = "334.55";
    double dTemp = 1;
    dTemp *= atof(dstrVariable);
    printf("\nString converted to a double result: %6.2f \n" , dTemp);

	//Test to convert Numbers to characters
    char strVariable2[10];
    int itemp = 334;
    itoa(itemp, strVariable2);
    printf("\nNumber converted to a character: %s\n\n", strVariable2);
    

	printf("Press any key to continue.");
	getchar(); getchar();
	exit(0);

}

//itoa: converts array to integer
int myatoi (char s[]){
	int i, result, sign;

	//skip white space
	for (i=0; isspace(s[i]); i++)
		;

	//get the sign of the integer using terniary
	sign = (s[i] == '-') ? -1 : 1;
	if (s[i] == '+' || s[i] == '-')
		i++;

	//get the numeric digits
	for (result=0; isdigit(s[i]); i++){
		result = 10 * result +(s[i] - '0');
		printf("debug %d \n", result);
	}
	return sign * result;
}

//convert string s to double
double atof(char s[]){
    double val, power;
    int i, sign;

    //skips white space
    for (i = 0; isspace(s[i]); i++);

    sign = (s[i] == '-') ? -1: 1;
    if (s[i] == '+' || s[i] == '-')
        i++;

    for (val = 0.0; isdigit(s[i]); i++)
        val = 10.0 * val + (s[i] - '0');
    if (s[i] == '.')
        i++;
    for (power = 1.0; isdigit(s[i]); i++) {
        val = 10.0 * val + (s[i] - '0');
        power *= 10.0;
    }
    return sign * val / power;
}

//convert n to characters in s
void itoa(int n, char s[]){
    int i, sign;

    if ((sign = n) < 0) //record sign
        n = -n; //make n positive 
    i = 0;
    do { // generate digits in reverse order
        s[i++] = n % 10 + '0'; //get next digit
    } while ((n /= 10) > 0); //delete it
    if (sign < 0)
        s[i++] = '-';
    s[i] = '\0';
    reverse(s);
}

//reverse string s in place
void reverse(char s[]){
    int c, i, j;

    for (i = 0, j = strlen(s)-1; i < j; i++, j--) {
        c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}
