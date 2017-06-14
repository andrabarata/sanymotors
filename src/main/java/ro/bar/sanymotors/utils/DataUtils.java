package ro.bar.sanymotors.utils;

import ro.bar.sanymotors.model.impl.ImageImpl;
import ro.bar.sanymotors.model.impl.PostImpl;
import ro.bar.sanymotors.service.GenericService;
import ro.bar.sanymotors.session.PostData;

public class DataUtils {

	 public static void setPostData(PostData data, PostImpl post)
	  {
	    data.setTitle(post.getTitle());
	    data.setDescription(post.getDescription());
	    data.setImage(((ImageImpl)post.getMainImage()).getBase64Content());
	    data.setPrice(post.getPrice().split(" ")[0]);
	    data.setCurrency(post.getPrice().split(" ")[1]);
	    data.setState(post.getState());
	  }
	  
	  public static void populatePost(GenericService genericService, PostData postData, PostImpl post) {
		  if (post.getAdditionalImages() != null)
			  post.getAdditionalImages().clear();
		  if (postData.getAdditionalImages() != null)
			  for (String base64 : postData.getAdditionalImages()) {
				  ImageImpl image = new ImageImpl();
				  image.setBase64Content(base64);
				  genericService.addEntity(image);
				  post.addAditionalImage(image);
			  }
		  if (post.getAttributes() != null)
			  post.getAttributes().clear();
		  if (post.getDateCreated() == null)
			  post.setNewCreationDate();
		  post.setDescription(postData.getDescription());
		  ImageImpl mainImage = new ImageImpl();
		  mainImage.setBase64Content(postData.getImage());
		  genericService.addEntity(mainImage);
		  post.setMainImage(mainImage);
		  post.setPrice(postData.getPrice() + " " + postData.getCurrency());
		  post.setState(postData.getState());
		  post.setTitle(postData.getTitle());
	  }
	  
	  public static void emptyPostData(PostData postData) {
		  postData.setAdditionalImages(null);
		  postData.setCategory(null);
		  postData.setCurrency(null);
		  postData.setDescription(null);
		  postData.setElementId(null);
		  postData.setImage(null);
		  postData.setTitle(null);
		  postData.setState(-1);
		  postData.setPrice(null);
	  }
}
