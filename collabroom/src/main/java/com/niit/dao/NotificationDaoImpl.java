package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.entity.Notification;

@Repository
@Transactional
public class NotificationDaoImpl implements NotificationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Notification> getNotificationsNotViewed(String email) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Notification where email=? and viewed=0");
		query.setString(0,email);
		List<Notification> notificationsNotViewed=query.list();
		return notificationsNotViewed;
	}

	public Notification getNotification(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class,id);
		return notification;
	}

	public void updateNotification(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class,id);
		notification.setViewed(true);
		session.update(notification);
	}

}
