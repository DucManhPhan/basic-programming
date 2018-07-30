#pragma once
#include "stdafx.h"


class CGraph
{
public:
	#pragma region Constructor
	CGraph();
	CGraph(int numberVertice);

	void									addVerticeToList(int nVertice, int nIndexList, int nWeight);
	#pragma endregion 

	#pragma region Traversal Graph
	void									dfs(int nStartVertice);
	void									bfs(int nStartVertice);

	void									traversalDFS(bool bVisited[], int nStartVertice);
	void									traversalBFS(bool bVisited[], int nStartVertice);
	#pragma endregion 

	#pragma region Topological Sorting
	void									topologicalSorting_DFS();
	void									topologicalSorting_BFS();
	queue<int>								getIndegreeZero(bool bVisited[]);
	void									printTopoOrder(bool bIsDFS);
	#pragma endregion

	#pragma region Longest Path in a Directed Acyclic Graph
	void									longestPath(int nSourceVertex);
	void									printLongestPath(int distance[]);
	#pragma endregion

private:
	vector<vector<pair<int, int> > >		m_vecAdjList; 
	int										m_nNumVertices; 
	stack<int>								m_stkTopoSort;			// Used to contain elements with topological sort in DFS. 
	vector<int>								m_vecTopoOrder;			// Used to contain elements with topological sort.
	vector<int>								m_vecIndegree; 
};
