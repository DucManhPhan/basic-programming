#pragma once

#include "pch.h"
#include "Node.h"


class CBtree {
public:
	CBtree(int degreeKeys);

	void traverse();

	CNode* search(int key);




private:
	CNode*          m_root;
	int             m_nMinimumDegreeKeys;
};