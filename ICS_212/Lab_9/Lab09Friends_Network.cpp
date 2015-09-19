#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void print_adjacencymatrix(int multiarray[][100], FILE *fileNew);

int main()
{
    int adjacency_matrix[100][100];//multidimensional array
    int *adjacency_list[100]; //array of pointers


    FILE *friendship;
    FILE *newFile;//Creates new file for Adjacency matrix result
    FILE *newFile2;//Creates new file for Adjacency_list results
    friendship = fopen("C:\\Users\\JohnBen\\Desktop\\ICS212\\friendship_network.txt", "r");
    newFile = fopen("C:\\Users\\JohnBen\\Desktop\\ICS212\\Adjacency_Matrix_Results.txt", "w");
    newFile2 = fopen("C:\\Users\\JohnBen\\Desktop\\ICS212\\Adjacency_List_Results.txt", "w");
    char line[150];
    while(fgets(line, 150, friendship) != NULL)
    {
        if (line[0] == 's') //skip the first line, the header line
			continue;
        char id[3]; char friends[50]; // variables for scanf
        sscanf(line, "%s %s", id, friends);

        //define one-dimensional array for adj_list
        int *arr_friends;
        arr_friends = (int *) malloc(sizeof(int));
        int friend_index = 0;
        
        //tokenize the friends column
		char *token;
		token = strtok(friends, "-");

        while (token != NULL  && strcmp(token,"NA"))
		{
			adjacency_matrix[atoi(id)][atoi(token)] = 1;//update adj_matrix if two are friends
            arr_friends[friend_index] = atoi(token); //Adds the friends to an array
            friend_index++;
            token = strtok(NULL, "-");
	    }

    }
    
    //Fill in the multidimensional array with 0's who are not friends with each other
    for(int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++){
            if (adjacency_matrix[i][j] != 1)
                adjacency_matrix[i][j] = 0;
        }
    }
    //A function that writes the adjacency matrix to a file
    print_adjacencymatrix(adjacency_matrix,newFile);


    printf("\n\nPress any key to exit.");
	getchar();
	exit(0);
}


//Prints the multidimensional array
void print_adjacencymatrix(int multiarray[][100], FILE *fileNew)
{
    for(int row = 0; row < 100; row++) {
        for (int column = 0; column < 100; column++){
            fprintf(fileNew," %d ", multiarray[row][column]);
        }
        fprintf(fileNew, "\n");
    }
}
