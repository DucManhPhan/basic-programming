// Given a string, find the length of the longest substring which has no repeating characters.

// Ex1: Input: String = "aabccbb"
// Output: 3
// Explanation: The longest substring without any repeating characters is "abc".

// Ex2: Input: String = "abbbb"
// Output: 2
// Explanation: The longest substring without any repeating characters is "ab".

// Ex3: Input: String = "abccde"
// Output: 3
// Explanantion: Longest substrings without any repeating characters are "abc" & "cde".

#include <iostream>
#include <string>
#include <algorithm>
#include <unordered_map>


bool hasContains(const std::unordered_map<char, int>& distinctCharacters, char c)
{
	if (distinctCharacters.find(c) == distinctCharacters.end())
	{
		return false;
	}

	return true;
}


int findMaxLength(const std::string& str)
{
	int size = str.size();
	int maxLength = 0;
	int windowStart = 0;
	std::unordered_map<char, int> frequencyOfcharacters;

	for (int windowEnd = 0; windowEnd < size; ++windowEnd)
	{
		bool isExisted = hasContains(frequencyOfcharacters, str[windowEnd]);
		if (isExisted)
		{
			frequencyOfcharacters.clear();

			maxLength = std::max(maxLength, windowEnd - windowStart);
			windowStart = windowEnd;
		}


		++frequencyOfcharacters[str[windowEnd]];
		maxLength = std::max(maxLength, windowEnd - windowStart + 1);
	}

	return maxLength;
}



int main()
{
	std::string str = "aaaaabbbb";  // "abcdef"	//  "aabccbb"	// "abccde"
	
	std::cout << findMaxLength(str);

	system("pause");
	return 0;
}