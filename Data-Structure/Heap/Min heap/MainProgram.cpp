#include "stdafx.h"
#include "MinHeap.h"



int main()
{
	CMinHeap minHeap(11);
	minHeap.insertKey(3);
	minHeap.insertKey(2);
	minHeap.insertKey(1);
	minHeap.insertKey(15);
	minHeap.insertKey(5);
	minHeap.insertKey(4);
	minHeap.insertKey(45);

	minHeap.printContent();

	minHeap.bubbleSort();

	minHeap.selectionSort();

	/*cout << minHeap.extractMin() << " ";
	cout << minHeap.getMini() << " ";

	cout << "\n";

	minHeap.setKey(2, 0);

	minHeap.printContent();

	cout << minHeap.getMini();*/

	system("pause");
	return 0;
}