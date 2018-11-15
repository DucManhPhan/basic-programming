#include "Basic-Trie.h"



int main()
{
	std::string str1 = "Abc";
	std::transform(str1.begin(), str1.end(), str1.begin(), ::tolower);

	Trie trie;
	trie.insert(str1);

	system("pause");
	return 0;
}
