package ro.bar.sanymotors.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {

	@Autowired 
	private SessionFactory sessionFactory;
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void save(Object obj){
		getCurrentSession().save(obj);
	}
	public void merge(Object obj){
		getCurrentSession().merge(obj);
	}
	public void deleteEntity(Object obj){
		getCurrentSession().delete(obj);
	}
	
	public List<?> getAllEntities(String className){
		return  getCurrentSession().createQuery("from " + className).list();
	}
	
	public Object findEntity(String className, String elementId){
		return  getCurrentSession().createQuery("from " + className+" where elementid="+elementId).list().get(0);
	}
}
