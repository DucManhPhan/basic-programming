#include <iostream>
#include <string>
#include <ctime>

#include <boost\asio.hpp>
#include <boost\array.hpp>

using boost::asio::ip::tcp;


std::string make_daytime_string()
{
	using namespace std; 
	time_t now = time(0);

	return ctime(&now);
}



int main()
{
	try
	{
		boost::asio::io_service io_service; 

		//ip::tcp::acceptor object needs to be created to listen for new connections.
		tcp::acceptor acceptor(io_service, tcp::endpoint(tcp::v4(), 13));

		for (;;)
		{
			tcp::socket socket(io_service);
			acceptor.accept(socket);

			std::string message = make_daytime_string();

			boost::system::error_code ignored_error; 
			boost::asio::write(socket, boost::asio::buffer(message),
				boost::asio::transfer_all(), ignored_error);
		}
	}
	catch (std::exception& ex)
	{
		std::cout << ex.what() << "\n";
	}

	system("pause");
	return 0;
}