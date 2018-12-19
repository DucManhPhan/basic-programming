#pragma once


class IGarbageCollector
{
public: 
	virtual void* allocate(size_t size) = 0;
	virtual void  free() = 0;
};