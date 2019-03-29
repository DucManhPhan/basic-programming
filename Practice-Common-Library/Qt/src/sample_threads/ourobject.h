#ifndef OUROBJECT_H
#define OUROBJECT_H
#include <QObject>
#include <iostream>


class OurObject : public QObject
{
    Q_OBJECT

public:
    OurObject();

public slots:
    void OurSlot(void);
};

#endif // OUROBJECT_H
