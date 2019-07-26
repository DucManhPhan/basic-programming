#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>

void printResult(std::vector<int>& vt)
{
    std::copy(vt.begin(), vt.end(), std::ostream_iterator<int>(std::cout, " "));
}


int main()
{
    // int arr[] = {1, 3, 6, 5, 7, 9};
    // std::vector<int> vt(arr, arr + 3);

    // printResult(vt);

    std::vector<int> tmp = {1, 3, 6, 5, 7, 9};
    int start = 0;
    int end = 4;
    std::vector<int> vtRes(tmp.begin() + start, tmp.begin() + end);

    printResult(vtRes);

    system("pause");
    return 0;
}