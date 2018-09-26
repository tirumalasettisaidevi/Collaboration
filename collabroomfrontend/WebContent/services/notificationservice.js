/**
 * 
 */

app.factory('NotificationService',function($http){
	var notificationService={}
	
	notificationService.getNotificationsNotViewed=function(){
		return $http.get("http://localhost:8088/collabroommiddleware/getnotifications");
	}
	
	notificationService.getNotification=function(id){
		return $http.get("http://localhost:8088/collabroommiddleware/getnotification/"+id);
	}
	
	notificationService.updateNotification=function(id){
		return $http.put("http://localhost:8088/collabroommiddleware/updatenotification/"+id);
	}
	
	return notificationService;
})