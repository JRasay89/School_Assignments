#include <string>
using namespace std;

class Point 
{
	public:
		int x_axis;
		int y_axis;
		string name;

		Point(){};
		Point(int, int, string);
		Point operator + (Point); //overload function for the + operator
};
