#include <iostream>
#include <chrono>


#define XSIZE 20

#define OUTPUT(x) std::cout << "Vi tri bat dau chuoi la: " << x << "\n";
		

void preMp(char *x, int m, int mpNext[]) {
	int i, j;

	i = 0;
	j = mpNext[0] = -1;
	while (i < m) {
		while (j > -1 && x[i] != x[j])
			j = mpNext[j];
		mpNext[++i] = ++j;
	}
}


void MP(char *x, int m, char *y, int n) {
	int i, j, mpNext[XSIZE];

	/* Preprocessing */
	preMp(x, m, mpNext);

	/* Searching */
	i = j = 0;
	while (j < n) {
		while (i > -1 && x[i] != y[j])
			i = mpNext[i];
		i++;
		j++;
		if (i >= m) {
			OUTPUT(j - i);
			i = mpNext[i];
		}
	}
}


int main(int argc, char **argv)
{
	std::chrono::high_resolution_clock::time_point t1 = std::chrono::high_resolution_clock::now();
	/*using clock = std::chrono::steady_clock;
	clock::time_point start = clock::now();*/
	MP("string", 6, "This is strin a string", 22);
	//clock::time_point end = clock::now();
	std::chrono::high_resolution_clock::time_point t2 = std::chrono::high_resolution_clock::now();
	
	auto duration = std::chrono::duration_cast<std::chrono::microseconds>(t2 - t1).count();
	std::cout << "Thoi gian thuc hien ham tren la: " << duration << "\n";

	/*clock::duration execution_time = end - start;
	auto duration = std::chrono::duration_cast<std::chrono::microseconds>(execution_time).count();
	std::cout << "Thoi gian thuc hien ham tren la: " << duration << "\n";*/

	system("pause");
	return 0;
}
