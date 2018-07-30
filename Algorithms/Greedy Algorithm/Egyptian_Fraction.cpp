// https://www.geeksforgeeks.org/greedy-algorithm-egyptian-fraction/

#include <iostream>
#include <string>
#include <vector>
#include <cmath>

int UCLN(int nBig, int nSmall)
{
	int nTemp = 0;

	while (nBig > nSmall)
	{
		nBig = nBig - nSmall;
		if (nBig < nSmall)
		{
			nTemp = nBig;
			nBig = nSmall; 
			nSmall = nTemp;
		}
	}

	return nBig;
}

void printUniqueFraction(int num, int den, const std::vector<int>& vec)
{
	int size = vec.size();

	std::cout << num << "/" << den << " = ";
	for (int i = 0; i < size; ++i)
	{
		if (i == size - 1)
		{
			std::cout << "1/" << vec[i];
		}
		else
		{
			std::cout << "1/" << vec[i] << " + ";
		}		
	}
}

std::vector<int> Egyptian_Fraction_Loop(int num, int den)
{
	std::vector<int> vecDenumerator; 

	while (den > num)
	{
		int nUCLN = UCLN(den, num);

		den = den / nUCLN;
		num = num / nUCLN;

		if (num == 1)
		{
			vecDenumerator.push_back(den);
			return vecDenumerator;
		}

		double nDivided = 1.0 * den / num;
		int nDen = ceil(nDivided);
		vecDenumerator.push_back(nDen);

		num = (num * nDen) - den;
		den *= nDen;
	}

	vecDenumerator.clear();

	return vecDenumerator;
}


void Egyptian_Fraction_Recursion(int num, int den)
{
	if (num > den)
	{
		return; 
	}

	if (num == 1)
	{
		std::cout << "1/" << den << "\n";
		return;
	}

	double fRes = (double)den / num;
	int nRes = ceil(fRes);

	std::cout << "1/" << nRes << " + ";

	int nNewNum = num * nRes - den; 
	int nNewDen = den * nRes; 

	int nUCLN = UCLN(nNewDen, nNewNum);
	nNewNum /= nUCLN;
	nNewDen /= nUCLN;

	Egyptian_Fraction_Recursion(nNewNum, nNewDen);
}


int main()
{
	int num = 5; 
	int den = 8;

	/*std::vector<int> vecDenumerator; 
	vecDenumerator = Egyptian_Fraction_Loop(num, den);

	if (!vecDenumerator.empty())
	{
		printUniqueFraction(num, den, vecDenumerator);
	}
	else
	{
		std::cout << "Do not represent to Egyptian fraction.\n";
	}*/

	std::cout << num << "/" << den << " = ";
	Egyptian_Fraction_Recursion(num, den);

	system("pause");
	return 0;
}