#include <iostream>
#include <vector>
#include <algorithm>



//void combinationSum(const std::vector<int> &ar, int sum, std::vector<std::vector<int>> &vecTwoDimension, std::vector<int> &vec, int i)
//{
//	if (sum < 0)
//	{
//		return;
//	}
//
//	if (sum == 0)
//	{
//		vecTwoDimension.push_back(vec);
//		return;
//	}
//
//	while (i < ar.size() && sum - ar[i] >= 0)
//	{
//		vec.push_back(ar[i]);
//
//		combinationSum(ar, sum - ar[i], vecTwoDimension, vec, i);
//
//		++i; 
//
//		vec.pop_back();
//	}
//}
//
//
//void printAllCase(const std::vector<std::vector<int>> &ar)
//{
//	for each (std::vector<int> var in ar)
//	{
//		for each (int i in var)
//		{
//			std::cout << i << " ";
//		}
//
//		std::cout << "\n";
//	}
//}
//
//
//int main()
//{
//	std::vector<int> ar = {7, 2, 3, 6}; //{ 6, 5, 7, 1, 8, 2, 9, 9, 7, 7, 9 }; //{7, 2, 5, 6};
//	std::vector<std::vector<int>> vecTwoDimension;
//	std::vector<int> vec;
//	int sum = 7;
//
//	std::sort(ar.begin(), ar.end());
//
//	// remove duplicates.
//	ar.erase(unique(ar.begin(), ar.end()), ar.end());
//
//	combinationSum(ar, sum, vecTwoDimension, vec, 0);
//
//	printAllCase(vecTwoDimension);
//
//	system("pause");
//	return 0;
//}


// other function to run 

void combinationSum(std::vector<int>& candidates, int target, std::vector<std::vector<int>>& res, std::vector<int> &combination, int begin)
{
	if (target == 0)
	{
		res.push_back(combination);
		return;
	}

	for (int i = begin; i != candidates.size() && target >= candidates[i]; ++i)
	{
		combination.push_back(candidates[i]);
		combinationSum(candidates, target - candidates[i], res, combination, i);
		combination.pop_back();
	}
}

std::vector<std::vector<int>> combinationSum(std::vector<int>& candidates, int target)
{
	std::sort(candidates.begin(), candidates.end());
	std::vector<std::vector<int>> res;
	std::vector<int> combination;

	combinationSum(candidates, target, res, combination, 0);

	return res;
}

void combinationSum2(std::vector<int> &candidates, int target, std::vector<std::vector<int> > &res, std::vector<int> &combination, int begin) {
	if (!target) {
		res.push_back(combination);
		return;
	}
	for (int i = begin; i != candidates.size() && target >= candidates[i]; ++i) {
		if (i == begin || candidates[i] != candidates[i - 1]) {
			combination.push_back(candidates[i]);
			combinationSum2(candidates, target - candidates[i], res, combination, i + 1);
			combination.pop_back();
		}
	}
}


std::vector<std::vector<int> > combinationSum2(std::vector<int> &candidates, int target) {
	std::sort(candidates.begin(), candidates.end());
	std::vector<std::vector<int> > res;
	std::vector<int> combination;
	combinationSum2(candidates, target, res, combination, 0);
	return res;
}


void printAllCase(const std::vector<std::vector<int>> &ar)
{
	for each (std::vector<int> var in ar)
	{
		for each (int i in var)
		{
			std::cout << i << " ";
		}

		std::cout << "\n";
	}
}


int main()
{
	std::vector<int> candidates = { 1, 1, 2, 5, 6, 7 };

	std::vector<std::vector<int>> ar = combinationSum2(candidates, 8);

	printAllCase(ar);

	system("pause");
	return 0;
}