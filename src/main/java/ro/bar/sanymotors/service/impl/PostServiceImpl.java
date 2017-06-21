package ro.bar.sanymotors.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.bar.sanymotors.dao.AttributeDao;
import ro.bar.sanymotors.dao.CategoryDao;
import ro.bar.sanymotors.dao.ImageDao;
import ro.bar.sanymotors.dao.PostDao;
import ro.bar.sanymotors.data.Announce;
import ro.bar.sanymotors.model.Post;
import ro.bar.sanymotors.model.impl.AttributeImpl;
import ro.bar.sanymotors.service.PostService;

@Service
@Transactional(readOnly = false)
public class PostServiceImpl implements PostService{

	@Autowired
	private PostDao postDao;
	
	@Autowired
	private AttributeDao attributeDao;
	
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Announce> getAllAnnounces(int page, int pageSize) throws SQLException {
		return postDao.getAnnounces(page, pageSize);
	}
	
	@Override
	public int getLastPage(int pageSize) {
		int postNumber = postDao.getPostCount();
		return getPage(postNumber, pageSize);
	}

	@Override
	public int getLastMotorcyclePage(int pageSize, String category) {
		int postNumber = postDao.getMotorcyclePostCount(category);
		return getPage(postNumber, pageSize);
	}

	@Override
	public int getLastPiecesPage(int pageSize) {
		int postNumber = postDao.getPiecesPostCount();
		return getPage(postNumber, pageSize);
	}

	@Override
	public List<String> getAvailableAttributeNames() {
		return attributeDao.getAvailableNames();
	}

	@Override
	public List<AttributeImpl> getAttributes(int elementId) {
		return attributeDao.getAttributes(elementId);
	}

	@Override
	public int getBelongingCategoryId(int elementId) {
		return postDao.getBelongingCategoryId(elementId);
	}

	@Override
	public List<String> getAdditionalImages(int elementId) {
		return imageDao.getAdditionalImageList(elementId);
	}

	@Override
	public List<Post> getAllMotorcycles(int page, int pageSize, String categoryId) {
		return postDao.getAllMotorcycles(page, pageSize, categoryId);
	}

	@Override
	public List<Post> getAllPieces(int page, int pageSize) {
		return postDao.getAllPieces(page, pageSize);
	}

	@Override
	public Map<Integer, String> getAllCategories() {
		return categoryDao.getAllCategories();
	}
	
	private int getPage(int postNumber, int pageSize){
		if (postNumber % pageSize == 0) {
			return postNumber / pageSize;
		}
		return postNumber / pageSize + 1;
	}
}
