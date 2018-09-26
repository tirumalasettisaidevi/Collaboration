/**
 * 
 */

app.factory('FriendService',function($http){
	var friendService={}
	
	friendService.getAllSuggestedUsers=function(){
		return $http.get("http://localhost:8088/collabroommiddleware/suggestedusers")
	}
	
	friendService.addFriend=function(toId){
		return $http.post("http://localhost:8088/collabroommiddleware/addfriend",toId)
	}
	
	friendService.getPendingRequests=function()
	{
		return $http.get("http://localhost:8088/collabroommiddleware/pendingrequests");
	}
	
	friendService.acceptRequest=function(request){
		return $http.put("http://localhost:8088/collabroommiddleware/acceptrequest",request);
	}
	
	friendService.deleteRequest=function(request){
		return $http.put("http://localhost:8088/collabroommiddleware/deleterequest",request);
	}
	
	friendService.getAllFriends=function(){
		return $http.get("http://localhost:8088/collabroommiddleware/friends");
	}
	
	
	return friendService;
})