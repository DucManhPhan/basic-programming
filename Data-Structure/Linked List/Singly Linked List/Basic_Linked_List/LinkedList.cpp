#include "LinkedList.h"


#pragma region Constructor
CLinkedList::CLinkedList()
{
	pFirstNode = nullptr;
	pLastNode = nullptr;

	count = 0;
}
#pragma endregion


#pragma region Make node
node* CLinkedList::RefFirstNode()
{
	return pFirstNode;
}


node* CLinkedList::makeNode(int data)
{
	return new node(data);
}
#pragma endregion


#pragma region Insert Operations
void CLinkedList::insertHead(node* newNode)
{
	//node *newNode = makeNode(data);

	if (!pFirstNode || !pLastNode)
	{
		pFirstNode = newNode;
		pLastNode = pFirstNode;
	}
	else
	{
		newNode->next = pFirstNode;
		pFirstNode = newNode;
	}

	++count;
}


void CLinkedList::insertTail(node* newNode)
{
	//node *newNode = makeNode(data);

	if (!pFirstNode || !pLastNode)
	{
		pFirstNode = newNode;
		pLastNode = pFirstNode;
	}
	else
	{
		pLastNode->next = newNode;
		pLastNode = pLastNode->next;
	}

	++count;
}


// insert node that have data = nData, after pSelectedNode.
void CLinkedList::insertAfter(node *pSelectedNode, node* newNode)
{
	if (pSelectedNode)
	{
		//node *newNode = makeNode(nData);

		// Connection Operations.
		newNode->next = pSelectedNode->next;
		pSelectedNode->next = newNode;

		++count;
	}
}
#pragma endregion


#pragma region Delete Operations
void CLinkedList::deleteHead()
{
	if (pFirstNode)
	{
		// Check the linked list have only one node. 
		if (pFirstNode == pLastNode)
		{
			pLastNode = nullptr;
		}

		node *pOldFirst = pFirstNode;
		pFirstNode = pFirstNode->next;

		delete pOldFirst;
		pOldFirst = nullptr;

		--count;
	}
}


void CLinkedList::deleteTail()
{
	if (pLastNode)
	{
		node *pOldLast = pLastNode;

		// Check the linked list have only one node. 
		if (pFirstNode == pLastNode)
		{
			pFirstNode = nullptr;
			pLastNode = nullptr;

			delete pOldLast;
			pOldLast = nullptr;
		}
		else
		{
			node *iter = nullptr;

			// Find the node is near the last node.
			for (iter = pFirstNode; iter->next->next; iter = iter->next)
			{
				// nothing to do.
			}

			pLastNode = iter;
			pLastNode->next = nullptr;

			// delete last node.
			delete pOldLast;
			pOldLast = nullptr;
		}
		
		--count;
	}
}


// delete node that have data = nData, after pNode.
void CLinkedList::deleteAfter(node *pNode)
{
	if (!pNode || !pNode->next)
	{
		return; 
	}

	node *pDeletedNode = pNode->next;
	pNode->next = pDeletedNode->next;

	delete pDeletedNode;
	pDeletedNode = nullptr;

	--count;
}


// delete node that is chose.
void CLinkedList::deleteNode(node *pSeletedNode)
{
	if (pSeletedNode)
	{
		// delete last node. 
		if (pSeletedNode->nData == pLastNode->nData)
		{
			deleteTail();
		}

		// delete head node.
		if (pSeletedNode->nData == pFirstNode->nData)
		{
			deleteHead();
		}

		node *pPreviousNode = nullptr;
		for (node *iter = pFirstNode; iter; iter = iter->next)
		{
			if (iter->next->nData == pSeletedNode->nData)
			{
				pPreviousNode = iter;
				pPreviousNode->next = pSeletedNode->next;

				break;
			}
		}

		delete pSeletedNode;
		pSeletedNode = nullptr;

		--count;
	}
}


// delete node that have pos position in linked list.
void CLinkedList::deleteNode(int pos)
{
	node *iter = pFirstNode;

	for (; iter && pos; --pos)
	{
		iter = iter->next;
	}

	deleteNode(iter);
}


void CLinkedList::deleteAll()
{
	node *pTemp = nullptr;
	node *pTmpFirst = pFirstNode;

	for (; pTmpFirst; pTmpFirst = pTemp)
	{
		pTemp = pTmpFirst->next;

		delete(pTmpFirst);
		pTmpFirst = nullptr;
	}

	pFirstNode = nullptr;
	pLastNode = nullptr;

	count = 0;
}
#pragma endregion 


