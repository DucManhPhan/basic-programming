#pragma once
#include "stdafx.h"

#define SIZE		30

struct SQRTDecompostionStruct
{
	int ct[SIZE];
	int mini;
	int maxi;
};

SQRTDecompostionStruct block[SIZE];


void cons(int n, int a[]);

void query(int l, int r, int n, int a[], int &ans);