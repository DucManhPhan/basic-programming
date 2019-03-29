#include "additionalthread.h"

AdditionalThread::AdditionalThread()
{
    // nothing to do.
}


void AdditionalThread::run()
{
    std::cout << "Thread 2 started.\n";
    exec();
}
