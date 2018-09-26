/**
 * 
 */

app.factory('UserService',function($http){
	var userService={}
	
	userService.registerUser=function(user){
		return $http.post("http://localhost:8088/collabroommiddleware/registeruser",user)
	}
	
	userService.login=function(user){
		return $http.post("http://localhost:8088/collabroommiddleware/login",user)
	}
	
	
	userService.logout=function(){
		return $http.put("http://localhost:8088/collabroommiddleware/logout")
	}
	
	userService.getUser=function(){
		return $http.get("http://localhost:8088/collabroommiddleware/getuser")
	}
	
	userService.updateUser=function(user){
		return $http.put("http://localhost:8088/collabroommiddleware/updateuser",user)
	}
	
	
	return userService
})