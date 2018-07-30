// Cho n so nguyen duong tao thanh day A = {A1, A2, ..., An}.
// Tim mot day con cua day A co tong cac phan tu bang so nguyen S cho truoc.

#include <iostream>
#include <vector>
#include <algorithm>

const int maxs = 40000;

void printSubarray(const std::vector<int>& a, const std::vector<int>& l,int s) {
	int i = 0;
	while (s > 0) {
		i = l[s];

		if (i > -1)
			std::cout << i << " " << a[i] << "\n";

		s = s - a[i];
	}
}

void subArray_sum_S(std::vector<int>& a, std::vector<int>& l, int s)
{
	int a_size = a.size();
	int max, maxtong, st;  // st - the iterator variable for maxtong downto 0.

	// initialize the first element of vector l.
	l[0] = 1;

	// initialize the maxtong variable.
	maxtong = 0;

	for (int i = 0; i < a_size; ++i)
	{
		max = maxtong;

		for (st = maxtong; st >= 0; --st)
		{
			if (st + a[i] > s)
				continue;

			if (l[st] != -1 && l[st + a[i]] == -1)
			{
				l[st + a[i]] = i;

				if (st + a[i] == s)
					break;

				if (max < st + a[i])
					max = st + a[i];
			}
		}

		maxtong = max;
	}
}



int main()
{
	std::vector<int> a = { 5, 4, 5, 8, 1, 3, 6, 9 };
	std::vector<int> l(maxs, -1);
	int s = 20;

	subArray_sum_S(a, l, s);

	printSubarray(a, l, s);

	system("pause");
	return 0;
}