#include <iostream>
#include <string>

#include "point.h"

using namespace std;

//implement class functions
Point::Point(int x, int y, string name_of_point)
{
	x_axis = x;
	y_axis = y;
	name = name_of_point;

}

//implement overload function which gets the sum of 2 numbers and cocatenate 2 strings
Point Point::operator + (Point myPoint)
{
	Point newPoint;

	newPoint.x_axis = this->x_axis + myPoint.x_axis; //this keyword is a pointer to the current object
	newPoint.y_axis = y_axis + myPoint.y_axis;
	newPoint.name = name + myPoint.name;
	return newPoint;

}

//test our overload function within main
int main()
{
	Point point_a(1, 2, "a");
	Point point_b(2, 3, "b");

	Point new_point = point_a.operator+(point_b);

	cout << "Point A values x: " << point_a.x_axis << "y: " << point_a.y_axis << " name: " << point_a.name << "\n";
	cout << "Point B values x: " << point_b.x_axis << "y: " << point_b.y_axis << " name: " << point_b.name << "\n";
	cout << "NewPoint Values x: " << new_point.x_axis << "y: " << new_point.y_axis << " name: " << new_point.name << "\n";
	
	printf("Press any key to continue: ");
	getchar();
	exit(0);
}