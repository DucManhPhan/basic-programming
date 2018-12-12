#include <iostream>
#include <boost\array.hpp>
#include <boost\asio.hpp>

using boost::asio::ip::tcp;



// boost::asio::buffer() function automatically determines the size of the array to help 
// prevent buffer overruns.
int main(int argc, char* argv[])
{
	try
	{
		/*if (argc != 2)
		{
			std::cerr << "Usage: client <host>" << "\n";
			return 1;
		}*/

		boost::asio::io_service io_service; 
		tcp::resolver resolver(io_service);
		tcp::resolver::query query("127.0.0.1", "daytime");
		tcp::resolver::iterator endpoint_iterator = resolver.resolve(query);
		tcp::resolver::iterator end;

		tcp::socket socket(io_service);
		boost::system::error_code error = boost::asio::error::host_not_found;
		while (error && endpoint_iterator != end)
		{
			socket.close();
			socket.connect(*endpoint_iterator++, error);
		}

		if (error)
		{
			throw boost::system::system_error(error);
		}

		for (;;)
		{
			boost::array<char, 128> buf;
			boost::system::error_code error;

			size_t len = socket.read_some(boost::asio::buffer(buf), error);

			if (error == boost::asio::error::eof)
			{
				break;	// connection closed cleanly by peer
			}
			else if (error)
			{
				throw boost::system::system_error(error);
			}
			else
			{
				std::cout.write(buf.data(), len);
			}
		}

	}
	catch (std::exception& ex)
	{
		std::cout << ex.what();
		return 1;
	}

	system("pause");
	return 0;
}