#pragma region Search Operations
// use iteration to search element in linked list.
node* CLinkedList::seek(int i)
{
	node *iter = nullptr;

	for (iter = pFirstNode; iter; iter = iter->next)
	{
		if (iter->nData == i)
		{
			return iter;
		}
	}

	return nullptr;
}


// use recursive to search element in linked list.
bool CLinkedList::searchRecursive(node *pNode, int i)
{
	if (pNode)
	{
		if (pNode->nData == i)
		{
			return true;
		}
		else
		{
			return searchRecursive(pNode->next, i);
		}
	}
	else
	{
		return false;
	}
}

#pragma endregion


#pragma region Determine size of Linked List
int CLinkedList::length()
{
	return count;
}


int CLinkedList::lengthRecursive(node *pNode)
{
	if (!pNode)
	{
		return 0; 
	}

	return lengthRecursive(pNode->next) + 1;
}


int CLinkedList::lengthIteration()
{
	int length = 0;
	node *pNode = pFirstNode;

	for (; pNode; ++length)
	{
		pNode = pNode->next;
	}

	return length;
}
#pragma endregion


#pragma region Print the Linked List
void CLinkedList::printList()
{
	if (!pFirstNode || !pLastNode)
	{
		std::cout << "List dang rong!\n";
		return;
	}

	for (node *iterNode = pFirstNode; iterNode; iterNode = iterNode->next)
	{
		std::cout << iterNode->nData << "  ";
	}

	std::cout << "\n";
}
#pragma endregion


#pragma region Reverse List
void CLinkedList::reverseList_Iterative()
{
	node *pPrevNode		= nullptr;
	node *pNextNode		= nullptr;
	node *pCurrentNode	= nullptr;

	for (pCurrentNode = pFirstNode, pPrevNode = nullptr; pCurrentNode; )
	{
		// save next node. 
		pNextNode = pCurrentNode->next;

		// assign the next pointer of node to pPrevNode. 
		pCurrentNode->next = pPrevNode;

		// Update the pointer pPrevNode.
		pPrevNode = pCurrentNode;

		// Update for the pCurrentNode. 
		pCurrentNode = pNextNode;
	}

	// swap two pointer pFirstNode and pLastNode. 
	node *pTempNode = pFirstNode;
	pFirstNode = pLastNode;
	pLastNode = pTempNode;
}


void CLinkedList::reverseList_Recursive()
{
	node* pNode = pFirstNode;

	if (pNode && pNode->next)
	{
		// Way 1: Use recursion and swap two pointer: head and last. 
		//reverse_Recursive(pNode, pNode->next);

		//// swap two pointer pFirstNode and pLastNode. 
		//node *pTempNode = pFirstNode;
		//pFirstNode = pLastNode;
		//pLastNode = pTempNode;

		//pLastNode->next = nullptr;

		// Way 2: Use recursion in reverse function with only head pointer. 
		//reverseUtil_Recursive(pNode, pNode->next, pFirstNode);

		//pNode->next = nullptr; // (*) if you do not have this line, inifity loop will happen in 1 and 2: 1 -> <- 2 <- 3 <- 4 <-5 <- 6
		//pLastNode = pNode; 

		// Way 3: Use recursion, but in order to prevent the loop in Way 2, because when 
		// you do not have the above line (*).
		reverseUtil_RecursiveNotInfinityLoop(pNode, nullptr, pFirstNode);
		pLastNode = pNode;
	}
}


void CLinkedList::reverse_Recursive(node* pFirstNode, node* pRestNode)
{
	// base case.
	if (!pRestNode)
	{
		return;
	}

	// recursive
	reverse_Recursive(pRestNode, pRestNode->next);

	// process. 
	pRestNode->next = pFirstNode;
}


void CLinkedList::reverseUtil_Recursive(node* pNode, node* pRemainNode, node*& pHeadNode)
{
	if (!pRemainNode)
	{
		return;
	}

	reverseUtil_Recursive(pRemainNode, pRemainNode->next, pHeadNode);

	if (!pRemainNode->next)
	{
		pHeadNode = pRemainNode;
	}

	pRemainNode->next = pNode;
}


