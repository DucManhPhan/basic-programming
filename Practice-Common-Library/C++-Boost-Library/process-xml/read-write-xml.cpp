#include <boost\property_tree\ptree.hpp>
#include <boost\property_tree\xml_parser.hpp>
#include <boost\foreach.hpp>
#include <boost\date_time\gregorian\gregorian.hpp>

#include <vector>
#include <string>
#include <iostream>
#include <locale>

typedef boost::gregorian::date Date;

struct Flight
{
	std::string carrier;
	unsigned int number; 
	Date date; 
	bool cancelled;
};

typedef std::vector<Flight> Sked;

// convert string to Date type in Boost.
class DateTranslator
{
private:
	typedef boost::date_time::date_facet<Date, char> tOFacet;
	typedef boost::date_time::date_input_facet<Date, char> tIFacet;
	std::locale locale_;

	static std::locale isoDateLocale()
	{
		std::locale loc; 
		loc = std::locale(loc, new tIFacet("%Y-%m-%d"));
		loc = std::locale(loc, new tOFacet("%Y-%m-%d"));
		
		return loc;
	}

public:
	typedef std::string internal_type;
	typedef Date external_type;

	DateTranslator() : locale_(isoDateLocale()) {}

	boost::optional<external_type> get_value(internal_type const& v)
	{
		std::istringstream stream(v);
		stream.imbue(locale_);
		external_type vAns;
		if (stream >> vAns) {
			return vAns;
		}
		else {
			return boost::none;
		}
	}

	boost::optional<internal_type> put_value(external_type const& v)
	{
		std::ostringstream ans;
		ans.imbue(locale_);
		ans << v;
		return ans.str();
	}
};

namespace boost {
	namespace property_tree {

		template<> struct translator_between<std::string, Date> {
			typedef DateTranslator type;
		};
	}
}

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
			f.date = v.second.get<Date>("date");
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