#pragma once
#include "stdafx.h"


class CGraph
{
public: 
	CGraph(int nNumberVertices);
	void addVertexToLst(int nVertex, int nLst);

	void findMotherVertex();
	void dfs(int nSourceVertex, bool bVisited[], bool bIsNeedFinishTime);
	//void bfs();
	void optimizeFindMotherVertex();

private: 
	CGraph();

	vector<vector<int>>		m_vecAdjList;
	vector<int>				m_vecFinishTime;

	int						m_nNumberVertices; 
};