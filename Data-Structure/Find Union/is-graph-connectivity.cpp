#include <iostream>
#include <string>
#include <fstream>

using namespace std;

const string path = "./graph.txt";
const int MN = 2001;
int id[MN];
int n;				// number of vertices
int m;				// number of edges


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
	return 1;
}


int getNumConnectedComponents()
{
	ifstream infile(path);
	if (!infile.is_open())
	{
		cout << "Do not open file.\n";
		return 0;
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

bool hasConnectivity()
{
	return getNumConnectedComponents() == 1 ? true : false;
}


int main()
{
	if (hasConnectivity())
	{
		cout << "The graph is connectivity.\n";
	}
	else
	{
		cout << "The graph is not connectivity.\n";
	}


	system("pause");
	return 0;
}
