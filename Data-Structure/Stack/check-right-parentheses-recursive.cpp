#include <iostream>
#include <vector>
#include <algorithm>
#include <string>



bool isRightParentheses(std::string& str)
{
	if (str.empty())
	{
		return true;
	}

	std::string strOpenParentheses = "{([";
	std::string strCloseParentheses = "})]";

	size_t index = strOpenParentheses.find(str[0]);
	if (index == std::string::npos)
	{
		return false;
	}

	int size = str.length();
	for (int i = 1; i < size; ++i)
	{
		if (strCloseParentheses.find(str[i]) == index)
		{
			std::string strInside(str.begin() + 1, str.begin() + i);
			std::string strOutside(str, i + 1);

			if (isRightParentheses(strInside) && isRightParentheses(strOutside))
			{
				return true;
			}
		}
	}

	return false;
}



int main()
{
	std::string str = "(([])){}";
	if (isRightParentheses(str))
	{
		std::cout << "Balanced\n";
	}
	else
	{
		std::cout << "Not balanced.\n";
	}

	system("pause");
	return 0;
}