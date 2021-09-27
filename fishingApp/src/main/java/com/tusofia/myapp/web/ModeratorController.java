package com.tusofia.myapp.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tusofia.myapp.model.Comment;
import com.tusofia.myapp.model.FishJournal;
import com.tusofia.myapp.service.CommentService;
import com.tusofia.myapp.service.JournalService;


@Controller
@RequestMapping("/moderate")
public class ModeratorController {
	
	  @Autowired
	    private CommentService commentService;
	  
	  @Autowired
	    private JournalService journalService;
	  
	  
	  
	
	  @GetMapping("/browseUnmoderated")
	    public String browseUnmoderated(Model model) {
	        model.addAttribute("comments", commentService.findAllUnmoderated());
	        model.addAttribute("journals", journalService.findAllUnmoderated());
	       
	        
	        return "browseUnmoderated";
	    }
	   
	    
	    @DeleteMapping("/browseUnmoderated/removeComment")
	    public String removeComment(@RequestParam Long id,Model model) {
	    	
	    	try {
	    		commentService.deleteById(id);
	    		 } catch (Exception ex) {
	   
	    		 }
	        
			
			 model.addAttribute("comments", commentService.findAllUnmoderated());

		        
		     return "browseUnmoderated :: #commentTable";
		    
	    
	    }
	    
	    
	    @PutMapping("/browseUnmoderated/acceptComment")
	    public String acceptComment(@RequestParam Long id,Model model) {
	    	

	        
			Comment com=commentService.getCommentById(id);
			com.setModerated(true);
			commentService.save(com);
			 model.addAttribute("comments", commentService.findAllUnmoderated());

		        
		     return "browseUnmoderated :: #commentTable";
		    
	    
	    }
	    
	 
	    
	    @DeleteMapping("/browseUnmoderated/removeJournal")
	    public String removeJournal(@RequestParam Long id,Model model) {
	    	

	        
			journalService.deleteById(id);
			 model.addAttribute("journals", journalService.findAllUnmoderated());

		        
		     return "browseUnmoderated :: #journalTable";
		    
	    
	    }
	    
	    
	    @PutMapping("/browseUnmoderated/acceptJournal")
	    public String acceptJournal(@RequestParam Long id,Model model) {
	    	

	        
			FishJournal journal=journalService.getJournalById(id);
			journal.setModerated(true);
			journalService.save(journal);
			 model.addAttribute("journals", journalService.findAllUnmoderated());

		        
		     return "browseUnmoderated :: #journalTable";
		    
	    
	    }
	    
	    
	    
	    
	  
	  
	    
	    
	    
	
}
