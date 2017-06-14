package ro.bar.sanymotors.service;

import java.util.List;

public interface GenericService {
	void addEntity(Object obj);
	void modifyEntity(Object obj);
	void removeEntity(Object obj);
	List<?> getAllEntities(String className);
	Object getEntity(String className, String elementId);
}
