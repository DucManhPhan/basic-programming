#include <iostream>
#include <string>
#include <cstring>


// Use strtok_s() in C/C++
void printLastCharacterWords_strtok(const std::string& strInput)
{
	std::string tmp(strInput);
	char* tok			= &(*tmp.begin());
	char* next_token	= nullptr;
	
	while ((tok = strtok_s(tok, " ", &next_token)) != nullptr)
	{
		std::cout << tok[strlen(tok) - 1] << " ";
		tok = nullptr;
	}
}


// iterate consequence character in string
void printLastCharacterWords_iteration(const std::string& strInput)
{
	std::string strTmp = strInput + " ";
	int length = strInput.length();

	for (int i = 0; i < length; ++i)
	{
		if (strTmp[i + 1] == ' ')
		{
			std::cout << strTmp[i] << " ";
		}
	}
}


int main()
{
	//printLastCharacterWords_iteration("Geeks for Geeks");

	printLastCharacterWords_strtok("Geeks for Geeks");


	system("pause");
	return 0;
}
