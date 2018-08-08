#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>
#include <list>
#include <queue>



#pragma region Hash Table
class CHashTable
{
public:
	CHashTable(int size) : m_HashTable(size), m_nSize(size)
	{
		// nothing to do. 
	}	

	int HashFunction(int value)
	{
		if (value < 0)
		{
			return m_nSize - (abs(value) % m_nSize);
		}
		else if (value > 0)
		{
			return value % m_nSize;
		}
		else
		{
			return 0;
		}			
	}

	void insertElement(int key, int value)
	{
		int index = HashFunction(key);
		
		m_HashTable[index].push_back(value);
	}

	void printTable()
	{
		for (int i = 0; i < m_nSize; ++i)
		{
			std::list<int>::iterator it; 
			std::list<int>::iterator it_begin = m_HashTable[i].begin();
			std::list<int>::iterator it_end = m_HashTable[i].end();

			for (it = it_begin; it != it_end; ++it)
			{
				std::cout << *it << "  ";
			}

			std::cout << "\n";
		}
	}

private:
	std::vector<std::list<int>>	m_HashTable;
	int				m_nSize; 
};

#pragma endregion


#pragma region Binary Tree
struct CNode
{
	int		m_nData; 
	CNode*		m_pLeft; 
	CNode*		m_pRight; 
	CNode*		m_pParent;

	CNode(int data) : m_nData(data), m_pLeft(nullptr), m_pRight(nullptr), m_pParent(nullptr)
	{
		// nothing to do. 
	}

	~CNode()
	{
		m_pLeft		= nullptr; 
		m_pRight	= nullptr;
		m_pParent	= nullptr;
	}
};


class CBinaryTree
{
public:
	// Constructor
	CBinaryTree() : m_pRoot(nullptr), m_nSize(0), m_HashTable(9)
	{
		// nothing to do. 
	}

	CNode* makeNode(int data)
	{
		return new CNode(data);
	}

	// Common Operation
	void insertElement(CNode* pNode)
	{
		if (!pNode)
		{
			std::cout << "No value in the inserted node.\n";
			return;
		}
		
		if (!m_pRoot)
		{
			m_pRoot = pNode; 
			m_pRoot->m_pParent = nullptr;

			return;
		}

		std::queue<CNode*> queTree;
		queTree.push(m_pRoot);

		while (!queTree.empty())
		{
			CNode* pTmpNode = queTree.front();
			queTree.pop();

			if (pTmpNode)
			{
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
	}

	void getVerticalOrder()
	{
		int horizontalDistance = 0;
		
		if (m_pRoot)
		{
			CNode* pNode = m_pRoot;
			getVerticalOrder_Utils(pNode, horizontalDistance);
		}
	}

	void getVerticalOrder_Utils(CNode* pNode, int horizontalDistance)
	{
		if (!pNode)
		{
			return;
		}

		m_HashTable.insertElement(horizontalDistance, pNode->m_nData);

		getVerticalOrder_Utils(pNode->m_pLeft, horizontalDistance - 1);
		getVerticalOrder_Utils(pNode->m_pRight, horizontalDistance + 1);
	}

	void printVerticalOrder()
	{
		m_HashTable.printTable();
	}

private: 
	CNode*		m_pRoot; 
	int		m_nSize; 
	CHashTable	m_HashTable;
};
#pragma endregion




int main()
{
	int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int size = sizeof(a) / sizeof(int);
	CBinaryTree binaryTree;

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = binaryTree.makeNode(a[i]);
		binaryTree.insertElement(pNode);
	}

	binaryTree.getVerticalOrder();
	binaryTree.printVerticalOrder();

	system("pause");
	return 0;
}



