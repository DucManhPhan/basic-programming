#include "Node.h"


CNode::CNode(int degreeKeys, bool leaf) : m_nMinDegreeKeys(degreeKeys)
                                        , m_bLeaf(leaf)
	                                      , m_nCurrentNumberKeys(0)
	                                      , m_vtKeys(m_nCurrentNumberKeys)
	                                      , m_vtChildNodes(0)
{
	// nothing to do
}


CNode* CNode::search(int key)
{

}


void CNode::traverse()
{

}