package com.niit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.niit.entity.BlogComment;
import com.niit.entity.BlogPost;
import com.niit.entity.Notification;

@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addBlogPost(BlogPost blogPost) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);
	}

	public List<BlogPost> listOfBlogs(int approved) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved="+approved);
		List<BlogPost> blogs=query.list();
		return blogs;
	}

	public BlogPost getBlog(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class,id);
		return blogPost;
	}

	public void approve(BlogPost blog) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		blog.setApproved(true);
		session.update(blog);
		Notification notification=new Notification();
		notification.setBlogTitle(blog.getBlogTitle());
		notification.setApprovalStatus("Approved");
		notification.setEmail(blog.getPostedBy().getEmail()); 
		session.save(notification);
	}

	public void reject(BlogPost blog,String rejectionReason) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Notification notification=new Notification();
		notification.setBlogTitle(blog.getBlogTitle());
		notification.setApprovalStatus("Rejected");
		notification.setEmail(blog.getPostedBy().getEmail());
		notification.setRejectionReason(rejectionReason);
		session.save(notification);
		session.delete(blog);
	}

	public void addBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
	}

	public List<BlogComment> getAllBlogComments(int blogPostId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogPost.id=?");
		query.setInteger(0,blogPostId);
		List<BlogComment> blogComments=query.list();
		return blogComments;
	}

}
