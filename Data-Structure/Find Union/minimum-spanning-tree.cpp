#include <iostream>
#include <string>
#include <algorithm>
#include <fstream>

using namespace std;
const string path = "graph.txt";
const int MN = 2001;
int id[MN];
int n;				// number of vertices
int m;				// number of edges

typedef struct _edge
{
	int first; 
	int second;
	int weight;
} Edge;

Edge edges[MN];
int SpanningTree[MN];		// contain index of edge


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


void readFile()
{
	ifstream infile(path);
	if (!infile.is_open())
	{
		cout << "Do not open file.\n";
		return;
	}

	infile >> n >> m;
	init();

	int first, second, weight;

	for (int i = 1; i <= m; ++i)
	{
		infile >> first >> second >> weight;

		edges[i].first = first;
		edges[i].second = second;
		edges[i].weight = weight;
	}

	infile.close();
}


void quickSort(int begin, int end)
{
	// select pivot  
	int pivot = (begin + end) / 2;

	int i = begin;
	int j = end;

	while (i <= j)
	{
		while (edges[i].weight < edges[pivot].weight) ++i;
		while (edges[j].weight > edges[pivot].weight) --j;

		if (i <= j)
		{
			if (i < j)
			{
				swap(edges[i], edges[j]);
			}

			++i;
			--j;
		}
	}

	if (begin < j)
	{
		quickSort(begin, j);
	}

	if (i < end)
	{
		quickSort(i, end);
	}
}


void getMinimumSpanningTree()
{
	quickSort(1, m);

	int j = 0;		// index of edge in spanning tree array

	for (int i = 1; i <= m; ++i)
	{
		int isNotCycle = quickUnion(edges[i].first, edges[i].second);
		if (isNotCycle)
		{
			++j;
			SpanningTree[j] = i;
		}
	}

	cout << "The vertices in minimum spanning tree: \n";
	int sumCoefficient = 0;

	for (int i = 1; i <= j; ++i)
	{
		cout << edges[SpanningTree[i]].first << " " << edges[SpanningTree[i]].second << "\n";
		sumCoefficient += edges[SpanningTree[i]].weight;
	}

	cout << "\nThe sum of coefficient of minimum spanning tree is: " << sumCoefficient << "\n";
}


int main()
{
	readFile();

	getMinimumSpanningTree();

	system("pause");
	return 0;
}