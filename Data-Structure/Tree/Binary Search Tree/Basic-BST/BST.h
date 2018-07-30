#pragma once

#include <iostream>
#include <algorithm>
#include <deque>
#include <map>

class node
{
public:
	int info;
	int hd;  // horizontal distance.

	node *parentNode;
	node *left;
	node *right;

	node(int data) : parentNode(NULL), left(NULL), right(NULL), info(data)
	{
		// nothing to do.
	}

	node() : info(0), parentNode(NULL), left(NULL), right(NULL)
	{
		// nothing to do.
	}

	~node()
	{
		info = 0; 
		parentNode = NULL; 
		left = right = NULL;
	}
};

// Basic operation about the tree.
node* find(int item, node* tree);
void insert(node *& tree, node*& parentNode, node *newNode);
void del(int item, node* tree);
void GetPressdor(node*& p, node*& q);

// Traversal the tree. 
void preorder(node* tree);
void inorder(node* tree);
void postorder(node* tree);

void display(node* tree);
int getHeight(node* tree);

void bottomView(node* tree);
void getMapTree(node* tree, std::map<int, node*> &mpNode);
void printBottomView(std::map<int, node*> &mpNode);

void printAncestorOfNode(node* tree, int infoNode);

void printTopView(node* tree);
void getMapNodeOfTopView(node* tree, std::map<int, node*>& mpNode);
void printNodesOfTopView(const std::map<int, node*>& mpNode);

void printVerticalOrder(node* tree);
