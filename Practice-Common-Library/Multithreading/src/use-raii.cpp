#include <iostream>
#include <thread>



void printHello()
{
	std::cout << "Use RAII paradigm for multithread.\n";
}

class thread_guard
{
private:
	std::thread m_thread;

public:
	thread_guard(std::thread& t_) : m_thread(std::move(t_))
	{
		// nothign to do
	}

	~thread_guard()
	{
		if (m_thread.joinable())
		{
			m_thread.join();
		}
	}

	thread_guard(const thread_guard&) = delete;
	thread_guard& operator=(const thread_guard&) = delete;
};



int main()
{
	std::thread t(printHello);

	thread_guard th(t);
	
	system("pause");
	return 0;
}
