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
	CLinkedList() : m_pHeadNode(nullptr), m_nCount(0)
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
			return;
		}

		if (!m_pHeadNode)
		{
			m_pHeadNode = pNewNode;
		}
		else
		{
			CNode* pCurrNode = m_pHeadNode;
			for (; pCurrNode->m_pNext; pCurrNode = pCurrNode->m_pNext)
			{
				// nothing to do. 
			}

			pCurrNode->m_pNext = pNewNode;
		}
	}

	void printList()
	{
		for (CNode* pTmpNode = m_pHeadNode; pTmpNode; pTmpNode = pTmpNode->m_pNext)
		{
			std::cout << pTmpNode->m_nData << "  ";
		}

		std::cout << "\n";
	}

	CNode* getHeadNode()
	{
		return m_pHeadNode;
	}

	void setHeadNode(CNode* pHeadNode)
	{
		m_pHeadNode = pHeadNode;
	}

	void mergeSort(CNode*& pNode)
	{
		//MergeSort(headRef)
		//	1) If head is NULL or there is only one element in the Linked List
		//	   then return.
		//	2) Else divide the linked list into two halves.
		//	   splitList(head, &a, &b); /* a and b are two halves */
		//	3) Sort the two halves a and b.
		//		MergeSort(a);
		//		MergeSort(b);
		//	4) Merge the sorted a and b(using SortedMerge() discussed here)
		//	   and update the head pointer using headRef.
		//	   *headRef = SortedMerge(a, b);

		if (!pNode || !pNode->m_pNext)
		{
			return;
		}

		// find the middle element in this list. 
		CNode* firstList = nullptr;
		CNode* secondList = nullptr;

		splitList(pNode, firstList, secondList);

		// divide the list into the smaller list. 
		mergeSort(firstList);
		mergeSort(secondList);

		// merge the sorted first list and second list. 
		//pNode = merge(firstList, secondList);
		pNode = merge_Recursive(firstList, secondList);
	}

	CNode* merge(CNode* firstList, CNode* secondList)
	{
		CNode* thirdList = nullptr;
		CNode* pHead_FirstList = firstList; 
		CNode* pHead_SecondList = secondList;
		CNode* pNextNode = nullptr;
		//CNode* pTmpNode = nullptr;
		//int data;

		for (; pHead_FirstList && pHead_SecondList;)
		{
			if (pHead_FirstList->m_nData <= pHead_SecondList->m_nData)
			{				
				/*data = pHead_FirstList->m_nData;
				pHead_FirstList = pHead_FirstList->m_pNext;*/

				pNextNode = pHead_FirstList->m_pNext;
				pHead_FirstList->m_pNext = nullptr;

				insertTailForNode(thirdList, pHead_FirstList);

				pHead_FirstList = pNextNode;
			}
			else
			{
				/*data = pHead_SecondList->m_nData;
				pHead_SecondList = pHead_SecondList->m_pNext;*/

				pNextNode = pHead_SecondList->m_pNext;
				pHead_SecondList->m_pNext = nullptr;

				insertTailForNode(thirdList, pHead_SecondList);

				pHead_SecondList = pNextNode;
			}

			/*pTmpNode = makeNode(data);					
			insertTailForNode(thirdList, pTmpNode);*/
		}

		// add to the first list. 
		for (; pHead_FirstList;)//pHead_FirstList = pHead_FirstList->m_pNext)
		{
			// pTmpNode = makeNode(pHead_FirstList->m_nData);
			// insertTailForNode(thirdList, pTmpNode);

			pNextNode = pHead_FirstList->m_pNext;
			pHead_FirstList->m_pNext = nullptr;

			insertTailForNode(thirdList, pHead_FirstList);

			pHead_FirstList = pNextNode;
		}

		// add to the second list.
		for (; pHead_SecondList;) //pHead_SecondList = pHead_SecondList->m_pNext)
		{
			// pTmpNode = makeNode(pHead_SecondList->m_nData);
			// insertTailForNode(thirdList, pTmpNode);

			pNextNode = pHead_SecondList->m_pNext;
			pHead_SecondList->m_pNext = nullptr;

			insertTailForNode(thirdList, pHead_SecondList);

			pHead_SecondList = pNextNode;
		}

		return thirdList;
	}

	CNode* merge_Recursive(CNode* firstList, CNode* secondList)
	{
		CNode* thirdList = nullptr; 

		if (!firstList)
		{
			return secondList;
		}
		else if (!secondList)
		{
			return firstList;
		}

		if (firstList->m_nData < secondList->m_nData)
		{
			thirdList = firstList;
			thirdList->m_pNext = merge_Recursive(firstList->m_pNext, secondList);
		}
		else
		{
			thirdList = secondList;
			thirdList->m_pNext = merge_Recursive(firstList, secondList->m_pNext);
		}
		
		return thirdList;
	}

	void splitList(CNode* pNode, CNode*& firstList, CNode*& secondList)
	{
		// get the first list. 
		firstList = pNode; 

		CNode* pSlowNode = pNode; 
		CNode* pFastNode = pNode; 
		CNode* pPrevSlowNode = nullptr;

		for (; pFastNode && pFastNode->m_pNext; pSlowNode = pSlowNode->m_pNext, pFastNode = pFastNode->m_pNext->m_pNext)
		{
			pPrevSlowNode = pSlowNode;
		}
		
		pPrevSlowNode->m_pNext = nullptr;
		secondList = pSlowNode;
	}

	friend void insertTailForNode(CNode*& pNode, CNode* pAddedNode)
	{
		if (!pAddedNode)
		{
			return;
		}

		if (!pNode)
		{
			pNode = pAddedNode;
		}
		else
		{
			CNode* tmpNode = pNode;
			for (; tmpNode->m_pNext; tmpNode = tmpNode->m_pNext)
			{
				// nothing to do.
			}

			tmpNode->m_pNext = pAddedNode;
		}
	}


private:
	CNode*		m_pHeadNode; 
	int			m_nCount;
};


int main()
{
	int arr[] = { 15, 10, 5, 20, 3, 2 };
	int size = sizeof(arr) / sizeof(int);
	CLinkedList lkist;

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = lkist.makeNode(arr[i]);
		lkist.insertTail(pNode);
	}

	lkist.printList();

	std::cout << "Merge sort is: " << "\n";
	CNode* pNode = lkist.getHeadNode();

	lkist.mergeSort(pNode);

	lkist.setHeadNode(pNode);

	lkist.printList();

	system("pause");
	return 0;
}


