#include <iostream>
#include <fstream>
#include <string>

using namespace std;

const string path = "graph.txt";
const int MN = 2001;
int id[MN];
int n;			// number of vertices
int m;			// number of edges


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


int quickUnion(int p, int q)
{
	int pid = root(p);
	int qid = root(q);

	if (pid == qid)
	{
		return 0;
	}

	pid < qid ? id[qid] = pid : id[pid] = qid;
}


// To check the graph has cycle, we use merge implementation of two edges: quickUnion()
bool hasCycle()
{
	ifstream infile(path);
	if (!infile.is_open())
	{
		cout << "Do not open file.\n";
		return false;
	}

	infile >> n >> m;
	init();

	int firstVertice, secondVertice;

	for (int i = 1; i <= m; ++i)
	{
		infile >> firstVertice >> secondVertice;

		int isCycle = quickUnion(firstVertice, secondVertice);
		if (!isCycle)
		{
			cout << firstVertice << " " << secondVertice << "\n";
			cout << "Graph has cycle.\n";
			return true;
		}
	}

	return false;
}


int main()
{
	hasCycle();


	system("pause");
	return 0;
}
