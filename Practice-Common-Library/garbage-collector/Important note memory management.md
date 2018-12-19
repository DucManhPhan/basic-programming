In C/C++ or Javascript, memory management is the important first thing that all you need to do. Understanding about memory management provides you powerful thing to optimize and write efficient code. 

The belows is the informations about memory manegement which is referred from some websites. These websites is noted at the end of this article.

## Allocate / Deallocate memory functions

The standard library functions *malloc, calloc, free, realloc* in C and the operators *new, new[] delete, delete[]* in C++, are used to manage memory. 

Functions such as *malloc* and *new* are general-purpose memory allocators. Your code may be single-threaded, but the malloc function it is linked to can handle multithreaded paradigms just as well. It is this extra functionality that degrades the performance of these routines. 

In their turn, *malloc* and *new* make calls to the operating system kernel requesting memory, while *free* and *delete* make requests to release memory. **This means that the operating system has to switch between user-space code and kernel code every time a request for memory is made. Programs making repeated calls to malloc or new eventually run slowly because of the repeated context switching.**


## Overloading function of allocating / deallocating memory

```C++
void* operator new(size_t size); 
void   operator delete(void* pointerToDelete);
```

The overridden new operator is responsible for allocating raw memory of the size specified in the first argument, and the delete operator frees this memory. Note that these routines are meant only to allocate or deallocate memory; they don't call constructors or destructors, respectively. A constructor is invoked on the memory allocated by the new operator, while the delete operator is called only after the destructor is called on an object.


## 


Refer: 

[Building your own memory manager for C/C++ projects](https://www.ibm.com/developerworks/aix/tutorials/au-memorymanager/index.html)