#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <locale>
#include <codecvt>
#include <sstream>

#include <boost\property_tree\json_parser.hpp>
#include <boost\property_tree\ptree.hpp>
#include <boost\foreach.hpp>
#include <boost\optional.hpp>

#include "json11.hpp"
#include <Windows.h>

using namespace json11;
using namespace std;


// Use WideCharToMultiByte() in windows.h to convert
string Utf16ToUtf8(const std::wstring& wsInput)
{
	string sRet;
	if (!wsInput.empty())
	{
		int iSizeRequired = WideCharToMultiByte(CP_UTF8, 0, wsInput.c_str(), -1, NULL, 0, NULL, NULL);
		if (iSizeRequired > 0)
		{
			vector<char> utf8String(iSizeRequired);
			int iBytesConverted = WideCharToMultiByte(CP_UTF8, 0, wsInput.c_str(), -1, &utf8String[0], utf8String.size(), NULL, NULL);
			if (iBytesConverted != 0)
			{
				sRet = &utf8String[0];
			}
			else
			{
				stringstream err;
				err << __FUNCTION__
						<< " std::string Utf16ToUtf8 failed to convert wstring '"
						<< wsInput.c_str() << L"'";
				throw runtime_error(err.str());
			}
		}
	}
	return sRet;
}


// Use wstring_conversion in STL
string convertUtf16ToUtf8(const wstring& wsInput)
{	
	wstring_convert<codecvt_utf8_utf16<char16_t>, char16_t> ws_convert; 
	string sDest = ws_convert.to_bytes(wsInput);

	return sDest;
}


wstring convertUtf8ToUtf16(const string& sInput)
{	
	//std::string str = "your string in utf8";
	std::wstring_convert<std::codecvt_utf8_utf16<char16_t>> converter;
	std::wstring wstr = converter.from_bytes(sInput);

	return wstr;
}


//json11::Json read(const string& path)
//{
//	// prepare for file stream
//	wstringstream wss;
//	wifstream wifs(path);
//	wifs.imbue(locale(locale(), new codecvt_utf8<wchar_t>));
//	wss << wifs.rdbuf();
//	wifs.close();
//
//	// convert wide-char to char
//
//}


//void readJapaneseJsonFile(const string& path)
//{
//	
//}



int main()
{
	wstring wstr = L"ƒ^ƒCƒgƒ‹";
	std::string str = convertUtf16ToUtf8(wstr);	
	std::wstring wstr1 = convertUtf8ToUtf16(str);

	std::wcout << wstr1 << "\n";

	system("pause");
	return 0;
}