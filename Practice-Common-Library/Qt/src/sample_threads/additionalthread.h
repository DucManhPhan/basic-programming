#ifndef ADDITIONALTHREAD_H
#define ADDITIONALTHREAD_H

#include <QThread>
#include <iostream>


class AdditionalThread : public QThread
{
    Q_OBJECT

public:
    AdditionalThread();

    void run();
};

#endif // ADDITIONALTHREAD_H
