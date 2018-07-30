#include "MinHeap.h"


#pragma region Constructors
CMinHeap::CMinHeap()
	: m_nCapacity(0)
	, m_nNumberOfLargestElement(0)
	, m_nCurrentSize(0)
	, m_vecArray(0)
{
	// nothing to do. 
}


CMinHeap::CMinHeap(int size)
	: m_nCapacity(size)
	, m_nNumberOfLargestElement(4)
	, m_nCurrentSize(0)
	, m_vecArray(size)
{
	// nothing to do. 
}
#pragma endregion 


#pragma region Implementations
// make the min heap property properly.
void CMinHeap::heapify(int i)
{
	int nLeft = leftKey(i);
	int nRight = rightKey(i);

	int nSmallest = i;

	if (nLeft < m_nCurrentSize && m_vecArray[nLeft] < m_vecArray[i])
	{
		nSmallest = nLeft;
	}

	if (nRight < m_nCurrentSize && m_vecArray[nRight] < m_vecArray[nSmallest])
	{
		nSmallest = nRight;
	}

	if (nSmallest != i)
	{
		swap(m_vecArray[nSmallest], m_vecArray[i]);
		heapify(nSmallest);
	}
}


// construct the min heap.
void CMinHeap::buildHeap()
{
	for (int i = m_nCurrentSize / 2; i >= 0; ++i)
	{
		heapify(i);
	}
}


// find the parent of its node. 
inline int CMinHeap::parentKey(int i)
{
	if (i > 0)
	{
		return (i - 1) / 2;
	}

	return -1;
}


// find the left of its node. 
inline int CMinHeap::leftKey(int i)
{
	return (2 * i + 1);
}


// find the right of its node. 
inline int CMinHeap::rightKey(int i)
{
	return 2 * (i + 1);
}


// returns the root element of Min Heap - O(1).
int CMinHeap::getMini()
{
	if (!m_vecArray.empty())
	{
		return m_vecArray[0];
	}

	return INT_MIN;
}


// removes the minimum element from Min Heap - O(logn).
int CMinHeap::extractMin()
{
	if (m_nCurrentSize <= 0)
	{
		return INT_MAX;
	}

	if (m_nCurrentSize == 1)
	{
		--m_nCurrentSize;
		return m_vecArray[0];
	}

	// save the minimize value of previous element. 
	int nMinValue = m_vecArray[0];
	swap(m_vecArray[m_nCurrentSize - 1], m_vecArray[0]);
	--m_nCurrentSize;

	// make the min heap property. 
	heapify(0);

	return nMinValue;
}


// set value of key ith to newValue - O(logn). 
void CMinHeap::setKey(int i, int newValue)
{
	if (i < 0 || i > m_nCurrentSize)
	{
		cout << "Minimize or Exceed position of the heap.\n";
		return;
	}

	m_vecArray[i] = newValue;

	// traverse up.
	while (i != 0 && m_vecArray[i] < m_vecArray[parentKey(i)])
	{
		swap(m_vecArray[i], m_vecArray[parentKey(i)]);
		i = parentKey(i);
	}

	// traverse down.
	heapify(i);
}


// inserts a new key at position m_nCurrentSize, and it takes O(logn) about time complexities.
void CMinHeap::insertKey(int newValue)
{
	if (m_nCurrentSize >= m_nCapacity)
	{
		cout << "Exceed or equal to capacity of heap.\n";
		return;
	}

	m_vecArray[m_nCurrentSize] = newValue;

	// traverse up to heap. 
	int nCurrentPosition = m_nCurrentSize;

	while (nCurrentPosition != 0 && m_vecArray[parentKey(nCurrentPosition)] > m_vecArray[nCurrentPosition])
	{
		swap(m_vecArray[parentKey(nCurrentPosition)], m_vecArray[nCurrentPosition]);
		nCurrentPosition = parentKey(nCurrentPosition);
	}

	++m_nCurrentSize;
}


// delete a key at index i - O(logn). 
void CMinHeap::deleteKey(int i)
{
	if (i < 0 || i > m_nCapacity)
	{
		cout << "Exceed the position of position 0 and last index.\n";
		return;
	}

	setKey(i, INT_MIN);
	extractMin();
}


