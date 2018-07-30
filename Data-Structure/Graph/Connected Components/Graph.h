#pragma once
#include "stdafx.h"


class CGraph
{
public: 
	#pragma region Constructor
	CGraph();
	CGraph(int nNumberVertices);
	void			addVertexToList(int nVertex, int nVerticeList);
	void			readFileToAdjMatrix();
	#pragma endregion

	#pragma region Traversal Graph
	void			dfs(int nSourceVertex, bool bVisited[], stack<int>& stkVertices);
	void			bfs(int nSourceVertex, bool bVisited[]);
	void			getFinishTimeOrder(int nSourceVertex, bool bVisited[]);
	#pragma endregion

	#pragma region Connected Component
	bool			IsConnectedGraph();
	void			KosarajuAlgorithm();
	void			reverseGraph(CGraph& reversedGraph);//, bool bVisited[]);
	#pragma endregion

private: 
	vector<vector<int>>				m_vecAdjLst; 
	vector<vector<int>>				m_vecAdjMatrix; 
	vector<int>						m_vecOrderFinishTime;

	int								m_nNumberVertices;

};