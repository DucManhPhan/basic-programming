#include <iostream>
#include <vector>


void solution_ActivityProblem(const int start[], const int finish[], int nSize)
{
	std::cout << "Indexes of the activities: ";
	std::cout << 0 << "\t";

	int nPrevious = 0;
	for (int i = 1; i < nSize; ++i)
	{
		if (start[i] > finish[nPrevious])
		{
			std::cout << i << "\t";
			nPrevious = i;
		}
	}

	std::cout << "\n";
}


int main()
{
	int start[] = { 1, 3, 0, 5, 8, 5 };
	int finish[] = { 2, 4, 6, 7, 9, 9 };
	
	int nSize = sizeof(start) / sizeof(int);

	solution_ActivityProblem(start, finish, nSize);

	system("pause");
	return 0;
}
