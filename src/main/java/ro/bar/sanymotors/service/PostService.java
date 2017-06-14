package ro.bar.sanymotors.service;

import java.sql.SQLException;
import java.util.List;

import ro.bar.sanymotors.data.Announce;

public interface PostService {
	public List<Announce> getAllAnnounces(int page, int pageSize) throws SQLException;
	public int getLastPage(int pageSize);
}
