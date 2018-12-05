#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <locale>
#include <codecvt>
#include <iterator>
#include <exception>

#include <boost/optional.hpp>
#include <boost\property_tree\xml_parser.hpp>
#include <boost\property_tree\ptree.hpp>
#include <boost/lexical_cast.hpp>
#include <boost\foreach.hpp>

using namespace std;

const std::wstring CATALOG = L"カタログ";
const std::wstring CD = L"ディスク";
const std::wstring TITLE = L"タイトル";
const std::wstring ARTIST = L"アーティスト";
const std::wstring COUNTRY = L"国";
const std::wstring COMPANY = L"会社";
const std::wstring PRICE = L"価格";
const std::wstring YEAR = L"年";


struct CCD
{
	std::wstring _title;
	std::wstring _artist;
	std::wstring _country;
	std::wstring _company;
	double			_price; 
	int					_year;

	CCD() : _title(L""), _artist(L""), _country(L"")
			 , _company(L""), _price(0.0), _year(0)
	{
		// nothing to do
	}

	// overloading the operator << 
	friend std::wostream& operator<< (std::wostream& stream, const CCD& cd)
	{
		stream << "Title: " << cd._title << "\n"
			<< "Artist: " << cd._artist << "\n"
			<< "Country: " << cd._country << "\n"
			<< "Company: " << cd._company << "\n"
			<< "Price: " << cd._price << "\n"
			<< "Year: " << cd._year << "\n";

		return stream;
	}
};


boost::property_tree::wptree read(const std::wstring& strPath)
{
	try
	{
		using boost::property_tree::wptree;

		std::wifstream ifs(strPath, std::ios::binary);
		ifs.imbue(locale(locale(), new codecvt_utf8<wchar_t, 0x10ffff, little_endian>));

		wptree pt;
		read_xml(ifs, pt);//, boost::property_tree::xml_parser::trim_whitespace);
		ifs.close();

		return pt;
	}
	catch (std::exception& ex)
	{
		//nothing to do.
		std::cout << ex.what() << "\n";		
	}	
}


void printData(const std::vector<CCD>& vecCD)
{
	int length = vecCD.size();
	for (int i = 0; i < length; ++i)
	{
		std::wcout << "Title: " << vecCD[i]._title << "\n";
		std::wcout << "Artist: " << vecCD[i]._artist << "\n";
		std::wcout << "Country: " << vecCD[i]._country << "\n";
		std::wcout << "Company: " << vecCD[i]._company << "\n";
		std::wcout << "Price: " << vecCD[i]._price << "\n";
		std::wcout << "Year: " << vecCD[i]._year << "\n";

		std::wcout << "\n";
	}
}


void readJapaneseXml(const std::wstring& strPath)
{
	using boost::property_tree::wptree;
	std::vector<CCD> vecCD;

	// get information from the file
	boost::property_tree::wptree pt = read(strPath);

	BOOST_FOREACH(const wptree::value_type &v, pt.get_child(CATALOG))
	{
		if (v.first == CD)
		{
			CCD cd; 
			cd._title = v.second.get<std::wstring>(TITLE);
			cd._artist = v.second.get<std::wstring>(ARTIST);
			cd._country = v.second.get<std::wstring>(COUNTRY);
			cd._company = v.second.get<std::wstring>(COMPANY);
			cd._price = v.second.get<double>(PRICE);
			cd._year = v.second.get<int>(YEAR);

			vecCD.push_back(std::move(cd));
		}
	}

	printData(vecCD);
}


int main()
{
	std::wstring path = L"./cd-catalog-japanese.xml";

	readJapaneseXml(path);

	system("pause");
	return 0;
}