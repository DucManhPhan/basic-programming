#pragma once
#include "stdafx.h"

#define PRIME		7
#define TABLESIZE	13

class CHashTableOpenAddressing
{
public:
	// Constructor
	CHashTableOpenAddressing(int size, int valueOfElement);

	// Common operations
	void insertElement(int data);

	void deleteElement(int data);

	void printHashTable();

	int firstHashFunction(int data);

	int secondHashFunction(int data);
	
	bool isFull();

	bool isNoElement();

private:
	vector<int>			m_vecHashTable;
	int					m_nCurrentSize;
};