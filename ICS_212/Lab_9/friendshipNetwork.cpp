#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {

	//define two alternative data structure to store the friendship network
	int adjacency_matrix[100][100]; //multidimensional array
	int *adjacency_list[100]; //array of pointers

	//Read friendship_network file
	FILE *fileFriendship;
	fileFriendship = fopen("C:\\Users\\JohnBen\\Desktop\\ICS212\\friendship_network.txt","r");
	char line[150];
	while (fgets(line, 150, fileFriendship) != NULL)
	{
		if (line[0] == 's') //skip the first line, the header line
			continue;
		char id[3]; char friends[50]; // variables for scanf
		sscanf(line, "%s %s", &id, &friends);

		printf("%i \t", atoi(id));

		//tokenize the friends column
		char *token;
		token = strtok(friends, "-");
		while (token != NULL  && strcmp(token,"NA"))
		{
			printf("%i ",atoi(token));
			token = strtok(NULL, "-");
			adjacency_matrix[atoi(id)][atoi(token)] = 1;//update adj_matrix if two are friends
		}

	}

}
