#include "mythread.h"
#include <QtCore>

QMutex mutex;

MyThread::MyThread(QObject* parent) : QThread(parent)
{
    // nothing to do.
    Stop = false;
}


void MyThread::run()
{


    for (int i = 0; i < 10000; ++i)
    {
        mutex.lock();
        if (this->Stop) break;
        mutex.unlock();

        emit NumberChanged(i);
        msleep(100);
    }

}
