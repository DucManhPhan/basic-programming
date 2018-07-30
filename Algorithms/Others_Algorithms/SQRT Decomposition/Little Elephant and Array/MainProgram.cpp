#include "stdafx.h"
#include "SQRT_LittleElephant.h"
#include "MO_LittleElephant.h"




int main() {
	int n = 17; //7;//17;
	int m = 6;

	int a[] = { 2, 1, 5, 6, 3, 2, 5, 5, 6, 3, 6, 6, 3, 5, 5, 6, 6 }; //{3, 1, 2, 2, 3, 3, 7}; //

	// construct SQRT.
	cons(n, a);

	while (m--) {
		int x, b;

		cout << "Input the start, end of subarray: ";
		cin >> x >> b;
		cout << "\n";

		int ans = 0;
		query(x, b, n, a, ans);
		cout << ans << endl;
	}

	system("pause");
	return 0;
}
