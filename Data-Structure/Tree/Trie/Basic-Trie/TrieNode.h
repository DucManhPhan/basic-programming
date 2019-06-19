#pragma once
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <stack>

#define ALPHABET_SIZE	 26


class TrieNode
{
public:
	TrieNode() : m_vecChildren(ALPHABET_SIZE, nullptr), m_numLeafNodes(0)
	{
		// nothing to do.
	}

	TrieNode(char c) : m_vecChildren(ALPHABET_SIZE, nullptr)
		, m_numLeafNodes(0)
		, m_character(c)
	{
		// nothing to do.
	}

	std::vector<TrieNode*>& getChildren()
	{
		return m_vecChildren;
	}

	int getNumLeafNodes()
	{
		return m_numLeafNodes;
	}

	void setNumLeafNodes(int numLeafNodes)
	{
		m_numLeafNodes = numLeafNodes;
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
	std::vector<TrieNode*>	m_vecChildren;
	int						m_numLeafNodes;
	char					m_character;
};
