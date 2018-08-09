#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>
#include <list>
#include <unordered_map>
#include <map>
#include <queue>


class CHashTable
{
public:
	// Constructor
	CHashTable(int size) : m_HashTable(size), m_nSize(size)
	{
		// nothing to do.
	}

	// Common operations
	int HashFunction(int key)
	{
		return key % m_nSize;
	}



private:
	std::vector<int>	m_HashTable;
	int					m_nSize;
};


struct CNode
{
	int		m_nData;
	CNode*	m_pParent; 
	CNode*	m_pLeft; 
	CNode*	m_pRight;

	CNode(int data) : m_nData(data), m_pParent(nullptr), m_pLeft(nullptr), m_pRight(nullptr)
	{
		// nothing to do.
	}

	~CNode()
	{
		m_pParent	= nullptr;
		m_pLeft		= nullptr;
		m_pRight	= nullptr;
	}
};


class CBinaryTree
{
public:
	// Constructor
	CBinaryTree() : m_pRoot(nullptr), m_nSize(0)
	{
		// nothing to do.
	}

	CNode* makeNode(int data)
	{
		return new CNode(data);
	}

	// Common operations
	void push(CNode* pNode)
	{
		if (!pNode)
		{
			std::cout << "Node is null, now.\n";
			return;
		}

		if (!m_pRoot)
		{
			m_pRoot = pNode; 
			m_pRoot->m_pParent = nullptr;
		}

		std::queue<CNode*> queTree;
		queTree.push(m_pRoot);

		while (!queTree.empty())
		{
			CNode* pTmpNode = queTree.front();
			queTree.pop();

			if (pTmpNode->m_pLeft)
			{
				queTree.push(pTmpNode->m_pLeft);
			}
			else
			{
				pTmpNode->m_pLeft = pNode; 
				pNode->m_pParent = pTmpNode;

				break;
			}

			if (pTmpNode->m_pRight)
			{
				queTree.push(pTmpNode->m_pRight);
			}
			else
			{
				pTmpNode->m_pRight = pNode; 
				pNode->m_pParent = pTmpNode;

				break;
			}

		}
	}

	void printLeftView()
	{
		
	}



private:
	CNode*		m_pRoot;
	int			m_nSize; 
};


int main()
{
	int a[] = { 4, 5, 2, 3, 1, 6, 7 };
	int size = sizeof(a) / sizeof(int);
	CBinaryTree binaTree;

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = binaTree.makeNode(a[i]);
		binaTree.push(pNode);
	}

	binaTree.printLeftView();

	system("pause");
	return 0;
}