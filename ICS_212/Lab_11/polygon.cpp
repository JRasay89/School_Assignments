#include <iostream>
#include <math.h>
#include "polygon.h"
#include <stdio.h>
#include <stdlib.h>

//***Global Variables***
Rectangle myRectangle(5,7);
Triangle myTriangle(1,2,3,7);

//************DEFINITIONS For Rectangle Class*****************


//Constructor of Rectangle
Rectangle::Rectangle(int req_width, int req_height)
{
	width = req_width;
	height = req_height;
	printf("\nRectangle Constructed. \n");
}

//Destructor of Rectangle
Rectangle::~Rectangle()
{
	printf("\nRectangle Destroyed. \n");
}

//implement area function
int Rectangle::area()
{
	int area = width * height;
	return area;
}

//implement perimeter function
int Rectangle::perimeter()
{
	printf("I can access the Rectangle height %i \n", myTriangle.height);
	int perimeter = 2 *(width + height);
	return perimeter;
}

//implement print_area_perimeter function
void Rectangle::print_area_perimeter()
{
    printf("Rectangle area: %i \t perimeter: %i \n", area(), perimeter());
}

//*****************************Definition For Triangle Class***********************************
//Constructor of Triangle
Triangle::Triangle(int req_first_edge, int req_second_edge, int req_third_edge, int req_height)
{
	first_edge = req_first_edge;
	second_edge = req_second_edge;
	third_edge = req_third_edge;
	height = req_height;
	printf("\nTriangle Constructed. \n");
}

//Second Constructor of Triangle(Equilateral Triangle)
Triangle::Triangle(int req_edge)
{
    first_edge = req_edge;
	second_edge = req_edge;
	third_edge = req_edge;
}

//Third Constructor with no parameter, and assigns all data members with a value of 10
Triangle::Triangle()
{
	first_edge = 10;
	second_edge = 10;
	third_edge = 10;
	height = 10;
}

//Destructor of Triangle
Triangle::~Triangle()
{
	printf("\nTriangle Destroyed. \n");
}

//implement perimeter function
int Triangle::perimeter()
{
	int perimeter = first_edge + second_edge + third_edge;
	return perimeter;
}

//implement area function
int Triangle::area()
{
	int area = (third_edge * height)/2;
	return area;
}


//implement print_area_perimeter function
void Triangle::print_area_perimeter()
{
   printf("Triangle area: %i \t perimeter: %i \n", area(), perimeter());
}



//main function
int main() {

	//Calling the friendship function
	friendOfRectangleAndTriangle();

	//Create an instance of Rectangle Class
	printf("Create a Rectangle Object ");
	Rectangle rectangle(4, 5);
    rectangle.print_area_perimeter();

	//create a pointer to the Rectangle object, and allocate memory "new" keyword
	printf("\n\nCreate a Rectangle object from dynamic memory");
	Rectangle *rect = new Rectangle(3,4); //New equal to the malloc
	rect->print_area_perimeter();
	delete rect; //Free the memory

	//Create an instance of Triangle Class
	printf("\nCreate a Triangle Object");
	Triangle triangle(4, 5, 6, 8);
    triangle.print_area_perimeter();

	//Create a pointer to the Triangle object, and allocate memory "new" keyword
	printf("\n\nCreate a Triangle object from dynamic memory");
	Triangle *tri = new Triangle(10,3,7,8);
    tri->print_area_perimeter();
	delete tri;


	printf("\nPress any key to continue.");
	getchar();getchar();
	exit(0);
}

//friend functions can serve to conduct operations between 2 classes
void friendOfRectangleAndTriangle()
{
	//if the height of rectangle and triangle are equal then do some magic
	if(myRectangle.height == myTriangle.height)
		printf("\n\n !! Do magic from friend function!! \n\n");
	else
		printf("\n\n !! Do NOT magic from friend function!! \n\n");

}
