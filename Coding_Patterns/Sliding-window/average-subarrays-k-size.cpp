// Given an array, find the average of all subarrays of size 'K' in it.

#include <iostream>
#include <algorithm>
#include <vector>
#include <iterator>


// brute force
std::vector<float> getAverages(const std::vector<int>& vtInput, int k)
{
	int sizeOfInput = vtInput.size();
	int numOfSteps = sizeOfInput - k + 1;
	std::vector<float> result(numOfSteps);

	for (int i = 0; i < numOfSteps; ++i)
	{
		float sum = 0;
		for (int j = i; j < k + i; ++j)
		{
			sum += vtInput[j];
		}

		result[i] = sum / k;
	}

	return result;
}


// sliding-window
std::vector<float> getAverages_SlidingWindow(const std::vector<int>& vtInput, int k)
{
	int sizeOfInput = vtInput.size();
	int numOfSteps = sizeOfInput - k + 1;
	std::vector<float> result(numOfSteps);

	int windowStart = 0;
	float windowSum = 0;

	for (int windowEnd = 0; windowEnd < sizeOfInput; ++windowEnd)
	{
		windowSum += vtInput[windowEnd];

		if (windowEnd >= k - 1)
		{
			result[windowStart] = windowSum / k;
			windowSum -= vtInput[windowStart];
			++windowStart;
		}
	}

	return result;
}


// print result
void printResult(const std::vector<float>& result)
{
	std::copy(result.begin(), result.end(), std::ostream_iterator<float>(std::cout, " "));
}


int main()
{
	std::vector<int> vt = { 1, 3, 2, 6, -1, 4, 1, 8, 2 };
	int k = 5;

	printResult(getAverages_SlidingWindow(vt, k));

	system("pause");
	return 0;
}