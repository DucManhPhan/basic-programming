#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>


void makeTabulation(const std::vector<int>& arr, std::vector<int>& L, std::vector<int>& tracker)
{
	int leng = arr.size();
	for (int i = 1; i < leng; ++i)
	{
		int lmax = 0;
		int jmax = -1;

		for (int j = 0; j < i; ++j)
		{
			if (arr[j] < arr[i] && lmax < L[j])
			{
				lmax = L[j];
				jmax = j;
			}
		}

		L[i] = lmax + 1;
		tracker[i] = jmax;
	}
}


void printResult(const std::vector<int>& arr, std::vector<int>& L, const std::vector<int>& tracker)
{
    std::vector<int>::iterator it = std::max_element(L.begin(), L.end());
    int indexMaxElem = std::distance(L.begin(), it);

    std::cout << "The longest increase sequence of the above array is: " << "\n";

    int i = indexMaxElem; 
    int j = 0;
    while (true)
    {
        std::cout << arr[i];
        j = tracker[i];
        if (j == -1)
        {
            break;
        }

        i = j;
    }
}


int main() 
{
    std::vector<int> arr = {5, 1, 2, 3, 4, 9, 5, 6, 7, 8, 1};
    std::vector<int> L(arr.size(), 0);
    std::vector<int> tracker(arr.size(), -1);

    makeTabulation(arr, L, tracker);
    printResult(arr, L, tracker);

    system("pause");
    return 0;
}