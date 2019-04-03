#include <iostream>
#include <algorithm>
#include <string>
#include <fstream>

using namespace std;

const int MN = 2001;
const char* inf = "./in.txt";
int n;   // number of people
int m;   // number of pair
int d[MN];
int numTeams;


void init()
{
	for (int i = 1; i <= n; ++i)
	{
		d[i] = i;
	}
}


int root(int i)	// find function
{
	while (i != d[i])	// find follow the parent of node
	{
		i = d[i];
	}

	return i;
}


bool connected(int p, int q)
{
	return root(p) == root(q);
}


int quickUnion(int p, int q)
{
	int i = root(p);
	int j = root(q);

	if (i == j)
	{
		return 0;
	}

	i < j ? d[j] = i : d[i] = j;
	return 1;
}


void readFile()
{
	ifstream infile(inf);
	if (!infile.is_open())
	{
		return;
	}


	infile >> n >> m;
	init();
	numTeams = n;

	int first, second;
	for (int i = 1; i <= m; ++i)
	{
		if (!(infile >> first >> second))
		{
			break;
		}

		numTeams -= quickUnion(first, second);
	}

	infile.close();

	// Print result
	cout << "The number of teams in class: " << numTeams << "\n";
	for (int i = 1; i <= n; ++i)
	{
		if (d[i] == i)		// find a leader of team
		{
			cout << "The team " << i << ": ";
			for (int j = i + 1; j <= n; ++j)
			{
				if (root(j) == i)	// find a member in this team
				{
					cout << j << " ";
				}
			}

			cout << "\n";
		}
	}
}


int main()
{
	readFile();

	system("pause");
	return 0;
}
