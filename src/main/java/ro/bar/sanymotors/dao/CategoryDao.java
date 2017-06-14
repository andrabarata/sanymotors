package ro.bar.sanymotors.dao;

import java.util.Map;

import ro.bar.sanymotors.model.impl.CategoryImpl;

public interface CategoryDao {

	public Map<Integer, String> getAllCategories();
	public CategoryImpl getCategoryById(int elementId);
	public String getPostCategoryName(int postId);
}
