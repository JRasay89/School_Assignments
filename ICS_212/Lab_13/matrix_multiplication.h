//////////////////////////////////////////////////////////
// matrix_multiplicatio.h                 November 2011 //
//														//
// Author: John Benedick Rasay							//
//////////////////////////////////////////////////////////
/////////////////////////////////////////////////////
//                                                 //
//           Function Declerations                 // 
//                                                 //     
/////////////////////////////////////////////////////
void matrix_multiplication(int **first_matrix, int **second_matrix, int **result_matrix, int first_row, int column_row, int second_column);
int **matrix_allocator(int row, int column);
void matrix_deallocator(int **matrix);