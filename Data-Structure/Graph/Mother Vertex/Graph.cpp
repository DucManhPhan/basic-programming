#include "Graph.h"



CGraph::CGraph()
	: m_nNumberVertices(0)
	, m_vecAdjList(0)
	, m_vecFinishTime(0)
{
	// nothing. 
}


CGraph::CGraph(int nNumberVertices)
	: m_nNumberVertices(nNumberVertices)
	, m_vecAdjList(nNumberVertices)
	, m_vecFinishTime(nNumberVertices)
{
	//nothing. 
}


void CGraph::addVertexToLst(int nVertex, int nLst)
{
	m_vecAdjList[nLst].push_back(nVertex);
}


// With this way, the complexity is O(V(V+E))
void CGraph::findMotherVertex()
{
	bool *bVisited = new bool[m_nNumberVertices]();

	for (int i = 0; i < m_nNumberVertices; ++i)
	{
		dfs(i, bVisited, false);
		if (std::any_of(bVisited, bVisited + m_nNumberVertices, [](bool bIsVisited)
		{
			return bIsVisited == false;
		}))
		{
			std::cout << "Vertex " << i << "is not mother vertex. \n";
		}
		else
		{
			std::cout << "Vertex " << i << " is mother vertex. \n";
		}

		//memset(bVisited, false, m_nNumberVertices);
		fill(bVisited, bVisited + m_nNumberVertices, false);
	}
}


void CGraph::dfs(int nSourceVertex, bool bVisited[], bool bIsNeedFinishTime)
{
	if (bVisited[nSourceVertex])
	{
		return;
	}

	bVisited[nSourceVertex] = true;

	for (auto indexVertex : m_vecAdjList[nSourceVertex])
	{
		dfs(indexVertex, bVisited, bIsNeedFinishTime);
	}

	if (bIsNeedFinishTime)
	{
		static int nTime = -1;
		++nTime;
		m_vecFinishTime[nTime] = nSourceVertex;
	}
}


// With this way, the complexity is O(V + E).
void CGraph::optimizeFindMotherVertex()
{
	// Initialize the array that has been marked when graph's nodes are visited. 
	//vector<bool> vecVisited(m_nNumberVertices, false);
	bool* bVisited = new bool[m_nNumberVertices]();
	int nMotherVertex = -1;

	// DFS 
	for (int i = 0; i < m_nNumberVertices; ++i)
	{
		if (!bVisited[i])
		{
			dfs(i, bVisited, false);
			nMotherVertex = i;
		}
	}

	// Reset memory for the bVisited. 
	fill(bVisited, bVisited + m_nNumberVertices, false);

	// Check whether nMotherVertex is mother vertex of the graph or not. 
	// If nMotherVertex is mother vertex, you can use only one depth first traversal. 
	// If not, it is not mother vertex. 
	dfs(nMotherVertex, bVisited, false);
	if (std::any_of(bVisited, bVisited + m_nNumberVertices, [](bool bVisited) {
		return bVisited == false;
	}))
	{
		std::cout << nMotherVertex << " is not mother vertex.\n";
	}
	else
	{
		std::cout << nMotherVertex << " is mother vertex.\n";
	}
}