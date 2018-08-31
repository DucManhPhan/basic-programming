# The reason why to use Binary Tree: 
  - Store information that naturally forms a hierarchy. 
  - Trees (with some ordering, ex: BST) provide moderate access / search (quicker than Linked List and slower than Arrays).
  - Trees provide moderate insertion / deletion (quicker than Arrays and slower than Unordered Linked List). 
  - Like Linked Lists and unlike Arrays, Trees don't have an upper limit on number of nodes when nodes are linked using pointers. 
 
 
# Types of Binary Trees:
  - Full Binary Tree 
  A binary tree is full if every node has 0 or 2 children. 
  In a full binary tree, number of leaf nodes is number of internal nodes plus 1.
  
  - Complete Binary Tree
  A binary tree is complete if all levels are completely filled except possibly the last level and the last level has all keys as left as possible. 
  
  - Perfect Binary Tree
  A binary tree is perfect in which all internal nodes have two children and all leaves are at same level.
  A perfect binary tree of height h has 2^h - 1 node.
  
  - Balanced Binary Tree
  A binary tree is balanced if height of the tree is O(logn) where n is the number of nodes. 
  Balanced Binary Trees have good performance as they provide O(logn) time to search, insert and delete. 


# The applications of Binary Trees
  - Heap is a tree data structure which is implemented using arrays and used to implement priority queues. 
  - B- Tree and B+ Tree are used to implement indexing in databases. 
  - Syntax Tree used in compilers. 
  - K-D Tree: A space partitioning tree used to organize points in K dimensional space. 
  - Trie used to implement dictionaries with prefix lookup.
  - Suffix Tree: For quick pattern searching in a fixed text. 
