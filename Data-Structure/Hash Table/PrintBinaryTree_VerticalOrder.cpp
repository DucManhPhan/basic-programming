#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>
#include <list>


#pragma region Hash Table
class CHashTable
{
public:
	CHashTable(int size) : m_HashTable(size), m_nSize(size)
	{
		// nothing to do. 
	}	

	int HashFunction(int value)
	{
		return value % m_nSize;
	}

	void insertElement(int data)
	{
		int index = HashFunction(data);
		
		m_HashTable[index].push_back(data);
	}

private:
	std::vector<std::list<int>>			m_HashTable;
	int									m_nSize; 
};

#pragma endregion



struct CNode
{
	int			m_nData; 
	CNode*		m_pLeft; 
	CNode*		m_pRight; 

	CNode(int data) : m_nData(data), m_pLeft(nullptr), m_pRight(nullptr)
	{
		// nothing to do. 
	}

	~CNode()
	{
		m_pLeft		= nullptr; 
		m_pRight	= nullptr;
	}
};


class CBinaryTree
{
public:
	// Constructor
	CBinaryTree() : m_pRoot(nullptr), m_nSize(0)
	{
		// nothing to do. 
	}

	// Common Operation
	void insertElement(int data)
	{
		
	}

	void deleteElement(int data);

	void printVerticalOrder();

private: 
	CNode*		m_pRoot; 
	int			m_nSize; 
};


int main()
{


	system("pause");
	return 0;
}



