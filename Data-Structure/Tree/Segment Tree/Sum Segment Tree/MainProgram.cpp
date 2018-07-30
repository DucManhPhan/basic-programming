// https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
// https://www.quora.com/Why-does-a-segment-tree-need-a-4n-size-array-Why-not-2n-1

#include "stdafx.h"

// get index of middle element that separate array. 
inline int getIndexOfMid(int start, int end);


// get max size of segment tree. 
int getMaxSize(int length);


// construct segment tree.
int* constructSegTree(int arr[], int length);

int constructSegTree_EachElement(int arr[], int start, int end, int* pSegTree, int indexOfSegTree);


// update operation for one node in segment tree. 
void updateNode(int* pSegTree, int arr[], int length, int indexOfOriginArray, int newValue);

void updateNode_EachElement(int* pSegTree, int start, int end, int indexOfOriginArray, int diff, int indexOfSegTree);

// update operation for range of elements. 
void updateRange(int* pSegTree, int start, int end, int queStart, int queEnd, int diff, int indexOfSegTree, int* pLazyArr);

// implement some query on segment tree. 
int getSum(int* pSegTree, int length, int queStart, int queEnd, int indexOfSegTree);

int getSum_EachElement(int* pSegTree, int start, int end, int queStart, int queEnd, int indexOfSegTree);

// query operation for range of elements. 
int getSumRange(int* pSegTree, int start, int end, int queStart, int queEnd, int indexOfSegTree, int* pLazyArr);


// print the content of segment tree. 
void printContent(int* pSegTree, int length);


int main()
{
	int arr[] = {1, 3, 5, 7, 9, 11}; //{ 5, 4, 6, 2, 3, 1, 7, 8, 9, 12, 10 };
	int length = sizeof(arr) / sizeof(int);
	int maxSize = getMaxSize(length);

	int* pSegTree = constructSegTree(arr, length);
	if (pSegTree == nullptr)
	{
		cout << "Khong the tao duoc segment tree.\n";
		return -1;
	}

	int* pLazyArr = new int[maxSize]();
	if (!pLazyArr)
	{
		cout << "Khong the tao duoc lazy array.\n";
		return -1;
	}

	//printContent(pSegTree, maxSize);

	/*int sum = getSum(pSegTree, length, 0, 10, 0);

	cout << "Sum cac phan tu trong khoang 2 - 10: " << sum << "\n" ;*/

	//updateNode(pSegTree, arr, length, 3, 5);

	/*int sum = getSum(pSegTree, length, 0, 5, 0);

	cout << "Sum cac phan tu trong khoang 1 - 6: " << sum << "\n";*/

	updateRange(pSegTree, 0, length - 1, 2, 4,  3, 0, pLazyArr);

	updateRange(pSegTree, 0, length - 1, 1, 4, 5, 0, pLazyArr);

	int sumRange = getSumRange(pSegTree, 0, length - 1, 2, 4, 0, pLazyArr);

	cout << "Sum trong khoang 2 - 4 sau khi update la: " << sumRange << "\n";

	system("pause");
	return 0;
}


// construct segment tree.
int* constructSegTree(int arr[], int length)
{
	if (length < 0)
	{
		return nullptr;
	}

	// When the number of elements in array is odd, you shouldn't use the expression: 2 * length - 1.
	int maxSize = getMaxSize(length);
	int* pSegTree = new int[maxSize]();
	if (pSegTree)
	{
		constructSegTree_EachElement(arr, 0, length - 1, pSegTree, 0);
		return pSegTree;
	}

	return nullptr;
}


int constructSegTree_EachElement(int arr[], int start, int end, int* pSegTree, int indexOfSegTree)
{
	if (start == end)
	{
		pSegTree[indexOfSegTree] = arr[start];
		return arr[start];
	}

	// get index of middle element between start and end. 
	int mid = getIndexOfMid(start, end);

	// calculate the value at node that have index = indexOfSegTree.
	int valueOfLeftNode = constructSegTree_EachElement(arr, start, mid, pSegTree, 2 * indexOfSegTree + 1);
	int valueOfRightNode = constructSegTree_EachElement(arr, mid + 1, end, pSegTree, 2 * indexOfSegTree + 2);
	pSegTree[indexOfSegTree] = valueOfLeftNode + valueOfRightNode;

	return pSegTree[indexOfSegTree];
}


// update operation for one node in segment tree. 
void updateNode(int* pSegTree, int arr[], int length, int indexOfOriginArray, int newValue)
{
	if (indexOfOriginArray < 0 || indexOfOriginArray >= length)
	{
		cout << "Chi so cua phan tu nay khong phu hop.\n";
		return;
	}

	int diff = newValue - arr[indexOfOriginArray];
	arr[indexOfOriginArray] = newValue;

	updateNode_EachElement(pSegTree, 0, length - 1, indexOfOriginArray, diff, 0);
}

