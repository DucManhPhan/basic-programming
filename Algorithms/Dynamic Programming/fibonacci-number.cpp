#include <iostream>
#include <vector>



// Use tabulation - BottomUp
int getFibonacciNumber_Tabulation(int num)
{
	int previousFib = 0; 
	int currentFib = 1;
	int newFib = -1;

	for (int i = 1; i < num; ++i)
	{
		newFib = previousFib + currentFib;

		previousFib = currentFib;
		currentFib = newFib;
	}

	return currentFib;
}


// Use Memoization - TopDown
int getFibonacciNumber_Mem(std::vector<int>& vtFib, int num)
{
	if (!vtFib[num] && num > 1)
	{
		vtFib[num] = getFibonacciNumber_Mem(vtFib, num - 1) + getFibonacciNumber_Mem(vtFib, num - 2);
	}
	
	return vtFib[num];
}




int main()
{
	std::vector<int> vtFib;
	int isRepeated = 1;
	int num = 0;

	do
	{
		std::cout << "Input our number: ";
		std::cin >> num;

		std::vector<int> vtFib(num, 0);
		vtFib[1] = 1;

		int res_tabulation = getFibonacciNumber_Tabulation(num - 1);
		int res_mem = getFibonacciNumber_Mem(vtFib, num - 1);

		std::cout << "In tabulation, the " << num << "th fibonacci number is: " << res_tabulation << "\n";

		std::cout << "In memoization, the " << num << "th fibonacci number is: " << res_mem << "\n";

		std::cout << "\nDo you want to implement again (Y - 1/N - 0): ";
		std::cin >> isRepeated;
	} while (isRepeated);
	
	system("pause");
	return 0;
}
