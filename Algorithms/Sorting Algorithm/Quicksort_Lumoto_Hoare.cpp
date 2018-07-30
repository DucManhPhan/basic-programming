#include <iostream>


void swap(int &a, int &b);
void PrintArray(int a[], int num);
void QuickSort(int a[], int left, int right);
int  partition(int a[], int left, int right);


int main()
{
	int a[] = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }; //{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}; //{1, 2, 3, 4, 5, 6, 7, 8, 9};//{3, 5, 33, 1, 8, 12, 4, 23, 8}; //{9, 12, 5, 6, 4, 3, 15, 13, 8, 2, 1};
	int n = sizeof(a) / sizeof(int);

	std::cout << "Mang chua duoc sap xep la: \n";
	PrintArray(a, n);

	QuickSort(a, 0, n - 1);

	std::cout << "Mang duoc sap xep la: \n";
	PrintArray(a, n);
	
	system("pause");
	return 0;
}


void swap(int &a, int &b)
{
	int temp = a;
	a = b;
	b = temp;
}

//int partition(int a[], int left, int right)
//{
//	int i = left, j = right;
//	int mid = (left + right) / 2;
//	int pivot = a[mid];
//
//	while (i <= j)
//	{
//		while (a[i] < pivot) ++i;
//
//		while (a[j] > pivot) --j;
//
//		if (i <= j)
//		{
//			if (i < j)
//				swap(a[i], a[j]);
//
//			++i; 
//			--j;
//		}
//	}
//	
//	return i - 1;
//}

// Phan tich truong hop chi co 1 dau = trong dk if.
//int partition(int a[], int left, int right)
//{
//	int i = left, j = right; 
//	int pivot = a[(left + right) / 2];
//
//	while (i < j)
//	{
//		while (a[i] < pivot) ++i;
//		while (a[j] > pivot) --j;
//		
//		if (i <= j)
//		{
//			if (i < j)
//			{
//				swap(a[i], a[j]);
//
//			}
//			++i;
//			--j;
//		}
//		
//	}
//
//	return i - 1;
//}

// Hoare partition.
int partition(int a[], int left, int right)
{
	// chon pivot tai phan tu dau tien. 
	/*int i = left - 1, j = right + 1; 
	int pivot = a[left];*/

	// chon pivot tai phan tu giua.
	int i = left - 1; 
	int j = right + 1; 
	int pivot = a[(left + right) / 2];

	while (1)
	{
		do {
			++i;
		} while (a[i] < pivot);

		do {
			--j;
		} while (a[j] > pivot);

		if (i >= j)
			return j;

		swap(a[i], a[j]);
	}
}

// Lumoto partition: nen can than voi cac tham so trong ham QuickSort() -> de gay ra stack overflow.
//int partition(int a[], int left, int right)
//{
//	int i = left - 1;
//	int pivot = a[right];
//
//	for (int j = left; j < right; ++j)
//	{
//		if (a[j] <= pivot)
//		{
//			++i;
//			swap(a[i], a[j]);
//		}
//	}
//
//	swap(a[i + 1], a[right]);
//
//	return i + 1;
//}

void PrintArray(int a[], int num)
{
	for (int i = 0; i < num; ++i)
	{
		std::cout << a[i] << " ";
	}

	std::cout << "\n";
}

void QuickSort(int a[], int left, int right)
{
	if (left < right)
	{
		int mid = partition(a, left, right);
		QuickSort(a, left, mid);
		QuickSort(a, mid + 1, right);
	}
}