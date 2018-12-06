#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#include <boost/property_tree/xml_parser.hpp>
#include <boost\property_tree\ptree.hpp>
#include <boost\foreach.hpp>


struct CPicture
{
	std::string title; 
	std::string nameArtist; 
	std::string country;
	std::string company;
	float		price; 
	int			year;
};


void readCatalogXml(std::istream& is, std::vector<CPicture>& vecPicture)
{
	using boost::property_tree::ptree;
	ptree pt;	
  	//read_xml(ifs, pt, boost::property_tree::xml_parser::trim_whitespace);
	read_xml(is, pt);

	BOOST_FOREACH(ptree::value_type const & v, pt.get_child("CATALOG"))
	{
		if (v.first == "CD")
		{
			CPicture picture;
			picture.title = v.second.get<std::string>("TITLE");
			picture.nameArtist = v.second.get<std::string>("ARTIST");
			picture.country = v.second.get<std::string>("COUNTRY");
			picture.company = v.second.get<std::string>("COMPANY");
			picture.price = v.second.get<float>("PRICE");
			picture.year = v.second.get<int>("YEAR");

			vecPicture.push_back(std::move(picture));
		}
	}
}

void writeCatalogXml(std::ostream& os, const std::vector<CPicture>& vecPic)
{
	using boost::property_tree::ptree;
	ptree pt;

	BOOST_FOREACH(const CPicture& pic, vecPic)
	{
		ptree& node = pt.add("CATALOG.CD", "\n");
		node.put("TITLE", pic.title + "\n");
		node.put("ARTIST", pic.nameArtist + "\n");
		node.put("COUNTRY", pic.country + "\n");
		node.put("COMPANY", pic.company + "\n");
		node.put("PRICE", std::to_string(pic.price) + "\n");
		node.put("YEAR", std::to_string(pic.year) + "\n");
	}

	boost::property_tree::xml_writer_settings<std::string> settings('\t', 1);
	write_xml(os, pt, settings);
}

void printContent(const std::vector<CPicture>& vecPic)
{
	for (auto pic : vecPic)
	{
		std::cout << pic.title << "\n";
		std::cout << pic.nameArtist << "\n";
		std::cout << pic.country << "\n";
		std::cout << pic.company << "\n";
		std::cout << pic.price << "\n";
		std::cout << pic.year << "\n";
		std::cout << "\n";
	}	
}


int main()
{
	std::string fileIn = "./cd-catalog.xml";
	std::vector<CPicture> vecPicture;

	readCatalogXml(std::ifstream(fileIn), vecPicture);

	//printContent(vecPicture);

	std::string fileOut = "./cd-catalog-output.xml";

	writeCatalogXml(std::ofstream(fileOut), vecPicture);

	system("pause");
	return 0;
}