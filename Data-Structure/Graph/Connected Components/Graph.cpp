#include "Graph.h"


#pragma region Constructor
CGraph::CGraph()
	: m_nNumberVertices(0)
	, m_vecAdjLst(0)
	, m_vecAdjMatrix(0)
	, m_vecOrderFinishTime(0)
{
	// nothing to do. 
}


CGraph::CGraph(int nNumberVertices)
	: m_nNumberVertices(nNumberVertices)
	, m_vecAdjLst(nNumberVertices)
	, m_vecAdjMatrix(nNumberVertices, vector<int>(nNumberVertices, 0))
	, m_vecOrderFinishTime(nNumberVertices)
{
	// nothing to do. 
}


void CGraph::addVertexToList(int nVertex, int nVerticeList)
{
	m_vecAdjLst[nVerticeList].push_back(nVertex);
}


void CGraph::readFileToAdjMatrix()
{

}
#pragma endregion


#pragma region Traversal Graph
void CGraph::dfs(int nSourceVertex, bool bVisited[], stack<int>& stkVertices)
{
	if (bVisited[nSourceVertex])
	{
		return; 
	}

	bVisited[nSourceVertex] = true; 

	for (auto nVertex : m_vecAdjLst[nSourceVertex])
	{
		dfs(nVertex, bVisited, stkVertices);
	}

	stkVertices.push(nSourceVertex);
}


void CGraph::bfs(int nSourceVertex, bool bVisited[])
{
	queue<int> queVertices; 
	queVertices.push(nSourceVertex);

	bVisited[nSourceVertex] = true; 

	while (!queVertices.empty())
	{
		int nVertex = queVertices.front();
		queVertices.pop();

		if (bVisited[nVertex])
		{
			continue;
		}

		bVisited[nVertex] = true;

		for (auto vert : m_vecAdjLst[nVertex])
		{
			queVertices.push(vert);
		}
	}
}


void CGraph::getFinishTimeOrder(int nSourceVertex, bool bVisited[])
{
	static int nTime = -1;

	if (bVisited[nSourceVertex])
	{
		return; 
	}

	bVisited[nSourceVertex] = true;

	for (auto nIndex : m_vecAdjLst[nSourceVertex])
	{
		if (!bVisited[nIndex])
		{
			getFinishTimeOrder(nIndex, bVisited);
		}
	}

	++nTime; 
	m_vecOrderFinishTime[nTime] = nSourceVertex;
}
#pragma endregion 


#pragma region Connected Component
bool CGraph::IsConnectedGraph()
{
	int nSourceVertex = 1; 
	bool *bVisited = new bool[m_nNumberVertices]();
	stack<int> stk;

	dfs(nSourceVertex, bVisited, stk);

	bool bCheckedAny = any_of(bVisited, bVisited + m_nNumberVertices, [](bool bVert) {
		return bVert != true;
	});

	return bCheckedAny != true ? true : false;
}


void CGraph::KosarajuAlgorithm()
{
	stack<int> tmpStack;
	int nCountCC = 0;

	// Initialize the marked array for visited state of the vertices in the graph. 
	bool* bVisited = new bool[m_nNumberVertices]();

	// Traversal DFS for this graph. 
	// So, you have the array of vertices that the elements of array have increased finish time.
	//for (int i = 0; i < m_nNumberVertices; ++i)
	for (int i = m_nNumberVertices - 1; i >= 0; --i)
	{
		if (!bVisited[i])
		{
			getFinishTimeOrder(i, bVisited);
		}
	}

	// Reset memory for the bVisited Array. 
	fill(bVisited, bVisited + m_nNumberVertices, false);

	// Reverse this graph. 
	CGraph ReversedGraph(m_nNumberVertices); 
	reverseGraph(ReversedGraph); // , bVisited);
	
	// Traversal DFS for the reversed graph. 
	// Print the connected components. 
	for (int i = m_nNumberVertices - 1; i >= 0; --i)
	{
		ReversedGraph.dfs(m_vecOrderFinishTime[i], bVisited, tmpStack);
		while (!tmpStack.empty())
		{
			int nIndexVertex = tmpStack.top();
			tmpStack.pop();

			std::cout << nIndexVertex << "  ";
		}
		cout << "\n";
		++nCountCC;
	}

	// Release memory for bVisited array. 
	delete bVisited;
	bVisited = nullptr;
}


void CGraph::reverseGraph(CGraph& reversedGraph) // , bool bVisited[])
{
	for (int i = 0; i < m_nNumberVertices; ++i)
	{
		for (int nIndex : m_vecAdjLst[i])
		{
			reversedGraph.addVertexToList(i, nIndex);
		}
	}
}
#pragma endregion