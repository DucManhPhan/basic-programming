#ifndef OURTHREAD_H
#define OURTHREAD_H
#include <QThread>
#include <iostream>

class OurThread : public QThread
{
    Q_OBJECT

public:
    OurThread();

    void run(void);

signals:
    void OurSignal(void);

};

#endif // OURTHREAD_H
