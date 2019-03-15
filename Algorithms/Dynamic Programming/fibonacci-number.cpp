#include <iostream>
#include <vector>



// Use tabulation - BottomUp
int getFibonacciNumber_Tabulation(std::vector<int>& vtFib, int num)
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
int getFibonacciNumber_Mem(int num) 
{
	return 0;
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

		int res = getFibonacciNumber_Tabulation(vtFib, num);

		std::cout << "The " << num << "th fibonacci number is: " << res << "\n";

		std::cout << "\nDo you want to implement again (Y - 1/N - 0): ";
		std::cin >> isRepeated;
	} while (isRepeated);

	



	system("pause");
	return 0;
}
