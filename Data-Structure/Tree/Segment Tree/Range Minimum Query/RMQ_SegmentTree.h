#pragma once

// construct the segment tree. 
int* constructSegTree(int arr[], int length);

void constructSegTreeUtil(int arr[], int start, int end, int* pSegTree, int indexOfSegTree);


// update value at the index of element. 
void updateSegTree(int* pSegTree, int arr[], int length, int indexOrigin, int value);

void updateSegTreeUtil(int* pSegTree, int start, int end, int indexOrigin, int value, int indexOfSegTree);


// query minimum element. 
int querySegTree(int* pSegTree, int length, int queStart, int queEnd);

int querySegTreeUtil(int* pSegTree, int start, int end, int queStart, int queEnd, int indexOfSegTree);


// update and query range elements. 
void updateRange(int* pSegTree, int* pLazyArr, int start, int end, int queStart, int queEnd, int diff, int indexOfSegTree);

int queryRange(int* pSegTree, int* pLazyArr, int start, int end, int queStart, int queEnd, int diff, int indexOfSegTree);


// some any implementation. 
int getIndexOfMid(int start, int end);

int getMaxSize(int length);

void printContent(int* pSegTree, int length);

int getMinElement(int a, int b);

void segmentTree();