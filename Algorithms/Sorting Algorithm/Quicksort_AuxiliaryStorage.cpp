// https://www.hackerrank.com/challenges/quicksort1?h_r=next-challenge&h_v=zen

#include <iostream>
#include <algorithm>
#include <vector>
#include <iterator>


void addElement(std::vector<int>& vecSum, std::vector<int>& vecTemp)
{
	int size = vecTemp.size();

	for (int i = 0; i < size; ++i)
	{
		vecSum.push_back(vecTemp[i]);
	}
}

std::vector<int> quicksort(std::vector<int> vecNumber)
{
	std::vector<int> left, equals, right;

	int pivot = vecNumber[0];
	int size = vecNumber.size();

	for (int i = 0; i < size; ++i)
	{
		if (vecNumber[i] > pivot)
		{
			right.push_back(vecNumber[i]);
		}
		else if (vecNumber[i] < pivot)
		{
			left.push_back(vecNumber[i]);
		}
		else
		{
			equals.push_back(vecNumber[i]);
		}
	}

	std::vector<int> vecSum;
	addElement(vecSum, left);
	addElement(vecSum, equals);
	addElement(vecSum, right);

	return vecSum;
}

int main()
{
	std::vector<int> vecNumber = { 4, 5, 3, 7, 2 };
	
	std::vector<int> vecSum = quicksort(vecNumber);
	
	std::copy(vecSum.begin(), vecSum.end(), std::ostream_iterator<int>(std::cout, " "));
	std::cout << std::endl;
	

	system("pause");
	return 0;
}