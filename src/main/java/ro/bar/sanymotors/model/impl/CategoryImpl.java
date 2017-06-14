package ro.bar.sanymotors.model.impl;

import java.util.ArrayList;
import java.util.List;

import ro.bar.sanymotors.model.Category;
import ro.bar.sanymotors.model.SerializableElement;

public class CategoryImpl extends SerializableElement implements Category{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private List<PostImpl> posts;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PostImpl> getPosts() {
		return posts;
	}
	public void setPosts(List<PostImpl> posts) {
		this.posts = posts;
	}
	
	public void addPost(PostImpl post) {
	    if (posts == null)
	    	posts = new ArrayList<>();
	    if (!hasPost(post))
	      posts.add(post);
	  }
	  
	  private boolean hasPost(PostImpl post) {
		  int i = 0;
		  for (PostImpl foundPost : posts) {
			  if (foundPost.getElementId() == post.getElementId()) {
				  posts.set(i, post);
				  return true;
			  }
		  }
		  return false;
	  }
}
