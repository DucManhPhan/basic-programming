// https://leetcode.com/problems/two-sum/

#include <iostream>
#include <vector>
#include <unordered_map>
#include <iterator>
#include <chrono>

bool isExistIn(std::unordered_map<int, int>& mp, int key)
{
    if (mp.find(key) == mp.end())
    {
        return false;
    }

    return true;
}


std::vector<int> twoSum(std::vector<int>& nums, int target)
{
    std::unordered_map<int, int> mp;

    int size = nums.size();
    for (int i = 0; i < size; ++i)
    {
        if (isExistIn(mp, target - nums[i]))
        {
            return std::vector<int>({i, mp[target- nums[i]]});
        }

        mp[nums[i]] = i;
    }
}


void printResult(const std::vector<int>& result)
{
    std::copy(result.begin(), result.end(), std::ostream_iterator<int>(std::cout, " "));
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