// https://www.interviewbit.com/problems/palindrome-partitioning/

#include <iostream>
#include <vector> 
#include <string>
#include <stack>
#include <queue>


void printPalindromeString(const std::vector<std::string>& vec);

bool checkPalindrome(const std::string& origin);

void generatePalindromePartitioning(const std::string& origin, std::vector<std::string>& vec, std::string& strTmp, int index);

int main()
{
	std::string strOrigin = "";//"super"; //"banana"; //"eye"; //; "madam"; //"yes"; "racecar";
	std::vector<std::string> vec; 
	std::string strTmp = "";

	generatePalindromePartitioning(strOrigin, vec, strTmp, 0);

	printPalindromeString(vec);

	system("pause");
	return 0;
}


bool checkPalindrome(const std::string& origin)
{
	int nSize = origin.size();

	if (nSize == 1)
	{
		return true;
	}

	for (int i = 0; i < nSize/2; ++i)
	{
		if (origin[i] != origin[nSize - i - 1])
		{
			return false;
		}
	}
	
	return true;
}


void generatePalindromePartitioning(const std::string& origin, std::vector<std::string>& vec, std::string& strTmp, int index)
{

}


void printPalindromeString(const std::vector<std::string>& vec)
{
	int nSize = vec.size();

	for (int i = 0; i < nSize; ++i)
	{
		std::cout << vec[i] << "\n";
	}

	std::cout << "\n";
}