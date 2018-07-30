#include "DoublyLinkedList.h"



#pragma region Constructors. 
CDoublyLinkedList::CDoublyLinkedList() : m_pHeadNode(nullptr)
									   , m_pLastNode(nullptr)
									   , m_nCount(0)
{
	// nothing to do.
}


CNode* CDoublyLinkedList::makeNode(int data)
{
	return new CNode(data);
}

#pragma endregion


#pragma region Insertion operation.
void CDoublyLinkedList::insertTail(CNode* pNewNode)
{
	if (!m_pHeadNode || !m_pLastNode)
	{
		m_pHeadNode = pNewNode; 
		m_pLastNode = m_pHeadNode;		
	}
	else
	{
		m_pLastNode->m_pNext = pNewNode;
		pNewNode->m_pPrevious = m_pLastNode;
		m_pLastNode = pNewNode;
	}

	++m_nCount;
}


void CDoublyLinkedList::insertBegin(CNode* pNewNode)
{
	if (!m_pHeadNode || !m_pLastNode)
	{
		m_pHeadNode = pNewNode;
		m_pLastNode = m_pHeadNode;		
	}
	else
	{
		pNewNode->m_pNext = m_pHeadNode;
		m_pHeadNode->m_pPrevious = pNewNode;
		m_pHeadNode = pNewNode;
	}
	
	++m_nCount;
}


void CDoublyLinkedList::insertBefore(int valueOfNode, CNode* pNewNode)
{
	CNode* pFoundNode = findNodeWith(valueOfNode);
	if (!pFoundNode)
	{
		std::cout << "Do not  find the node with value is: " << valueOfNode << "\n";
		return;
	}

	CNode* pPreviousNode = pFoundNode->m_pPrevious;

	// link to the other nodes. 
	pPreviousNode->m_pNext = pNewNode;
	pNewNode->m_pPrevious = pPreviousNode;
	pNewNode->m_pNext = pFoundNode;
	pFoundNode->m_pPrevious = pNewNode;

	++m_nCount;
}


void CDoublyLinkedList::insertAfter(int valueOfNode, CNode* pNewNode)
{
	CNode* pFoundNode = findNodeWith(valueOfNode);
	if (!pFoundNode)
	{
		std::cout << "Do not find the node with value is: " << valueOfNode << "\n";
		return;
	}

	CNode* pNextNode = pFoundNode->m_pNext;

	// link to the other nodes. 
	pFoundNode->m_pNext = pNewNode; 
	pNewNode->m_pPrevious = pFoundNode;
	pNewNode->m_pNext = pNextNode;
	pNextNode->m_pPrevious = pNewNode; 	

	++m_nCount;
}


CNode* CDoublyLinkedList::findNodeWith(int value)
{
	CNode* pFoundNode = nullptr; 

	for (CNode* pCurrentNode = m_pHeadNode; pCurrentNode; pCurrentNode = pCurrentNode->m_pNext)
	{
		if (pCurrentNode->m_nData == value)
		{
			pFoundNode = pCurrentNode;
			break;
		}
	}

	return pFoundNode;
}
#pragma endregion


#pragma region Deletion operation.
void CDoublyLinkedList::deleteTail()
{
	if (!m_pHeadNode || !m_pLastNode)
	{
		std::cout << "List is null.\n";
		return;
	}

	// link the last node to the other nodes. 
	CNode* pRemarkedNode = m_pLastNode;	
	m_pLastNode = m_pLastNode->m_pPrevious;
	m_pLastNode->m_pNext = nullptr;

	// delete last node. 
	delete pRemarkedNode; 
	pRemarkedNode = nullptr; 
}


void CDoublyLinkedList::deleteBegin()
{
	if (!m_pHeadNode || !m_pLastNode)
	{
		std::cout << "List is null.\n";
		return;
	}
	
	CNode* pRemarkedNode = m_pHeadNode;
	m_pHeadNode = m_pHeadNode->m_pNext;
	m_pHeadNode->m_pPrevious = nullptr;

	// delete the head node. 
	delete pRemarkedNode;
	pRemarkedNode = nullptr;
}


