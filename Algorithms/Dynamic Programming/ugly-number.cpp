#include <iostream>
#include <algorithm>
#include <vector>


int getMinUglyNumber(int first, int second, int third)
{
	int tmp = std::min(first, second);

	return tmp < third ? tmp : third;
}


int findUglyNumber(int nthPos)
{
	int nIndex2 = 0;	// index of array that have prime factor is 2
	int nIndex3 = 0; 
	int nIndex5 = 0;	

	int nextMultiple2 = 2;
	int nextMultiple3 = 3;
	int nextMultiple5 = 5;

	int minUgly = 0;

	std::vector<int> vecUglyNum(nthPos, 1);

	for (int i = 1; i < nthPos; ++i)
	{
		minUgly = getMinUglyNumber(nextMultiple2, nextMultiple3, nextMultiple5);
		vecUglyNum[i] = minUgly;

		if (minUgly == nextMultiple2)
		{
			++nIndex2;
			nextMultiple2 = 2 * vecUglyNum[nIndex2];
		} 
		
		if (minUgly == nextMultiple3)
		{
			++nIndex3;
			nextMultiple3 = 3 * vecUglyNum[nIndex3];
		}
		
		if (minUgly == nextMultiple5)
		{
			++nIndex5;
			nextMultiple5 = 5 * vecUglyNum[nIndex5];
		}
	}

	return minUgly;
}


int main()
{
	int nthPos = -1;

	std::cout << "Enter nth position of ugly number that you want: ";
	std::cin >> nthPos;

	if (nthPos > 1)
	{
		std::cout << nthPos << " ugly number is: " << findUglyNumber(nthPos);
	}
	else
	{
		std::cout << "you should again this input.\n";
	}

	return 0;
}
