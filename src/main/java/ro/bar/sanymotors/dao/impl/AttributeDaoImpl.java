package ro.bar.sanymotors.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ro.bar.sanymotors.dao.AbstractDao;
import ro.bar.sanymotors.dao.AttributeDao;
import ro.bar.sanymotors.model.impl.AttributeImpl;

@Repository
public class AttributeDaoImpl extends AbstractDao implements AttributeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAvailableNames() {
		 String sqlQuery = "select distinct name from attribute";
		 Query query = getCurrentSession().createSQLQuery(sqlQuery);
		 return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttributeImpl> getAttributes(int postId) {
		 String hqlQuery = "select post.attributes from PostImpl post where post.elementId=" + postId;
		 Query query = getCurrentSession().createQuery(hqlQuery);
		 return query.list();
	}

}
