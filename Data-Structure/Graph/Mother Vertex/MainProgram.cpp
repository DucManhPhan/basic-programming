#include "stdafx.h"
#include "Graph.h"


int main()
{
	/*CGraph graph(7);

	graph.addVertexToLst(1, 0);
	graph.addVertexToLst(2, 0);

	graph.addVertexToLst(3, 1);
	graph.addVertexToLst(4, 1);

	graph.addVertexToLst(2, 5);
	graph.addVertexToLst(6, 5);

	graph.addVertexToLst(0, 6);
	graph.addVertexToLst(4, 6);*/

	CGraph graph(10);

	graph.addVertexToLst(1, 0);
	graph.addVertexToLst(2, 0);

	graph.addVertexToLst(3, 1);
	graph.addVertexToLst(4, 1);

	graph.addVertexToLst(3, 2);

	graph.addVertexToLst(5, 3);
	graph.addVertexToLst(0, 3);

	graph.addVertexToLst(5, 4);
	graph.addVertexToLst(6, 4);

	graph.addVertexToLst(4, 5);

	graph.addVertexToLst(5, 6);
	graph.addVertexToLst(7, 6);
	graph.addVertexToLst(8, 6);
	graph.addVertexToLst(9, 6);

	graph.addVertexToLst(9, 7);

	graph.addVertexToLst(9, 8);

	graph.addVertexToLst(8, 9);

	graph.optimizeFindMotherVertex();

	system("pause");
	return 0;
}