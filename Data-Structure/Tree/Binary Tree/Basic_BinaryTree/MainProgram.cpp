#include "BinaryTree.h"


int main()
{
	int a[] = {10, 11, 9, 7, 15, 8};
	int size = sizeof(a) / sizeof(int);

	CBinaryTree binaTree;

	for (int i = 0; i < size; ++i)
	{
		CNode* pNode = binaTree.makeNode(a[i]);

		binaTree.insertElement(pNode);
	}


	system("pause");
	return 0;
}