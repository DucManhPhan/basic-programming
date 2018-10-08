#include <iostream>
#include <boost\filesystem\path.hpp>
#include <boost\filesystem.hpp>
#include <string>
#include <exception>


namespace fs = boost::filesystem;

void recursive_copy(const fs::path&src, const fs::path& dst)
{
	try {
		bool bExist = fs::exists(dst);
		if (!bExist) {
			/*throw std::runtime_error(dst.generic_string() + " exists");*/
			std::cout << "File does not exist. \n";
			//return;
		}

		if (fs::is_directory(src)) {
			fs::create_directories(dst);
			for (fs::directory_entry& item : fs::directory_iterator(src)) {
				recursive_copy(item.path(), dst / item.path().filename());
			}
		}
		else if (fs::is_regular_file(src)) {
			fs::copy(src, dst);
		}
		else {
			throw std::runtime_error(dst.generic_string() + " not dir or file");
		}
	} catch (std::exception& ex) {
		std::cout << ex.what() << "\n";
	}
}



int main()
{
	fs::path pthSrc("...\\Task\\14-09-2018");
	fs::path pthDst("...\\Task\\17-09-2018");

	std::cout << "Copy files: \n";
	recursive_copy(pthSrc, pthDst);

	system("pause");
	return 0;
}