// This way has some part that is as same as with iteration of reversation function.
void CLinkedList::reverseUtil_RecursiveNotInfinityLoop(node* pNode, node* pPrevNode, node*& pHeadNode)
{
	if (!pNode)
	{
		return;
	}	

	if (!pNode->next)
	{
		pHeadNode = pNode;
	}

	// save the next node for the later recursion.
	node* pNextNode = pNode->next;

	// link to the previous node. 
	pNode->next = pPrevNode;
	
	pPrevNode = pNode;
	pNode = pNextNode;

	reverseUtil_RecursiveNotInfinityLoop(pNode, pPrevNode, pHeadNode);
}
#pragma endregion


# pragma region The other methods
// get Nth node in linked list. 
node* CLinkedList::getNthNode(int n)
{
	if (count < n)
	{
		std::cout << "The number of elements is not enough. \nSo, don't get the " << n << " node in the linked list.\n";
		return nullptr; 
	}

	node* tmp = pFirstNode;

	for (int i = 0; i < n; ++i)
	{
		tmp = tmp->next;
	}

	return tmp;
}


// get Nth node from the end of linked list. 
node* CLinkedList::getEndOfNthNode(int n)
{
	if (count - 1 < n)
	{
		std::cout << "Access to exceed the element in linked list.\n";
		return nullptr;
	}

	node* tmp = getNthNode(count - 1 - n);
	return tmp;
}


// print the middle element of linked list. 
void CLinkedList::printMiddleElement()
{
	if (count <= 1)
	{
		std::cout << "There are no enough element in linked list.\n";
		return; 
	}

	// get the index of middle element; 
	int nMiddle = count / 2; 

	node* tmp = getNthNode(nMiddle);
	if (tmp == nullptr)
	{
		std::cout << "Do not get this middle element.\n";
		return;
	}

	std::cout << "The middle element is: " << tmp->nData << "\n";
}


// count the number of times a given element occurs in linked list. 
int CLinkedList::countNumberGivenElement(node* givenNode)
{
	int nNumber = 0;	

	for (node* tmp = pFirstNode; tmp; tmp = tmp->next)
	{
		if (tmp->nData == givenNode->nData)
		{
			++nNumber;
		}
	}

	return nNumber;
}


// detect loop.
bool CLinkedList::isLoop()
{
	std::map<node*, int> mpLinkedList;

	// Case 1: use hashing, not use the flag. 
	for (node* tmp = pFirstNode; tmp; tmp = tmp->next)
	{
		if (mpLinkedList.find(tmp) != mpLinkedList.end())
		{
			return true; 
		}

		int nCount = 0;
		mpLinkedList[tmp] = ++nCount;
	}
	
	return false; 


	// Case 2: use the flag. 
	/*for (node* tmp = pFirstNode; tmp; tmp = tmp->next)
	{
		++tmp->nFlag;
		if (tmp->nFlag == LOOP)
		{
			return true;
		}
	}*/


	// Floyd's Cycle Finding Algorithm - fastest method. 
	/*node* lowNode = pFirstNode;
	node* fastNode = pFirstNode;

	while (lowNode && fastNode && fastNode->next)
	{
		lowNode = lowNode->next;
		fastNode = fastNode->next->next;

		if (lowNode == fastNode)
		{
			return true;
		}
	}

	return false; */	
}


#pragma region Check whether the linked list is palindrome or not
bool CLinkedList::isPalindrome()
{
	// Way 1: Use recursion.
	//node* tmpNode = pFirstNode;
	//return recursive_Palindrome(tmpNode, tmpNode->next);

	// Way 2: Use stack. 
	//return stack_Palindrome();

	// Way 3: Use reversation of linked list. 
	// 1. Get the middle element.
	node* pStartNode = pFirstNode;
	node* pPrevMiddleElement = nullptr; 
	bool isEven = false; 

	node* pMiddleNode = getMiddleElement(pPrevMiddleElement, isEven);

	// 2. Reverse the second half. 
	node* lastNodeOfSecondLL = nullptr; 
	node* secondLL = nullptr; 

	secondLL = isEven ? pMiddleNode : pMiddleNode->next;
	pPrevMiddleElement->next = nullptr;

	//secondLL = recursive_makeReversedLinkedListFrom(secondLL);
	secondLL = iterative_makeReversedLinkedListFrom(secondLL);

	// 3. Check the first half and second half are identical. 
	bool isPalindrome = compareLinkedLists(pStartNode, secondLL);

	// 4. Construct the linked list by reversing the second half again and attach it back to the first half. 
	//secondLL = recursive_makeReversedLinkedListFrom(secondLL);
	secondLL = iterative_makeReversedLinkedListFrom(secondLL);

	if (isEven)
	{
		pPrevMiddleElement->next = secondLL;
	}
	else
	{
		pPrevMiddleElement->next = pMiddleNode;
		pMiddleNode->next = secondLL;
	}	

	return isPalindrome;
}


