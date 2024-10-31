// Given an array of positive numbers and a positive number k, 
// find the maximum sum of any contiguous subarray of size k.

#include <iostream>
#include <algorithm>
#include <vector>
#include <iterator>


// brute-force: O(n * k)
int findMax(const std::vector<int>& vtInput, int k)
{
	int numOfInput = vtInput.size();
	int numOfSteps = numOfInput - k + 1;
	int max = 0;

	for (int i = 0; i < numOfSteps; ++i)
	{
		int sum = 0;
		for (int j = i; j < k + i; ++j)
		{
			sum += vtInput[j];
		}

		max = max < sum ? sum : max;
	}

	return max;
}


// sliding-window: O(n)
int findMax_SlidingWindow(const std::vector<int>& vtInput, int k)
{
	int size = vtInput.size();
	int windowStart = 0;
	int max = 0;
	int windowSum = 0;

	for (int windowEnd = 0; windowEnd < size; ++windowEnd)
	{
		windowSum += vtInput[windowEnd];

		if (windowEnd >= k - 1) // one window
		{
			max = max < windowSum ? windowSum : max;
			windowSum -= vtInput[windowStart];
			++windowStart;
		}
	}

	return max;
}



int main()
{
	std::vector<int> vtInput = { 2, 1, 5, 1, 3, 2 };	// { 2, 3, 4, 1, 5 }
	int k = 3;

	std::cout << findMax_SlidingWindow(vtInput, k);

	system("pause");
	return 0;
}