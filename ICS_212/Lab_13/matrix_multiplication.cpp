//////////////////////////////////////////////////////////
// matrix_multiplication                 November 2011  //
//														//
// Author: John Benedick Rasay							//
//////////////////////////////////////////////////////////
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#include "matrix_multiplication.h"

int main()
{
	int firstmatrix_row = 5;							//The row of the first matrix
	int column_row = 2;							        //The Column of the first matrix and the row of the second matrix 
	int secondmatrix_col = 3;							//The column of the second matrix
    
    //The first Matrix
    int **matrix_1 = matrix_allocator(firstmatrix_row, column_row);
    //Initialize the values of the matrix with random numbers between 0 and 10
    srand(time(NULL));
    for (int i = 0; i < firstmatrix_row; i++)
        for (int j = 0; j < column_row; j++)
            matrix_1[i][j] = rand() % 10;

    //The second Matrix
    int **matrix_2 = matrix_allocator(column_row, secondmatrix_col);
    //Initialize the values of the matrix with random numbers between 0 and 10
    for (int i = 0; i < column_row; i++)
        for (int j = 0; j < secondmatrix_col; j++)
            matrix_2[i][j] = rand() % 10;

    //The result Matrix
    int **result_matrix = matrix_allocator(firstmatrix_row, secondmatrix_col);

	//Matrix Multiplication function: Multiplies the first and second matrix and display the result
    matrix_multiplication(matrix_1, matrix_2, result_matrix, firstmatrix_row, column_row, secondmatrix_col);

    //Deallocate the memory
    matrix_deallocator(matrix_1);
    matrix_deallocator(matrix_2);
    matrix_deallocator(result_matrix);


	printf("\nPress any key to continue: \n");
	getchar();
	exit(0);
}
