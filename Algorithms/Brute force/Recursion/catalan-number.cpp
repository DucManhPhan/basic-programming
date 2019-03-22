#include <iostream>
#include <vector>


int getNthCatalanNumber(int num)
{
	if (num == 0)
	{
		return 1;
	}

	int res = 0;

	if (num > 0)
	{
		for (int i = 0; i < num; ++i)
		{
			res += (getNthCatalanNumber(i) * getNthCatalanNumber(num - i - 1));
		}
	}

	return res;
}


int main()
{
	std::cout << "The nth catalan number is: ";

	for (int i = 0; i < 10; ++i)
	{
		std::cout << getNthCatalanNumber(i) << " ";
	}
	 
	std::cout << "\n";

	system("pause");
	return 0;
}