package ro.bar.sanymotors.dao;

import java.sql.SQLException;
import java.util.List;

import ro.bar.sanymotors.data.Announce;
import ro.bar.sanymotors.model.Post;

public interface PostDao{
	
	public List<Announce> getAnnounces(int page,int pageSize) throws SQLException;
	public int getPostCount();
	public int getMotorcyclePostCount(String category);
	public int getPiecesPostCount();
	public int getBelongingCategoryId(int elementId);
	public List<Post> getAllMotorcycles(int page, int pageSize, String categoryId);
	public List<Post> getAllPieces(int page, int pageSize);
	
}
