#include "Btree.h"


CBtree::CBtree(int degreeKeys)
{
	m_root = nullptr;
	m_nMinimumDegreeKeys = degreeKeys;
}


void CBtree::traverse()
{
	if (m_root)
	{
		m_root->traverse();
	}
}


CNode* CBtree::search(int key)
{
	return (m_root == nullptr) ? nullptr : m_root->search(key);
}

