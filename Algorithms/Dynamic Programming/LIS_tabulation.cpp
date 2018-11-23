#include <iostream>
#include <vector>
#include <algorithm>

void makeTabulation(const std::vector<int>& arr, std::vector<int>& L, std::vector<int>& tracker)
{
    int leng = arr.size();
    for (int i = 0; i < leng; ++i)
    {
        int lmax = 0; 
        int jmax = 0;

        for (int j = 0; j < i; ++j)
        {
            if (arr[j] < arr[j + 1] && lmax < L[j])
            {
                lmax = L[j];
                jmax = j;
            }
        }

        L[i] = lmax + 1; 
        tracker[i] = jmax; 
    }
}


void printResult(const std::vector<int>& arr, const std::vector<int>& L, const std::vector<int>& tracker)
{
    std::vector<int>::iterator it = std::max(arr.begin(), arr.end());
    int indexMaxElem = std::distance(arr.begin(), it);

    std::cout << "The longest increase sequence of the above array is: " << "\n";


}


int main() 
{
    std::vector<int> arr = {5, 2, 3, 4, 9, 5, 6, 7, 8, 1};
    std::vector<int> L(arr.size(), 0);
    std::vector<int> tracker(arr.size(), -1);




    system("pause");
    return 0;
}