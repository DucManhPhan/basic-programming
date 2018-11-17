#include "Basic-Trie.h"



int main()
{
	std::string str1 = "Abc";
	std::transform(str1.begin(), str1.end(), str1.begin(), ::tolower);

	Trie trie;
	trie.insert("apple");
	bool isRight = trie.search("apple");   // returns true

	isRight = trie.search("app");     // returns false

	isRight = trie.startsWith("app"); // returns true

	trie.insert("app");

	isRight = trie.search("app");     // returns true

	system("pause");
	return 0;
}
