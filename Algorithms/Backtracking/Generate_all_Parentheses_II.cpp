// https://www.interviewbit.com/problems/generate-all-parentheses-ii/
// Result: "((()))", "(()())", "(())()", "()(())", "()()()"

#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std; 

#define NUMBER	3

void printAllParenthese(const std::vector<std::string>& vec)
{
	std::vector<std::string>::const_iterator itbegin = vec.begin();
	std::vector<std::string>::const_iterator itend = vec.end();
	std::vector<std::string>::const_iterator it;

	for (it = itbegin; it != itend; ++it)
	{
		std::cout << *it << "\n"; 
	}

	std::cout << "\n";
}

void parenHelper(vector<string>& res, string& ans, int left, int right, string paren = "()")
{
	if (right < left || left < 0) // not feasible
	{
		return;
	}
	if (right == 0 && left == 0)
	{
		res.push_back(ans);
		return;
	}
	else
	{
		for (int i = 0; i < 2; i++)
		{
			ans.push_back(paren[i]);
			if (i)
				parenHelper(res, ans, left, right - 1);
			else
				parenHelper(res, ans, left - 1, right);
			ans.pop_back();
		}
	}
}


vector<string> generateParenthesis(int n)
{
	vector<string> res;
	string ans;
	parenHelper(res, ans, n, n);
	return res;
}


int main()
{
	std::vector<std::string>& vec = generateParenthesis(2);
	printAllParenthese(vec);

	system("pause");
	return 0;
}
