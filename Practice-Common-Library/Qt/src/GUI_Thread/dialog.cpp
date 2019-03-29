#include "dialog.h"
#include "ui_dialog.h"

Dialog::Dialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Dialog)
{
    ui->setupUi(this);
    m_pNumberThread = new MyThread(this);
    connect(m_pNumberThread, SIGNAL(NumberChanged(int)), this, SLOT(onNumberChanged(int)));
}

Dialog::~Dialog()
{
    delete ui;
}


void Dialog::onNumberChanged(int number)
{
    ui->label->setText(QString::number(number));
}

void Dialog::on_pushButton_clicked()
{    
    m_pNumberThread->Stop = false;
    m_pNumberThread->start();
}

void Dialog::on_pushButton_2_clicked()
{
    m_pNumberThread->Stop = true;
}
