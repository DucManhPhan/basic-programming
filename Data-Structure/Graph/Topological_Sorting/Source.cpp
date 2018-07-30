#include "stdafx.h"
#include "Graph.h"


int main()
{
	CGraph graph(6);

	graph.addVertextToListNode(2, 5);
	graph.addVertextToListNode(0, 5);
	graph.addVertextToListNode(0, 4);
	graph.addVertextToListNode(1, 4);
	graph.addVertextToListNode(3, 2);
	graph.addVertextToListNode(1, 3);

	graph.topologicalSorting();

	graph.printTopoOrder();

	system("pause");
	return 0;
}