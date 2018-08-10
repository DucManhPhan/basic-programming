#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>
#include <list>
#include <unordered_map>
#include <map>
#include <queue>


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

			return;
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
		int nLevelNode = 0;
		std::map<int, CNode*> mpLeftView; 

		std::queue<std::pair<CNode*, int>> queTree;
		queTree.push(std::make_pair(m_pRoot, nLevelNode));

		while (!queTree.empty())
		{
			auto pairTmpNode = queTree.front();
			queTree.pop();

			CNode* pNode = pairTmpNode.first;
			int key = pairTmpNode.second;
			if (!isExist(mpLeftView, key))
			{
				mpLeftView[key] = pNode;
			}

			if (pNode->m_pLeft)
			{
				queTree.push(std::make_pair(pNode->m_pLeft, key + 1));
			}

			if (pNode->m_pRight)
			{
				queTree.push(std::make_pair(pNode->m_pRight, key + 1));
			}
		}

		// print left view 
		std::map<int, CNode*>::iterator it; 
		std::map<int, CNode*>::iterator it_begin = mpLeftView.begin();
		std::map<int, CNode*>::iterator it_end = mpLeftView.end();

		for (it = it_begin; it != it_end; ++it)
		{
			std::cout << it->second->m_nData << "\n";
		}

		std::cout << "\n";
	}

	bool isExist(const std::map<int, CNode*>& mpLeftView, int key)
	{
		std::map<int, CNode*>::const_iterator it = mpLeftView.find(key);

		return it != mpLeftView.end();
	}

	// the other way: use recursive. 
	void printLeftView_Recursive()
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