// https://www.geeksforgeeks.org/union-and-intersection-of-two-linked-lists/


#include <iostream>


struct CNode
{
	int			m_nData; 
	CNode*		m_pNext; 

	CNode(int data) : m_nData(data), m_pNext(nullptr)
	{
		// nothing to do.
	}

	~CNode()
	{
		m_pNext = nullptr;
	}
};


class CLinkedList
{
public:
	// Constructor
	CLinkedList() : m_pFirstNode(nullptr), m_nCount(0)
	{
		// nothing to do. 
	}

	CNode* makeNode(int data)
	{
		return new CNode(data);
	}

	// Insertion operation
	void insertTail(CNode* pNewNode)
	{
		if (!pNewNode)
		{
			return;
		}

		if (!m_pFirstNode)
		{
			m_pFirstNode = pNewNode; 
		}
		else
		{
			// find the final node. 
			CNode* pCurrentNode = m_pFirstNode;

			for (; pCurrentNode->m_pNext; pCurrentNode = pCurrentNode->m_pNext)
			{
				// nothing to do.				
			}

			pCurrentNode->m_pNext = pNewNode;
		}
	}

	// Deletion operation
	void deleteList()
	{
		CNode* pCurrNode = m_pFirstNode;
		CNode* pNextNode = nullptr;

		for (; pCurrNode; )
		{
			// save the next element.
			pNextNode = pCurrNode->m_pNext;

			// delete the current node. 
			delete pCurrNode;

			pCurrNode = pNextNode;
		}
	}

	// Union operation
	CLinkedList unionWith(const CLinkedList& lklist)
	{

	}

	// Intersection operation 
	CLinkedList intersectWith(const CLinkedList& lklist)
	{

	}

private:
	CNode*		m_pFirstNode; 
	int			m_nCount;
};


int main()
{
	int firstArr[] = { 10, 15, 4, 20 };
	int secondArr[] = { 8, 4, 2, 10 };
	int sizeOfFirstArr = sizeof(firstArr) / sizeof(int);
	int sizeOfSecondArr = sizeof(secondArr) / sizeof(int);

	CLinkedList FirsLklist, SecondLklist; 

	// input for linked lists. 
	for (int i = 0; i < sizeOfFirstArr; ++i)
	{
		FirsLklist.insertTail(FirsLklist.makeNode(firstArr[i]));
	}

	for (int i = 0; i < sizeOfSecondArr; ++i)
	{
		SecondLklist.insertTail(SecondLklist.makeNode(secondArr[i]));
	}

	// common operations.




	system("pause");
	return 0;
}