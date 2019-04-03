#include <iostream>
#include <string>
#include <fstream>


using namespace std;

const int SIZE = 200;
int id[SIZE];
int sz[SIZE];		// keep track size of each tree
int n;				// number of people
int m;				// number of pair


void init()
{
	for (int i = 1; i <= n; ++i)
	{
		id[i] = i;
		sz[i] = 1;
	}
}


int root(int i)		// O(logn)
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


int quickUnion(int p, int q)	// O(logn)
{
	int pid = root(p);
	int qid = root(q);

	if (pid == qid)
	{
		return 0;
	}

	if (sz[pid] < sz[qid])
	{
		id[pid] = qid;
		sz[qid] += sz[pid];
	}
	else
	{
		id[qid] = pid;
		sz[pid] += sz[qid];
	}

	return 1;
}


void readFile()
{
	const string path = "./in.txt";
	ifstream infile(path);

	if (!infile.is_open())
	{
		cout << "Do not open file.\n";
		return;
	}

	infile >> n >> m;
	init();

	int numTeams = n;
	int first, second;

	for (int i = 1; i <= m; ++i)
	{
		infile >> first >> second;

		numTeams -= quickUnion(first, second);
	}

	infile.close();	
}


int main()
{
	cout << "Run program.\n";
	readFile();

	system("pause");
	return 0;
}
