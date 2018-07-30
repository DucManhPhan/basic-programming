#include "SQRT_Rang_Sum_Query.h"



// use the Square Root Decomposition.

// create the array of Square Root Decomposition.
int* constructSRD(int arr[], int size, int& sizeSRD)
{
	sizeSRD = (int)sqrt(size);
	int* pLookup = new int[sizeSRD]();

	if (!pLookup)
	{
		cout << "Do not allocate memory for SRD.\n";
		return nullptr;
	}

	constructSRDUtil(pLookup, sizeSRD, arr, size);

	return pLookup;
}


void constructSRDUtil(int* pLookup, int sizeSRD, int arr[], int sizeOrigin)
{
	int nIndexBlock = -1;

	for (int i = 0; i < sizeOrigin; ++i)
	{
		if (i % sizeSRD == 0)
		{
			++nIndexBlock;
		}

		pLookup[nIndexBlock] += arr[i];
	}
}


// update 
void update(int* pLookup, int sizeSRD, int arr[], int size, int indexOrigin, int value)
{
	if (indexOrigin < 0 || indexOrigin >= size)
	{
		cout << "Invalid input for element's index.\n";
		return;
	}

	int diff = value - arr[indexOrigin];
	arr[indexOrigin] = value;

	pLookup[indexOrigin % sizeSRD] += diff;
}


// sum query. 
int getSum(int* pLookup, int sizeSRD, int arr[], int size, int queStart, int queEnd)
{
	if (queStart > queEnd)
	{
		return -1;
	}

	int sumResult = 0;

	// calculate for the first block of pLookup array. 
	while (queStart < queEnd && queStart % sizeSRD && queStart != 0)
	{
		sumResult += arr[queStart];
		++queStart;
	}

	// calculate for the second, third, ... block that have the specific condition.
	while (queStart + sizeSRD <= queEnd)
	{
		sumResult += pLookup[queStart / sizeSRD];
		queStart += sizeSRD;
	}

	// the last block. 
	while (queStart <= queEnd)
	{
		sumResult += arr[queStart];
		++queStart;
	}

	return sumResult;
}


// basic implementation 
void initializeSRD()
{
	int arr[] = { 1, 5, 2, 4, 6, 1, 3, 5, 7, 12, 8 };
	int size = sizeof(arr) / sizeof(int);
	int sizeSRD = -1;

	int* pSRD = constructSRD(arr, size, sizeSRD);
	if (!pSRD)
	{
		cout << "Do not create a Square Root Decomposition.\n";
		return;
	}

	update(pSRD, sizeSRD, arr, size, 2, 6);

	int result = getSum(pSRD, sizeSRD, arr, size, 1, 6);

	cout << "Result se la: " << result << "\n";


	//printContentSRD(pSRD, sizeSRD);
}


void printContentSRD(int* pSRD, int size)
{
	copy(pSRD, pSRD + size, ostream_iterator<int>(cout, " "));
}



// Case 2. 
// create the array of Square Root Decomposition.
int* constructSQRT_II(int arr[], int size)
{
	int sizeSQRT = calcSizeSQRT(size);
	int* pLookup = new int[sizeSQRT]();

	if (!pLookup)
	{
		cout << "Do not create the Square Root Decomposition array.\n";
		return nullptr;
	}

	constructUtilSQRT_II(pLookup, sizeSQRT, arr, size);

	return pLookup;
}


void constructUtilSQRT_II(int* pLookup, int sizeSQRT, int arr[], int size)
{
	for (int i = 0; i < size; ++i)
	{
		pLookup[i / sizeSQRT] += arr[i];
	}
}


// sum query. 
int querySum_II(int* pLookup, int arr[], int size, int l, int r)
{
	int sizeSQRT = calcSizeSQRT(size);
	/* This way get too much time complexity because of the division operations which are slower than the other arithmetic operations.
	int sum = 0;

	for (int i = l; i <= r; )
	{
		if (i % sizeSQRT == 0 && i + sizeSQRT <= r)
		{
			sum += pLookup[i / sizeSQRT];  // **
			i += sizeSQRT;
		}
		else
		{
			sum += arr[i];
			++i;
		}
	}

	return sum;*/

	// This way improves the time complexity of the sum of elements.
	int sum = 0;
	int firstBlock = l / sizeSQRT;
	int lastBlock = r / sizeSQRT;
	int isStartIndexZero = 1;  // do not start at zero. 

	int end = 0;

	if (firstBlock == lastBlock)
	{
		for (int i = l; i <= r; ++i)
		{
			sum += arr[i];
		}
	}
	else
	{
		if (l == 0)
		{
			isStartIndexZero = 0;
		}

		for (int i = l, end = (firstBlock + 1) * sizeSQRT - 1; isStartIndexZero && i <= end; ++i)
		{
			sum += arr[i];
		}

		for (int i = firstBlock + isStartIndexZero; i <= lastBlock - 1; ++i)
		{
			sum += pLookup[i];
		}

		for (int i = lastBlock * sizeSQRT; i <= r; ++i)
		{
			sum += arr[i];
		}
	}

	return sum;
}


void queryUpdate_II(int* pLookup, int arr[], int size, int i, int value)
{
	int sizeSQRT = calcSizeSQRT(size);

	int diff = value - arr[i];
	arr[i] += diff;
	pLookup[i / sizeSQRT] += diff;
}

// calculate the size of SQRT array. 
int calcSizeSQRT(int sizeOrigin)
{
	return (int)sqrt(sizeOrigin) + 1;
}


// intialize SQRT.
void initializeSQRT_II()
{
	int arr[] = { 1, 5, 2, 4, 6, 1, 3, 5, 7, 12, 8 }; //{3, 1, 2, 2, 3, 3, 7}; 
	int size = sizeof(arr) / sizeof(int);
	int sizeSRD = -1;

	int* pSRD = constructSQRT_II(arr, size);
	if (!pSRD)
	{
		cout << "Do not create the blocks array.\n";
		return;
	}

	//queryUpdate_II(pSRD, arr, size, 2, 6);

	int result = querySum_II(pSRD, arr, size, 1, 7);

	cout << "Result se la: " << result << "\n";
}