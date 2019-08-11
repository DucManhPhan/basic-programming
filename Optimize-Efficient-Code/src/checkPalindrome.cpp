#include <string>


bool checkPalindrome(std::string is)
{
    return is == std::string(is.rbegin(), is.rend());
}


int main()
{
    std::string tmp = "aabccaa";
    bool isPalindrome = checkPalindrome(tmp);

    system("pause");
    return 0;
}