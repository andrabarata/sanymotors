package ro.bar.sanymotors.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.bar.sanymotors.dao.PostDao;
import ro.bar.sanymotors.data.Announce;
import ro.bar.sanymotors.service.PostService;

@Service
@Transactional(readOnly = false)
public class PostServiceImpl implements PostService{

	@Autowired
	private PostDao postDao;
	
	@Override
	public List<Announce> getAllAnnounces(int page, int pageSize) throws SQLException {
		return postDao.getAnnounces(page, pageSize);
	}

	@Override
	public int getLastPage(int pageSize) {
		int postNumber = postDao.getPostCount();
		if (postNumber % pageSize == 0) {
			return postNumber / pageSize;
		} else {
			return postNumber / pageSize + 1;
		}
	}
	
}
