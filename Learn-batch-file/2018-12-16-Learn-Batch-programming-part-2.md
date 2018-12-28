---
layout: post
title: Learn batch programming - Part 2 - some useful commands
bigimg: /img/image-header/ravashing-beach.jpg
tags: [batch programming]
---

In this article, you will find something out about some useful commands such as cd, copy, del, move, md, find and processing the argument that you pass.

## CD - Change directory

Syntax: 

```
    CD [/D] [drive:][path]
    CD [..]
```

with: 
  
  /D : change the current DRIVE in addition to changing folder. 

Ex: 

```
::change to the parent directory
cd ..

::change to the ROOT directory
cd \

::change to the grand-parent directory
cd ..\..

::change the current drive
C:> E:
E:>
```


## Set command - Display, set or remove environment variables

Syntax: 

```
  SET variable
  SET variable=string     // set value for variable
  SET "variable=string"   // set value for variable
  SET "variable="         // delete a variable
  SET variable=           // delete a variable

  SET /A "variable=expression"      // arithmetic expression
  SET /P variable=[promptString]    // get information from user input
  SET "
```


Note: 
- SET /A will treat any character string in the expression as an environment variable name. This allows you to do arithmetic with environment variables without having to type any % signs to get the values. SET /A _result=5 + _MyVar
- A variable can contain spaces and also the variable name itself can contain spaces, therefore the following assignment:

```
SET _var =MyText
```

will create a variable called "_var " - note the trailing space
- Using set without parameters to display all the current environment variables. 
- Type set with a variable name to display that variable.
- It is good practice to avoid using any delimiter characters (spaces, commas etc) in the variable name.
- It is a common practice to prefix variable names with either an undescore or a dollar sign _variable or $variable, these prefixes are not required but help to prevent any confusion with the standard built-in Windows Environment variables or any other other command strings.

## Pause program when finishing task

Syntax: 

```
  pause
```

## Copy files to another location

Syntax: 

```
  COPY [options] [/A|/B] source [/A|/B] [+ source2 [/A|/B]...] [destination [/A|/B]]

  COPY source1 + source2.. destination [options]
```

with: 
- /A : ASCII text file (default)
- /B : Binary file
- /D : allow the destination file to be created decrypted.
- /Y : Suppress confirmation prompt, when overwriting files.
- /-Y : Enable confirmation prompt, when overwriting files.

Ex: 

```
::Copy a file in the current folder
COPY source_file.doc newfile.doc

:: Copy from a different folder/directory:
COPY "C:\my work\some file.doc" "D:\New docs\newfile.doc"

:: Specify the source only, with a wildcard will copy all :: the files into the current directory:
COPY "C:\my work\*.doc"

:: Specify the source with a wildcard and the destination :: as a single file, this is generally only useful with  :: plain text files.
COPY "C:\my work\*.txt" "D:\New docs\combined.txt"

:: Quiet copy (no feedback on screen)
COPY source_file.doc newfile.doc >nul

:: Copy a file, but do not overwrite if the destination 
:: file already exists, this technique only works for a 
:: single file, no wildcards:
Echo n|COPY /-y c:\demo\source_file.txt c:\dir\dest.txt
```

## XCopy files and folders to another folder

Syntax: 

```
XCOPY source [destination] [options]
```




Thanks for your reading.
