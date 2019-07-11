// https://leetcode.com/problems/two-sum/

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include < numeric>


void getTwoSum(std::vector<int>& nums, std::vector<int>& result, int target, int currentIndex)
{
    if (result.size() == 2)
    {
        int sumElements = std::accumulate(result.begin(), result.end(), 0);
        if (target == sumElements)
        {
            return;
        }
    }

    for (int i = currentIndex; i < nums.size(); ++i)
    {
        result.push_back(nums[i]);
        getTwoSum(nums, result, target, i + 1);
        result.pop_back();
    }
}


std::vector<int> twoSum(std::vector<int>& nums, int target)
{
    if (nums.empty() || target == 0) 
    {
        return std::vector<int>();
    }

    std::vector<int> results = {};
    getTwoSum(nums, results, target, 0);

    return results;
}

void printResult(const std::vector<int>& result)
{
    std::copy(result.begin(), result.end(), std::ostream_iterator<int>(std::cout, "  "));
	std::cout << "\n";
}


int main() 
{
    std::vector<int> nums = {2, 7, 11, 15};
    int target = 9;

    std::vector<int> results = twoSum(nums, target);
    printResult(results);

    system("pause");
    return 0;
}
