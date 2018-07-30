#pragma once
#include "stdafx.h"

// Case 1. 
// create the array of Square Root Decomposition.
int* constructSRD(int arr[], int size, int& sizeSRD);

void constructSRDUtil(int* pLookup, int sizeSRD, int arr[], int sizeOrigin);


// update 
void update(int* pLookup, int sizeSRD, int arr[], int size, int indexOrigin, int value);


// sum query. 
int getSum(int* pLookup, int sizeSRD, int arr[], int size, int queStart, int queEnd);


// basic implementation 
void initializeSRD();

void printContentSRD(int* pSRD, int size);


// Case 2. 
// create the array of Square Root Decomposition.
int* constructSQRT_II(int arr[], int size);

void constructUtilSQRT_II(int* pLookup, int sizeSQRT, int arr[], int size);

// sum query. 
int querySum_II(int* pLookup, int arr[], int size, int l, int r);


// update query. 
void queryUpdate_II(int* pLookup, int arr[], int size, int i, int value);


// calculate the size of SQRT array. 
int calcSizeSQRT(int sizeOrigin);


// intialize SQRT.
void initializeSQRT_II();