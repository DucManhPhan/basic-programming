#include <QCoreApplication>
#include <additionalthread.h>
#include <ourthread.h>
#include <ourobject.h>

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    OurThread           firstThread;
    AdditionalThread    secondThread;

    OurObject ourobject;

    QObject::connect(&firstThread, SIGNAL(OurSignal()), &ourobject, SLOT(OurSlot()));

    secondThread.start();
    ourobject.moveToThread(&secondThread);
    firstThread.start();

    return a.exec();
}
