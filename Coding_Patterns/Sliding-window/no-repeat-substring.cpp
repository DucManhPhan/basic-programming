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


// Wrong when do not calculate the case: "aabcadb".
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


int findMaxLength_Other(const std::string& str)
{
	int windowStart = 0, maxLength = 0;

	// save the last index of each character
	std::unordered_map<char, int> charIndexMap;

	for (int windowEnd = 0; windowEnd < str.length(); windowEnd++)
	{
		char rightChar = str[windowEnd];
		// if the map already contains the 'rightChar', shrink the window from the beginning so that
		// we have only one occurrence of 'rightChar'
		if (charIndexMap.find(rightChar) != charIndexMap.end()) {
			// this is tricky; in the current window, we will not have any 'rightChar' after its
			// previous index and if 'windowStart' is already ahead of the last index of 'rightChar',
			// we'll keep 'windowStart'
			windowStart = std::max(windowStart, charIndexMap[rightChar] + 1);
		}

		charIndexMap[str[windowEnd]] = windowEnd;  // insert the 'rightChar' into the map
		maxLength = std::max(maxLength, windowEnd - windowStart + 1);  // remember the maximum length so far
	}

	return maxLength;
}


int main()
{
	std::string str = "aabcadb";  // "abcdef"	//  "aabccbb"	// "abccde"
	
	std::cout << "Result of educative.io: " << findMaxLength_Other(str) << "\n";

	std::cout << "Result: " << findMaxLength(str) << "\n";

	system("pause");
	return 0;
}