#include "Basic-Trie.h"



int main()
{
	/*std::string str1 = "Abc";
	std::transform(str1.begin(), str1.end(), str1.begin(), ::tolower);*/

	// Initialize data for Trie 
	Trie trie;

	std::string keys[] = {
		"the", "a", "there", "answer", "any", "by",
		"bye", "their", "hero", "heroplane"
	};

	int size = sizeof(keys) / sizeof(keys[0]);
	for (int i = 0; i < size; ++i)
	{
		trie.insert(keys[i]);
	}

	/*trie.search("the") ? std::cout << "Yes\n" : std::cout << "No\n";
	trie.search("these") ? std::cout << "Yes\n" : std::cout << "No\n";

	trie.search("hero") ? std::cout << "Yes\n" : std::cout << "No\n";

	Deletion_State isRemoved = trie.remove("heroplane");
	bool isExist = trie.search("herolane");
	if (isExist)
	{
		std::cout << "it still exists.\n";
	}
	else
	{
		std::cout << "No exist.\n";
	}*/

	std::string prefix = "";//"th";
	std::vector<std::string> vtWords = trie.getWordsWithPrefix(prefix);

	for (std::string word : vtWords)
	{
		std::cout << word << "\n";
	}

	system("pause");
	return 0;
}
