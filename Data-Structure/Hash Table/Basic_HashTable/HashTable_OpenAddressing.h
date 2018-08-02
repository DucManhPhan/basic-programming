#pragma once
#include "stdafx.h"

#define PRIME	7

class CHashTableOpenAddressing
{
public:
	// Constructor
	CHashTableOpenAddressing(int size);

	// Common operations
	void insertElement(int data);

	void deleteElement(int data);

	void printHashTable();

	int firstHashFunction(int data);

	int secondHashFunction(int data);
	

private:
	vector<int>			m_vecHashTable;
	int					m_nSize;
};