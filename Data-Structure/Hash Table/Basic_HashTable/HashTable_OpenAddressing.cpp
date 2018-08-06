#include "HashTable_OpenAddressing.h"



#pragma region Constructor
CHashTableOpenAddressing::CHashTableOpenAddressing(int size, int valueOfElement) : m_nCurrentSize(0)
, m_vecHashTable(size, valueOfElement)
{
	// nothing to do.
}
#pragma endregion


#pragma region Common operations
void CHashTableOpenAddressing::insertElement(int data)
{
	if (isFull())
	{
		return;
	}

	int nFirstIndex = firstHashFunction(data);

	if (m_vecHashTable[nFirstIndex] != -1)		// have collision.
	{
		int nSecondIndex = secondHashFunction(data);
		int i = 1; 

		for (; ; ++i)
		{
			int nNewIndex = firstHashFunction(nFirstIndex + i * nSecondIndex);
			if (m_vecHashTable[nNewIndex] == -1)		// no collision.
			{
				m_vecHashTable[nNewIndex] = data; 
				break;
			}
		}
	}
	else	// no collision.
	{
		m_vecHashTable[nFirstIndex] = data;
	}

	++m_nCurrentSize;
}


void CHashTableOpenAddressing::printHashTable()
{
	if (m_nCurrentSize == 0)
	{
		std::cout << "This hash table has no element.\n";
		return;
	}

	for (int i = 0; i < TABLESIZE; ++i)
	{
		if (m_vecHashTable[i] != -1)
		{
			std::cout << i << "-->" << m_vecHashTable[i] << "  ";
		}
		else
		{
			std::cout << i << "\n";
		}
	}

	std::cout << "\n";
}

int CHashTableOpenAddressing::firstHashFunction(int data)
{
	return data % TABLESIZE;
}

int CHashTableOpenAddressing::secondHashFunction(int data)
{
	return PRIME - (data % PRIME);
}


bool CHashTableOpenAddressing::isFull()
{
	return m_nCurrentSize == TABLESIZE;
}


bool CHashTableOpenAddressing::isNoElement()
{
	return m_nCurrentSize == 0;
}
#pragma endregion