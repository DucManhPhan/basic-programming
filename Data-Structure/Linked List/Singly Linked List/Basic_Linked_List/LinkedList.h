#pragma once

#include "stdafx.h"

#define NOT_ITERATE		0
#define ITERATED		1
#define LOOP			2

struct node
{
	int nData; 
	int nFlag; 

	node *next;

	node(int n) : nData(n), next(nullptr), nFlag(0)
	{
		// nothing to do.
	}
};

typedef void(*LLCALLBACK)(int, void*);


class CLinkedList
{
private:
	node* pFirstNode;
	node* pLastNode;
	int count;

public:
	CLinkedList();

	node *RefFirstNode();

	node* makeNode(int data);

	// Insert Operations.
	void insertHead(node* newNode);
	void insertTail(node* newNode);
	void insertAfter(node *pSelectedNode, node* newNode);
	void insertAtMiddle(node* newNode);

	// Delete Opertaions.
	void deleteHead();
	void deleteTail();
	void deleteAfter(node *pNode);
	void deleteAll();

	// delete node that was selected.
	void deleteNode(node *pSeletedNode);

	// delete node that have pos position in linked list.
	void deleteNode(int pos);

	// Search Operations.
	node* seek(int i);
	bool searchRecursive(node *pNode, int i);

	// Determine the size of Linked List.
	int length();
	int lengthRecursive(node *pNode);
	int lengthIteration();

	// Print Linked List.
	void printList();

	// Reverse the Linked List.
	void reverseList_Iterative();
	void reverseList_Recursive();//node *&pNode);
	void reverse_Recursive(node* pFirstNode, node* pRestNode);
	void reverseUtil_Recursive(node* pNode, node* pRemainNode, node*& pHeadNode);
	void reverseUtil_RecursiveNotInfinityLoop(node* pNode, node* pPrevNode, node*& pHeadNode);

	// get Nth node in linked list. 
	node* getNthNode(int n);

	// get Nth node from the end of linked list. 
	node* getEndOfNthNode(int n);

	// print the middle element of linked list. 
	void printMiddleElement();

	// count the number of times a given element occurs in linked list. 
	int countNumberGivenElement(node* givenNode);

	// detect loop.
	bool isLoop();

	// check whether a linked list is palindrome 
	bool isPalindrome();
	bool recursive_Palindrome(node*& firstNode, node* lastNode);
	bool stack_Palindrome();

	node* getMiddleElement(node*& prevSlowNode, bool& isEven);
	node* recursive_makeReversedLinkedListFrom(node*& firstNode);
	void  recursive_ReverseLL(node* firstNode, node* remainedNode, node*& lastNode);
	node* iterative_makeReversedLinkedListFrom(node*& firstNode);
	bool compareLinkedLists(node* firstLinkedList, node* secondLinkedList);


	// Quicksort with Linked Lists. 


	// Merge sort with Linked Lists. 


	// Merge two sorted linked lists.



	// Rotate Linked List.



	// Union and intersection of two Linked Lists 



	// Sum of last n nodes of the given linked list. 



};