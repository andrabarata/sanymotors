package ro.bar.sanymotors.dao;

import java.util.List;

import ro.bar.sanymotors.model.impl.AttributeImpl;

public interface AttributeDao {

	public List<String> getAvailableNames();
	public List<AttributeImpl> getAttributes(int postId);
}
