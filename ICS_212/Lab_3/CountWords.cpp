#include <stdio.h>
#include <stdlib.h>

#define IN 1 //inside a word
#define OUT 0 //outside a word

int main(int argc, char argv[]) {

	//step 1: define our variables
	int num_of_chars, num_of_words, num_of_space, num_of_lines, state;
	int digit[10];// store the number of digits from 0 to 9

	//step 2: initialize variables
	num_of_chars = num_of_words = num_of_space = num_of_lines = 0;
	for (int i=0; i < 10; i++)
		digit[i] = 0;

	//step 3: count the items within a loop
	int c;
	state = OUT; // at the beginning, we are outside a word
	while ((c = getchar()) !=EOF) {
		num_of_chars++;//increase the number of chars by one

		if (c >= '0' && c <= '9')//count the number of digits
			digit[c - '0']++;

		if (c == '\n')//increase the number of new lines by one
			num_of_lines++;

		if (c == ' ' || c == '\t'){ //increase the number of spaces by one
			num_of_space++;
		    state = OUT;
		}
		if (c == '\n') {
            break;
		}
		else if (state == OUT){ //increase the number of words by one, at the start of a new word
			num_of_words++;
			state = IN;
		}

	}
	//step 4: print-out the result, number of items
	printf("total number of characters %d \n", num_of_chars);
	printf("total number of words %d \n", num_of_words);
	printf("total number of lines %d \n", num_of_lines);
	printf("total number of spaces %d \n", num_of_space);
	printf("\nPress any key to continue.");
	getchar();
	exit(0);

}
