// Given two sorted arrays, find their union and intersection.

#include <iostream>
#include <vector>
#include <iterator>


std::vector<int> getUnionOf(const std::vector<int>& firstVec, const std::vector<int>& secondVec)
{
	int i = 0;			// the index of first vector. 
	int j = 0;			// the index of second vector.
	int sizeFistVec = firstVec.size();
	int sizeSecondVec = secondVec.size();

	std::vector<int> thirdVec;

	for (; i < sizeFistVec && j < sizeSecondVec;)
	{
		if (firstVec[i] < secondVec[j])
		{
			thirdVec.push_back(firstVec[i]);
			++i;
		}
		else if (firstVec[i] > secondVec[j])
		{
			thirdVec.push_back(secondVec[j]);
			++j;
		}
		else
		{
			thirdVec.push_back(firstVec[i]);

			++i; 
			++j;
		}
	}

	for (; i < sizeFistVec; ++i)
	{
		thirdVec.push_back(firstVec[i]);
	}

	for (; j < sizeSecondVec; ++j)
	{
		thirdVec.push_back(secondVec[j]);
	}

	return thirdVec;
}


std::vector<int> getIntersectionOf(const std::vector<int>& firstVec, const std::vector<int>& secondVec)
{
	int i = 0;			// the index of first vector. 
	int j = 0;			// the index of second vector.
	int sizeFistVec = firstVec.size();
	int sizeSecondVec = secondVec.size();

	std::vector<int> thirdVec;

	for (; i < sizeFistVec && j < sizeSecondVec;)
	{
		if (firstVec[i] < secondVec[j])
		{		
			++i;
		}
		else if (firstVec[i] > secondVec[j])
		{			
			++j;
		}
		else
		{
			thirdVec.push_back(firstVec[i]);

			++i;
			++j;
		}
	}

	return thirdVec;
}

void printArray(const std::vector<int>& vec)
{
	std::copy(vec.begin(), vec.end(), std::ostream_iterator<int>(std::cout, "  "));
	std::cout << "\n";
}


int main()
{
	std::vector<int> firstVec = { 1, 3, 4, 5, 7 };
	std::vector<int> secondVec = { 2, 3, 5, 6 };

	std::vector<int> unionVec = getIntersectionOf(firstVec, secondVec);

	printArray(unionVec);


	system("pause");
	return 0;
}