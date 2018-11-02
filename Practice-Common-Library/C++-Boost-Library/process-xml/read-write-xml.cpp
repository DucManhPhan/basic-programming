#include <boost\property_tree\ptree.hpp>
#include <boost\property_tree\xml_parser.hpp>
#include <boost\foreach.hpp>

#include <vector>
#include <string>
#include <iostream>


struct Flight
{
	std::string carrier;
	unsigned int number; 
	std::string date; 
	bool cancelled;
};

typedef std::vector<Flight> Sked;


Sked read(std::istream& is)
{
	using boost::property_tree::ptree;
	ptree pt;
	read_xml(is, pt);

	Sked ans; 
	BOOST_FOREACH(ptree::value_type const& v, pt.get_child("sked"))
	{
		if (v.first == "flight")
		{
			Flight f; 
			f.carrier = v.second.get<std::string>("carrier");
			f.number = v.second.get<unsigned int>("number");
			f.date = v.second.get<std::string>("date");
			f.cancelled = v.second.get("<xmlattr>.cancelled", false);
			ans.push_back(f);
		}
	}

	return ans;
}

void printSked(std::vector<Flight>& skeds)
{
	for (Flight& ske : skeds)
	{
		std::cout << ske.carrier << "\n";
		std::cout << ske.date << "\n";
		std::cout << ske.number << "\n";
		std::cout << ske.cancelled << "\n";
		std::cout << "\n";
	}
}


int main()
{
	std::ifstream ifile("./informations.xml");
	Sked ske = read(ifile);

	printSked(ske);

	system("pause");
	return 0;
}