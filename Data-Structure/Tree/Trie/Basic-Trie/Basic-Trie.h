#pragma once
#include "TrieNode.h"


enum Deletion_State {
	SUCCESS = 0, 
	ERROR, 
	NO_EXIST,
};


class Trie
{
public: 
	Trie();

	void insert(const std::string& str);

	Deletion_State remove(const std::string& str);

	bool search(const std::string& str);

	bool startsWith(const std::string& prefix);

private:
	TrieNode* remove(TrieNode* pRoot, const std::string& str, int level = 0);

	bool isEmpty(TrieNode* pRoot);

private: 
	TrieNode*					m_pRoot;
};