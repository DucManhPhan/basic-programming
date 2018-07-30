// https://www.interviewbit.com/problems/letter-phone/

#include <iostream>
#include <vector>
#include <string>
#include <map>

using namespace std;


void solve_LetterPhone(const map<char, string>& mpNumber, const string& strNumber, int indexOfString, int size, string& letter_phone)
{
	if (indexOfString == size)
	{
		cout << letter_phone << "\n";
		return;
	}

	char c = strNumber[indexOfString];
	string strTmp = mpNumber.at(c);

	for (int i = 0; i < strTmp.length(); ++i)
	{	
		letter_phone.push_back(strTmp[i]);

		solve_LetterPhone(mpNumber, strNumber, indexOfString + 1, size, letter_phone);

		letter_phone.pop_back();
	}
}


int main()
{
	string strNumber = "234567";
	int size = strNumber.length();

	map<char, string> mpNumber; 
	mpNumber['0'] = "0";
	mpNumber['1'] = "1";
	mpNumber['2'] = "abc";
	mpNumber['3'] = "def";
	mpNumber['4'] = "ghi";
	mpNumber['5'] = "jkl";
	mpNumber['6'] = "mno";
	mpNumber['7'] = "pqrs";
	mpNumber['8'] = "tuv";
	mpNumber['9'] = "wxyz";

	string letter_phone;

	solve_LetterPhone(mpNumber, strNumber, 0, size, letter_phone);

	system("pause");
	return 0;
}