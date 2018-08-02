#include "HashTable_OpenAddressing.h"



#pragma region Constructor
CHashTableOpenAddressing::CHashTableOpenAddressing(int size) : m_nSize(size)
															 , m_vecHashTable(size)
{
	// nothing to do.
}
#pragma endregion


#pragma region Common operations
void CHashTableOpenAddressing::insertElement(int data)
{	
	int nFirstIndex = firstHashFunction(data);	
	int nSecondIndex = -1;
	int nPrevFirstIndex = -1;
	int i = 0;

	for (; ; ++i)
	{
		if (nPrevFirstIndex == nFirstIndex)
		{
			nSecondIndex = secondHashFunction(data);

		}

		
	}
}


void CHashTableOpenAddressing::deleteElement(int data)
{

}


void CHashTableOpenAddressing::printHashTable()
{

}

int CHashTableOpenAddressing::firstHashFunction(int data)
{
	return data % m_nSize;
}


int CHashTableOpenAddressing::secondHashFunction(int data)
{
	return PRIME - (data % PRIME);
}
#pragma endregion