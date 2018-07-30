// https://www.hackerrank.com/challenges/find-the-median

#include <iostream>
#include <algorithm>
#include <vector>

void swap(int& a, int& b)
{
	int tmp = a; 
	a = b; 
	b = tmp;
}

int partition(std::vector<int>& vec, int start, int end, int pivotIndex)
{
	int pivotValue = vec[pivotIndex];
	int i = start - 1;

	swap(vec[pivotIndex], vec[end]);
	for (int j = start; j < end; ++j)
	{
		if (vec[j] < pivotValue)
		{
			++i;
			swap(vec[i], vec[j]);
		}
	}

	swap(vec[end], vec[i + 1]);
	return i + 1;
}


int quickSelect(std::vector<int>& vec, int start, int end, int k)
{
	if (start == end)
		return vec[start];

	int pivotIndex = (start + end) / 2;
	pivotIndex = partition(vec, start, end, pivotIndex);

	if (k == pivotIndex)
		return vec[k];
	else if (k < pivotIndex)
		return quickSelect(vec, start, pivotIndex - 1, k);
	else
		return quickSelect(vec, pivotIndex + 1, end, k);
}



int main()
{
	std::vector<int> vec = { 0, 1, 2, 4, 6, 5, 3 };
	int size = vec.size();
	int k = size / 2;
	
	int median = quickSelect(vec, 0, size - 1, k);
	std::cout << median;


	system("pause");
	return 0;
}