#include "Graph.h"

#pragma region Constructors
CGraph::CGraph()
	: m_vecAdjList(0)
	, m_nNumVertices(0)
	, m_vecIndegree(0)
	, m_vecTopoOrder(0)
{
	// nothing to do. 
}


CGraph::CGraph(int numberVertice)
	: m_vecAdjList(numberVertice)
	, m_nNumVertices(numberVertice)
	, m_vecIndegree(numberVertice, 0)
	, m_vecTopoOrder(numberVertice)
{
	// nothing to do.
}


void CGraph::addVerticeToList(int nVertice, int nIndexList, int nWeight)
{
	if (!m_vecAdjList.empty())
	{
		m_vecAdjList[nIndexList].push_back(pair<int, int>(nVertice, nWeight));
		++m_vecIndegree[nVertice];
	}
}
#pragma endregion 


#pragma region Traversal Graph
void CGraph::dfs(int nStartVertice)
{
	bool *bVisited = new bool[m_nNumVertices]();
	if (bVisited)
	{
		traversalDFS(bVisited, nStartVertice);
	}
	else
	{
		cout << "Do not create array bool.\n";
		return;
	}

	delete bVisited;
	bVisited = nullptr;
}


void CGraph::bfs(int nStartVertice)
{
	bool *bVisited = new bool[m_nNumVertices]();
	if (bVisited)
	{
		traversalBFS(bVisited, nStartVertice);
	}
	else
	{
		cout << "Do not create array bool.\n";
		return;
	}

	delete bVisited;
	bVisited = nullptr;
}


void CGraph::traversalDFS(bool bVisited[], int nStartVertice)
{
	if (bVisited[nStartVertice])
	{
		return; 
	}

	// Mark the vertice that is traversed. 
	bVisited[nStartVertice] = true; 

	// Do something with this vertice. 
	//cout << nStartVertice << "--";

	// Continuously traversal the depth of vertice. 
	for (auto nVertice : m_vecAdjList[nStartVertice])
	{
		traversalDFS(bVisited, nVertice.first);
	}

	m_stkTopoSort.push(nStartVertice);
}


void CGraph::traversalBFS(bool bVisited[], int nStartVertice)
{
	// Add vertice at nStartVertice into queue & Mark this vertice. 
	queue<int> quVertices;
	quVertices.push(nStartVertice);
	bVisited[nStartVertice] = true; 

	// Traversal graph with breadth. 
	while (!quVertices.empty())
	{
		int nVertice = quVertices.front();
		quVertices.pop();

		// Do something with choosen vertice. 
		cout << nVertice << "\n";

		// Consider all vertices that is linked with nVertice. 
		for (auto vertice : m_vecAdjList[nVertice])
		{
			if (bVisited[vertice.first])
			{
				continue;
			}
			
			bVisited[vertice.first] = true;
			quVertices.push(vertice.first);
		}
	}
}
#pragma endregion


#pragma region Topological Sorting
void CGraph::topologicalSorting_DFS()
{
	bool* bVisited = new bool[m_nNumVertices]();

	for (int i = 0; i < m_nNumVertices; ++i)
	{
		if (!bVisited[i])
		{
			traversalDFS(bVisited, i);
		}
	}

	delete bVisited;
	bVisited = nullptr;
}


void CGraph::topologicalSorting_BFS()
{
	// Initialize 
	int nIndex = 0;
	bool *bVisited = new bool[m_nNumVertices]();
	queue<int> queIndegree = getIndegreeZero(bVisited);
	std::fill(m_vecTopoOrder.begin(), m_vecTopoOrder.end(), 0);
		
	// Traversal queue. 
	while (!queIndegree.empty())
	{
		int nVertex = queIndegree.front();
		queIndegree.pop();

		m_vecTopoOrder[nIndex++] = nVertex;

		for (auto i : m_vecAdjList[nVertex])
		{
			if (!bVisited[i.first])
			{
				m_vecIndegree[i.first] -= 1;
				if (m_vecIndegree[i.first] == 0)
				{
					queIndegree.push(i.first);
					bVisited[i.first] = true; 
				}
			}
		}
	}

	delete bVisited;
	bVisited = nullptr;
}


queue<int> CGraph::getIndegreeZero(bool bVisited[])
{
	queue<int> queIndegree; 

	for (int i = 0; i < m_nNumVertices; ++i)
	{
		if (m_vecIndegree[i] == 0)
		{
			queIndegree.push(i);
			bVisited[i] = true;
		}
	}

	return queIndegree;
}


void CGraph::printTopoOrder(bool bIsDFS)
{
	if (bIsDFS)
	{
		int nIndex = 0;

		while (!m_stkTopoSort.empty())
		{
			int nVer = m_stkTopoSort.top();
			m_stkTopoSort.pop();

			m_vecTopoOrder[nIndex++] = nVer;
		}
	}
	
	copy(m_vecTopoOrder.begin(), m_vecTopoOrder.end(), ostream_iterator<int>(cout, " "));
}

#pragma endregion


#pragma region Longest Path in a Directed Acyclic Graph
void CGraph::longestPath(int nSourceVertex)
{
	int nNextVertex = -1;

	// Initialize values for distance array. 
	int* distance = new int[m_nNumVertices];
	/*for (int i = 0; i < m_nNumVertices; ++i)
	{
		distance[i] = INT_MIN;
	}*/
	std::fill(distance, distance + m_nNumVertices, INT_MIN);
	distance[nSourceVertex] = 0;

	// Get the topological ordering of this graph. 
	topologicalSorting_DFS();
	
	// Reserve the order of elements in stack that contains order of topology sorting. 
	//printTopoOrder();

	// Compute the distance of each vertex in adjacent list for one node. 
	while (!m_stkTopoSort.empty())
	{
		int nVert = m_stkTopoSort.top();
		m_stkTopoSort.pop();

		if (distance[nVert] != INT_MIN)
		{
			int nSize = m_vecAdjList[nVert].size();
			for (int i = 0; i < nSize; ++i)
			{
				nNextVertex = m_vecAdjList[nVert][i].first;

				if (distance[nNextVertex] < distance[nVert] + m_vecAdjList[nVert][i].second)
				{
					distance[nNextVertex] = distance[nVert] + m_vecAdjList[nVert][i].second;
				}
			}
		}
	}

	// Print longest path of the graph.
	printLongestPath(distance);
}


void CGraph::printLongestPath(int distance[])
{
	for (int i = 0; i < m_nNumVertices; ++i)
	{
		distance[i] == INT_MIN ? cout << " INF " : cout << distance[i] << " ";
	}
}

#pragma endregion