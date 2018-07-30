// Insert the element into the sorted circular linked list.

#include <iostream>


enum State
{
	InsertedFirst = 0, 
	Normal,
	InsertedEnd
};


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


	void insertTail(CNode* pNewNode)
	{
		if (!pNewNode)
		{
			std::cout << "The new element is null value.\n";
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

	}


	void insertBegin(CNode* pNewNode)
	{
		if (!pNewNode)
		{
			std::cout << "The new element is null value.\n";
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
		}
	}


	void insertAfter(CNode* pCurrentNode, CNode* pNewNode)
	{
		if (!pCurrentNode || !pNewNode)
		{
			return; 
		}

		pNewNode->m_pNext = pCurrentNode->m_pNext;
		pCurrentNode->m_pNext = pNewNode;
	}


	void insertElementToSortedCLL(CNode* pNewNode)
	{
		if (!pNewNode)
		{
			std::cout << "This node does not exist.\n";
			return;
		}

		State state;
		CNode* pFoundNode = findNodeLessThan(pNewNode->m_nData, state);

		switch (state)
		{
		case InsertedFirst:
			insertBegin(pNewNode);
			break;

		case InsertedEnd:
			insertTail(pNewNode);
			break;
			
		case Normal:
			insertAfter(pFoundNode, pNewNode);
			break;

		default:
			break;
		}
	}


	CNode* findNodeLessThan(int value, State& st)
	{
		CNode* pCurrentNode = m_pLastNode->m_pNext;
		CNode* pPrevNode = nullptr;		
		bool isExist = false; 

		for (; pCurrentNode != m_pLastNode; pCurrentNode = pCurrentNode->m_pNext)
		{
			if (value < pCurrentNode->m_nData)
			{			
				isExist = true;
				break;
			}

			pPrevNode = pCurrentNode;
		}	

		if (isExist && pPrevNode == nullptr)
		{
			st = InsertedFirst;
		}
		else if (!isExist)		
		{
			if (value < m_pLastNode->m_nData)
			{
				st = Normal;
			}
			else
			{
				st = InsertedEnd;
			}
		}
		else
		{
			st = Normal;
		}

		return pPrevNode;
	}


	void printList()
	{
		CNode* pNode = m_pLastNode->m_pNext;

		for (; pNode != m_pLastNode; pNode = pNode->m_pNext)
		{
			std::cout << pNode->m_nData << "  ";
		}

		std::cout << pNode->m_nData << "\n";
	}

private:
	CNode*		m_pLastNode; 
	int			m_nCount; 	
};


int main()
{
	int a[] = { 3, 7, 9, 11 };
	int size = sizeof(a) / sizeof(int);
	CCircularLinkedList lkist;

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = lkist.makeNode(a[i]);
		lkist.insertTail(pNode);
	}

	std::cout << "The sorted linked list: ";
	lkist.printList();

	std::cout << "\n";

	lkist.insertElementToSortedCLL(lkist.makeNode(20));

	std::cout << "After insertion, the sorted linked list: ";
	lkist.printList();

	system("pause");
	return 0;
}