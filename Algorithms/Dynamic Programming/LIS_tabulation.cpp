#include <iostream>
#include <vector>


void makeTabulation(const std::vector<int>& arr, std::vector<int>& L, std::vector<int>& tracker)
{
    int leng = arr.size();
    for (int i = 0; i < leng; ++i)
    {
        int lmax = 0; 
        int jmax = 0;

        for (int j = 0; j < i; ++j)
        {
            if (arr[j] < arr[i] && lmax < L[j])
            {
                
            }
        }

        L[i] = lmax + 1; 
        tracker[i] = jmax; 
    }
}


int main() 
{
    std::vector<int> arr = {5, 2, 3, 4, 9, 5, 6, 7, 8};
    std::vector<int> L(arr.size(), 1);
    std::vector<int> tracker(arr.size(), -1);




    system("pause");
    return 0;
}