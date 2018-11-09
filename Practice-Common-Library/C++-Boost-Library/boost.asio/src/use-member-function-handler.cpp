#include <iostream>
#include <boost\bind.hpp>
#include <boost\asio.hpp>
#include <boost\date_time\posix_time\posix_time.hpp>


class CPrinter
{
public:
	CPrinter(boost::asio::io_service& io) : timer_(io, boost::posix_time::seconds(1))
																				, count_(0)
	{
		timer_.async_wait(boost::bind(&CPrinter::print, this));
	}

	~CPrinter()
	{
		std::cout << "Final count is: " << count_ << "\n";
	}

	void print()
	{
		if (count_ < 5)
		{
			std::cout << count_ << "\n";
			++count_;

			timer_.expires_at(timer_.expires_at() + boost::posix_time::seconds(1));
			timer_.async_wait(boost::bind(&CPrinter::print, this));
		}
	}

private: 
	boost::asio::deadline_timer timer_;
	int count_;
};



int main()
{
	boost::asio::io_service io;
	CPrinter p(io);

	io.run();

	system("pause");
	return 0;
}