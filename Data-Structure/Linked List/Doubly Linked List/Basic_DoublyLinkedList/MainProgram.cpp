#include "stdafx.h"
#include "DoublyLinkedList.h"




int main()
{
	vector<int> arr = { 1, 2, 3, 4, 5, 6 };
	int size = arr.size();
	CDoublyLinkedList lkist;

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = lkist.makeNode(arr[i]);
		lkist.insertTail(pNode);
	}

	lkist.printList();

	/*lkist.insertBefore(4, lkist.makeNode(10));

	lkist.printList();

	lkist.deleteMiddle(5);

	lkist.printList();*/

	cout << "Reverse the doubly linked list: \n";
	lkist.recursive_ReverseList();

	lkist.printList();

	system("pause");
	return 0;
}


