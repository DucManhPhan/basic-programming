#include <stdio.h>
#include <time.h>
#include <stdlib.h>


#define NUMBER 10

/*
- EH : balanced.
- LH : incline on left.
- RH : incline on right.
*/
enum factor{EH = 0, LH, RH};


typedef struct _Node
{
	int Data;
	int BalanceFactor;  

	struct _Node *pParent;  // save the parent node's address.
	struct _Node *pRight;  // right of the tree.
	struct _Node *pLeft;  // left of the tree.
}Node;

Node *pRoot;


// Functions.
// Initialize the AVL tree. 
Node* InitTree();

// Create the tree's node.
Node* CreateNode(int _data);

// max between two number. 
int MaxNumber(int a, int b);

// The height of the tree. 
int HeightOfTree(Node *pNode);

// Abs of the number. 
int Abs(int a, int b);

// Abs of the delta between two number.
int AbsDelta(Node *pNode);

// Connect pSourceNode into the left of pDestinationNode.
void LLConnect( Node *pSourceNode, Node *pDestinationNode);

// Connect pSourceNode into the right of pDestinationNode.
void RRConnect(Node *pSourceNode, Node *pDestinationNode);

// Rotate the left. 
void RotateLeft(Node *pChildNode, Node *pParentNode);

// Rotate the right. 
void RotateRight(Node *pChildNode, Node *pParentNode);

// Balance the tree.
void BalanceTree(Node *pNode);

// Insert pSourceNode into pDestinationNode of tree. 
void InsertNode(Node *pSourceNode, Node *pDestinationNode);

// Delete a node of the tree. 
void DeleteNode(Node *pRoot, Node *pNode);

// Sort all nodes in the tree.
void SortTree(Node *pRoot);

// Print tree. 
void NLR(Node *pNode);


int main()
{
	int i, _data;
	Node *pNode = NULL;

	pRoot = InitTree();

	srand( time( NULL ));
	for (i = 0; i < NUMBER; ++i)
	{
		int _random = rand()%10 + 1;
		Node *p = CreateNode(_random);

		InsertNode(p, pRoot);
	}

	NLR(pRoot);

	printf("\nDua ra gia tri ma ban can xoa: ");
	scanf_s("%d", &_data);

	pNode = CreateNode(_data);
	DeleteNode(pNode);



	return 0;
}


// Functions.
// Initialize the AVL tree. 
Node* InitTree()
{
	return NULL;
}


// Create the tree's node.
Node* CreateNode(int _data)
{
	Node *pNode = (Node*)malloc(sizeof(Node));

	pNode->Data = _data;
	pNode->BalanceFactor = EH;
	pNode->pLeft = NULL;
	pNode->pRight = NULL;
	pNode->pParent = NULL;

	return pNode;
}


// Connect pSourceNode into the left of pDestinationNode.
void LLConnect(Node *pSourceNode, Node *pDestinationNode)
{
	if (pSourceNode)
	{
		pSourceNode->pParent = pDestinationNode;
	}

	if (pDestinationNode)
	{
		pDestinationNode->pLeft = pSourceNode;
	}
	else
	{
		pRoot = pSourceNode;
	}
}


// Connect pSourceNode into the right of pDestinationNode.
void RRConnect(Node *pSourceNode, Node *pDestinationNode)
{
	if (pSourceNode)
	{
		pSourceNode->pParent = pDestinationNode;
	}

	if (pDestinationNode)
	{
		pDestinationNode->pRight = pSourceNode;
	}
	else
	{
		pRoot = pSourceNode;
	}
}


// Rotate the left. 
void RotateLeft(Node *pChildNode, Node *pParentNode)
{
	// Check whether the parent node is zero.
	if (!pParentNode)
	{
		LLConnect(pChildNode, pParentNode);
	}

	// rotate the left.
	Node *pParentParentNode = pParentNode->pParent;
	RRConnect(pChildNode->pLeft, pParentNode);
	LLConnect(pParentNode, pChildNode);

	// Get the child node to connect to parent of the parent node.
	if (!pParentParentNode)
	{
		LLConnect(pChildNode, pParentParentNode);
	}
	else
	{
		if (pParentParentNode->pLeft == pParentNode)
		{
			LLConnect(pChildNode, pParentParentNode);
		}
		else
		{
			RRConnect(pChildNode, pParentParentNode);
		}

	}
}


