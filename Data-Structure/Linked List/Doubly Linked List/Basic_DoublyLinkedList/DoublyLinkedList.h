#pragma once
#include "stdafx.h"



struct CNode
{
	int			m_nData; 
	CNode*		m_pNext; 
	CNode*		m_pPrevious; 

	CNode(int data) : m_nData(data), m_pNext(nullptr), m_pPrevious(nullptr)
	{
		// nothing to do. 
	}

	~CNode()
	{
		m_pNext = nullptr;
		m_pPrevious = nullptr;
	}
};



class CDoublyLinkedList
{
public:
	// Constructors. 
	CDoublyLinkedList();

	CNode* makeNode(int data);

	// Insertion operation.
	void insertTail(CNode* pNewNode);
	void insertBegin(CNode* pNewNode);
	void insertBefore(int valueOfNode, CNode* pNewNode);
	void insertAfter(int valueOfNode, CNode* pNewNode);
	CNode* findNodeWith(int value);

	// Deletion operation.
	void deleteTail();
	void deleteBegin();
	void deleteMiddle(int valueOfNode);

	// Print list. 
	void printList();

	// Reverse list. 
	void iterative_ReverseList();
	void recursive_ReverseList();
	void childRecursiveReverse(CNode* firstNode, CNode* remainNode, CNode*& pHeadNode);
	void childRecursiveReverse_NotInfinityLoop(CNode* pCurrentNode, CNode* pPrevNode, CNode*& pHeadNode);

private:
	CNode*		m_pHeadNode; 
	CNode*		m_pLastNode; 
	int			m_nCount;
};