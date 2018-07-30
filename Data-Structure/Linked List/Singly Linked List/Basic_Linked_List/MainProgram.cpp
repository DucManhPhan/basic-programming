#include "stdafx.h"
#include "LinkedList.h"





int main()
{
	int a[] = { 1, 2, 3, 4, 5, 6 };
	int size = sizeof(a) / sizeof(int);
	CLinkedList lkist;
	node* loopedNode = nullptr;

	for (int i = 0; i < size; ++i)
	{
		node* tmp = lkist.makeNode(a[i]);

		lkist.insertTail(tmp);
	}

	lkist.reverseList_Recursive();
	lkist.printList();

	system("pause");
	return 0;
}
