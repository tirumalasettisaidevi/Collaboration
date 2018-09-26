package com.niit.dao;

import com.niit.entity.BlogPost;
import com.niit.entity.BlogPostLikes;

public interface BlogPostLikesDao {
	
	BlogPostLikes hasUserLikedBlog(int blogId,String email);

	BlogPost updateLikes(int id,String email);

}
