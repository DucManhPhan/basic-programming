# Denifition fo Threaded binary tree. 
  - A binary tree is threaded by making all right child pointers that would normally be a null point to the inorder successor of the node (if it exists), 
  and all left child pointers that would normally be null point to the inorder predessor of the node. 
  
  
# Types of Threaded Binary tree:
  - Single Threaded
  Each node is threaded towards either the inorder predecessor or successor (left or right) means all right child null pointer will point to 
  inorder successor or all left null pointers will point to inorder predecessor. 
  
  - Double Threaded 
  Each node is threaded towards both the inorder predecessor and successor (left and right) means all right null pointers will point to 
  the inorder successor and all left null pointers will point to the inorder predecessor. 

  
# The reason why to choose Threaded Binary Tree:
  - Binary trees have a lot of wasted space: the leaf nodes each have 2 null pointers. We can use these pointers to improve the speed of 
  inorder traversal. 
  - Threaded binary tree makes the tree traversal faster since we do not need stack or recursion of traversal. 

