#pragma once
#include "stdafx.h"


class CMinHeap
{
public: 
	CMinHeap();
	CMinHeap(int size);

	// make the min heap property properly.
	void heapify(int i);

	// construct the min heap.
	void buildHeap();

	// find the parent of its node. 
	inline int parentKey(int i);

	// find the left of its node. 
	inline int leftKey(int i);

	// find the right of its node. 
	inline int rightKey(int i);

	// returns the root element of Min Heap - O(1).
	int getMini();

	// removes the minimum element from Min Heap - O(logn).
	int extractMin();

	// decreases value of key - O(logn). 
	void setKey(int i, int newValue);

	// inserts a new key takes O(logn).
	void insertKey(int newValue);

	// delete a key - O(logn). 
	void deleteKey(int i);

	// interchange the value of each a and b. 
	void swap(int& a, int& b);

	// print content of min heap.
	void printContent();

	#pragma region k largest element
	// Buble sort
	void bubbleSort();

	// Selection sort
	void selectionSort();

	// use temporary array
	void useTemporaryArray();

	// use heapsort
	void heapSort();

	// use quickselect. 
	void quickSelect(int start, int end);

	void quickSelectInitialize();

	int partition(int start, int end);

	// use min heap. 
	void useMinHeap();

	#pragma endregion

private: 
	vector<int>			m_vecArray; 
	int					m_nCapacity; 
	int					m_nCurrentSize;

	int					m_nNumberOfLargestElement;
};