//////////////////////////////////////////////////////////
// matrix_multiplication_functions       November 2011  //
//														//
// Author: John Benedick Rasay							//
//////////////////////////////////////////////////////////
#include <stdio.h>
#include <stdlib.h>

////////////////////////////////////////////////////////////////////
// matrix_allocator                                               //
//                                                                //
// Action:              Creates a two-dimensional dynamic array   //
//                                                                //
//                                                                //
// Parameter:  row:     The number of row the matrix will have    //
//             column:  The number of column the matrix will have // 
//                                                                //
// Return:     matrix                                             //
//                                                                //
////////////////////////////////////////////////////////////////////
int **matrix_allocator(int row, int column)
{
    int **matrix;
    matrix= new int*[row];
    for(int i = 0; i < row; i++)
        matrix[i] = new int[column];
    return matrix;
}


///////////////////////////////////////////////////////////////////
// matrix_deallocator                                            //
//                                                               //
// Action:               Deallocates the memory for the matrices //
//                                                               //    
//                                                               //
// Parameter:   matrix:  The matrix to be deallocated            //
//                                                               //
///////////////////////////////////////////////////////////////////
void matrix_deallocator(int **matrix)
{
    printf("\nThe matrix has been deleted\n");
    delete [] *matrix;
    delete [] matrix;
}



////////////////////////////////////////////////////////////////////////////////////////////
// matrix_multiplication                                                                  //
//                                                                                        //
//                                                                                        //
// Action:                       Multiplies two matrixes and display them and the result. //
//                                                                                        //
//                                                                                        //
// Parameter:  first_matrix:     The first matrix                                         //
//             second_matrix:    The second matrix                                        //
//             result_matrix:    The result matrix                                        //
//             first_row:        The row of the first matrix                              //
//             column_row:       The column of the first matrix                           //
//                               and row of the second matrix                             //
//             second_column:    The column of the second matrix                          //
//                                                                                        //
//                                                                                        //
// Return:     NONE                                                                       //
////////////////////////////////////////////////////////////////////////////////////////////
void matrix_multiplication(int **first_matrix, int **second_matrix, int **result_matrix, int first_row, int column_row, int second_column)
{
    //Displays the first matrix
    printf("The First Matrix\n");
    for (int i = 0; i < first_row; i++){
        printf("| ");
        for (int j = 0; j < column_row; j++)
            printf(" %i\t ", first_matrix[i][j]);
        printf("|\n");
    }

    printf("\n\n  (Multyplied by)   \n\n");

    //Displays the second matrix
    printf("The Second Matrix\n");
    for (int i = 0; i < column_row; i++){
        printf("| ");
        for (int j = 0; j < second_column; j++)
            printf(" %i\t ", second_matrix[i][j]);
        printf("|\n");
    }

    printf(" \n\n   (Equals)   \n\n");

    //Multiplies the first and second matrix and store the result in the result matrix
    for (int i = 0; i < first_row; i++){
        for (int z = 0; z < second_column; z++){
            int result = 0;
            for (int j = 0; j < column_row; j++){
                result += first_matrix[i][j] * second_matrix[j][z];
            }
            result_matrix[i][z] = result;
        }
    }

    //Displays the result after the first and second matrix has been multiplied
    printf("The Result\n");
    for (int i = 0; i < first_row; i++){
        printf("|");
        for (int j = 0; j < second_column; j++)
            printf(" %i\t ", result_matrix[i][j]);
        printf("|\n");
    }

}