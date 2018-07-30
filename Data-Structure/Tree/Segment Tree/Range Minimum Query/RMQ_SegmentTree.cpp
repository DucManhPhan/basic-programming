#include "RMQ_SegmentTree.h"
#include "stdafx.h"



// construct the segment tree. 
int* constructSegTree(int arr[], int length)
{
	int size = getMaxSize(length);

	int* pSegTree = new int[size]();
	if (pSegTree)
	{
		constructSegTreeUtil(arr, 0, length - 1, pSegTree, 0);
		return pSegTree;
	}

	return nullptr;
}


void constructSegTreeUtil(int arr[], int start, int end, int* pSegTree, int indexOfSegTree)
{
	if (start == end)
	{
		pSegTree[indexOfSegTree] = arr[start];
		return;
	}

	int mid = getIndexOfMid(start, end);
	constructSegTreeUtil(arr, start, mid, pSegTree, 2 * indexOfSegTree + 1);
	constructSegTreeUtil(arr, mid + 1, end, pSegTree, 2 * indexOfSegTree + 2);

	pSegTree[indexOfSegTree] = getMinElement(pSegTree[2 * indexOfSegTree + 1], pSegTree[2 * indexOfSegTree + 2]);
}


// update value at the index of element. 
void updateSegTree(int* pSegTree, int arr[], int length, int indexOrigin, int value)
{
	if (indexOrigin < 0 || indexOrigin >= length)
	{
		cout << "Invalid input.\n";
		return;
	}

	int diff = value - arr[indexOrigin];
	arr[indexOrigin] = value;

	updateSegTreeUtil(pSegTree, 0, length - 1, indexOrigin, diff, 0);
}


void updateSegTreeUtil(int* pSegTree, int start, int end, int indexOrigin, int value, int indexOfSegTree)
{
	// outside of range elements: start - end.
	if (indexOrigin < start || indexOrigin > end)
	{
		return;
	}

	// inside.
	if (start == end)
	{
		pSegTree[indexOfSegTree] += value;
		return;
	}


	int mid = getIndexOfMid(start, end);
	updateSegTreeUtil(pSegTree, start, mid, indexOrigin, value, 2 * indexOfSegTree + 1);
	updateSegTreeUtil(pSegTree, mid + 1, end, indexOrigin, value, 2 * indexOfSegTree + 2);

	pSegTree[indexOfSegTree] = getMinElement(pSegTree[2 * indexOfSegTree + 1], pSegTree[2 * indexOfSegTree + 2]);
}


// query minimum element. 
int querySegTree(int* pSegTree, int length, int queStart, int queEnd)
{
	if (queStart < 0 || queEnd >= length)
	{
		cout << "Invalid input.\n";
		return -1;
	}

	return querySegTreeUtil(pSegTree, 0, length - 1, queStart, queEnd, 0);
}


int querySegTreeUtil(int* pSegTree, int start, int end, int queStart, int queEnd, int indexOfSegTree)
{
	// outside of query index. 
	if (start > queEnd || end < queStart)
	{
		return INT_MAX;
	}

	// inside of query index: queStart --- start --- end --- queEnd. 
	if (queStart <= start && end <= queEnd)
	{
		return pSegTree[indexOfSegTree];
	}

	// overlap. 
	int mid = getIndexOfMid(start, end);
	int minLeft = querySegTreeUtil(pSegTree, start, mid, queStart, queEnd, 2 * indexOfSegTree + 1);
	int minRight = querySegTreeUtil(pSegTree, mid + 1, end, queStart, queEnd, 2 * indexOfSegTree + 2);

	return getMinElement(minLeft, minRight);
}


