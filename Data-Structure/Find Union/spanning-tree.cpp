#include <iostream>
#include <fstream>
#include <string>

using namespace std;

const string path = "graph.txt";
const int MN = 1000;
int id[MN];
int n;				// number of vertices
int m;				// number of edges

struct
{
	int firstVertice;
	int secondVertice;
} spanningTree[MN];


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


void getSpanningTree()
{
	ifstream infile(path);
	if (!infile.is_open())
	{
		cout << "Do not open file.\n";
		return;
	}

	infile >> n >> m;
	init();

	int edges_spanningtree = n - 1;
	int first, second;

	for (int i = 1; i <= m; ++i)
	{
		static int count = 0;
		infile >> first >> second;

		int isCycle = quickUnion(first, second);
		if (isCycle)
		{
			++count;
			spanningTree[count].firstVertice  = first;
			spanningTree[count].secondVertice = second;
		}

		if (count == edges_spanningtree)
		{
			break;
		}
	}
}


void printSpanningTree()
{
	for (int i = 1; i <= n - 1; ++i)
	{
		cout << spanningTree[i].firstVertice << " " << spanningTree[i].secondVertice << "\n";
	}
}


int main()
{
	getSpanningTree();

	printSpanningTree();

	system("pause");
	return 0;
}