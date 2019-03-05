#pragma once
#include "pch.h"



class CNode {
public:
	CNode();



private:
	std::vector<int>			m_vtKeys;
	std::vector<CNode*>		m_vtNodes;


};