#pragma region Way 1 _ Use recursive
bool CLinkedList::recursive_Palindrome(node*& firstNode, node* lastNode)
{
	if (!lastNode)
	{
		return true;
	}

	bool firstResult = recursive_Palindrome(firstNode, lastNode->next);
	if (!firstResult)
	{
		return false; 
	}

	// check data of two nodes. 
	bool secondResult = false;
	if (firstNode->nData == lastNode->nData)
	{
		secondResult = true;
	}

	firstNode = firstNode->next;

	return secondResult;
}

#pragma endregion


#pragma region Way 2 _ Use stack. 
bool CLinkedList::stack_Palindrome()
{
	stack<int> stkPalindrome; 

	// get elements for the stack. 	
	for (node* tmpNode = pFirstNode; tmpNode; tmpNode = tmpNode->next)
	{
		stkPalindrome.push(tmpNode->nData);
	}

	// compare the elements of the stack and linked list. 
	bool res = true;
	

	for (node* tmpNode = pFirstNode; !stkPalindrome.empty() || (tmpNode != nullptr); tmpNode = tmpNode->next)
	{
		int stkData = stkPalindrome.top();
		stkPalindrome.pop();

		if (stkData != tmpNode->nData)
		{
			res = false;
			break;
		}		
	}

	return res; 
}

#pragma endregion


#pragma region Way 3 _ Use the reversation of the second half
node* CLinkedList::getMiddleElement(node*& prevSlowNode, bool& isEven)
{
	node* pSlowNode = pFirstNode;
	node* pFastNode = pFirstNode;

	for (; pFastNode && pFastNode->next; pSlowNode = pSlowNode->next, pFastNode = pFastNode->next->next)
	{
		prevSlowNode = pSlowNode;
	}

	isEven = pFastNode ? false : true;

	return pSlowNode;
}


node* CLinkedList::recursive_makeReversedLinkedListFrom(node*& firstNode)
{
	if (!firstNode)
	{
		return nullptr; 
	}

	node* lastNode = nullptr; 
	recursive_ReverseLL(firstNode, firstNode->next, lastNode);

	firstNode->next = nullptr; 

	return lastNode;
}


void  CLinkedList::recursive_ReverseLL(node* firstNode, node* remainedNode, node*& lastNode)
{
	if (!remainedNode)
	{
		return;
	}

	recursive_ReverseLL(firstNode->next, remainedNode->next, lastNode);

	if (!remainedNode->next)
	{
		lastNode = remainedNode;
	}

	remainedNode->next = firstNode;
}


node* CLinkedList::iterative_makeReversedLinkedListFrom(node*& firstNode)
{
	node* pPrevNode = nullptr; 
	node* pNextNode = nullptr; 
	node* pCurrentNode = nullptr;

	for (pCurrentNode = firstNode; pCurrentNode;)
	{
		// save the next node of the current node. 
		pNextNode = pCurrentNode->next;

		pCurrentNode->next = pPrevNode;

		pPrevNode = pCurrentNode;
		pCurrentNode = pNextNode;
	}

	//firstNode->next = nullptr;

	return pPrevNode;
}


bool CLinkedList::compareLinkedLists(node* firstLinkedList, node* secondLinkedList)
{
	if (!firstLinkedList || !secondLinkedList)
	{
		return false; 
	}

	node* tmpFirstLL = firstLinkedList;
	node* tmpSecondLL = secondLinkedList;

	for (; tmpFirstLL || tmpSecondLL; tmpFirstLL = tmpFirstLL->next, tmpSecondLL = tmpSecondLL->next)
	{
		if (tmpFirstLL->nData != tmpSecondLL->nData)
		{
			return false;
		}
	}

	return true; 
}


#pragma endregion


#pragma endregion

#pragma endregion