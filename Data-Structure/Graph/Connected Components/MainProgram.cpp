//https://www.geeksforgeeks.org/strongly-connected-components/

#include "stdafx.h"
#include "Graph.h"


int main()
{
	/*CGraph graph(5);

	graph.addVertexToList(2, 0);
	graph.addVertexToList(3, 0);

	graph.addVertexToList(0, 1);

	graph.addVertexToList(1, 2);

	graph.addVertexToList(4, 3);*/

	CGraph graph(10);

	graph.addVertexToList(1, 0);
	graph.addVertexToList(2, 0);

	graph.addVertexToList(3, 1);
	graph.addVertexToList(4, 1);

	graph.addVertexToList(3, 2);

	graph.addVertexToList(5, 3);
	graph.addVertexToList(0, 3);

	graph.addVertexToList(5, 4);
	graph.addVertexToList(6, 4);

	graph.addVertexToList(4, 5);

	graph.addVertexToList(5, 6);
	graph.addVertexToList(7, 6);
	graph.addVertexToList(8, 6);
	graph.addVertexToList(9, 6);

	graph.addVertexToList(9, 7);

	graph.addVertexToList(9, 8);

	graph.addVertexToList(8, 9);

	graph.KosarajuAlgorithm();

	system("pause");
	return 0;
}