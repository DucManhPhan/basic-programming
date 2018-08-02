#include "HashTable_Chaining.h"



#pragma region Constructors. 
CHashTableChaining::CHashTableChaining(int numberOfElem) : m_nSize(numberOfElem)
													     , m_vecHashTable(numberOfElem)
{
	// nothing to do
}
#pragma endregion


#pragma region Common Operations
int CHashTableChaining::hashFunction(int key)
{
	return key % m_nSize;
}


void CHashTableChaining::insertElement(int data)
{
	if (data < 0)
	{
		cout << "Data of this element is negative.\n";
		return;
	}

	int nIndex = hashFunction(data);
	m_vecHashTable[nIndex].push_back(data);
}


void CHashTableChaining::deleteElement(int data)
{
	if (data < 0)
	{
		cout << "Data of this element is negative.\n";
		return;
	}

	int nIndex = hashFunction(data);

	list<int>::iterator it;
	list<int>::iterator itBegin = m_vecHashTable[nIndex].begin();
	list<int>::iterator itEnd = m_vecHashTable[nIndex].end();

	for (it = itBegin; it != itEnd; ++it)
	{
		if (*it == data)
		{
			m_vecHashTable[nIndex].remove(*it);
			return;
		}
	}
}


int CHashTableChaining::getSize()
{
	return m_nSize;
}


void CHashTableChaining::printHashTable()
{
	for (int i = 0; i < m_nSize; ++i)
	{
		list<int>::iterator it;
		list<int>::iterator itBegin = m_vecHashTable[i].begin();
		list<int>::iterator itEnd = m_vecHashTable[i].end();

		for (it = itBegin; it != itEnd; ++it)
		{
			cout << *it << "   ";
		}

		cout << "\n";
	}
}
#pragma endregion