package ro.bar.sanymotors.dao;

import java.sql.SQLException;
import java.util.List;

import ro.bar.sanymotors.data.Announce;

public interface PostDao{
	
	public List<Announce> getAnnounces(int page,int pageSize) throws SQLException;
	public int getPostCount();
}
