$(document).ready(function () {
    
   
   
      var token = $("meta[name='_csrf']").attr("content");
      var header = $("meta[name='_csrf_header']").attr("content");


      $(document).ajaxSend(function(e, xhr, options) {
          xhr.setRequestHeader(header, token);
      });
     
});






function tournamentRewardDistribute(id){
	
	
	$.ajax({
	    type : "PUT",
	    url : "/admin/tournament/distributeRewards",
	    data: {"id" : id},
	    

	    success: function (fragment) {       
	    	 $("#tourTable").replaceWith(fragment);
			              
	    },
	    error: function (e) {
	        console.log(e);
	    }
	})  
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	