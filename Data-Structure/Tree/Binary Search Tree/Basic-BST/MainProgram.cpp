#include "BST.h"

node* root = NULL;

int main()
{
	int choice, num;
	node *temp;
	int a[] = {67, 55, 70, 46, 69, 90, 68, 85, 100};
	int n = sizeof(a) / sizeof(int);

	for (int i = 0; i < n; ++i)
	{
		node *temp = new node(a[i]);
		insert(root, root, temp);
	}

	while (1)
	{
		std::cout << "-----------------" << std::endl;
		std::cout << "Operations on BST" << std::endl;
		std::cout << "-----------------" << std::endl;

		std::cout << "1.Insert Element " << std::endl;
		std::cout << "2.Delete Element " << std::endl;
		std::cout << "3.Inorder Traversal" << std::endl;
		std::cout << "4.Preorder Traversal" << std::endl;
		std::cout << "5.Postorder Traversal" << std::endl;
		std::cout << "6.Display" << std::endl;
		std::cout << "7.Quit" << std::endl;
		std::cout << "8.Bottom view of tree" << std::endl;
		std::cout << "9.Ancestor of node" << std::endl;
		std::cout << "10.Left view of tree" << std::endl;
		std::cout << "11.Right view of tree" << std::endl;
		std::cout << "Vertical Order of tree" << std::endl;
		std::cout << "Top view of tree: " << std::endl;

		std::cout << "Enter your choice : ";

		std::cin >> choice;
		switch (choice)
		{
		case 1:
			temp = new node();
			std::cout << "Enter the number to be inserted: ";
			std::cin >> temp->info;

			insert(root, root, temp);
			break;

		case 2:
			if (root == NULL)
			{
				std::cout << "Tree is empty, nothing to delete." << std::endl;
				continue;
			}

			std::cout << "Enter the number to be deleted: ";
			std::cin >> num;
			del(num, root);
			break;

		case 3:
			std::cout << "Inorder Traversal of BST: " << std::endl;
			inorder(root);
			std::cout << std::endl;
			break;

		case 4:
			std::cout << "Preorder Traversal of BST: " << std::endl;
			preorder(root);
			std::cout << std::endl;
			break;

		case 5:
			std::cout << "Postorder Traversal of BST: " << std::endl;
			postorder(root);
			std::cout << std::endl;
			break;

		case 6:
			std::cout << "Display BST: " << std::endl;
			display(root);
			std::cout << std::endl;
			break;

		case 7:
			exit(1);
			break;

		case 8:
			std::cout << "Bottom view of tree: " << std::endl;
			bottomView(root);
			std::cout << std::endl;
			break;

		case 9: 
			{
				int tmp = 0;

				std::cout << "Enter the info of node: ";
				std::cin >> tmp;

				std::cout << "Ancestor of node: " << std::endl;
				printAncestorOfNode(root, tmp);
			}
			break;


		case 10:
			std::cout << "Left view of tree: " << std::endl;
			//printLeftView(root);
			break;

		case 11:
			std::cout << "Right view of tree: " << std::endl;
			//printRightView(root);
			break;

		case 12:
			std::cout << "Vertical Order of tree" << std::endl;

			break;

		case 13:
			std::cout << "Top view of tree: " << std::endl;
			printTopView(root);
			break;

		default:
			std::cout << "Wrong choice." << std::endl;
		}
	}

	return 0;
}