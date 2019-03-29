#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <fstream>


void readFile(const std::string& path, int& numStudents, std::vector<int>& students)
{
	std::ifstream infile(path);
	if (!infile.is_open())
	{
		return;
	}

	int minNumber;
	int first, second, numLines;

	infile >> first >> second;
	numStudents = first;
	numLines	= second;

	students.reserve(numStudents);

	for (int i = 0; i < numLines; ++i)
	{
		infile >> first >> second;

		if (first > second)
		{
			students[second] = first;
		}
		else
		{
			students[first] = second;
		}
	}
}


int root(int i, const std::vector<int>& students)
{
	while (i != students[i])
	{
		i = students[i];
	}

	return i;
}


bool connected(int p, int q, const std::vector<int>& students)
{
	return root(p, students) == root(q, students);
}




int main()
{





	system("pause");
	return 0;
}