void updateNode_EachElement(int* pSegTree, int start, int end, int indexOfOriginArray, int diff, int indexOfSegTree)
{
	if (indexOfOriginArray < start || indexOfOriginArray > end)
	{
		return;
	}	

	pSegTree[indexOfSegTree] += diff;

	if (start == end)
	{
		return;
	}

	int mid = getIndexOfMid(start, end);
	updateNode_EachElement(pSegTree, start, mid, indexOfOriginArray, diff, 2 * indexOfSegTree + 1);
	updateNode_EachElement(pSegTree, mid + 1, end, indexOfOriginArray, diff, 2 * indexOfSegTree + 2);
}


// implement some query on segment tree. 
int getSum(int* pSegTree, int length, int queStart, int queEnd, int indexOfSegTree)
{
	if (queStart > queEnd)
	{
		cout << "Khong the query duoc.\n";
		return -1;
	}

	return getSum_EachElement(pSegTree, 0, length - 1, queStart, queEnd, indexOfSegTree);
}


int getSum_EachElement(int* pSegTree, int start, int end, int queStart, int queEnd, int indexOfSegTree)
{
	if (queEnd < start || queStart > end)
	{
		return 0;
	}

	// Check the case that queStart---start---end---queEnd.
	if (queStart <= start && end <= queEnd)
	{
		return pSegTree[indexOfSegTree];
	}

	int mid = getIndexOfMid(start, end);
	int sumOfLeftNode = getSum_EachElement(pSegTree, start, mid, queStart, queEnd, 2 * indexOfSegTree + 1);
	int sumOfRightNode = getSum_EachElement(pSegTree, mid + 1, end, queStart, queEnd, 2 * indexOfSegTree + 2);

	return  sumOfLeftNode + sumOfRightNode;
}


// get index of middle element that separate array. 
int getIndexOfMid(int start, int end)
{
	return start + ((end - start) / 2);
}


// print the content of segment tree. 
void printContent(int* pSegTree, int length)
{
	copy(pSegTree, pSegTree + length, ostream_iterator<int>(cout, "  "));
}


// get max size of segment tree. 
int getMaxSize(int length)
{
	int height = (int)(ceil(log2(length)));
	int maxSize = 2 * (int)pow(2, height) - 1;

	return maxSize;
}


// update operation for range of elements. 
void updateRange(int* pSegTree, int start, int end, int queStart, int queEnd, int diff, int indexOfSegTree, int* pLazyArr)
{
	// Firstly, you have to check for the needed update element. 
	// In order to update the value for the elements that will be changed. 
	if (pLazyArr[indexOfSegTree] != 0)
	{
		pSegTree[indexOfSegTree] += (end - start + 1) * pLazyArr[indexOfSegTree];

		if (start != end)
		{
			pLazyArr[2 * indexOfSegTree + 1] += pLazyArr[indexOfSegTree];
			pLazyArr[2 * indexOfSegTree + 2] += pLazyArr[indexOfSegTree];
		}

		// reset value of pLazy[indexofSegTree] to prepare for update again.
		pLazyArr[indexOfSegTree] = 0;
	}

	// outside of the query indexes. 
	if (start > end || start > queEnd || end < queStart)
	{
		return; 
	}

	// inside - queStart --- start --- end --- queEnd. 
	if (queStart <= start && end <= queEnd)
	{
		pSegTree[indexOfSegTree] += (end - start + 1) * diff;

		if (start != end)
		{
			pLazyArr[2 * indexOfSegTree + 1] += diff;
			pLazyArr[2 * indexOfSegTree + 2] += diff;
		}

		return;
	}

	// overlaps. 
	int mid = getIndexOfMid(start, end);
	updateRange(pSegTree, start, mid, queStart, queEnd, diff, 2 * indexOfSegTree + 1, pLazyArr);
	updateRange(pSegTree, mid + 1, end, queStart, queEnd, diff, 2 * indexOfSegTree + 2, pLazyArr);

	pSegTree[indexOfSegTree] = pSegTree[2 * indexOfSegTree + 1] + pSegTree[2 * indexOfSegTree + 2];
}


// query operation for range of elements. 
int getSumRange(int* pSegTree, int start, int end, int queStart, int queEnd, int indexOfSegTree, int* pLazyArr)
{
	// update elements. 
	if (pLazyArr[indexOfSegTree] != 0)
	{
		pSegTree[indexOfSegTree] += (end - start + 1) * pLazyArr[indexOfSegTree];

		if (start != end)
		{
			pLazyArr[2 * indexOfSegTree + 1] += pLazyArr[indexOfSegTree];
			pLazyArr[2 * indexOfSegTree + 2] += pLazyArr[indexOfSegTree];
		}

		pLazyArr[indexOfSegTree] = 0;
	}

	// outside of range element in (queStart, queEnd).
	if (start > end || start > queEnd || end < queStart)
	{
		return 0;
	}

	// inside of range element. 
	// queStart --- start --- end --- queEnd. 
	if (queStart <= start && end <= queEnd)
	{
		return pSegTree[indexOfSegTree];
	}

	// overlaps. 
	int mid = getIndexOfMid(start, end);
	int sum1 = getSumRange(pSegTree, start, mid, queStart, queEnd, 2 * indexOfSegTree + 1, pLazyArr);
	int sum2 = getSumRange(pSegTree, mid + 1, end, queStart, queEnd, 2 * indexOfSegTree + 2, pLazyArr);

	return sum1 + sum2;
}