#include "BinaryTree.h"



// Constructor
CBinaryTree::CBinaryTree() : m_pRoot(nullptr), m_nCount(0)
{
	// nothing to do.
}


CNode* CBinaryTree::makeNode(int data)
{
	return new CNode(data);
}


#pragma region Common Operations
void CBinaryTree::insertElement(CNode* pNode)
{
	if (!pNode)
	{
		std::cout << "The inserted node is null.\n";
		return;
	}

	if (!m_pRoot)
	{
		m_pRoot = pNode; 
		m_pRoot->m_pParent = nullptr;

		return;
	}
	
	std::queue<CNode*> dqTree;
	dqTree.push(m_pRoot);

	while (dqTree.empty())
	{
		CNode* pTmpNode = dqTree.back();
		dqTree.pop();

		if (pTmpNode)
		{
			if (pTmpNode->m_pLeft)
			{
				dqTree.push(pTmpNode->m_pLeft);
			}
			else
			{
				pTmpNode->m_pLeft = pNode; 
				pNode->m_pParent = pTmpNode;
			}
			
			if (pTmpNode->m_pRight)
			{
				dqTree.push(pTmpNode->m_pRight);
			}
			else
			{
				pTmpNode->m_pRight = pNode; 
				pNode->m_pParent = pTmpNode;
			}
		}
	}
}


// find the replaced node that is deepest and rightmost node in binary tree.
void CBinaryTree::deleteElement(int data)
{
	CNode* pDeletedNode = getDeletedNode(data);

	CNode* pRightmostnode = getRightmostNode(m_pRoot);


}


bool CBinaryTree::empty()
{
	return m_pRoot == nullptr;
}


CNode* CBinaryTree::getDeletedNode(int data)
{
	if (empty())
	{
		std::cout << "Tree has no element.\n";
		return;
	}

	std::queue<CNode*>	queTree;
	queTree.push(m_pRoot);

	while (queTree.empty())
	{
		CNode* pTmpNode = queTree.back();
		queTree.pop();
		if (pTmpNode)
		{
			if (pTmpNode->m_nData == data)
			{
				return pTmpNode;
			}

			if (pTmpNode->m_pLeft)
			{
				queTree.push(pTmpNode->m_pLeft);
			}

			if (pTmpNode->m_pRight)
			{
				queTree.push(pTmpNode->m_pRight);
			}
		}
	}
}


CNode* CBinaryTree::getRightmostNode(CNode* pNode)
{
	if (!pNode)
	{
		return nullptr;
	}

	CNode* pRightmost = getRightmostNode(pNode->m_pRight);
	if (pRightmost == nullptr)
	{
		return pNode;
	}

	return pRightmost;
}


void CBinaryTree::deleteTree()
{

}
#pragma endregion