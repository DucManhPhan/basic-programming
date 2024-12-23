// Trie - reTrieval data structure

#include "Basic-Trie.h"

#define CHARACTER_FIRST 'a'

Trie::Trie() : m_pRoot(nullptr)
{
	// nothing to do
}

void Trie::insert(const std::string& str)
{
	if (str.empty())
	{
		return;
	}

	if (!m_pRoot)
	{
		m_pRoot = new TrieNode();
	}

	TrieNode* pNode = m_pRoot;
	int length = str.size();
	for (int i = 0; i < length; ++i)
	{
		int index = int(str[i] - CHARACTER_FIRST);
		TrieNode*& pTmpNode = pNode->getChildren()[index];

		// make the new trie node if not exist
		if (!pTmpNode)
		{
			pTmpNode = new TrieNode(str[i]);
		}

		// next character in trie
		pNode = pTmpNode;

		// check the final character in string
		if (i == length - 1)
		{
			int numLeafNode = pNode->getNumLeafNodes();
			pNode->setNumLeafNodes(++numLeafNode);
		}
	}
}


bool Trie::search(const std::string& str)
{
	if (!m_pRoot && str == "")
	{
		return false;
	}

	int nLength = str.length();
	TrieNode* pNode = m_pRoot;

	for (int i = 0; i < nLength; ++i)
	{
		int nIndex = (int)(str[i] - CHARACTER_FIRST);
		TrieNode* pTmpNode = pNode->getChildren()[nIndex];
		if (pTmpNode)
		{
			pNode = pTmpNode;
		}
		else
		{
			return false;
		}
	}

	return pNode->getNumLeafNodes() > 0;
}


bool Trie::startsWith(const std::string& prefix)
{
	if (!m_pRoot && prefix == "")
	{
		return false;
	}

	int nLength = prefix.length();
	TrieNode* pNode = m_pRoot;

	for (int i = 0; i < nLength; ++i)
	{
		int nIndex = (int)(prefix[i] - CHARACTER_FIRST);
		TrieNode* pTmpNode = pNode->getChildren()[nIndex];
		if (pTmpNode)
		{			
			pNode = pTmpNode;
		}
		else
		{
			return false;
		}
	}
	
	return true;
}


Deletion_State Trie::remove(const std::string& str)
{
	if (this->m_pRoot == nullptr)
	{
		return Deletion_State::ERROR;
	}

	// check whether this string exist in Trie or not
	bool isExist = this->search(str);
	if (!isExist) {
		return Deletion_State::NO_EXIST;
	}

	// deletion operation
	remove(m_pRoot, str);

	return Deletion_State::SUCCESS;
}


TrieNode* Trie::remove(TrieNode* pRoot, const std::string& str, int level)
{
	if (!pRoot)
	{
		return nullptr;
	}

	// basic final operation
	if (level == str.size())
	{
		int num = pRoot->getNumLeafNodes();
		if (num >= 1)
		{
			pRoot->setNumLeafNodes(num - 1);
		}

		if (isEmpty(pRoot) && pRoot->getNumLeafNodes() == 0)
		{
			delete pRoot;
			pRoot = nullptr;
		}

		return pRoot;
	}

	// recursive operation
	int index = str[level] - CHARACTER_FIRST;
	pRoot->getChildren()[index] = this->remove(pRoot->getChildren()[index], str, level + 1);

	if (isEmpty(pRoot) && pRoot->getNumLeafNodes() == 0)
	{
		delete pRoot;
		pRoot = nullptr;
	}

	return pRoot;
}


bool Trie::isEmpty(TrieNode* pRoot)
{
	if (!pRoot)
	{
		return false;
	}

	std::vector<TrieNode*> vtChildrenNodes = pRoot->getChildren();
	int size = vtChildrenNodes.size();

	for (int i = 0; i < size; ++i)
	{
		if (vtChildrenNodes[i] != nullptr)
		{
			return false;
		}
	}

	return true;
}


TrieNode* Trie::getPrefix(const std::string& str)
{
	if (!m_pRoot)
	{
		return nullptr;
	}

	TrieNode* pNode = m_pRoot;
	int size = str.size();
	for (int i = 0; i < size; ++i)
	{
		int index = str[i] - CHARACTER_FIRST;
		TrieNode* pTmpNode = pNode->getChildren()[index];
		if (pTmpNode == nullptr)
		{
			return nullptr;
		}

		pNode = pTmpNode;
	}

	return pNode;
}


std::vector<std::string> Trie::getWordsWithPrefix(const std::string& str)
{
	TrieNode* prefixNode = getPrefix(str);
	if (!prefixNode)
	{
		return std::vector<std::string>();
	}

	std::vector<std::string> vtWords;
	std::string tmp = str;
	this->findWords(prefixNode, tmp, vtWords);

	return vtWords;
}


void Trie::findWords(TrieNode* pNode, std::string prefix, std::vector<std::string>& vtWords)
{
	if (!pNode)
	{
		vtWords.push_back(prefix);
		return;
	}

	int numOfLeafNodes = pNode->getNumLeafNodes();
	if (numOfLeafNodes >= 1)
	{
		vtWords.push_back(prefix);
	}

	std::vector<TrieNode*> vtChildren = pNode->getChildren();
	int size = vtChildren.size();
	for (int i = 0; i < size; ++i)
	{
		if (vtChildren[i] != nullptr)
		{
			findWords(vtChildren[i], prefix + vtChildren[i]->getCharacter(), vtWords);
		}
	}
}