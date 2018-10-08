#include <iostream>
#include <string>



void GetAllFileInFolder(const wstring& root,
                                      vector<fs::path>& out_paths) {
  if (!fs::exists(root) || !fs::is_directory(root)) return;
  fs::path rootPath(root);
  fs::recursive_directory_iterator it(rootPath);
  const fs::recursive_directory_iterator endit;

  while (it != endit) {
    if (fs::is_regular_file(*it)) out_paths.push_back(it->path());
    ++it;
  }
}






