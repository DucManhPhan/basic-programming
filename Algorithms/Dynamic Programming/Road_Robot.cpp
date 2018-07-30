// Use dynamic programming method.

#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

#define LOWERBOUND INT_MIN 

// global variables.
int rows = 4;
int columns = 6;

int s_best = 0;
int i_best = 0;

// Bottom up. 
void RoadRobot_BottomUp(std::vector<std::vector<int>> vec);

// Top down.
void RoadRobot_TopDown(const std::vector<std::vector<int>> &vec);

// Calculate the optimize road with top down.
void Calculate_TopDown(const std::vector<std::vector<int>> &vec, std::vector<std::vector<int>>& s, std::vector<std::vector<int>>& l);

// Print the optimization road. 
void PrintOptimizeRoad(std::vector<std::vector<int>> s, std::vector<std::vector<int>> l);

// Calculate the optimize road with bottom up.
void Calculate_BottomUp(std::vector<std::vector<int>> vec, std::vector<std::vector<int>>& s, std::vector<std::vector<int>>& l);

// compute the optimized road. 
int computingOptimizedRoad(int row, int column, const std::vector<std::vector<int>> &vec, std::vector<std::vector<int>>& s, std::vector<std::vector<int>>& l);


int main()
{
	std::vector<std::vector<int>> vecCoin = { {4, 9, 12, 8, 1, 5},
								 {5, 8, 3, 7, 5, 4},
								 {3, 2, 8, 2, 10, 6},
								 {2, 5, 5, 9, 6, 4} };

	RoadRobot_BottomUp(vecCoin);
	
	//RoadRobot_TopDown(vecCoin);

	system("pause");
	return 0;
}


void RoadRobot_BottomUp(std::vector<std::vector<int>> vec)
{
	std::vector<std::vector<int>> s(rows, std::vector<int>(columns));	// the optimize road. 
	std::vector<std::vector<int>> l(rows, std::vector<int>(columns));	// the saved optimize rows.

	// calculate the optimize road. 
	Calculate_BottomUp(vec, s, l);

	// Print the optimize road.
	PrintOptimizeRoad(s, l);
}



void RoadRobot_TopDown(const std::vector<std::vector<int>> &vec)
{
	std::vector<std::vector<int>> s(rows, std::vector<int>(columns, LOWERBOUND));	// the optimize road. 
	std::vector<std::vector<int>> l(rows, std::vector<int>(columns, LOWERBOUND));	// the saved optimize rows.

	// calculate the optimize road. 
	Calculate_TopDown(vec, s, l);

	// Print the optimize road.
	PrintOptimizeRoad(s, l);
}


// Calculate the optimize road with top down.
void Calculate_TopDown(const std::vector<std::vector<int>> &vec, std::vector<std::vector<int>>& s, std::vector<std::vector<int>>& l)
{
	// initialize for the first column of vector that contains result s. 
	for (int i = 0; i < rows; ++i)
	{
		s[i][0] = vec[i][0];
	}

	// compute the array of optimized road for robot. 
	for (int i = 0; i < rows; ++i)
	{
		computingOptimizedRoad(i, columns - 1, vec, s, l);
	}

	// compute the value that is biggest in the s array. 
	s_best = s[0][columns - 1];
	i_best = 0;

	for (int i = rows - 1; i >= 0; --i)
	{
		if (s_best < s[i][columns - 1])
		{
			s_best = s[i][columns - 1];
			i_best = i;
		}
	}
}


int computingOptimizedRoad(int row, int column, const std::vector<std::vector<int>> &vec, std::vector<std::vector<int>>& s, std::vector<std::vector<int>>& l)
{
	if (s[row][column] == LOWERBOUND )
	{
		for (int i = row - 1; i <= row + 1; ++i)
		{
			if (i >= 0 && i < rows)
			{
				int nComputedLastValue = computingOptimizedRoad(i, column - 1, vec, s, l);

				if (s[row][column] < nComputedLastValue + vec[row][column])
				{
					s[row][column] = nComputedLastValue + vec[row][column];
					l[row][column] = i;
				}
			}
		}
	}

	return s[row][column];
}


void PrintOptimizeRoad(std::vector<std::vector<int>> s, std::vector<std::vector<int>> l)
{
	for (int j = columns - 1; j >= 0; --j)
	{
		std::cout << "(" << i_best << ", " << j << ")\n";
		i_best = l[i_best][j];
	}
}


void Calculate_BottomUp(std::vector<std::vector<int>> vec, std::vector<std::vector<int>>& s, std::vector<std::vector<int>>& l)
{
	int i, j; // index of array: rows & columns.

	// Initialization for two dimensional vector s with the first column of two dimensional vector vec.
	for (i = 0; i < rows; ++i)
	{
		s[i][0] = vec[i][0];
	}

	for (j = 1; j < columns; ++j)
	{
		for (i = 0; i < rows; ++i)
		{
			s[i][j] = -200;
			for (int k = i - 1; k < i + 2; ++k)
			{
				if (k < 0 || k >= rows)
				{
					continue;
				}
				else
				{
					if (s[i][j] < s[k][j - 1] + vec[i][j])
					{
						s[i][j] = s[k][j - 1] + vec[i][j];
						l[i][j] = k;
					}
				}
			}
		}
	}

	s_best = s[0][columns - 1];
	i_best = 0;

	for (i = 1; i < rows; ++i)
	{
		if (s_best < s[i][columns - 1])
		{
			s_best = s[i][columns - 1];
			i_best = i;
		}
	}
}