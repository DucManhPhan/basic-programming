#include <iostream>
#include <algorithm>
#include <vector>

struct CNode
{
	int m_nData;
	int m_nMax;			// the max number of increased elements till this element.
	CNode* m_pLeft; 
	CNode* m_pRight;

	CNode(int _data, int max) : m_nData(_data), m_nMax(max), m_pLeft(nullptr), m_pRight(nullptr)
	{
		// nothing to do.
	}
};

typedef CNode* pBST;
int maxNumberElement = 0;

CNode* makeNode(int _data, int max)
{
	return new CNode(_data, max);
}

void push(int _data, CNode*& pNode)
{
	if (!pNode) 
	{
		pNode = makeNode(_data, maxNumberElement);
		return;
	}

	if (_data >= pNode->m_nData)
	{
		if (maxNumberElement < pNode->m_nMax + 1)
		{
			maxNumberElement = pNode->m_nMax + 1;
		}
		push(_data, pNode->m_pRight);
	}
	else if (_data < pNode->m_nData)
	{
		push(_data, pNode->m_pLeft);
		if (maxNumberElement > pNode->m_nMax)
		{
			pNode->m_nMax = maxNumberElement;
		}
	}
}


int main()
{
	std::vector<int> vec = {50, 3, 10, 7, 40, 80}; //{5, 2, 3, 4, 9, 10, 5, 6, 7, 8};
	
	pBST pTree = nullptr; 
	int size = vec.size();
	for (int i = 0; i < size; ++i)
	{
		maxNumberElement = 1;
		push(vec[i], pTree);
	}

	std::cout << "The max is: " << maxNumberElement;

	system("pause");
	return 0;
}