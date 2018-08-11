#pragma once
#include "stdafx.h"


struct CNode
{
	int		m_nData; 
	
	CNode*		m_pParent;
	CNode*		m_pLeft; 
	CNode*		m_pRight;

	CNode(int data) : m_nData(data), m_pParent(nullptr)
			, m_pRight(nullptr), m_pLeft(nullptr)
	{
		// nothing to do. 
	}

	~CNode()
	{
		m_pParent = nullptr; 
		m_pRight  = nullptr;
		m_pLeft   = nullptr;
	}
};


class CBinaryTree
{
public:
	// Constructor
	CBinaryTree();

	CNode*	makeNode(int data);

	// Common Operations
	void	insertElement(CNode* pNode);
	void	deleteElement(int data);	

	CNode*	getDeletedNode(int data, CNode*& pDeepestNode);

	bool	empty();
	
	void	printTree();
	void	printTreeUtils(CNode* pNode);

private:
	CNode*			m_pRoot;
	int			m_nCount;
};
