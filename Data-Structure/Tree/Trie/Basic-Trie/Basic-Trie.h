#pragma once
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

#define ALPHABET_SIZE	 26


class Trie
{
public: 
	Trie();

	void insert(const std::string& str);

	bool del(const std::string& str);

	bool search(const std::string& str);

	bool startsWidth(const std::string& prefix);

private: 

	class TrieNode
	{
	public: 
		TrieNode() : m_vecChildren(ALPHABET_SIZE, nullptr), m_isLeafNode(false)
		{
			// nothing to do.
		}

		TrieNode(char c) : m_vecChildren(ALPHABET_SIZE, nullptr)
										 , m_isLeafNode(false)
										 , m_character(c)
		{
			// nothing to do.
		}

		std::vector<TrieNode*>& getChildren()
		{
			return m_vecChildren;
		}

		bool getIsLeafNode()
		{
			return m_isLeafNode;
		}

		char getCharacter()
		{
			return m_character;
		}

		void setCharacter(char c)
		{
			m_character = c;
		}

	private: 
		std::vector<TrieNode*>		m_vecChildren; 
		bool											m_isLeafNode;
		char											m_character;
	};

	TrieNode*				m_pRoot;
};