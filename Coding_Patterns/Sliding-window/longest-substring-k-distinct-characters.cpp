// Given a string, find the length of the longest substring in it with no more than K distinct characters

#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_map>
#include <limits>
#include <vector>
#include <iterator>


// brute-force
int findLengthOfLongestSubstring(const std::string& strInput, int numOfDistinctCharacters)
{
	return 0;
}


// sliding-window
bool isExistedInMap(const std::unordered_map<char, int>& distinctCharacters, char c)
{
	if (distinctCharacters.find(c) == distinctCharacters.end())
	{
		return false;
	}

	return true;
}

int findLengthOfLongestSubstring_SlidingWindow(const std::string& strInput, int numOfDistinctCharacters)
{
	int sizeInput = strInput.size();
	int windowStart = 0;
	int maxLength = 0;
	int missingCharacters = numOfDistinctCharacters;
	std::unordered_map<char, int> distinctCharacters;

	for (int windowEnd = 0; windowEnd < sizeInput; ++windowEnd)
	{
		bool isExisted = isExistedInMap(distinctCharacters, strInput[windowEnd]);
		if (isExisted)
		{
			distinctCharacters[strInput[windowEnd]] += 1;
			maxLength = std::max(maxLength, windowEnd - windowStart + 1);
		}
		else
		{
			while (missingCharacters == 0)
			{
				distinctCharacters[strInput[windowStart]] -= 1;
				if (distinctCharacters[strInput[windowStart]] == 0)
				{
					missingCharacters += 1;
					distinctCharacters.erase(strInput[windowStart]);
				}

				++windowStart;
			}

			distinctCharacters[strInput[windowEnd]] += 1;
			missingCharacters -= 1;
			maxLength = std::max(maxLength, windowEnd - windowStart + 1);
		}
	}

	return maxLength;
}


std::vector<std::string> getSubstring(const std::string& strInput, int numOfDistinctCharacters, int k)
{
	int size = strInput.size();
	int windowStart = 0;
	int missingCharacters = numOfDistinctCharacters;
	std::unordered_map<char, int> distinctCharacters;
	std::vector<std::string> result;

	for (int windowEnd = 0; windowEnd < size; ++windowEnd)
	{
		bool isExisted = isExistedInMap(distinctCharacters, strInput[windowEnd]);
		if (isExisted)
		{
			distinctCharacters[strInput[windowEnd]] += 1;
		}
		else
		{
			while (missingCharacters == 0)
			{
				distinctCharacters[strInput[windowStart]] -= 1;
				if (distinctCharacters[strInput[windowStart]] == 0)
				{
					distinctCharacters.erase(strInput[windowStart]);
					missingCharacters += 1;
				}

				++windowStart;
			}

			distinctCharacters[strInput[windowEnd]] += 1;
			missingCharacters -= 1;
		}

		if (windowEnd >= k - 1)
		{
			if (missingCharacters == 0 && (windowEnd - windowStart + 1) == k)
			{
				std::string tmp(strInput.begin() + windowStart, strInput.begin() + windowEnd + 1);
				result.push_back(std::move(tmp));
			}
		}
	}

	return result;
}


void printResult(const std::vector<std::string>& res)
{
	std::copy(res.begin(), res.end(), std::ostream_iterator<std::string>(std::cout, " "));
}


int main()
{
	std::string strInput = "araaci";	// "araaci";	// "cbbebi";
	int numOfDistinctCharacters = 2;		// 1;	// 3;

	int maxLength = findLengthOfLongestSubstring_SlidingWindow(strInput, numOfDistinctCharacters);

	std::cout << "Max length is: " << maxLength << "\n";

	printResult(getSubstring(strInput, numOfDistinctCharacters, maxLength));

	system("pause");
	return 0;
}