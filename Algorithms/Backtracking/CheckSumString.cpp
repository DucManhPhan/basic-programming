// Check if a given string is sum - string.
// http://www.geeksforgeeks.org/check-given-string-sum-string/

#include <iostream>
#include <string>

using std::string;

// to find sum of two number as string. 
string string_sum(string str1, string str2)
{
	if (str1.size() < str2.size())
	{
		swap(str1, str2);
	}

	int m = str1.size();
	int n = str2.size();
	string ans = "";

	// sum the str2 with str1
	int carry = 0;
	for (int i = 0; i < n; ++i)
	{
		// sum of current digits. 
		int ds = ((str1[m - 1 - i] - '0') +
			(str2[n - 1 - i] - '0') +
			carry) % 10;
		carry = ((str1[m - 1 - i] - '0') +
			(str2[n - 1 - i] - '0') +
			carry) / 10;

		ans = char(ds + '0') + ans;
	}

	for (int i = n; i < m; ++i)
	{
		int ds = (str1[m - 1 - i] - '0' + carry) % 10;
		carry = str1[m - 1 - i] - '0' + carry / 10;

		ans = char(ds + '0') + ans;
	}

	if (carry)
	{
		ans = char(carry + '0') + ans;
	}

	return ans;
}

// returns true of two sub-strings of given
// lengths of str[beg ...] can cause a positive result.
bool checkSumStrUtil(string str, int beg,
	int len1, int len2)
{
	// Finding two substrings of given lengths 
	// and their sum. 
	string s1 = str.substr(beg, len1);
	string s2 = str.substr(beg + len1, len2);
	string s3 = string_sum(s1, s2);

	int s3_len = s3.size();

	// if number of digits s3 is greater then 
	// the available string size. 
	if (s3_len > str.size() - len1 - len2 - beg)
	{
		return false; 
	}

	// got s3 as next number in main string. 
	if (s3 == str.substr(beg + len1 + len2, s3_len))
	{
		// if reach at the end of the string. 
		if (beg + len1 + len2 + s3_len == str.size())
		{
			return true;
		}

		// otherwise call recursively for s2, s3. 
		return checkSumStrUtil(str, beg + len1, len2, s3_len);
	}

	// don't get s3 in main string. 
	return false; 
}

bool isSumStr(string str)
{
	int n = str.size();

	// choosing first two numbers and checking
	// whether it is sum-string or not. 
	for (int i = 1; i < n; ++i)
	{
		for (int j = 1; i + j < n; ++j)
		{
			if (checkSumStrUtil(str, 0, i, j))
				return true;
		}
	}

	return false;
}


int main()
{
	//string str = string_sum("12", "24");
	std::cout << isSumStr("1212243660") << std::endl;
	std::cout << isSumStr("123456787");

	system("pause");
	return 0;
}
