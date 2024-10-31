// Given an array of characters where each character represents a fruit tree, you are given two baskets 
// and your goal is to put maximum of fruits in each basket. The only restriction is that each basket can have only one type of fruit.

// We can start with any tree, but once we have started, we can not skip a tree. We will pick one fruit from each tree
// until we cannot, i.e., we will stop when we have to pick from a third fruit type.

//Input: Fruit = ['A', 'B', 'C', 'A', 'C']
//	Output : 3
//	Explanation : We can put 2 'C' in one basket and one 'A' in the other from the subarray['C', 'A', 'C']

//Input: Fruit = ['A', 'B', 'C', 'B', 'B', 'C']
//	Output : 5
//	Explanation : We can put 3 'B' in one basket and two 'C' in the other basket.
//	This can be done if we start with the second letter : ['B', 'C', 'B', 'B', 'C']

// --> Write a function to return the maximum number of fruits in both in baskets.

#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <vector>


int findMaximumFruits(const std::vector<char>& fruits)
{
	int numOfFruits = fruits.size();
	int maxLength = 0;
	int windowStart = 0;
	std::unordered_map<char, int> baskets;

	for (int windowEnd = 0; windowEnd < numOfFruits; ++windowEnd)
	{
		++baskets[fruits[windowEnd]];

		while (baskets.size() > 2)
		{
			--baskets[fruits[windowStart]];
			if (baskets[fruits[windowStart]] == 0)
			{
				baskets.erase(fruits[windowStart]);
			}

			++windowStart;
		}

		maxLength = std::max(maxLength, windowEnd - windowStart + 1);
	}

	return maxLength;
}




int main()
{
	std::vector<char> Fruit = { 'A', 'B', 'C', 'B', 'B', 'C' }; // { 'A', 'B', 'C', 'A', 'C' };

	std::cout << findMaximumFruits(Fruit) << " ";

	system("pause");
	return 0;
}
