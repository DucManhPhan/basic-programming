// https://www.pluralsight.com/blog/software-development/how-to-measure-execution-time-intervals-in-c--
// https://www.geeksforgeeks.org/measure-execution-time-function-cpp/

#include <iostream>
#include <chrono>       // for high_resolution_clock
#include <vector>


int main()
{
    // Record start time
    auto start = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < 1000000; ++i)
    {
        int a = i;
    }

    // Record end time
    auto finish = std::chrono::high_resolution_clock::now();

    std::chrono::duration<double> elapsed = finish - start;
    std::cout << "Result when creating variable in for loop is: " << elapsed.count() << "\n\n";

    // Record start time
    auto start_second = std::chrono::high_resolution_clock::now();

    int b = 0;
    for (int i = 0; i < 1000000; ++i)
    {
        b = i;
    }

    // Record finish time
    auto finish_second = std::chrono::high_resolution_clock::now();

    std::chrono::duration<double> elapsed_second = finish - start;
    std::cout << "Result when creating variable out of for loop is: " << elapsed_second.count() << "\n\n";

    system("pause");
    return 0;
}
