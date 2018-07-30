#include "Graph.h"


CGraph::CGraph()
	: m_vecAdjList(0)
	, m_nNumberVertices(0)
	, m_vecVisited(nullptr)
	, m_vecInDegreeNode(0)
{
	// nothing.
}


CGraph::CGraph(int numberVertices)
	: m_vecAdjList(numberVertices)
	, m_nNumberVertices(numberVertices)
	, m_vecVisited(new bool[m_nNumberVertices]())
	, m_vecInDegreeNode(numberVertices, 0)
	, m_stkTopoSort()
{
	// nothing.
}


CGraph::~CGraph()
{
	if (m_vecVisited)
	{
		delete m_vecVisited;
		m_vecVisited = nullptr;
	}
}


void CGraph::dfs(int nVertex)
{
	if (m_vecVisited[nVertex])
	{
		return; 
	}

	// Mark the visited node. 
	m_vecVisited[nVertex] = true; 

	// Do sth with the node. 

	// Traversal all of nodes that stick with the visited node. 
	for (auto nVertice : m_vecAdjList[nVertex])
	{
		dfs(nVertice);
	}

	// add vertext to stack that contains result. 
	m_stkTopoSort.push(nVertex);
}


void CGraph::topologicalSorting()
{
	// List of vertice that have indegree equal 0.
	vector<int> vecZeroNode = getZeroIndegreeNode();

	for (auto vertex : vecZeroNode)
	{
		dfs(vertex);
	}
}


vector<int> CGraph::getZeroIndegreeNode()
{
	vector<int> tmp;

	for (int i = 0; i < m_nNumberVertices; ++i)
	{
		if (m_vecInDegreeNode[i] == 0)
		{
			tmp.push_back(i);
		}
	}

	return tmp;
}


void CGraph::addVertextToListNode(int nVertex, int nListNode)
{
	m_vecAdjList[nListNode].push_back(nVertex);
	++m_vecInDegreeNode[nVertex];
}


void CGraph::printTopoOrder()
{
	if (m_stkTopoSort.empty())
	{
		cout << "Stack has no element!\n";
	}
	
	while (!m_stkTopoSort.empty())
	{
		int vertex = m_stkTopoSort.top();
		m_stkTopoSort.pop();

		cout << vertex << " ";
	}

	cout << "\n";

}