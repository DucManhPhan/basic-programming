// Content of in.txt file
			/*
			8 5
			2 5
			3 7
			8 6
			5 1
			4 3
			*/

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>

using namespace std;

const int MN = 2001;
int id[MN];
int n;					// number of vertices
int m;					// number of edges
const string path = "./in.txt";


void init()
{
	for (int i = 1; i <= n; ++i)
	{
		id[i] = i;
	}
}


int root(int i)
{
	while (i != id[i])
	{
		i = id[i];
	}

	return i;
}


bool connected(int p, int q)
{
	return root(p) == root(q);
}


int quickUnion(int p, int q)
{
	int pid = root(p);
	int qid = root(q);

	if (pid == qid)
	{
		return 0;
	}

	pid < qid ? id[qid] = pid : id[pid] = qid;
	return 1;
}


int getNumConnectedComponent()
{
	ifstream infile(path);
	if (!infile.is_open())
	{
		cout << "Do not open file.\n";
		return -1;
	}

	infile >> n >> m;
	init();

	int numConnectedComponents = n;
	int first, second;

	for (int i = 1; i <= m; ++i)
	{
		infile >> first >> second;

		numConnectedComponents -= quickUnion(first, second);
	}

	return numConnectedComponents;
}


void printConnectedComponents()
{
	int components = getNumConnectedComponent();
	int count = 1;

	cout << "There are " << components << " connected components: \n";
	for (int i = 1; i <= n; ++i)
	{
		if (id[i] == i)
		{
			cout << "Connected component " << count << ": " << i << " ";
			for (int j = i + 1; j <= n; ++j)
			{
				if (root(j) == i)
				{
					cout << j << " ";
				}
			}

			++count;
			cout << "\n";
		}
	}
}


int main()
{
	printConnectedComponents();

	system("pause");
	return 0;
}
