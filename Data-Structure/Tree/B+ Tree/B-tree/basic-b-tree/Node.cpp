#include "Node.h"


CNode::CNode(int degreeKeys, bool leaf) : m_nMinDegreeKeys(degreeKeys)
                                        , m_bLeaf(leaf)
	                                    , m_nCurrentNumberKeys(0)
	                                    , m_vtKeys(2 * m_nMinDegreeKeys - 1)
	                                    , m_vtChildNodes(2 * m_nMinDegreeKeys)
{
	// nothing to do
}


CNode* CNode::search(int key)
{
	int i = 0;

	while (i < m_nCurrentNumberKeys && key > m_vtKeys[i])
	{
		++i;
	}

	if (m_vtKeys[i] == key)
	{
		return this;
	}

	if (m_bLeaf)
	{
		return nullptr;
	}

	return m_vtChildNodes[i]->search(key);
}


void CNode::traverse()
{
	int i = 0;

	// Because the number of children of a node is equal to the number of keys in it plus 1
	// num_of_child_nodes = num_of_current_number_keys + 1
	for (i = 0; i < m_nCurrentNumberKeys; ++i)
	{
		if (!m_bLeaf)
		{
			m_vtChildNodes[i]->traverse();
		}

		std::cout << " " << m_vtKeys[i];
	}

	// the last child node
	if (!m_bLeaf)
	{
		m_vtChildNodes[i]->traverse();
	}
}