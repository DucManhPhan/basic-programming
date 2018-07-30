#pragma once


struct CNode
{
	int m_nData; 
	CNode* m_pNext; 

	CNode(int data) : m_nData(data), m_pNext(nullptr)
	{
		// nothing to do. 
	}	
};



class CCircularLinkedList
{
public:
	// Constructor. 
	CCircularLinkedList();
	CNode* makeNode(int value);

	// Insertion operation.
	void insertTail(CNode* newNode);
	void insertBegin(CNode* newNode);
	void insertAfter(CNode* newNode, int valueOfNode);
	void insertBefore(CNode* newNode, int valueOfNode);

	CNode* findNodeWith(CNode*& pPrevNode, int valueOfNode);

	// Deletion operation.
	void deleteNodeAt(int valueOfNode);
	void deleteBegin();
	void deleteTail();

	// Reverse the circular linked list.
	void reverseList_Iterative();
	void reverseList_Recursive();
	void reverseList_RecursiveUtil(const CNode* pFirstNode, CNode* pCurrentNode, CNode* pPrevNode, CNode*& pTailNode);

	// Print this circular linked list. 
	void printList();

private: 
	CNode*		m_pLastNode; 
	int			m_nCount; 

};