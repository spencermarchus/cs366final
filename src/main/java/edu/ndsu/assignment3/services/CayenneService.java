package edu.ndsu.assignment3.services;

import org.apache.cayenne.ObjectContext;

public interface CayenneService  
{
	ObjectContext newContext();
}


