#pragma once
#include "stdafx.h"



class CHashTableChaining
{
public:
	// Constructors. 
	CHashTableChaining(int numberOfElem);

	// Common Operations
	void insertElement(int data);

	void deleteElement(int data);

	int getSize();

	int hashFunction(int key);

	void printHashTable();

private:
	vector<list<int>>		m_vecHashTable;
	int				m_nSize; 
};
