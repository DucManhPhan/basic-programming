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

	while (!dqTree.empty())
	{
		CNode* pTmpNode = dqTree.front();
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

				break;
			}
			
			if (pTmpNode->m_pRight)
			{
				dqTree.push(pTmpNode->m_pRight);
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


// find the replaced node that is deepest and rightmost node in binary tree.
void CBinaryTree::deleteElement(int data)
{
	CNode* pDeepestNode = nullptr;
	CNode* pDeletedNode = getDeletedNode(data, pDeepestNode);

	if (pDeepestNode)
	{
		int nValueDeepestNode = pDeepestNode->m_nData;

		// save the parent of the deepest node. 
		CNode* pParentOfDeepestNode = pDeepestNode->m_pParent;
		if (pParentOfDeepestNode->m_pRight == pDeepestNode)
		{
			pParentOfDeepestNode->m_pRight = nullptr;
		}
		else if (pParentOfDeepestNode->m_pLeft == pDeepestNode)
		{
			pParentOfDeepestNode->m_pLeft = nullptr;
		}

		// assign value of the deepest node to the deleted node. 
		pDeletedNode->m_nData = nValueDeepestNode;

		// delete the Deepest node. 
		delete pDeepestNode;
		pDeepestNode = nullptr;
	}
}


bool CBinaryTree::empty()
{
	return m_pRoot == nullptr;
}


CNode* CBinaryTree::getDeletedNode(int data, CNode*& pDeepestNode)
{
	if (empty())
	{
		std::cout << "Tree has no element.\n";
		return nullptr;
	}

	std::queue<CNode*>	queTree;
	queTree.push(m_pRoot);

	CNode* pTmpNode = nullptr;
	CNode* pDeleteNode = nullptr;

	while (!queTree.empty())
	{
		pTmpNode = queTree.front();
		queTree.pop();
		if (pTmpNode)
		{
			if (pTmpNode->m_nData == data)
			{
				pDeleteNode = pTmpNode;
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

	pDeepestNode = pTmpNode;
	return pDeleteNode;
}


void CBinaryTree::deleteTree()
{


}

	
void CBinaryTree::printTree()
{
	if (!m_pRoot)
	{
		return;
	}
	
	printTreeUtils(m_pRoot);
}


void CBinaryTree::printTreeUtils(CNode* pNode)
{
	if (pNode)
	{
		printTreeUtils(pNode->m_pLeft);

		std::cout << pNode->m_nData << "  ";

		printTreeUtils(pNode->m_pRight);
	}
}
#pragma endregion