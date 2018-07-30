#include "BST.h"

//extern node* root;

node* find(int item, node* tree)
{
	if (!tree)
	{
		return NULL;
	}

	if (item < tree->info)
	{
		find(item, tree->left);
	}
	else if (item > tree->info)
	{
		find(item, tree->right);
	}

	return tree;

}


void insert(node *& tree, node*& parentNode, node *newNode)
{
	if (tree == NULL)
	{
		if (parentNode != tree)
		{
			newNode->parentNode = parentNode;
		}
		else
		{
			newNode->parentNode = NULL;
		}

		newNode->left = NULL;
		newNode->right = NULL;

		tree = newNode;
	}
	else
	{
		if (newNode->info < tree->info)
		{
			insert(tree->left, tree, newNode);
		}
		else if (newNode->info > tree->info)
		{
			insert(tree->right, tree, newNode);
		}
	}
}


void del(int item, node* tree)
{
	if (tree)
	{
		if (item < tree->info)
		{
			del(item, tree->left);
		}
		else if (item > tree->info)
		{
			del(item, tree->right);
		}
		else
		{
			node* pFoundNode = tree;

			if (pFoundNode->left == NULL)
			{
				tree = tree->right;
			}
			else if (pFoundNode->right == NULL)
			{
				tree = tree->left;
			}
			else
			{
				GetPressdor(pFoundNode, tree->right);
			}

			delete pFoundNode;
		}
	}
}


void preorder(node* tree)
{
	if (tree)
	{
		std::cout << tree->info << " ";
		preorder(tree->left);
		preorder(tree->right);
	}
}


void inorder(node* tree)
{
	if (tree)
	{
		inorder(tree->left);
		std::cout << tree->info << " ";
		inorder(tree->right);
	}
}


void postorder(node* tree)
{
	if (tree)
	{
		postorder(tree->left);
		postorder(tree->right);
		std::cout << tree->info << " ";
	}
}


void display(node* tree)
{
	if (tree == NULL)
	{
		return;
	}

	node *pNode = tree;
	std::deque<node*> dqNode;
	while (pNode)
	{
		std::cout << pNode->info << " ";

		if (pNode->left)
		{
			dqNode.push_back(pNode->left);
		}

		if (pNode->right)
		{
			dqNode.push_back(pNode->right);
		}

		if (dqNode.size() != 0)
		{
			pNode = dqNode.front();
			dqNode.pop_front();
		}
		else
		{
			pNode = NULL;
		}
	}
}


int getHeight(node* tree)
{
	if (tree == NULL)
	{
		return -1;
	}

	return std::max(getHeight(tree->left), getHeight(tree->right)) + 1;
}

// Tim phan tu trai nhat cua cay con phai. 
void GetPressdor(node*& p, node*& q)
{
	if (q->left)
	{
		GetPressdor(p, q->left);
	}
	else
	{
		// Gan du lieu sang cho node p
		p->info = q->info;
		p = q;
		q = q->right;
	}
}

// Print the bottom view of tree. 
void bottomView(node* tree)
{
	// get map node in the tree. 
	std::map<int, node*> mpNode;
	getMapTree(tree, mpNode);

	// print the bottom view of the tree. 
	printBottomView(mpNode);
}

void getMapTree(node* tree, std::map<int, node*> &mpNode)
{
	if (!tree)
	{
		return; 
	}

	node* pNode = tree;
	int hd = 0;
	std::deque<node*> dqNode; 

	pNode->hd = hd;
	mpNode[hd] = pNode;

	while (pNode)
	{
		hd = pNode->hd; 

		if (pNode->left)
		{
			pNode->left->hd = hd - 1; 
			mpNode[hd - 1] = pNode->left;
			dqNode.push_back(pNode->left);
		}

		if (pNode->right)
		{
			pNode->right->hd = hd + 1; 
			mpNode[hd + 1] = pNode->right;
			dqNode.push_back(pNode->right);
		}

		if (dqNode.size() != 0)
		{
			pNode = dqNode.front();
			dqNode.pop_front();
		}
		else
		{
			pNode = NULL;
		}
	}
}

void printBottomView(std::map<int, node*> &mpNode)
{
	std::map<int, node*>::iterator it, it_begin = mpNode.begin(), it_end = mpNode.end(); 
	for (it = it_begin; it != it_end; ++it)
	{
		std::cout << it->second->info << " ";
	}
}
// End of bottom view of tree.


// Print Ancestor of specific node. 
void printAncestorOfNode(node* tree, int infoNode)
{

}
// End print ancestor of node.


// Print the top view of tree.
void printTopView(node* tree)
{
	if (tree)
	{
		std::map<int, node*> mpNode; 
		getMapNodeOfTopView(tree, mpNode);

		printNodesOfTopView(mpNode);
	}
}

void getMapNodeOfTopView(node* tree, std::map<int, node*>& mpNode)
{
	std::deque<node*> dqNode; 
	int hd = 0; 
	node *pNode = tree; 

	mpNode[hd] = tree;
	pNode->hd = hd; 

	while (pNode)
	{
		hd = pNode->hd;

		if (pNode->left)
		{
			pNode->left->hd = hd - 1; 

			if (!mpNode.count(hd - 1))
			{
				mpNode[hd - 1] = pNode->left;
			}
			
			dqNode.push_back(pNode->left);
		}

		if (pNode->right)
		{
			pNode->right->hd = hd + 1; 
			
			if (!mpNode.count(hd + 1))
			{
				mpNode[hd + 1] = pNode->right;
			}

			dqNode.push_back(pNode->right);
		}

		if (dqNode.size() != 0)
		{
			pNode = dqNode.front();
			dqNode.pop_front();
		}
		else
		{
			pNode = NULL;
		}
	}
}

void printNodesOfTopView(const std::map<int, node*>& mpNode)
{
	std::map<int, node*>::const_iterator it, it_begin = mpNode.cbegin(), it_end = mpNode.cend();
	for (it = it_begin; it != it_end; ++it)
	{
		std::cout << it->second->info << " ";
	}
}
// End of Top view.