void CDoublyLinkedList::deleteMiddle(int valueOfNode)
{
	CNode* pFoundNode = findNodeWith(valueOfNode);
	if (!pFoundNode)
	{
		std::cout << "Do not exist element in linked list.\n";
		return;
	}

	// link to the other nodes. 
	pFoundNode->m_pPrevious->m_pNext = pFoundNode->m_pNext;
	pFoundNode->m_pNext->m_pPrevious = pFoundNode->m_pPrevious;
	
	// delete the node. 
	delete pFoundNode;
	pFoundNode = nullptr;
}
#pragma endregion


#pragma region Print list. 
void CDoublyLinkedList::printList()
{
	for (CNode* pCurrentNode = m_pHeadNode; pCurrentNode; pCurrentNode = pCurrentNode->m_pNext)
	{
		std::cout << pCurrentNode->m_nData << "  ";
	}

	std::cout << "\n";
}
#pragma endregion


#pragma region Reverse list. 
void CDoublyLinkedList::iterative_ReverseList()
{
	CNode* pPrevNode = nullptr; 
	CNode* pCurrentNode = m_pHeadNode;
	CNode* pNextNode = nullptr;

	for (; pCurrentNode;)
	{
		pNextNode = pCurrentNode->m_pNext;

		// link to the other nodes. 
		pCurrentNode->m_pNext = pPrevNode;
		pCurrentNode->m_pPrevious = pNextNode; 

		pPrevNode = pCurrentNode;
		pCurrentNode = pNextNode;
	}

	m_pHeadNode = pPrevNode;
}


void CDoublyLinkedList::recursive_ReverseList()
{
	CNode* pTempNode = m_pHeadNode;
	if (!pTempNode || !(pTempNode->m_pNext))
	{
		cout << "The doubly linked list in empty or The list have one element.\n";
		return;
	}

	// Way 1: This way have to fix the next, previous pointer of the head element. 
	// Because it only run from the second element. 
	//childRecursiveReverse(pTempNode, pTempNode->m_pNext, m_pHeadNode);
	//
	//pTempNode->m_pPrevious = pTempNode->m_pNext; //(*)
	//pTempNode->m_pNext = nullptr;					// (**)
	//m_pLastNode = pTempNode;

	// Way 2: Do not worry about this problem: infinity loop when you do not have the two line: (*) and (**).
	childRecursiveReverse_NotInfinityLoop(pTempNode, nullptr, m_pHeadNode);
	m_pLastNode = pTempNode;
}


void CDoublyLinkedList::childRecursiveReverse(CNode* firstNode, CNode* remainNode, CNode*& pHeadNode)
{
	if (!remainNode)
	{		
		return;
	}

	childRecursiveReverse(firstNode->m_pNext, remainNode->m_pNext, pHeadNode);

	if (!remainNode->m_pNext)
	{
		pHeadNode = remainNode;
	}

	remainNode->m_pPrevious = remainNode->m_pNext;
	remainNode->m_pNext = firstNode;	
}


void CDoublyLinkedList::childRecursiveReverse_NotInfinityLoop(CNode* pCurrentNode, CNode* pPrevNode, CNode*& pHeadNode)
{
	if (!pCurrentNode)
	{
		return;
	}

	if (!pCurrentNode->m_pNext)
	{
		pHeadNode = pCurrentNode;
	}

	CNode* pNextNode = pCurrentNode->m_pNext;

	pCurrentNode->m_pNext = pPrevNode;
	pCurrentNode->m_pPrevious = pNextNode;

	pPrevNode = pCurrentNode;
	pCurrentNode = pNextNode;

	childRecursiveReverse_NotInfinityLoop(pCurrentNode, pPrevNode, pHeadNode);
}
#pragma endregion
