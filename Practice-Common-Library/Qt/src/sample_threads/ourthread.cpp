#include "ourthread.h"

OurThread::OurThread()
{
    // nothing to do.
}


void OurThread::run(void)
{
    std::cout << "OurThread is started.\n";

    int i = 0;
    while (1)
    {
        msleep(200);
        ++i;
        if (i == 20)
        {
            emit OurSignal();
        }
    }
}



