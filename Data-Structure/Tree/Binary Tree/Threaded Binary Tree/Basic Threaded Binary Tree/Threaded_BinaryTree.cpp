#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>


#pragma region Definition of Threaded Binary Tree node
struct CNode
{
	int		m_nData;
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


#pragma region Delete element
void deleteElement(int data)
{

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

	InorderTree();

	system("pause");
	return 0;
}
#pragma endregion
