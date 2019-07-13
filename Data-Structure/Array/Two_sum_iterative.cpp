// https://leetcode.com/problems/two-sum/

#include <iostream>
#include <vector>
#include <iterator>

std::vector<int> twoSum(const std::vector<int>& nums, int target)
{
    if (nums.empty())
    {
        return std::vector<int>();
    }

    std::vector<int> result = {};
    int size = nums.size();
    for (int i = 0; i < size; ++i)
    {
        for (int j = i + 1; j < size; ++j)
        {
            if (nums[i] + nums[j] == target)
            {
                result.push_back(nums[i]);
                result.push_back(nums[j]);

                return result;
            }
        }
    }
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

    std::vector<int> result = twoSum(nums, target);
    printResult(result);


    system("pause");
    return 0;
}