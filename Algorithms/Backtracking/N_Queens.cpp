#include <iostream>
#include <vector>
#include <list>

int tableChess[8][8], lineDiagUp[15], lineDiagDown[15], rows[8];
int nCount = 0;

void printResults()
{
	for (int i = 0; i < 8; ++i)
	{
		for (int j = 0; j < 8; ++j)
		{
			std::cout << tableChess[i][j] << " ";
		}

		std::cout << "\n";
	}

	std::cout << "\n";
}


void putQueens(int nCol)
{
	for (int iRow = 0; iRow < 8; ++iRow)
	{
		if (rows[iRow] == 0 && lineDiagUp[iRow + nCol]		 == 0 
							&& lineDiagDown[iRow - nCol + 7] == 0)
		{
			// put the queen at this position (iRow, nCol).
			tableChess[iRow][nCol] = 1;

			// Save the unsafety zones.
			rows[iRow] = 1;
			lineDiagUp[iRow + nCol] = 1;
			lineDiagDown[iRow - nCol + 7] = 1;

			if (nCol == 7)
			{
				++nCount;
				printResults();
			}
			else
			{
				putQueens(nCol + 1);
			}

			// Delete state for new solution.
			tableChess[iRow][nCol] = 0;
			rows[iRow] = 0;
			lineDiagUp[iRow + nCol] = 0;
			lineDiagDown[iRow - nCol + 7] = 0;

		}
	}
}





int main()
{
	memset(tableChess, 0, sizeof(tableChess[0][0] * 8 * 8));
	memset(rows, 0, sizeof(rows));
	memset(lineDiagDown, 0, sizeof(lineDiagDown));
	memset(lineDiagUp, 0, sizeof(lineDiagUp));

	putQueens(0);
	std::cout << "Co tat ca " << nCount << " ket qua!\n";

	system("pause");
	return 0;
}