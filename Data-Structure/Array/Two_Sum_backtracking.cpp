// https://leetcode.com/problems/two-sum/
// Use backtracking to implement this problem - Complexity: O(n)

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include < numeric>
#include <chrono>


void getTwoSum(std::vector<int>& nums, std::vector<int>& tmp, std::vector<std::vector<int>>& results, int target, int currentIndex)
{
    if (tmp.size() == 2)
    {
        int sumElements = std::accumulate(tmp.begin(), tmp.end(), 0);
        if (target == sumElements)
        {
            results.push_back(tmp);
            return;
        }
    }

    for (int i = currentIndex; i < nums.size(); ++i)
    {
        if (tmp.size() < 2) {
            tmp.push_back(nums[i]);
            getTwoSum(nums, tmp, results, target, i + 1);
            tmp.pop_back();
        }
    }
}


std::vector<int> twoSum(std::vector<int>& nums, int target)
{
    if (nums.empty() || target == 0) 
    {
        return std::vector<int>();
    }

    std::vector<std::vector<int>> results = {};
    std::vector<int> tmp = {};
    getTwoSum(nums, tmp, results, target, 0);

    return results[0];
}

void printResult(const std::vector<int>& result)
{
    std::copy(result.begin(), result.end(), std::ostream_iterator<int>(std::cout, "  "));
	std::cout << "\n";
}


int main()
{
    std::vector<int> nums = {2, 1, 8, 11, 15, 7};
    int target = 9;

    // Record start time
    auto start = std::chrono::high_resolution_clock::now();

    std::vector<int> result = twoSum(nums, target);

    // Record end time
    auto finish = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> elapsed = finish - start;

    std::cout << "Elapsed time of this problem is: " << elapsed.count() << "\n";
    printResult(result);

    system("pause");
    return 0;
}