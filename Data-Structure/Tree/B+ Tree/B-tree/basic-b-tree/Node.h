#pragma once
#include <vector>
#include <iostream>


class CNode {
public:
	CNode(int degreeKeys, bool leaf);

	void traverse();

	CNode* search(int key);

	//friend class CBtree;

private:
	std::vector<int>	    m_vtKeys;
	std::vector<CNode*>		m_vtChildNodes;

	int                     m_nCurrentNumberKeys;
	int                     m_nMinDegreeKeys;
	bool                    m_bLeaf;	
};