// update and query range elements. 
void updateRange(int* pSegTree, int* pLazyArr, int start, int end, int queStart, int queEnd, int diff, int indexOfSegTree)
{
	// check whether pLazyArr[] array need to be updated. 
	if (pLazyArr[indexOfSegTree] != 0)
	{
		pSegTree[indexOfSegTree] += pLazyArr[indexOfSegTree];

		if (start != end)
		{
			pLazyArr[2 * indexOfSegTree + 1] += pLazyArr[indexOfSegTree];
			pLazyArr[2 * indexOfSegTree + 2] += pLazyArr[indexOfSegTree];
		}
	}

	// outside. 
	if (start > queEnd || end < queStart)
	{
		return;
	}

	// inside: queStart --- start --- end --- queEnd. 
	if (queStart <= start && end <= queEnd)
	{
		pSegTree[indexOfSegTree] += diff;

		if (start != end)
		{
			pLazyArr[2 * indexOfSegTree + 1] += diff;
			pLazyArr[2 * indexOfSegTree + 2] += diff;
		}

		return;
	}

	// overlap.
	int mid = getIndexOfMid(start, end);
	updateRange(pSegTree, pLazyArr, start, mid, queStart, queEnd, diff, 2 * indexOfSegTree + 1);
	updateRange(pSegTree, pLazyArr, mid + 1, end, queStart, queEnd, diff, 2 * indexOfSegTree + 2);

	pSegTree[indexOfSegTree] = getMinElement(pSegTree[2 * indexOfSegTree + 1], pSegTree[2 * indexOfSegTree + 2]);
}


int queryRange(int* pSegTree, int* pLazyArr, int start, int end, int queStart, int queEnd, int diff, int indexOfSegTree)
{
	// check whether the element in range need to be updated or not. 
	if (pLazyArr[indexOfSegTree] != 0)
	{
		pSegTree[indexOfSegTree] += pLazyArr[indexOfSegTree];

		if (start != end)
		{
			pLazyArr[2 * indexOfSegTree + 1] += pLazyArr[indexOfSegTree];
			pLazyArr[2 * indexOfSegTree + 2] += pLazyArr[indexOfSegTree];
		}
	}

	// outside. 
	if (end < queStart || start > queEnd)
	{
		return INT_MAX;
	}

	// inside. 
	if (queStart <= start && end <= queEnd)
	{
		return pSegTree[indexOfSegTree];
	}

	// overlap. 
	int mid = getIndexOfMid(start, end);
	int min1 = queryRange(pSegTree, pLazyArr, start, mid, queStart, queEnd, diff, 2 * indexOfSegTree + 1);
	int min2 = queryRange(pSegTree, pLazyArr, mid + 1, end, queStart, queEnd, diff, 2 * indexOfSegTree + 2);

	return getMinElement(min1, min2);
}


// some any implementation. 
int getIndexOfMid(int start, int end)
{
	return (start + (end - start) / 2);
}


int getMaxSize(int length)
{
	int height = (int)ceil(log2(length));
	int size = 2 * (int)(pow(2, height)) - 1;

	return size;
}


void printContent(int* pSegTree, int length)
{
	int maxSize = getMaxSize(length);
	copy(pSegTree, pSegTree + maxSize, ostream_iterator<int>(cout, " "));
}


int getMinElement(int a, int b)
{
	return a < b ? a : b;
}


void segmentTree()
{
	int arr[] = { 2, 5, 1, 4, 9, 3 };
	int size = sizeof(arr) / sizeof(int);

	int* pSegTree = constructSegTree(arr, size);
	if (!pSegTree)
	{
		cout << "Do not create a segment tree.\n";
		return;
	}

	int maxSize = getMaxSize(size);
	int* pLazyArr = new int[maxSize]();

	updateRange(pSegTree, pLazyArr, 0, size - 1, 2, 4, 3, 0);
	updateRange(pSegTree, pLazyArr, 0, size - 1, 0, 4, 2, 0);

	int result = queryRange(pSegTree, pLazyArr, 0, size - 1, 2, 4, 3, 0);

	cout << "After updated elements, the minimum element of this segment is: " << result << "\n";

	//int result = querySegTree(pSegTree, size, 2, 4);

	//cout << "Minimum element of the segment 3 - 5 is: " << result << "\n";

	//updateSegTree(pSegTree, arr, size, 3, 10);

	printContent(pSegTree, size);
}