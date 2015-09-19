#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "employee.h"


int main(){
    Employee *pEmp = (Employee *) malloc(sizeof(Employee));
    pEmp->homeAddress = (Address *) malloc(sizeof(Address));
    pEmp->workAddress = (Address *) malloc(sizeof(Address));

    //Filling in a value for Employee and Address(home & work)
    strcpy(pEmp->firstName, "John");
    strcpy(pEmp->lastName, "Rasay");
    pEmp->height = 5.10;
    //Home Address
    strcpy(pEmp->homeAddress->street,"1724 C North School St");
    strcpy(pEmp->homeAddress->city,"Honolulu");
    strcpy(pEmp->homeAddress->state,"Hi");
    strcpy(pEmp->homeAddress->country,"United States");
    pEmp->homeAddress->zipcode = 96819;
    //Work Address
    strcpy(pEmp->workAddress->street,"98-1005 Moanalua Road Aiea");
    strcpy(pEmp->workAddress->city,"Honolulu");
    strcpy(pEmp->workAddress->state,"Hi");
    strcpy(pEmp->workAddress->country,"United States");
    pEmp->workAddress->zipcode = 96701;

    //Prints the info of the Employee
    printinfo(pEmp);
    
    //Free memory
    destructor(pEmp);


	printf("\n\nPress any key to continue.");
	getchar();
	exit(0);

}

void printinfo(Employee *info){
       
    printf("First Name: %s\n", info->firstName);
    printf("Last Name: %s\n", info->lastName);
    printf("Height: %2.2f\n", info->height);

    printf("\nHome Address\n");
    printf("Street: %s\n",info->homeAddress->street);
    printf("city: %s\n",info->homeAddress->city);
    printf("State: %s\n",info->homeAddress->state);
    printf("Country: %s\n",info->homeAddress->country);
    printf("ZipCode: %d\n",info->homeAddress->zipcode);

    printf("\nWork Address\n");
    printf("Street: %s\n",info->workAddress->street);
    printf("city: %s\n",info->workAddress->city);
    printf("State: %s\n",info->workAddress->state);
    printf("Country: %s\n",info->workAddress->country);
    printf("ZipCode: %d\n",info->workAddress->zipcode);
}

void destructor(Employee *info){
    free(info);
}
