#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
#include <iterator>


struct CNode
{
	int			m_nData; 
	CNode*		m_pLeft; 
	CNode*		m_pRight; 

	bool		m_bIsChecked;

	CNode(int data) : m_nData(data), m_pLeft(nullptr), m_pRight(nullptr), m_bIsChecked(false)
	{
		// nothing to do. 
	}

	~CNode()
	{
		m_pLeft		= nullptr; 
		m_pRight	= nullptr;
	}
};


CNode*	pRoot;		// maintain the root node of binary tree. 


void makeBinaryTree()
{
	pRoot = new CNode(1);
	pRoot->m_pLeft = new CNode(2);
	pRoot->m_pRight = new CNode(3);

	pRoot->m_pLeft->m_pLeft = new CNode(4);
	pRoot->m_pLeft->m_pRight = new CNode(5);

	pRoot->m_pLeft->m_pRight->m_pLeft = new CNode(6);
	pRoot->m_pLeft->m_pRight->m_pRight = new CNode(7);

	pRoot->m_pLeft->m_pRight->m_pLeft->m_pRight = new CNode(10);
	pRoot->m_pLeft->m_pRight->m_pRight->m_pLeft = new CNode(11);
	pRoot->m_pLeft->m_pRight->m_pRight->m_pRight = new CNode(12);
}


void InorderTree_UseStack(std::vector<int>& vecInorderTree)
{
	CNode* pTmpNode = pRoot;

	std::stack<CNode*> stkInorderTree; 
	stkInorderTree.push(pTmpNode);

	while (!stkInorderTree.empty())
	{
		pTmpNode = stkInorderTree.top();
		if (pTmpNode)
		{
			if (pTmpNode->m_pLeft && !pTmpNode->m_pLeft->m_bIsChecked)
			{
				pTmpNode->m_pLeft->m_bIsChecked = true;
				stkInorderTree.push(pTmpNode->m_pLeft);
				continue;
			}
			
			vecInorderTree.push_back(pTmpNode->m_nData);
			stkInorderTree.pop();

			if (pTmpNode->m_pRight)
			{
				stkInorderTree.push(pTmpNode->m_pRight);
			}
		}
	}
}


void InorderTree_Recursion(CNode* pNode)
{
	if (!pNode)
	{
		return;
	}

	InorderTree_Recursion(pNode->m_pLeft);
	
	std::cout << pNode->m_nData << "  ";

	InorderTree_Recursion(pNode->m_pRight);
}



int main()
{
	makeBinaryTree();

	InorderTree_Recursion(pRoot);

	std::cout << "\n";

	std::vector<int> vectInorderTree;
	InorderTree_UseStack(vectInorderTree);

	std::copy(vectInorderTree.begin(), vectInorderTree.end(), std::ostream_iterator<int>(std::cout, " "));

	system("pause");
	return 0;
}


