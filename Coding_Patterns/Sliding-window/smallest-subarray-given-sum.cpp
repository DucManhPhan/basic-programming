// Given an array of positive numbers and a positive number S, find the length of the smallest
// subarray whose sum is greater than or equal to S. Return 0, if no such subarray exists.

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>


int getLengthOfSmallestSubarray(const std::vector<std::vector<int>>& res)
{
	int size = res.size();
	int smallestLength = INT32_MAX;

	for (int i = 0; i < size; ++i)
	{
		if (smallestLength > res[i].size())
		{
			smallestLength = res[i].size();
		}
	}

	return smallestLength;
}


// brute-force
int findSmallestSubarray(const std::vector<int>& vtInput, int s, std::vector<std::vector<int>>& result)
{
	int size = vtInput.size();
	std::vector<std::vector<int>> tmp;

	for (int k = size; k >= 1; --k)
	{
		int numOfSteps = size - k + 1;
		//int smallestLength = 0;

		for (int i = 0; i < numOfSteps; ++i)
		{
			int sum = 0;
			std::vector<int> res;

			for (int j = i; j < k + i; ++j)
			{
				res.push_back(vtInput[j]);
				sum += vtInput[j];
			}

			if (sum >= s)
			{
				tmp.push_back(res);
			}
		}
	}

	// filter matrix to get the length of the smallest subarray whose sum is greater than or equal to S
	int smallestLength = getLengthOfSmallestSubarray(tmp);

	for (int i = 0; i < tmp.size(); ++i)
	{
		if (tmp[i].size() == smallestLength)
		{
			result.push_back(tmp[i]);
		}
	}

	return smallestLength;
}


// sliding-window
int findSmallestSubarray_SlidingWindow(const std::vector<int>& vtInput, int s, std::vector<std::vector<int>>& result)
{
	int size = vtInput.size();
	int windowStart = 0;
	int windowSum = 0;

	for (int windowEnd = 0; windowEnd < size; ++windowEnd)
	{

	}

}


// print result
void printResult(const std::vector<std::vector<int>>& result)
{
	int num = result.size();

	for (int i = 0; i < num; ++i)
	{
		int sizeChildArray = result[i].size();

		std::cout << "[";
		for (int j = 0; j < sizeChildArray; ++j)
		{
			std::cout << result[i][j] << ((j == sizeChildArray - 1) ? "" : ", ");
		}

		std::cout << "] " << ((i == num - 1) ? "\n" : "or ");
	}
}



int main()
{
	std::vector<int> vtInput = { 2, 1, 5, 2, 8 }; 	// { 3, 4, 1, 1, 6 }; 	// { 2, 1, 5, 2, 3, 2 };
	int s = 7; // 7;	// 7;	// 8;
	std::vector<std::vector<int>> result;
	int smallestLength = findSmallestSubarray(vtInput, s, result);

	std::cout << "Input: " << "[";
	//std::copy(vtInput.begin(), vtInput.end(), std::ostream_iterator<int>(std::cout, ", "));
	for (int i = 0; i < vtInput.size(); ++i)
	{
		std::cout << vtInput[i] << ((i == vtInput.size() - 1) ? "" : ", ");
	}

	std::cout << "], S = " << s << "\n";

	std::cout << "Output: " << smallestLength << "\n";
	std::cout << "Explanation: Smallest subarrays with a sum greater than or equal to '" << s << "' are ";
	printResult(result);

	system("pause");
	return 0;
}
