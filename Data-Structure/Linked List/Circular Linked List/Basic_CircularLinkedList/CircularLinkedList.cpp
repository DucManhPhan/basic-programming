#include "stdafx.h"
#include "CircularLinkedList.h"



#pragma region Constructor
CCircularLinkedList::CCircularLinkedList() : m_nCount(0), m_pLastNode(nullptr)
{
	// nothing to do. 
}


CNode* CCircularLinkedList::makeNode(int value)
{
	return new CNode(value);
}
#pragma endregion


#pragma region Insertion operation.
void CCircularLinkedList::insertTail(CNode* newNode)
{
	if (!newNode)
	{
		cout << "New node is null!\n";
		return;
	}

	if (!m_pLastNode)
	{
		m_pLastNode = newNode;
		newNode->m_pNext = newNode;
	}
	else
	{
		newNode->m_pNext = m_pLastNode->m_pNext;
		m_pLastNode->m_pNext = newNode;
		m_pLastNode = newNode;
	}	
}


void CCircularLinkedList::insertBegin(CNode* newNode)
{
	if (!newNode)
	{
		cout << "New node have a null value.\n";
		return;
	}

	if (!m_pLastNode)
	{
		m_pLastNode = newNode;
		newNode->m_pNext = newNode;
	}
	else
	{
		newNode->m_pNext = m_pLastNode->m_pNext;
		m_pLastNode->m_pNext = newNode;
	}	
}


void CCircularLinkedList::insertAfter(CNode* newNode, int valueOfNode)
{
	CNode* pPrevNode = nullptr;
	CNode* pFoundNode = findNodeWith(pPrevNode, valueOfNode);

	// Insert the new node after the found node. 
	if (!pFoundNode)
	{
		cout << "No exist the element with the value that is: " << valueOfNode << "\n";
		return;
	}
	else
	{
		if (pFoundNode == m_pLastNode)
		{
			insertTail(newNode);
			return;
		}
		else
		{
			newNode->m_pNext = pFoundNode->m_pNext;
			pFoundNode->m_pNext = newNode;
		}
	}
}


void CCircularLinkedList::insertBefore(CNode* newNode, int valueOfNode)
{
	CNode* pPrevNode = nullptr;
	CNode* pFoundNode = findNodeWith(pPrevNode, valueOfNode);

	// Insert the new node before the found node. 
	if (!pFoundNode)
	{
		cout << "No exist the element with the value that is: " << valueOfNode << "\n";
		return;
	}
	else
	{
		newNode->m_pNext = pFoundNode;
		pPrevNode->m_pNext = newNode;
	}
}


CNode* CCircularLinkedList::findNodeWith(CNode*& pPrevNode, int valueOfNode)
{
	CNode* pFoundNode = nullptr;
	CNode* pNode = m_pLastNode->m_pNext;
	pPrevNode = m_pLastNode;

	// traverse circular linked list to search the node that have m_nData = valueOfNode. 
	for (; pNode != m_pLastNode; pNode = pNode->m_pNext)
	{
		if (pNode->m_nData == valueOfNode)
		{
			pFoundNode = pNode;
			break;
		}

		pPrevNode = pNode;
	}

	// compare with the last node. 
	if (!pFoundNode)
	{
		if (m_pLastNode->m_nData == valueOfNode)
		{
			pFoundNode = m_pLastNode;
		}
	}

	return pFoundNode;
}

#pragma endregion


#pragma region Deletion operation.
void CCircularLinkedList::deleteNodeAt(int valueOfNode)
{
	CNode* pPrevNode = nullptr; 
	CNode* pFoundNode = findNodeWith(pPrevNode, valueOfNode);

	// links the other nodes. 
	pPrevNode->m_pNext = pFoundNode->m_pNext;
	if (pFoundNode == m_pLastNode)
	{
		m_pLastNode = pPrevNode;
	}

	// delete node. 
	delete pFoundNode;
	pFoundNode = nullptr; 
}


void CCircularLinkedList::deleteBegin()
{
	if (!m_pLastNode)
	{
		return;
	}

	// save the first node. 
	CNode* pFirstNode = m_pLastNode->m_pNext;

	// link the other nodes. 
	m_pLastNode->m_pNext = pFirstNode->m_pNext;

	// delete first node. 
	delete pFirstNode;
	pFirstNode = nullptr; 
}


void CCircularLinkedList::deleteTail()
{
	if (!m_pLastNode)
	{
		return; 
	}

	// save the last node. 
	CNode* pLastNode = m_pLastNode;

	// links to the other nodes. 
	CNode* pPrevNode = nullptr; 
	//CNode* pFoundNode = findNodeWith(pPrevNode, pLastNode->m_nData);
	for (CNode* pCurrentNode = pLastNode->m_pNext;; pCurrentNode = pCurrentNode->m_pNext)
	{
		if (pCurrentNode->m_pNext == pLastNode)
		{
			pPrevNode = pCurrentNode;
			break;
		}
	}

	if (pPrevNode)
	{
		pPrevNode->m_pNext = pLastNode->m_pNext;
		m_pLastNode = pPrevNode;
	}

	// delete the last node. 
	delete pLastNode;
	pLastNode = nullptr;
}
#pragma endregion 


#pragma region Print this circular linked list. 
void CCircularLinkedList::printList()
{
	for (CNode* pNode = m_pLastNode->m_pNext; pNode != m_pLastNode; pNode = pNode->m_pNext)
	{
		cout << pNode->m_nData << "  ";
	}

	cout << m_pLastNode->m_nData << "\n";
}
#pragma endregion


#pragma region Reverse the circular linked list.
void CCircularLinkedList::reverseList_Iterative()
{
	CNode* pFirstNode = m_pLastNode->m_pNext;
	CNode* pCurrentNode = pFirstNode;
	CNode* pNextNode = nullptr; 
	CNode* pPrevNode = m_pLastNode;
	
	bool isOneLoop = false;

	for (; pCurrentNode != pFirstNode || isOneLoop == false;)
	{
		// save the next elements. 
		pNextNode = pCurrentNode->m_pNext;

		// link the current node to the previous node. 
		pCurrentNode->m_pNext = pPrevNode;

		// restore the links to the other nodes. 
		pPrevNode = pCurrentNode;
		pCurrentNode = pNextNode;
		
		if (pCurrentNode == pFirstNode)
		{
			isOneLoop = true;
		}
	}

	m_pLastNode = pNextNode;
}


void CCircularLinkedList::reverseList_Recursive()
{
	if (!m_pLastNode || !m_pLastNode->m_pNext)
	{
		return;
	}

	CNode* pTmpNode = m_pLastNode->m_pNext;
	reverseList_RecursiveUtil(pTmpNode, pTmpNode, m_pLastNode, m_pLastNode);
}


void CCircularLinkedList::reverseList_RecursiveUtil(const CNode* pFirstNode, CNode* pCurrentNode, CNode* pPrevNode, CNode*& pTailNode)
{
	if (!pCurrentNode)
	{
		return;
	}	

	CNode* pNextNode = pCurrentNode->m_pNext;

	pCurrentNode->m_pNext = pPrevNode;

	pPrevNode = pCurrentNode;
	pCurrentNode = pNextNode;
	
	if (pCurrentNode == pFirstNode)
	{
		m_pLastNode = pCurrentNode;	
		return;
	}

	reverseList_RecursiveUtil(pFirstNode, pCurrentNode, pPrevNode, m_pLastNode);
}

#pragma endregion