#include <stdlib.h>
#include <stdio.h>

//Define Linked List ADT
struct Node {
	Node *pPreviousNode;
	void *pData;
	Node *pNextNode;
};

struct LinkedList {
    Node *pFirstNode;
    Node *pLastNode;
};

struct StudentInfo{
	int id;
	char name[30];
	char lastName[30];
	char birthPlace[30];
	short birthYear;
};

void insertAfter(Node *newNode, Node *node) { //insert newNode after node
	newNode -> pPreviousNode = node;
	newNode -> pNextNode = node ->pNextNode;
	(node -> pNextNode)->pPreviousNode = newNode;
	node ->pNextNode = newNode;

}

void deleteNode(Node *node) {
	(node->pPreviousNode) ->pNextNode = node->pNextNode;
	(node->pNextNode) -> pPreviousNode = node ->pPreviousNode;
	free(node);//Free the Node
}

int main(){
//Step 1: Construct our Linked List with sentinel nodes
LinkedList *myLinkedList = (LinkedList *) malloc(sizeof(LinkedList));

Node *firstNode = (Node *) malloc(sizeof(Node)); //sentinel for start node
Node *lastNode = (Node *) malloc(sizeof(Node)); //sentinel for end node

firstNode ->pPreviousNode = NULL;
firstNode->pData = NULL;
firstNode->pNextNode = lastNode;
myLinkedList->pFirstNode = firstNode;

lastNode->pPreviousNode = firstNode;
lastNode->pData = NULL;
lastNode->pNextNode = NULL;
myLinkedList->pLastNode = lastNode;

//Step 2: Open file for reading, and read the records one by one, and insert into LinkedList one by one
FILE *fileStudent;
fileStudent = fopen("C:\\Users\\JohnBen\\Desktop\\ICS212\\lab06\\student.txt","r");
char line[150];
while (fgets(line, 150, fileStudent)) {
	if(line[0] == 'I') //skip the first line, the header line
		continue;
	StudentInfo *student = (StudentInfo *) malloc(sizeof(StudentInfo));
	sscanf(line, "%i %s %s %s %i", &student->id, student->name, student->lastName, student->birthPlace, &student->birthYear );

	Node *newNode = (Node *) malloc(sizeof(Node));
	newNode->pData = student;
	insertAfter(newNode, myLinkedList->pFirstNode);
}
fclose(fileStudent);

//Delete the birthYear greater than 1995
Node *delNode = myLinkedList->pFirstNode;
delNode = delNode->pNextNode;
StudentInfo *thedata;
while (delNode->pNextNode != NULL){
    thedata = (StudentInfo *)delNode->pData;
    if (thedata->birthYear >= 1995)
        deleteNode(delNode->pPreviousNode);
    delNode = delNode->pNextNode;
}

//step 3: Open a new file for writing. Then iterate through the nodes of LinkedList, write them to the file one by one
FILE *fileStudentResult;
fileStudentResult = fopen("C:\\Users\\JohnBen\\Desktop\\ICS212\\lab06\\studentResult.txt","w"); //open file for writing
Node  *tempNode = myLinkedList->pFirstNode;
tempNode = tempNode->pNextNode;//skip the first sentinel node
StudentInfo  *info;
while (tempNode->pNextNode != NULL){
	info = (StudentInfo *)tempNode->pData; //casting the pData to StudentInfo
    fprintf(fileStudentResult,"%i \t %s \t %s \t %s \t %i \n", info->id, info->name, info->lastName, info->birthPlace, info->birthYear); //print to the file
	tempNode = tempNode->pNextNode; //go to the next node
}
printf("\n\npress any key to exit.");
getchar();
exit(0);
}
