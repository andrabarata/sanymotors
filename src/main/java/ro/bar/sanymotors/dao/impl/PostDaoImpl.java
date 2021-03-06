package ro.bar.sanymotors.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ro.bar.sanymotors.dao.AbstractDao;
import ro.bar.sanymotors.dao.PostDao;
import ro.bar.sanymotors.data.Announce;
import ro.bar.sanymotors.model.Post;

@Repository
public class PostDaoImpl extends AbstractDao implements PostDao{
	
	@Override
	public List<Announce> getAnnounces(int page, int pageSize) throws SQLException {
		List<Announce> announces = new ArrayList<>();
	 	String sqlQuery = "select post.elementid, post.title, image.content, post.description, post.date_created,cat.name from post "
	 			+ "inner join image on post.image_fk=image.elementid inner join category cat on post.category_fk=cat.elementid";
	 	int startIdx = pageSize * (page - 1);
	 	Query query = getCurrentSession().createSQLQuery(sqlQuery).setFirstResult(startIdx).setMaxResults(pageSize);
	 	@SuppressWarnings("unchecked")
		List<Object[]> qResult = query.list();
	 	for (Object[] result : qResult) {
	 		int index = ((Integer)result[0]).intValue();
	 		String title = (String)result[1];
	 		byte[] bdata = (byte[])result[2];
	 		String base64Content = new String(bdata);
	 		String description = (String) result[3];
	 		String dateCreated = (String) result[4];
	 		String category = (String) result[5];
	 		Announce announce = new Announce(index, title, description, base64Content, dateCreated, category);
	 		announces.add(announce);
		}
		return announces;
	}

	@Override
	public int getPostCount() {
		String sqlQuery = "select count(*) from post";
		return ((BigInteger) getCurrentSession().createSQLQuery(sqlQuery).uniqueResult()).intValue();
	}

	@Override
	public int getMotorcyclePostCount(String categoryId) {
		String sqlQuery = "select count(*) from post where state!=3";
		if (StringUtils.isNotEmpty(categoryId)) {
			sqlQuery += " and category_fk=" + categoryId;
		}
	    return ((BigInteger)getCurrentSession().createSQLQuery(sqlQuery).uniqueResult()).intValue();
	}

	@Override
	public int getPiecesPostCount() {
		String sqlQuery = "select count(*) from post where state=3";
	    return ((BigInteger)getCurrentSession().createSQLQuery(sqlQuery).uniqueResult()).intValue();
	}
	
	@Override
	public int getRentPostCount() {
		String sqlQuery = "select count(*) from post where state=2";
	    return ((BigInteger)getCurrentSession().createSQLQuery(sqlQuery).uniqueResult()).intValue();
	}

	@Override
	public int getBelongingCategoryId(int elementId) {
		 String sqlQuery = "select category_fk from post where elementId=" + elementId;
		 return ((Integer)getCurrentSession().createSQLQuery(sqlQuery).uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllMotorcycles(int page, int pageSize, String categoryId) {
		String hqlQuery;
	    if (categoryId != null) {
	      hqlQuery = "select posts from CategoryImpl categ inner join categ.posts posts where posts.state!=3 and categ.elementId = " + categoryId;
	    } else
	      hqlQuery = "select posts from PostImpl posts where posts.state!=3";
	    if (page == -1) {
	      return getCurrentSession().createQuery(hqlQuery).list();
	    }
	    int startIdx = pageSize * (page - 1);
	    return getCurrentSession().createQuery(hqlQuery).setFirstResult(startIdx).setMaxResults(pageSize).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllPieces(int page, int pageSize) {
		 int startIdx = pageSize * (page - 1);
		 String hqlQuery = "select posts from PostImpl posts where posts.state = 3";
		 return getCurrentSession().createQuery(hqlQuery).setFirstResult(startIdx).setMaxResults(pageSize).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllRents(int page, int pageSize) {
		 int startIdx = pageSize * (page - 1);
		 String hqlQuery = "select posts from PostImpl posts where posts.state = 2";
		 return getCurrentSession().createQuery(hqlQuery).setFirstResult(startIdx).setMaxResults(pageSize).list();
	}
	
}
