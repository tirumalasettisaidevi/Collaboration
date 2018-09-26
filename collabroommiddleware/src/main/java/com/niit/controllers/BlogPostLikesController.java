package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.BlogPostLikesDao;
import com.niit.entity.BlogPost;
import com.niit.entity.BlogPostLikes;
import com.niit.entity.ErrorClazz;

@Controller
public class BlogPostLikesController {

	@Autowired
	private BlogPostLikesDao blogPostLikesDao; 
	
	@RequestMapping(value="/hasuserlikedblog/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> hasUserLikedBlog(@PathVariable int blogId,HttpSession session)
	{
		String email=(String)session.getAttribute("loginId");
		if(email==null)
		{
			ErrorClazz error = new ErrorClazz(5,"UnAuthorized Access....");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		BlogPostLikes blogPostLikes=blogPostLikesDao.hasUserLikedBlog(blogId, email);
		
		return new ResponseEntity<BlogPostLikes>(blogPostLikes,HttpStatus.OK);
			
	}
	
	
	@RequestMapping(value="/updatelikes/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateLikes(@PathVariable int id,HttpSession session)
	{
		String email=(String)session.getAttribute("loginId");
		if(email==null)
		{
			ErrorClazz error = new ErrorClazz(5,"UnAuthorized Access....");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		BlogPost blogPost=blogPostLikesDao.updateLikes(id,email);
		
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
	}
	
}
