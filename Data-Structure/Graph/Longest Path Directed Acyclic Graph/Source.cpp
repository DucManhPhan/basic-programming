// https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/

#include "stdafx.h"
#include "Graph.h"

int main()
{
	CGraph graph(6);

	// add informations of the node to graph.
	/*graph.addVerticeToList(1, 0, 5);
	graph.addVerticeToList(2, 0, 3);

	graph.addVerticeToList(2, 1, 2);
	graph.addVerticeToList(3, 1, 6);

	graph.addVerticeToList(3, 2, 7);
	graph.addVerticeToList(4, 2, 4);
	graph.addVerticeToList(5, 2, 2);

	graph.addVerticeToList(4, 3, -1);
	graph.addVerticeToList(5, 3, 1);

	graph.addVerticeToList(5, 4, -2);
	
	graph.longestPath(0);*/
	
	/*graph.addVerticeToList(1, 0, 0);
	graph.addVerticeToList(3, 0, 0);

	graph.addVerticeToList(2, 1, 0);
	graph.addVerticeToList(3, 1, 0);

	graph.addVerticeToList(3, 2, 0);
	graph.addVerticeToList(4, 2, 0);
	graph.addVerticeToList(5, 2, 0);

	graph.addVerticeToList(4, 3, 0);
	graph.addVerticeToList(5, 3, 0);

	graph.addVerticeToList(5, 4, 0);*/

	graph.addVerticeToList(2, 5, 2);
	graph.addVerticeToList(0, 5, 5);

	graph.addVerticeToList(0, 4, 7);
	graph.addVerticeToList(1, 4, 9);

	graph.addVerticeToList(3, 2, 10);

	graph.addVerticeToList(1, 3, -2);

	/*graph.topologicalSorting_BFS();
	graph.printTopoOrder(false);	*/

	/*graph.topologicalSorting_DFS();
	graph.printTopoOrder(true);*/

	graph.longestPath(5);

	system("pause");
	return 0;
}