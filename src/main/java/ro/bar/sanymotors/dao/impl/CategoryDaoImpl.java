package ro.bar.sanymotors.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ro.bar.sanymotors.dao.AbstractDao;
import ro.bar.sanymotors.dao.CategoryDao;
import ro.bar.sanymotors.model.impl.CategoryImpl;

@Repository
public class CategoryDaoImpl extends AbstractDao implements CategoryDao{

	@Override
	public Map<Integer, String> getAllCategories() {
		 Map<Integer, String> data = new HashMap<Integer, String>();
		 @SuppressWarnings("unchecked")
		List<Object[]> resultSet = getCurrentSession().createSQLQuery("select elementid, name from category").list();
		 for (Object[] result : resultSet) {
			 data.put((Integer)result[0], (String)result[1]);
		 }
		 return data;
	}

	@Override
	public CategoryImpl getCategoryById(int elementId) {
		 Query query = getCurrentSession().createQuery("from CategoryImpl where elementId=:elemId");
		 query.setParameter("elemId", elementId);
		 return (CategoryImpl) query.uniqueResult();
	}

	@Override
	public String getPostCategoryName(int postId) {
		String sqlQuery = "select name from category categ inner join post thep on thep.category_fk=categ.elementid where thep.elementid=:elemId";
		return (String)getCurrentSession().createSQLQuery(sqlQuery).setParameter("elemId", postId).uniqueResult();
	}

}
