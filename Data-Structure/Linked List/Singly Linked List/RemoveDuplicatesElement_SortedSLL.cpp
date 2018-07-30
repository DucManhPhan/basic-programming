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
	// Constructor.
	CLinkedList() : m_pHeadNode(nullptr), m_nCount(0)
	{
		// nothing to do.
	}

	CNode* makeNode(int data)
	{
		return new CNode(data);
	}

	// Insertion operation.
	void insertTail(CNode* pNewNode)
	{
		if (!pNewNode)
		{
			return;
		}

		if (!m_pHeadNode)
		{
			m_pHeadNode = pNewNode;
			return;
		}

		CNode* tmpNode = m_pHeadNode;
		for (; tmpNode->m_pNext; tmpNode = tmpNode->m_pNext)
		{
			// nothing to do.
		}

		tmpNode->m_pNext = pNewNode;
	}

	// Deletion operation.
	void deleteNode(CNode* pCurrentNode, CNode* pPrevNode)
	{
		if (!m_pHeadNode || !pCurrentNode)
		{
			return;
		}
		
		if (!m_pHeadNode->m_pNext)
		{
			delete m_pHeadNode;
			m_pHeadNode = nullptr;
		}

		// links the previous node with the next of current node. 
		pPrevNode->m_pNext = pCurrentNode->m_pNext;

		// delete node.
		delete pCurrentNode;
		pCurrentNode = nullptr;
	}

	// Print list.
	void printList()
	{
		if (!m_pHeadNode)
		{
			return;
		}

		for (CNode* tmp = m_pHeadNode; tmp; tmp = tmp->m_pNext)
		{
			std::cout << tmp->m_nData << "   ";
		}

		std::cout << "\n";
	}

	// Remove the duplicate elements in the sorted singly linked list. 
	void removeDuplicateElements()
	{
		CNode* pTmpNode = m_pHeadNode;
		CNode* pPrevNode = nullptr;
		CNode* pNextNode = nullptr;

		for (; pTmpNode;)
		{
			// save the next node. 
			pNextNode = pTmpNode->m_pNext;

			if (pPrevNode != nullptr && pTmpNode->m_nData == pPrevNode->m_nData)
			{								
				// delete this node.
				deleteNode(pTmpNode, pPrevNode);

				// continue with the other nodes. 
				pTmpNode = pNextNode;

				continue;
			}
			
			pPrevNode = pTmpNode;
			pTmpNode = pNextNode;			
		}
	}


private:
	CNode*		m_pHeadNode; 
	int			m_nCount;
};



int main()
{
	int arr[] = { 1,2,2,2,2,5,6,8 }; //{ 1, 2, 3, 4, 4, 5, 6, 7, 7, 7, 7, 9, 89 };
	int size = sizeof(arr) / sizeof(int);
	CLinkedList lkist;

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = lkist.makeNode(arr[i]);

		lkist.insertTail(pNode);
	}

	lkist.printList();

	lkist.removeDuplicateElements();

	lkist.printList();

	system("pause");
	return 0;
}