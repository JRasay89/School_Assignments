//include std. libraries
#include <stdio.h>
#include <stdlib.h>
#include "quicksort.h"

//Defined Windows and MSDOS
#define Windows
#define MSDOS

//Conditional Inclusion
#if (defined Windows || defined MSDOS)
#define System "Dear Windows user, the result is "
#else
#define System "Dear Universal user, the result is "
#endif

//main function
int main()
{
	//define a simple int array
	int v[10] = {20,5,6,7,10,15,0,2,3,4};

	//sort the array using quicksort
	quick_sort(v, 0, 9, &ascending_sorter);

	//print result, the sorted array
	for(int i = 0; i < 10; i++)
		printf("%i \n", v[i]);

    //binary search
    int searched = binsearch(20, v, 10);
    if (searched == -1)
        printf("\nThe element is not in the array");
    else
        printf("\n%s%d\n",System,searched);//Prints the search element location with conditional inclusion

    printf("\n\nPress any key to continue.");
	getchar();
	exit(0);

}

//quicksort function: a recursive algorith which sorts a given array
void quick_sort(int v[], int left, int right, int(*compare)(int, int)) // This is a function pointer -> int(*compare)(int, int)
{
	//do nothing, if array contains less than 2 elements
	if (left >= right)
		return;

	//choose a partition element, and move it to v[0]
	swap(v, left, (left+right)/2);
	int last = left;

	//partition the sets into two subsets
	for (int i = left+1; i <= right; i++)
	{
		if (compare(v[i], v[left]) == true)//call the function pointer for sort-order
			swap(v, ++last, i);
	}
	swap(v, left, last);//restore the partition element to its previous location

	//call quicksort for these 2 subsets
	quick_sort(v, left, last-1, compare);
	quick_sort(v, last+1, right, compare);

}

//swap function: interchange v[i] and v[j]
void swap(int v[], int i, int j)
{
	int temp;

	temp = v[i];
	v[i] = v[j];
	v[j] = temp;
}

//*****Actually, our customers will write these functions in the future, and use them
//return true if first_arg > second arg
int ascending_sorter(int first_arg, int second_arg)
{
	if (first_arg > second_arg)
		return -1;
	else
		return 1;
}

//return true if first_arg < second arg
int descending_sorter(int first_arg, int second_arg)
{
	if (first_arg < second_arg)
		return -1;
	else
		return 1;
}

//binary search function
int binsearch(int x, int v[], int n)
{
	int low, high, mid;

	low = 0;
	high = n - 1;
	while (low <= high){
		mid = (low+high)/2;
		if (x <v[mid])
			high = mid - 1;
		else if (x > v[mid])
			low = mid + 1;
		else //found a match
			return mid;
	}
	return -1; //found no match
}
