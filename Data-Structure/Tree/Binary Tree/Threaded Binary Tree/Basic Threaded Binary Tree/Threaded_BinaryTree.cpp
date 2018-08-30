#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>


#pragma region Definition of Threaded Binary Tree node
struct CNode
{
	int			m_nData;
	CNode*		m_pLeft; 
	CNode*		m_pRight;
	CNode*		m_pParent;

	bool		m_bLeftThread; 
	bool		m_bRightThread;

	CNode(int data) : m_nData(data), m_pLeft(nullptr), m_pRight(nullptr)
					, m_bLeftThread (true), m_bRightThread(true)
					, m_pParent(nullptr)
	{
		// nothing to do. 
	}

	~CNode()
	{
		m_pLeft = nullptr; 
		m_pRight = nullptr;
	}

};
#pragma endregion 


// root node for Threaded binary tree. 
CNode*		pRoot = nullptr; 


#pragma region Common Operation
#pragma region Create Node
CNode* makeNode(int data)
{
	return new CNode(data);
}
#pragma endregion


#pragma region Insert element
void insertElement(CNode* pNode)
{
	CNode* pTmpNode = pRoot;
	CNode* pParent = nullptr; 

	// find the suitable position in order to insert that pNode. 
	for (; pTmpNode;)
	{
		// duplicate elements. 
		if (pTmpNode->m_nData == pNode->m_nData)
		{
			return;
		}

		pParent = pTmpNode;
		if (pNode->m_nData < pTmpNode->m_nData)
		{
			if (pTmpNode->m_bLeftThread == false)
			{
				pTmpNode = pTmpNode->m_pLeft;
			}
			else
			{
				break;
			}
		}
		else if (pTmpNode->m_nData < pNode->m_nData)
		{
			if (pTmpNode->m_bRightThread == false)
			{
				pTmpNode = pTmpNode->m_pRight;
			}
			else
			{
				break;
			}
		}
	}

	if (!pRoot)
	{
		pRoot = pNode;
		return;
	}

	if (pNode->m_nData < pTmpNode->m_nData)		// left node.
	{
		pNode->m_pLeft = pParent->m_pLeft;
		pNode->m_pRight = pParent;

		pParent->m_bLeftThread = false; 
		pParent->m_pLeft = pNode;
	}
	else if (pTmpNode->m_nData < pNode->m_nData)	// right node. 
	{
		pNode->m_pRight = pParent->m_pRight;
		pNode->m_pLeft = pParent; 

		pParent->m_bRightThread = false; 
		pParent->m_pRight = pNode;
	}
	else
	{
		return;
	}
}
#pragma endregion


#pragma region Inorder traversal
CNode* InorderPredessorNode(CNode* pNode)
{
	if (!pNode)
	{
		return nullptr;
	}

	// that node has the left threaded node. 
	if (pNode->m_bLeftThread)
	{
		return pNode->m_pLeft;
	}

	// find the most right node of the left subtree. 
	pNode = pNode->m_pLeft;
	while (pNode->m_bRightThread == false)
	{
		pNode = pNode->m_pRight;
	}

	return pNode;
}


CNode* InorderSucessorNode(CNode* pNode)
{
	if (!pNode)
	{
		return nullptr;
	}

	// that node has the right threaded node. 
	if (pNode->m_bRightThread)
	{
		return pNode->m_pRight;
	}

	// find the most left node of right subtree. 
	pNode = pNode->m_pRight;
	while (pNode->m_bLeftThread == false)
	{
		pNode = pNode->m_pLeft;
	}

	return pNode;
}


void InorderTree()
{
	if (!pRoot)
	{
		return;
	}

	CNode* pNode = pRoot;
	while (pNode->m_bLeftThread == false)
	{
		pNode = pNode->m_pLeft;
	}

	while (pNode)
	{
		std::cout << pNode->m_nData << " ";
		pNode = InorderSucessorNode(pNode);
	}
}
#pragma endregion


#pragma region Search Operation
CNode* searchElement(int data, CNode*& pParent)
{
	CNode* pTmpNode = pRoot; 
	pParent = nullptr; 

	while (pTmpNode != nullptr)
	{
		if (pTmpNode->m_nData == data)
		{
			std::cout << "\nFound node with the value - " << data << "\n";
			return pTmpNode;
		}

		if (pTmpNode->m_nData > data)
		{
			if (pTmpNode->m_bLeftThread == false)
			{
				pParent = pTmpNode;
				pTmpNode = pTmpNode->m_pLeft;
			}
			else
			{
				break;
			}
		}

		if (pTmpNode->m_nData < data)
		{
			if (pTmpNode->m_bRightThread == false)
			{
				pParent = pTmpNode;
				pTmpNode = pTmpNode->m_pRight;
			}
			else
			{
				break;
			}
		}
	}

	return nullptr;
}

