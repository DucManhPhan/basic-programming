#include <iostream>
#include <vector>


// Use Memoization - Top Down
int getNthCatalanNumber_Mem(std::vector<int>& vtCatalanNum, int num)
{
	if (num <= 1 || vtCatalanNum[num] > 0)
	{
		return vtCatalanNum[num];
	}

	int res = 0;

	for (int i = 0; i < num; ++i)
	{
		res += (getNthCatalanNumber_Mem(vtCatalanNum, i) * getNthCatalanNumber_Mem(vtCatalanNum, num - 1 - i));
	}

	vtCatalanNum[num] = res;
	return res;
}


// Use Tabulation - Bottom Up
int getNthCatalanNumber_Tabulation(int num)
{
	std::vector<int> vtCatalanNum(num + 1, 0);
	vtCatalanNum[0] = 1;
	vtCatalanNum[1] = 1;

	for (int i = 2; i <= num; ++i)
	{
		for (int j = 0; j < i; ++j)
		{
			vtCatalanNum[i] += vtCatalanNum[j] * vtCatalanNum[i - j - 1];
		}
	}

	return vtCatalanNum[num];
}


// Driver for Memoization
int main()
{
	int nthCatalanNum = 10;
	std::vector<int> vtCatalanNum(nthCatalanNum, 0);
	vtCatalanNum[0] = 1;
	vtCatalanNum[1] = 1;

	getNthCatalanNumber_Mem(vtCatalanNum, nthCatalanNum - 1);

	std::cout << "The sequence of catalan numbers is: ";
	for (int i = 0; i < nthCatalanNum; ++i)
	{
		std::cout << vtCatalanNum[i] << " ";
	}

	system("pause");
	return 0;
}





// Driver for Tabulation
//int main()
//{
//	int nthCatalanNum = 10;
//	for (int i = 0; i < nthCatalanNum; ++i)
//	{
//		if (i <= 1)
//		{
//			std::cout << 1 << " ";
//		}
//		else
//		{
//			std::cout << getNthCatalanNumber_Tabulation(i) << " ";
//		}
//	}
//
//
//
//	system("pause");
//	return 0;
//}