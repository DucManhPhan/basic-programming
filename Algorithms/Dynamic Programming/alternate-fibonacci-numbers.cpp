//Give a number N, print alternate fibonacci numbers till n - th Fibonacci.
//
//Examples:
//
//	Input:  N = 7
//	Output : 0 1 3 8
//
//	Input : N = 15
//	Output : 0 1 3 8 21 55 144 377

#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>



std::vector<int> alternateFibonacciNumber(int nNumber) 
{
	std::vector<int> vtAlternateFibNum;
	int nPreviousFib = 0;
	int nCurrentFib = 1;
	int nNewFib = 0;
	

	for (int i = 0; i < nNumber; ++i)
	{
		if (i % 2 == 0) {
			vtAlternateFibNum.push_back(nPreviousFib);
		}

		nNewFib = nPreviousFib + nCurrentFib;

		nPreviousFib = nCurrentFib;
		nCurrentFib = nNewFib;
	}

	return vtAlternateFibNum;
}


int main()
{
	std::vector<int> vtAlternateFib = alternateFibonacciNumber(15);

	std::copy(vtAlternateFib.begin(), vtAlternateFib.end(), std::ostream_iterator<int>(std::cout, " "));

	system("pause");
	return 0;
}