#pragma endregion 


#pragma region Delete element
// There is one child node: left node or right node 
void del_OneChildNode(CNode* pParent, CNode* pNode)
{
	if (!pRoot)
	{
		return;
	}

	CNode* pChild = nullptr;

	// get child of the deleted node. 
	if (pNode->m_bLeftThread == false)
	{
		pChild = pNode->m_pLeft;
	}
	else
	{
		pChild = pNode->m_pRight;
	}

	// links the pointers of the Parent node to the Child node of deleted node. 
	if (!pParent)
	{
		pRoot = pChild;
	}
	else if (pNode == pParent->m_pLeft)
	{
		pParent->m_pLeft = pChild;
	}
	else if (pNode == pParent->m_pRight)
	{
		pParent->m_pRight = pChild;
	}

	// get the inorder predecessor and successor of the deleted node. 
	CNode* pSuccessor = InorderSucessorNode(pNode);
	CNode* pPredecessor = InorderPredessorNode(pNode);

	if (pNode->m_bLeftThread == false)
	{
		pPredecessor->m_pRight = pSuccessor;
	}
	else if (pNode->m_bRightThread == false)
	{
		pSuccessor->m_pLeft = pPredecessor;
	}

	delete pNode;
	pNode = nullptr;
}


// There is no child node. 
void del_NoChildNode(CNode* pParent, CNode* pNode)
{
	if (!pRoot)
	{
		return;
	}

	if (!pParent)
	{
		pRoot = nullptr;

		delete pNode;
		pNode = nullptr;

		return;
	}

	if (pParent->m_pLeft == pNode)
	{
		pParent->m_pLeft = pNode->m_pLeft;
		pParent->m_bLeftThread = true;
	}
	else
	{
		pParent->m_pRight = pNode->m_pRight;
		pParent->m_bRightThread = true;
	}

	delete pNode;
	pNode = nullptr;
}



// There are two child nodes
void del_TwoChildNode(CNode* pParent, CNode* pNode)
{
	if (!pRoot)
	{
		return;
	}

	// find the left most node of the right subtree of the pNode.
	CNode* pParentSuccessor = pNode; 
	CNode* pSuccessor = pNode->m_pRight;

	while (pSuccessor->m_bLeftThread == false)
	{
		pParentSuccessor = pSuccessor;
		pSuccessor = pSuccessor->m_pLeft;
	}

	// swap value between the deleted node and the successor node. 
	pNode->m_nData = pSuccessor->m_nData;

	if (pSuccessor->m_bLeftThread == true && pSuccessor->m_bRightThread == true)   // have no child nodes. 
	{
		del_NoChildNode(pParentSuccessor, pSuccessor);
	}
	else
	{
		del_OneChildNode(pParentSuccessor, pSuccessor);
	}
}





void deleteElement(int data)
{
	CNode* pParent = nullptr; 

	// search element with data. 
	CNode* pNode = searchElement(data, pParent);
	if (!pNode)
	{
		std::cout << "Do not exist this element.\n";
		return;
	}

	CNode* pFound = pNode;

	// When deleting node, there are 3 cases: 
	//	- case 1 - it is leaf node. 
	//  - case 2 - it has one child, or the right subtree or the left subtree. 
	//  - case 3 - it has two child. 
	if (pFound->m_bLeftThread == false && pFound->m_bRightThread == false)		// have two child
	{
		del_TwoChildNode(pParent, pFound);
	}
	else if (pFound->m_bLeftThread == false || pFound->m_bRightThread == false)		// have one child node
	{
		del_OneChildNode(pParent, pFound);
	}
	else  // no have child node
	{
		del_NoChildNode(pParent, pFound);
	}	
}
#pragma endregion
#pragma endregion


#pragma region Main Function
int main()
{
	int a[] = {20, 10, 30, 5, 16, 14, 17, 13};
	int size = sizeof(a) / sizeof(int);

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = makeNode(a[i]);

		insertElement(pNode);
	}

	deleteElement(16);

	InorderTree();

	system("pause");
	return 0;
}
#pragma endregion