//Declaration of classes (prototypes)
class Rectangle {
	//part 1: Data members of this class
	private:
	int width;
	int height;

	public://Scope of these functions will be public
	//part 2: function members of this class
	Rectangle(int, int);//Constructor function of this class
	~Rectangle(); //Desctructor function of this class
    void print_area_perimeter(); //Prints the area and perimeter of the Rectangle
	friend void friendOfRectangleAndTriangle(); //function friend

    private:
	int area();
	int perimeter();



};

class Triangle{
    private:
	//part 1: Data members of this class
	int first_edge;
	int second_edge;
	int third_edge;
	int height;

	public:
	//part 2: function members of this class
	Triangle(int,int,int,int);//Constructor function of this class
    Triangle(int);//Second Constructor
	Triangle();//Third Constructor with no parameter
	~Triangle(); //Destructor function of this class
    void print_area_perimeter(); //Prints the area and perimeter of the Triangle
	friend void friendOfRectangleAndTriangle(); //function friend
	friend Rectangle; //Class friend
	int area();
	int perimeter();


};

//function declaration
void friendOfRectangleAndTriangle();
