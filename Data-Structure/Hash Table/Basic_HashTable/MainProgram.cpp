// Hash Table with Chaining hasing and Open Addressing.

#include "HashTable_Chaining.h"
#include "HashTable_OpenAddressing.h"





int main()
{
	int a[] = { 15, 11, 27, 8, 12 };
	int nNumber = sizeof(a) / sizeof(a[0]);

	CHashTableChaining hstChaining(7);
				
	for (int i = 0; i < nNumber; i++)
	{
		hstChaining.insertElement(a[i]);
	}		

	hstChaining.printHashTable();

	system("pause");
	return 0;
}