#pragma once
#include "IGarbageCollector.h"


class CGarbageCollector : public IGarbageCollector
{
public: 
	CGarbageCollector();

	~CGarbageCollector();

	virtual void* alloc(size_t size);

	virtual void  free();
	
private:

};