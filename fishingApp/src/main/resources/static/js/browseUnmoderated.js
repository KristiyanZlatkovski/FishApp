$(document).ready(function () {
    jQuery('#ajaxForm').submit( function(e){ 
    e.preventDefault();
});
      $("#choice").change(function() {
    	  changeBrowse();
    });
   
   
      var token = $("meta[name='_csrf']").attr("content");
      var header = $("meta[name='_csrf_header']").attr("content");


      $(document).ajaxSend(function(e, xhr, options) {
          xhr.setRequestHeader(header, token);
      });
     
});




function changeBrowse() {
    var choice = $("#choice").val();
    
switch(choice){

case("Comment"):
document.getElementById("commentTable").style.display = "block";
document.getElementById("journalTable").style.display = "none";
break;
	
case("Journal"):
document.getElementById("commentTable").style.display = "none";
document.getElementById("journalTable").style.display = "block";
break;
}
    
    
  
};




function removeComment(id){
	


	
	
	$.ajax({
	    type : "DELETE",
	    url : "/moderate/browseUnmoderated/removeComment",
	    data: {"id" : id},
	    

	    success: function (fragment) {       
	    	 $("#commentTable").replaceWith(fragment);
			 document.getElementById("commentTable").style.display = "block";               
	    },
	    error: function (e) {
	        console.log(e);
	    }
	})   
	
		
}



function acceptComment(id){
	
	
	
	$.ajax({
	    type : "PUT",
	    url : "/moderate/browseUnmoderated/acceptComment",
	    data: {"id" : id},
	    

	    success: function (fragment) {       
	    	 $("#commentTable").replaceWith(fragment);
			 document.getElementById("commentTable").style.display = "block";               
	    },
	    error: function (e) {
	        console.log(e);
	    }
	})  
		
	}


function removeJournal(id){
	


	
	
	$.ajax({
	    type : "DELETE",
	    url : "/moderate/browseUnmoderated/removeJournal",
	    data: {"id" : id},
	    

	    success: function (fragment) {       
	    	 $("#journalTable").replaceWith(fragment);
			 document.getElementById("journalTable").style.display = "block";               
	    },
	    error: function (e) {
	        console.log(e);
	    }
	})   
	
		
}

function acceptJournal(id){
	
	
	
	$.ajax({
	    type : "PUT",
	    url : "/moderate/browseUnmoderated/acceptJournal",
	    data: {"id" : id},
	    

	    success: function (fragment) {       
	    	 $("#journalTable").replaceWith(fragment);
			 document.getElementById("journalTable").style.display = "block";               
	    },
	    error: function (e) {
	        console.log(e);
	    }
	})  
		
	}

