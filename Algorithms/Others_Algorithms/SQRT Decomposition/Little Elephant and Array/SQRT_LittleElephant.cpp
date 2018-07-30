#include "SQRT_LittleElephant.h"


// Construct SQRT array.
void cons(int n, int a[])
{
	int sizeSRD = (int)sqrt(n);
	int indexBlock = -1;

	for (int i = 0; i < n; i++)
	{
		if (i % sizeSRD == 0)
		{
			++indexBlock;

			// initialize min and max of block[indexBlock].
			block[indexBlock].mini = a[i];
			block[indexBlock].maxi = a[i];
		}

		// find the elements in block[indexBlock].
		block[indexBlock].ct[a[i]]++;
		block[indexBlock].mini = min(block[indexBlock].mini, a[i]);
		block[indexBlock].maxi = max(block[indexBlock].maxi, a[i]);
	}
}


// implement query. 
void query(int l, int r, int n, int a[], int &ans)
{
	int sizeSRD = (int)(sqrt(n));
	map<int, int> mpTimes;
	mpTimes[0] = -1;

	// first block.
	while (l < r && l % sizeSRD && l != 0)
	{
		++mpTimes[a[l]];
		++l;
	}

	// middle block. 
	while (l + sizeSRD <= r)
	{
		int i = l / sizeSRD;

		for (int j = block[i].mini; j <= block[i].maxi; ++j)
		{
			mpTimes[j] += block[i].ct[j];
		}

		l += sizeSRD;
	}

	// last block. 	
	while (l <= r)
	{
		++mpTimes[a[l]];
		++l;
	}

	// result. 
	map<int, int>::iterator it = mpTimes.begin();
	for (; it != mpTimes.end(); it++)
	{
		if (it->second == it->first)
		{
			ans++;
		}
	}
}