// interchange the value of each a and b. 
void CMinHeap::swap(int& a, int& b)
{
	int tmp = a;
	a = b;
	b = tmp;
}


// print content of min heap.
void CMinHeap::printContent()
{
	vector<int>::iterator it_begin = m_vecArray.begin();
	//vector<int>::iterator it_end = m_vecArray.end();

	copy(it_begin, it_begin + m_nCurrentSize, ostream_iterator<int>(cout, " "));

	cout << "\n";
}
#pragma endregion 


#pragma region k largest element
// Buble sort
void CMinHeap::bubbleSort()
{
	vector<int> vecTmp(m_vecArray);
	static int nNumberOfLargest = m_nNumberOfLargestElement;

	for (int i = m_nCurrentSize - 1; i >= 0; --i)
	{
		--nNumberOfLargest;
		bool bFlag = true;

		for (int j = 0; j < i - 1; ++j)
		{
			if (vecTmp[j] > vecTmp[j + 1])
			{
				swap(vecTmp[j], vecTmp[j + 1]);
				bFlag = false;
			}
		}

		if (nNumberOfLargest == 0 || bFlag)
		{
			copy(vecTmp.begin() + m_nCurrentSize - m_nNumberOfLargestElement, vecTmp.begin() + m_nCurrentSize, ostream_iterator<int>(cout, " "));
			cout << "\n";

			return;
		}
	}
}


// Selection sort
void CMinHeap::selectionSort()
{
	vector<int> vecTmp(m_vecArray);
	static int nNumberOfLargest = m_nNumberOfLargestElement;
	int max = -1;

	for (int i = m_nCurrentSize - 1; i >= 0; --i)
	{
		max = i;
		--nNumberOfLargest;

		for (int j = i - 1; j >= 0; --j)
		{
			if (vecTmp[j] > vecTmp[max])
			{
				max = j;
			}
		}

		if (max != i)
		{
			swap(vecTmp[max], vecTmp[i]);
		}

		if (nNumberOfLargest == 0)
		{
			copy(vecTmp.begin() + m_nCurrentSize - m_nNumberOfLargestElement, vecTmp.begin() + m_nCurrentSize, ostream_iterator<int>(cout, " "));
			cout << "\n";

			return;
		}
	}
}


// use temporary array
void CMinHeap::useTemporaryArray()
{

}


// use heapsort
void CMinHeap::heapSort()
{
	vector<int> vecTmp(m_vecArray);
	static int nNumberOfLargest = m_nNumberOfLargestElement;

	buildHeap();

	for (int i = m_nCurrentSize - 1; i >= 0; --i)
	{
		swap(vecTmp[i], vecTmp[0]);
		--m_nCurrentSize;

		heapify(0);
	}
}


// use quickselect. 
void CMinHeap::quickSelect(int start, int end)
{
	if (start <= end)
	{
		int pivot = partition(start, end);

		if (pivot == m_nNumberOfLargestElement)
		{
			return;
		}

		quickSelect(start, pivot - 1);
		quickSelect(pivot + 1, end);
	}
}


void CMinHeap::quickSelectInitialize()
{
	vector<int> vecTmp(m_vecArray);
	static int nNumberOfLargest = m_nNumberOfLargestElement;

	quickSelect(0, m_nCurrentSize - 1);

	copy(vecTmp.begin() + m_nCurrentSize - m_nNumberOfLargestElement, vecTmp.begin() + m_nCurrentSize, ostream_iterator<int>(cout, " "));
	cout << "\n";
}


int CMinHeap::partition(int start, int end)
{
	int pivot = m_vecArray[end];
	int i = start - 1; 

	for (int j = start; j < end; ++j)
	{
		if (m_vecArray[j] < pivot)
		{
			++i;
			swap(m_vecArray[j], m_vecArray[i]);
		}
	}

	swap(m_vecArray[i + 1], m_vecArray[end]);
	return i + 1;
}


// use min heap. 
void CMinHeap::useMinHeap()
{

}


#pragma endregion