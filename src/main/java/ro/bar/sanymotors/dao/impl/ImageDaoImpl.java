package ro.bar.sanymotors.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ro.bar.sanymotors.dao.AbstractDao;
import ro.bar.sanymotors.dao.ImageDao;

@Repository
public class ImageDaoImpl extends AbstractDao implements ImageDao{

	@Override
	public List<String> getAdditionalImageList(int elementId) {
		String sql = "select content from image img join additionalimages additImg on img.elementid=additImg.image_fk where additImg.post_fk=" + elementId;
	    Query query = getCurrentSession().createSQLQuery(sql);
	    @SuppressWarnings("unchecked")
		List<Object> contents = query.list();
	    List<String> contentList = new ArrayList<>();
	    for (Object content : contents) {
	      byte[] bdata = (byte[])content;
	      String base64Content = new String(bdata);
	      contentList.add(base64Content);
	    }
	    return contentList;
	}

}