// Rotate the right. 
void RotateRight(Node *pChildNode, Node *pParentNode)
{
	// Check whether the parent node is zero.
	if (!pParentNode)
	{
		RRConnect(pChildNode, pParentNode);
	}

	// Get the child node to connect to parent of the parent node.
	Node *pParentParentNode = pParentNode->pParent;
	LLConnect(pChildNode->pRight, pParentNode);
	RRConnect(pParentNode, pChildNode);

	// Get the child node to connect to parent of the parent node.
	if (!pParentParentNode)
	{
		RRConnect(pChildNode, pParentNode);
	}
	else
	{
		if (pParentParentNode->pRight == pParentNode)
		{
			RRConnect(pChildNode, pParentParentNode);
		}
		else
		{
			LLConnect(pChildNode, pParentParentNode);
		}
	}
}


// max between two number. 
int MaxNumber(int a, int b)
{
	return (a > b) ? a : b;
}


// The height of the tree. 
int HeightOfTree(Node *pNode)
{
	if (!pNode)
	{
		return 0;
	}

	return MaxNumber(HeightOfTree(pNode->pLeft), HeightOfTree(pNode->pRight)) + 1;
}


// Abs of the number. 
int Abs(int a, int b)
{
	return (a > b) ? (a - b) : (b - a);
}

// Abs of the delta between two number.
int AbsDelta(Node *pNode)
{
	if (!pNode)
	{
		return 0;
	}

	return Abs(HeightOfTree(pNode->pLeft), HeightOfTree(pNode->pRight));
}

// Balance the tree.
void BalanceTree(Node *pNode)
{
	// Check whether the tree is null.
	if (!pNode)
	{
		return;
	}

	// Check whether the heigh of subtree's the left less than the height of subtree's right is 1
	if (AbsDelta(pNode) <= 1)
	{
		return; 
	}

	BalanceTree(pNode->pLeft);
	BalanceTree(pNode->pRight);

	if (HeightOfTree(pNode->pLeft) > HeightOfTree(pNode->pRight))
	{
		if (!pNode->pLeft)
		{
			return;
		}

		if (HeightOfTree(pNode->pLeft->pLeft) < HeightOfTree(pNode->pLeft->pRight)) // LR.
		{
			RotateLeft(pNode->pLeft->pRight, pNode->pLeft);
		}

		RotateRight(pNode->pLeft, pNode);
	}
	else if (HeightOfTree(pNode->pRight) > HeightOfTree(pNode->pLeft))
	{
		// Check whether pNode->pRight is NULL.
		if (!pNode->pRight)
		{
			return;
		}

		if (HeightOfTree(pNode->pRight->pRight) < HeightOfTree(pNode->pRight->pLeft)) // RL.
		{
			RotateRight(pNode->pRight->pLeft, pNode->pRight);
		}

		RotateLeft(pNode->pRight, pNode);
	}
}


// Insert pSourceNode into pDestinationNode of tree. 
void InsertNode(Node *pSourceNode, Node *pDestinationNode)
{
	// Check whether inserted pSourceNode is NULL.
	if (!pSourceNode)
	{
		return;
	}

	// Check whether pDestinationNode is NULL.
	if (!pDestinationNode)
	{
		LLConnect(pSourceNode, pDestinationNode);
	}


	if (pSourceNode->Data == pDestinationNode->Data)
	{
		printf("\nDu lieu da ton tai trong cay.\n");

		return;
	}
	else if (pSourceNode->Data < pDestinationNode->Data)
	{
		if (pDestinationNode->pLeft)
		{
			InsertNode(pSourceNode, pDestinationNode->pLeft);
		}
		else
		{
			LLConnect(pSourceNode, pDestinationNode);
		}
	}
	else if (pSourceNode->Data > pDestinationNode->Data)
	{
		if (pDestinationNode->pRight)
		{
			InsertNode(pSourceNode, pDestinationNode);
		}
		else 
		{
			RRConnect(pSourceNode, pDestinationNode);
		}
	}

	BalanceTree(pDestinationNode);
}

// Delete a node of the tree. 
void DeleteNode(Node *pRoot, Node *pNode)
{
	if (pNode == NULL)
	{
		printf("\nKhong ton tai node de xoa.\n");
		
		return;
	}

	if (pNode->Data > pRoot->Data)
	{
		DeleteNode(pRoot->pRight, pNode);
	}
	else if (pNode->Data < pRoot->Data)
	{
		DeleteNode(pRoot->pLeft, pNode);
	}
	else
	{
		if (pNode->pLeft == NULL && pNode->pRight == NULL)
		{
			delete pNode;
		}
		else if (pNode->pLeft == NULL)
		{
			pNode->pParent = pNode->pRight;
			delete pNode;
		}
		else if (pNode->pRight == NULL)
		{
			pNode->pParent = pNode->pLeft;
			delete pNode;
		}
		else
		{

		}

	}


}



// Print tree. 
void NLR(Node *pNode)
{
	if (pNode != NULL)
	{
		printf("%d", pNode->Data);
		NLR(pNode->pLeft);
		NLR(pNode->pRight);
	}
}
