#include "stdafx.h"
#include "CircularLinkedList.h"



int main()
{
	CCircularLinkedList lkist;

	int a[] = {1, 2, 3, 4}; //{ 1, 5, 8, 7, 9, 6, 8 };
	int size = sizeof(a) / sizeof(int);

	// input for circular linked list. 
	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = lkist.makeNode(a[i]);
		lkist.insertTail(pNode);
	}

	lkist.printList();
	lkist.reverseList_Recursive();

	lkist.printList();

	system("pause");
	return 0;
}