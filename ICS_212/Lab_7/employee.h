struct Address{
	char street[60];
	char city[30];
	char state[20];
	char country[40];
	int zipcode;
};

struct Employee{
	char firstName[30];
	char lastName[30];
	double height;
	Address *homeAddress;
	Address *workAddress;
};

//Function Decleration
void printinfo(Employee *info);
void destructor(Employee *info);
