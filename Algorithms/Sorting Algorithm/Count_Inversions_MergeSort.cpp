#include <iostream>
#include <vector>
#include <iterator>

long long numInversions = 0;

void merge(std::vector<int>& a, int p, int q, int r){
    int i = 0, j = 0, k = 0, num_left_elements_remaining = 0;
    
    int nl = q - p + 1;
    int *left = new int[nl]; //p to q
    
    int nr = r - q;	//r - (q+1) + 1
    int *right = new int[nr];    //q+1 to r

    //copy left array
    for(i = p, j = 0; i <= q; i++, j++){
        left[j] = a[i];
    }
    
    //copy right array
    for(i = q + 1, j = 0; i <= r; i++, j++){
        right[j] = a[i];
    }
    
    i = 0; j = 0;
    k = p;
    while(i < nl && j < nr){
        if(left[i] <= right[j]){
            a[k++] = left[i++];
        }
        else{
            a[k++] = right[j++];
	        num_left_elements_remaining = nl - i;	//number of elements remaining in the left array is the number of inversions related to element right[j]
            numInversions += num_left_elements_remaining;
        }
    }

    while(i < nl){
        a[k++] = left[i++];
    }

    while(j < nr){
        a[k++] = right[j++];
    }
    
    delete[] left;
    delete[] right;
}

void MergeSort(std::vector<int>& a, int p, int r){
    if(p < r){
        int q = (p + r)/2;
        MergeSort(a, p, q);
        MergeSort(a, q + 1, r);
        merge(a, p, q, r);
    }
}

long long count_inversions(std::vector<int>& a) {
	int nNumber = a.size() - 1;
	MergeSort(a, 0, nNumber);
    
    return numInversions;
}

int main()
{
	int temp[] = {2, 1, 3, 1, 2};
	std::vector<int> a;
	a.assign(temp, temp + 5);

	long long nTemp = count_inversions(a);

	std::cout << nTemp << std::endl;

	std::copy(a.begin(), a.end(), std::ostream_iterator<int>(std::cout, "  "));

	system("pause");
	return 0;
}
