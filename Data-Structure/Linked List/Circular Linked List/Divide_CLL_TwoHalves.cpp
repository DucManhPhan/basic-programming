#include <iostream>


struct CNode
{
	int		m_nData; 
	CNode*	m_pNext; 

	CNode(int data) : m_nData(data), m_pNext(nullptr)
	{
		// nothing to do. 
	}
};


class CCircularLinkedList
{
public:
	CCircularLinkedList() : m_pLastNode(nullptr), m_nCount(0)
	{
		// nothing to do. 
	}

	CNode* makeNode(int data)
	{
		return new CNode(data);
	}

	bool isEmpty()
	{
		return m_pLastNode == nullptr; 
	}

	int getSize()
	{
		return m_nCount;
	}

	void insertTail(CNode* pNewNode)
	{
		if (!pNewNode)
		{
			return;
		}

		if (!m_pLastNode)
		{
			pNewNode->m_pNext = pNewNode; 
			m_pLastNode = pNewNode; 
		}
		else
		{
			pNewNode->m_pNext = m_pLastNode->m_pNext;
			m_pLastNode->m_pNext = pNewNode; 
			m_pLastNode = pNewNode; 
		}

		++m_nCount;
	}

	void divideTwoHalves(CCircularLinkedList& list1, CCircularLinkedList& list2)
	{
		if (!m_pLastNode || m_nCount == 1)
		{
			std::cout << "Do not divide this circular linked list.\n";
			return;
		}

		CNode* pPrevMiddleNode = nullptr; 
		bool isEven = false; 
		CNode* pMiddleNode = getMiddleElement(pPrevMiddleNode, isEven);

		CNode* pFirstNode = nullptr; 
		CNode* pLastNode = nullptr; 

		if (isEven)
		{
			// make the list 1.
			pFirstNode = m_pLastNode->m_pNext;
			pLastNode = pMiddleNode;

			makeChildCircularLL(pFirstNode, pLastNode, list1);

			// make the list 2. 
			pFirstNode = pMiddleNode;
			pLastNode = m_pLastNode->m_pNext;

			makeChildCircularLL(pFirstNode, pLastNode, list2);
		}
		else
		{
			// make the list 1. 
			pFirstNode = m_pLastNode->m_pNext;
			pLastNode = pMiddleNode->m_pNext;

			makeChildCircularLL(pFirstNode, pLastNode, list1);

			// make the list 2. 
			pFirstNode = pMiddleNode->m_pNext;
			pLastNode = m_pLastNode->m_pNext;

			makeChildCircularLL(pFirstNode, pLastNode, list2);
		}
	}

	CNode* getMiddleElement(CNode*& pPrevMiddleElem, bool& isEven)
	{
		CNode* pSlowNode = m_pLastNode->m_pNext;
		CNode* pFastNode = m_pLastNode->m_pNext;
		bool isLoop = false; 

		for (; pFastNode != m_pLastNode && pFastNode != m_pLastNode->m_pNext || isLoop == false;)
		{			
			pPrevMiddleElem = pSlowNode;

			pSlowNode = pSlowNode->m_pNext;
			pFastNode = pFastNode->m_pNext->m_pNext;

			if (pFastNode == m_pLastNode || pFastNode == m_pLastNode->m_pNext)
			{
				isLoop = true;
			}
		}		

		isEven = pFastNode == m_pLastNode ? false : true; 

		return pSlowNode;
	}


	void makeChildCircularLL(CNode* pFirstNode, CNode* pLastNode, CCircularLinkedList& list)
	{
		CNode* pCurrentNode = pFirstNode;
		for (; pCurrentNode != pLastNode; pCurrentNode = pCurrentNode->m_pNext)
		{
			CNode* pTmpNode = makeNode(pCurrentNode->m_nData);
			list.insertTail(pTmpNode);
		}
	}


	void printList()
	{
		CNode* pCurrentNode = m_pLastNode->m_pNext;

		for (; pCurrentNode != m_pLastNode; pCurrentNode = pCurrentNode->m_pNext)
		{
			std::cout << pCurrentNode->m_nData << "   ";
		}

		std::cout << pCurrentNode->m_nData << "\n";
	}

private:
	CNode*		m_pLastNode; 
	int			m_nCount;
};



int main()
{
	CCircularLinkedList lkist;
	int arr[] = { 1, 2 };//, 2, 3, 4, 5 };
	int size = sizeof(arr) / sizeof(int);

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = lkist.makeNode(arr[i]);
		lkist.insertTail(pNode);
	}

	lkist.printList();

	CCircularLinkedList list1;
	CCircularLinkedList list2;

	if (lkist.getSize() == 1 || lkist.isEmpty())
	{
		std::cout << "Do not divide this circular linked list.\n";
		return -1;
	}

	std::cout << "The elements of the two list have: \n"; 
	lkist.divideTwoHalves(list1, list2);
	
	std::cout << "List 1: ";
	list1.printList();

	std::cout << "\nList 2: ";
	list2.printList();

	system("pause");
	return 0;
}