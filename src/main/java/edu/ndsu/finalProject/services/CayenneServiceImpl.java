package edu.ndsu.finalProject.services;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

public class CayenneServiceImpl implements CayenneService
{
	private ServerRuntime cayenneRuntime; 
	
	public CayenneServiceImpl() {
		cayenneRuntime = ServerRuntime.builder().addConfig("cayenne-cayenne.xml").build();
	}
	
	@Override
	public ObjectContext newContext() {
		return cayenneRuntime.newContext();
	}
}
