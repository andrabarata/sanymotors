package ro.bar.sanymotors.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.bar.sanymotors.dao.PostDao;
import ro.bar.sanymotors.dao.impl.PostDaoImpl;
import ro.bar.sanymotors.service.GenericService;



@Service
@Transactional(readOnly = false)
public class GenericServiceImpl implements GenericService{

	@Autowired
	private PostDao postDao;
	
	
	@Override
	public void addEntity(Object obj) {
		((PostDaoImpl)postDao).save(obj);
	}

	@Override
	public void removeEntity(Object obj) {
		((PostDaoImpl)postDao).deleteEntity(obj);
	}

	@Override
	public List<?> getAllEntities(String className) {
		return ((PostDaoImpl)postDao).getAllEntities(className);
	}

	@Override
	public Object getEntity(String className, String elementId) {
		return ((PostDaoImpl)postDao).findEntity(className, elementId);
	}

	@Override
	public void modifyEntity(Object obj) {
		((PostDaoImpl)postDao).merge(obj);
	}

}
