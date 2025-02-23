#include <iostream>
#include <condition_variable>
#include <thread>
#include <functional>
#include <mutex>

using namespace std::placeholders;

class Application
{
private:
	std::mutex							m_mutex;
	std::condition_variable m_condVar;
	bool										m_bDataLoaded;

public:
	Application()
	{
		m_bDataLoaded = false;
	}

	void loadData()
	{
		std::this_thread::sleep_for(std::chrono::milliseconds(1000));
		std::cout << "loading data from xml.\n";

		std::lock_guard<std::mutex> guard(m_mutex);

		m_bDataLoaded = true;

		m_condVar.notify_one();
	}

	bool isDataLoaded()
	{
		return m_bDataLoaded;
	}

	void mainTask()
	{
		std::cout << "Do some handshaking.\n";
		std::unique_lock<std::mutex> mlock(m_mutex);

		m_condVar.wait(mlock, std::bind(&Application::isDataLoaded, this));
		std::cout << "Do processing on loaded data.\n";
	}
};



int main()
{
	Application app;

	std::thread thread_1(&Application::mainTask, &app);
	std::thread thread_2(&Application::loadData, &app);

	thread_1.join();
	thread_2.join();

	system("pause");
	return 0;
}