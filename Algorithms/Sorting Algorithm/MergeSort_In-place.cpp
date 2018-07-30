// ManhPD5 - Start
//#include <iostream>
//#include <algorithm>
//
//void Merge(int a[], int start, int mid, int end);
//void MergeSort(int a[], int start, int end);
//void PrintArray(int a[], int num);
//
//int main()
//{
//	int a[] = { 0, 2, 2, 2, 3, 4, 5, 7, 8, 5, 3, 1, 3, 5, 6 };
//	int num = sizeof(a) / sizeof(int);
//
//	std::cout << "Mang chua sap xep: ";
//	PrintArray(a, num);
//
//	MergeSort(a, 0, num - 1);
//
//	std::cout << "Mang sau khi duoc sap xep: ";
//	PrintArray(a, num);
//
//	system("pause");
//	return 0;
//}
//
//void Merge(int a[], int start, int mid, int end)
//{
//	int i = start; 
//	int j = mid + 1;
//
//	int k = 0;
//
//	int temp[100];
//
//	while (i <= mid && j <= end)
//	{
//		if (a[i] > a[j])
//		{
//			temp[k] = a[j];
//			++j;
//		}
//		else
//		{
//			temp[k] = a[i];
//			++i;
//		}
//
//		++k;
//	}
//
//	while (j <= end)
//	{
//		temp[k] = a[j];
//		++j;
//		++k;
//	}
//
//	while (i <= mid)
//	{
//		temp[k] = a[i];
//		++k;
//		++i;
//	}
//
//	for (int i = start, j = 0; i < end + 1; ++i, ++j)
//	{
//		a[i] = temp[j];
//	}
//}
//
//
//void MergeSort(int a[], int start, int end)
//{
//	if (start < end)
//	{
//		int mid = (start + end) / 2;
//
//		MergeSort(a, start, mid);
//		MergeSort(a, mid + 1, end);
//		Merge(a, start, mid, end);	
//	}
//}
//
//
//void PrintArray(int a[], int num)
//{
//	for (int i = 0; i < num; ++i)
//	{
//		std::cout << a[i] << "  ";
//	}
//
//	std::cout << "\n";
//}
// ManhPD5 - End

#include <iostream>

void Merge(int a[], int start, int end)
{
	int mid = (start + end) / 2;
	int i = start; 
	int j = mid + 1;
	int temp; 

	while (i <= j && j <= end)
	{
		if (a[i] > a[j])
		{
			temp = a[j];
			for (int k = j; k > i; --k)
			{
				a[k] = a[k - 1];
			}

			a[i] = temp;
			++j;
		}

		++i;
	}
}


void Merger_Sort(int a[], int start, int end)
{
	if (end - start < 1)
	{
		return;
	}

	int mid = (start + end) / 2;
	Merger_Sort(a, start, mid);
	Merger_Sort(a, mid + 1, end);
	Merge(a, start, end);
}


void PrintArray(int a[], int num)
{
	for (int i = 0; i < num; ++i)
	{
		std::cout << a[i] << "  ";
	}

	std::cout << "\n";
}

int main()
{
	int a[] = { 0, 2, 2, 2, 3, 4, 5, 7, 8, 5, 3, 1, 3, 5, 6 };
    int n = sizeof ( a ) / sizeof ( int );
    std::cout << n << std::endl;
    Merger_Sort( a, 0, n - 1 );
	
	PrintArray(a, n);

	system("pause");
	return 0;
}