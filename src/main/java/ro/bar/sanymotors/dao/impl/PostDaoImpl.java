package ro.bar.sanymotors.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ro.bar.sanymotors.dao.AbstractDao;
import ro.bar.sanymotors.dao.PostDao;
import ro.bar.sanymotors.data.Announce;

@Repository
public class PostDaoImpl extends AbstractDao implements PostDao{
	
	@Override
	public List<Announce> getAnnounces(int page, int pageSize) throws SQLException {
		List<Announce> announces = new ArrayList<>();
		String sqlQuery = "select post.elementid, post.title, image.content from post inner join image on post.image_fk=image.elementid";
		int startIdx = pageSize * (page - 1);
		Query query = getCurrentSession().createSQLQuery(sqlQuery).setFirstResult(startIdx).setMaxResults(pageSize);
		List<Object[]> qResult = query.list();
		for (Object[] result: qResult){
			int index = (int) result[0];
			String title = (String) result[1];
			byte[] bdata =  (byte[]) result[2];
			String base64Content = new String(bdata);
			Announce announce = new Announce(index, title, base64Content);
			announces.add(announce);
		}
		return announces;
	}

	@Override
	public int getPostCount() {
		String sqlQuery = "select count(*) from post";
		return ((BigInteger) getCurrentSession().createSQLQuery(sqlQuery).uniqueResult()).intValue();
	}
	
}
