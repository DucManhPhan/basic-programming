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

	binaTree.printTree();
	std::cout << "\n";

	binaTree.deleteElement(11);

	binaTree.printTree();
	std::cout << "\n";

	system("pause");
	return 0;
}