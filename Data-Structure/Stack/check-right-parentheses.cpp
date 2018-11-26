// Given string str, how to examine the pairs and the orders of "{", "}"
// "(", ")", "[", "]" are correct. 
// Ex: "[()]{}{[()()]()}"

#include <iostream>
#include <algorithm>
#include <stack>
#include <vector>
#include <string>
#include <map>
#include <unordered_map>


std::map<char, char> mpChar = { {'(', ')'}, {'{', '}'}, {'[', ']'} };

bool isRightParentheses(const std::string& str)
{
	std::stack<char> stk;
	std::vector<char> vtChar = { '(', '{', '[' };

	int length = str.length();
	for (int i = 0; i < length; ++i)
	{
		if (std::find(vtChar.begin(), vtChar.end(), str[i]) != vtChar.end())
		{
			stk.push(str[i]);
		}
		else
		{
			if (stk.empty())
			{
				return false; 
			}

			char currentChar = stk.top();
			stk.pop();
			if (mpChar[currentChar] != str[i])
			{
				return false;
			}
		}		
	}

	if (!stk.empty())
	{
		return false;
	}

	return true;
}


bool isRightParentheses_Short(const std::string& str)
{
	std::stack<char> s;
	std::unordered_map<char, char> m;
	m[')'] = '(';
	m['}'] = '{';
	m[']'] = '[';

	for (char ch : str)
	{
		if (ch == '(' || ch == '{' || ch == '[')
		{
			s.push(ch);
		}
		else if ((ch == ')' || ch == '}' || ch == ']') && m[ch] == s.top())
		{
			s.pop();
		}
		else
			return false;
	}

	return s.empty() ? true : false;
}

int main()
{
	std::string str = "[()]{}{[()()]()}"; //"[(])";

	if (isRightParentheses_Short(str))
	{
		std::cout << "The string contains the balanced parentheses.\n";
	}
	else
	{
		std::cout << "Do not contains the balanced parentheses.\n";
	}

	system("pause");
	return 0;
}