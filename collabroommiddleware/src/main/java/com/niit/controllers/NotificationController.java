package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.NotificationDao;
import com.niit.entity.ErrorClazz;
import com.niit.entity.Notification;

@Controller
public class NotificationController {
	
	@Autowired
	private NotificationDao notificationDao;
	
	public NotificationController()
	{
		System.out.println("NotificationController bean is instantiated");
	}
	
	@RequestMapping(value="/getnotifications",method=RequestMethod.GET)
	public ResponseEntity<?> getNotificationsNotViewed(HttpSession session)
	{
		String email=(String)session.getAttribute("loginId");
		
		if(email==null)
		{
			ErrorClazz error = new ErrorClazz(5,"UnAuthorized Access....");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Notification> notificationsNotViewed=notificationDao.getNotificationsNotViewed(email);
		return new ResponseEntity<List<Notification>>(notificationsNotViewed,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getnotification/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getNotification(@PathVariable int id,HttpSession session)
	{
		String email=(String)session.getAttribute("loginId");
		
		if(email==null)
		{
			ErrorClazz error = new ErrorClazz(5,"UnAuthorized Access....");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Notification notification=notificationDao.getNotification(id);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updatenotification/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateNotification(@PathVariable int id,HttpSession session)
	{
		String email=(String)session.getAttribute("loginId");
		
		if(email==null)
		{
			ErrorClazz error = new ErrorClazz(5,"UnAuthorized Access....");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		notificationDao.updateNotification(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
