#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


void printEachResult(const vector<int>& vec)
{
	for (int i = 0; i < vec.size(); i++)
	{
		cout << vec[i] << " ";
	}
	
	cout << "\n";
}


void subSet_Solved(const vector<int>& a, int begin, vector<int>& vecTmp, vector<vector<int>>& vec2Dimension)
{
	if (begin <= a.size())
	{
		vec2Dimension.push_back(vecTmp);
		printEachResult(vecTmp);

		//return;
	}

	for (int i = begin; i < a.size(); ++i)
	{
		vecTmp.push_back(a[i]);

		subSet_Solved(a, i + 1, vecTmp, vec2Dimension);

		vecTmp.pop_back();
	}
}


int main()
{
	vector<int> a = {11, 16, 15, 5};//{ 1, 2, 3 };
	vector<int> vecTmp;
	vector<vector<int>> vec2Dimension;

	sort(a.begin(), a.end());

	subSet_Solved(a, 0, vecTmp, vec2Dimension);

	system("pause");
	return 0;
}
