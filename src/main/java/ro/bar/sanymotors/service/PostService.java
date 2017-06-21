package ro.bar.sanymotors.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ro.bar.sanymotors.data.Announce;
import ro.bar.sanymotors.model.Post;
import ro.bar.sanymotors.model.impl.AttributeImpl;

public interface PostService {
	
	public List<Announce> getAllAnnounces(int page, int pageSize) throws SQLException;
	public int getLastPage(int pageSize);
	public int getLastMotorcyclePage(int pageSize, String category);
	public int getLastPiecesPage(int pageSize);
	public List<String> getAvailableAttributeNames();
	public List<AttributeImpl> getAttributes(int elementId);
	public int getBelongingCategoryId(int elementId);
	public List<String> getAdditionalImages(int elementId);
	public List<Post> getAllMotorcycles(int page, int pageSize, String categoryId);
	public List<Post> getAllPieces(int page, int pageSize);
	public Map<Integer, String> getAllCategories();
}
