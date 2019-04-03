#include <iostream>
#include <fstream>


using namespace std;

const int MN = 2001;// capacity of array
int id[MN];			// trees
int n;				// number of elements
int m;				// number of pairs
int sz[MN];			// keep track the size of each tree


void init()
{
	for (int i = 1; i <= n; ++i)
	{
		id[i] = i;
		sz[i] = 1;
	}
}


int root(int i)
{
	// path compression by linking to the root
	/*int root = i;

	while (root != id[root])
	{
		root = id[root];
	}

	while (i != root)
	{	
		int newi = id[i];
		id[i] = root;
		i = newi;
	}*/


	// path compression by linking to grandparent (halving the depth)
	while (i != id[i])
	{
		id[i] = id[id[i]];	// First time, assign this tree to the grandparent of the tree's root node
		i = id[i];			// Finally, assign parent of parent ... of the tree to the root node.
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

	if (sz[pid] > sz[qid])
	{
		id[qid] = pid;
		sz[pid] += sz[qid];
	}
	else
	{
		id[pid] = qid;
		sz[qid] += pid;
	}

	return 1;
}



int main()
{



	system("pause");
	return 0;
}
