#pragma once
#include "stdafx.h"


class CGraph
{
public:
	CGraph();
	CGraph(int numberVertices);

	~CGraph();

	void								dfs(int nVertex);
	void								topologicalSorting();
	vector<int>							getZeroIndegreeNode();
	void								addVertextToListNode(int nVertex, int nListNode);
	void								printTopoOrder();

private:
	vector<vector<int>>					m_vecAdjList; 
	int									m_nNumberVertices; 
	bool*								m_vecVisited;			// mark all node that is traversed. 
	vector<int>							m_vecInDegreeNode;		// in-degree of node. 
	stack<int>							m_stkTopoSort;			// contains result. 
};