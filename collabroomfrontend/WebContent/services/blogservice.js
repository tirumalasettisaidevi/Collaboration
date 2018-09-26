/**
 * 
 */

app.factory('BlogService',function($http){
	var blogService={}
	
	blogService.addBlog=function(blog){
		return $http.post("http://localhost:8088/collabroommiddleware/addblogpost",blog)
	}
	
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get("http://localhost:8088/collabroommiddleware/getblogs/"+0)
	}
	
	blogService.getBlogsApproved=function(){
		return $http.get("http://localhost:8088/collabroommiddleware/getblogs/"+1)
	}
	
	blogService.getBlog=function(id){
		return $http.get("http://localhost:8088/collabroommiddleware/getblog/"+id)
	}
	
	blogService.hasUserLikedBlog=function(id){
		return $http.get("http://localhost:8088/collabroommiddleware/hasuserlikedblog/"+id)
	}
	
	blogService.approve=function(blog){
		return $http.put("http://localhost:8088/collabroommiddleware/approve",blog)
	}
	
	blogService.reject=function(blog,rejectionReason){
		return $http.put("http://localhost:8088/collabroommiddleware/reject/"+rejectionReason,blog)
	}
	
	blogService.updateLikes=function(id){
		return $http.put("http://localhost:8088/collabroommiddleware/updatelikes/"+id);
	}
	
	blogService.addComment=function(blogComment){
		return $http.post("http://localhost:8088/collabroommiddleware/addcomment",blogComment);
	}
	
	blogService.getBlogComments=function(id){
		return $http.get("http://localhost:8088/collabroommiddleware/blogcomments/"+id);
	}
	
	
	return blogService;
	
})