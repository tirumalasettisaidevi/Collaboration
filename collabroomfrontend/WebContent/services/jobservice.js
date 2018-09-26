/**
 * 
 */

app.factory('JobService',function($http){
	var jobService={}
	
	jobService.addJob=function(job){
		return $http.post("http://localhost:8088/collabroommiddleware/addjob",job)
	}
	
	jobService.getAllJobs=function(){
		return $http.get("http://localhost:8088/collabroommiddleware/alljobs")
	}
	
	jobService.getJob=function(id){
		return $http.get("http://localhost:8088/collabroommiddleware/getjob/"+id)
	}
	
	return jobService;
})