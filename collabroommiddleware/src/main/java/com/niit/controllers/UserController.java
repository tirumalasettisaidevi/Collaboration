package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.UserDao;
import com.niit.entity.ErrorClazz;
import com.niit.entity.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
		
		if(!userDao.isEmailUnique(user.getEmail()))
		{
			ErrorClazz error=new ErrorClazz(1,"Email already exists. Please enter a different email address");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
		}
		
		try {
			userDao.registerUser(user);
		}
		catch(Exception e)
		{
			ErrorClazz error=new ErrorClazz(2,"Some required fields are empty..."+e.getMessage());
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session)
	{
		User validUser=userDao.login(user);
		if(validUser==null)
		{
			ErrorClazz error=new ErrorClazz(5,"Login Failed. Invalid UserId/Password");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		else {
			validUser.setOnline(true);
			userDao.update(validUser);
			session.setAttribute("loginId",user.getEmail());
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
			
		}
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		String email=(String)session.getAttribute("loginId");
		if(email==null)
		{
			ErrorClazz error=new ErrorClazz(4,"Please Login...");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUser(email);
		user.setOnline(false);
		userDao.update(user);
		session.removeAttribute("loginId");
		session.invalidate();
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session)
	{
		String email = (String)session.getAttribute("loginId");
		if(email==null)
		{
			ErrorClazz error=new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUser(email);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateuser",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session)
	{
		String email = (String)session.getAttribute("loginId");
		if(email==null)
		{
			ErrorClazz error=new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		try {
		userDao.update(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			ErrorClazz error = new ErrorClazz(5,"Unable to update User details "+e.getMessage());
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

}
