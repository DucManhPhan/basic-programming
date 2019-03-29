#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <string>
#include <fstream>
#include <utility>


std::vector<std::pair<int, int>> readFile(const std::string& path, int& numStudents, std::vector<int>& students)
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
	std::vector<std::pair<int, int>> pairMems(numLines);

	for (int i = 0; i < numLines; ++i)
	{
		infile >> first >> second;
		pairMems[i].first = first;
		pairMems[i].second = second;

		if (first > second)
		{
			students[second] = first;
		}
		else
		{
			students[first] = second;
		}
	}

	return pairMems;
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


std::vector<std::set<int>> getMembersInTeams(const std::vector<std::pair<int, int>>& pairMems, const std::vector<int>& students)
{
	std::vector<std::set<int>> results;
	int sizePairMember = pairMems.size();

	for (int i = 0; i < sizePairMember; ++i)
	{
		std::set<int> team;
		if (connected(pairMems[i].first, pairMems[i].second, students))
		{
			team.insert();
		}
	}
}


int main()
{





	system("pause");
	return 0;
}
