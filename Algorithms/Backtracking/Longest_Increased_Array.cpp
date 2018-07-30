/* Find the longest increased subarray.
It can be not consecutive subarray.
*/

#include <iostream>
#include <vector>
#include <stack>
#include <list>
#include <queue>
#include <deque>
#include <iterator>
#include <algorithm>

using namespace std; 


// Get vectors that have increased subarray. 
void findIncreasedSubarrays(const vector<int>& vecOrigin, vector<vector<int>>& vecResults);


// recursive function for find increased subarray. 
void findSubarray(const vector<int>& vecOrigin, vector<vector<int>>& vecResults, vector<int>& vecTmp, int nIndexOfElement);


// get longest subarray. 
void getLongestSubarray(const vector<vector<int>>& vecResult, int& nIndexOfSubarray);


// print result. 
void printSubarray(const vector<int>& vec);


int main()
{
	vector<int> vecOrigin = { 1, 2, 8, 10, 5, 9, 3, 3, 6, 7 };
	vector<vector<int>> vecResults;
	int nIndex = -1;

	findIncreasedSubarrays(vecOrigin, vecResults);

	getLongestSubarray(vecResults, nIndex);

	if (nIndex != -1)
	{
		cout << "Found array is: " << "\n";
		printSubarray(vecResults[nIndex]);
	}
	else
	{
		cout << "No subarray is found in origin array.\n";
	}

	system("pause");
	return 0;
}


void findIncreasedSubarrays(const vector<int>& vecOrigin, vector<vector<int>>& vecResults)
{
	if (!vecOrigin.empty())
	{
		vector<int> vecTmp;
		int nIndex = 0;

		findSubarray(vecOrigin, vecResults, vecTmp, nIndex);
	}
}


void findSubarray(const vector<int>& vecOrigin, vector<vector<int>>& vecResults, vector<int>& vecTmp, int nIndexOfElement)
{
	static int nSize = vecOrigin.size();


	if (!vecTmp.empty())
	{
		vecTmp.push_back(vecOrigin[nIndexOfElement]);
	}
	else
	{
		if (vecTmp[vecTmp.size() - 1] < vecOrigin[nIndexOfElement])
		{
			vecTmp.push_back(vecOrigin[nIndexOfElement]);
		}
		else
		{
			if (vecTmp.size() >= 2)
			{
				vecResults.push_back(vecTmp);
			}
		}
	}

	for (int i = nIndexOfElement; i < nSize; ++i)
	{
		if (i == nIndexOfElement || vecOrigin[i] != vecOrigin[i - 1])
		{
			findSubarray(vecOrigin, vecResults, vecTmp, i + 1);
			/*if (!vecTmp.empty())
			{*/
			vecTmp.pop_back();
			//}
		}
	}
}


void getLongestSubarray(const vector<vector<int>>& vecResult, int& nIndexOfSubarray)
{
	vector<vector<int>>::const_iterator itBegin = vecResult.begin();
	vector<vector<int>>::const_iterator itEnd = vecResult.end();
	vector<vector<int>>::const_iterator it;

	it = max_element(vecResult.begin(), vecResult.end(), [](vector<int>& p, vector<int>& q){
		return p.size() < q.size();
	});

	if (it != itEnd)
	{
		nIndexOfSubarray = distance(it, itEnd) - 1; 
	}
	else
	{
		nIndexOfSubarray = -1;
	}
}


void printSubarray(const vector<int>& vec)
{
	for (auto i : vec)
	{
		cout << i << " ";
	}

	cout << "\n";
}