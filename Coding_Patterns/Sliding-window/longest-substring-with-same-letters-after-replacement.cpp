// Given a string with lowercase letter only, if you are allowed to replace no more than k letters with any letters,
// find the length of the longest substring having the same letters after replacement.

// Input: String="aabccbb", k=2
// Output: 5
// Explanation : Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".

// Input: String = "abbcb", k = 1
// Output : 4
// Explanation : Replace the 'c' with 'b' to have a longest repeating substring "bbbb".

// Input: String = "abccde", k = 1
// Output : 3
// Explanation : Replace the 'b' or 'd' with 'c' to have the longest repeating substring "ccc".

#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_map>

int findLengthOfLongestSubstring(const std::string& str)
{
	int length = str.size();
	int windowStart = 0;
	int maxLength = 0;
	int maxRepeatedCharacter = 0;
	std::unordered_map<char, int> frequencyCharacters;

	for (int windowEnd = 0; windowEnd < length; ++windowEnd)
	{
		++frequencyCharacters[str[windowEnd]];
		maxRepeatedCharacter = std::max(maxRepeatedCharacter, frequencyCharacters[str[windowEnd]]);


	}

	return maxLength;
}


int main()
{



	system("pause");
	return 0;
}
