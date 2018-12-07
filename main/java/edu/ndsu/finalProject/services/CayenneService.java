package edu.ndsu.finalProject.services;

import org.apache.cayenne.ObjectContext;

public interface CayenneService 
{
	ObjectContext newContext();
}

