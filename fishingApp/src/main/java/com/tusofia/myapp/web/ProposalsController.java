package com.tusofia.myapp.web;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tusofia.myapp.model.Fish;
import com.tusofia.myapp.model.FishProp;
import com.tusofia.myapp.model.FishToWaterProp;
import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.model.WaterProp;
import com.tusofia.myapp.service.FTWPService;
import com.tusofia.myapp.service.FishProposalService;
import com.tusofia.myapp.service.FishService;
import com.tusofia.myapp.service.PictureService;
import com.tusofia.myapp.service.UserService;
import com.tusofia.myapp.service.WaterProposalService;
import com.tusofia.myapp.service.WaterService;


@Controller
public class ProposalsController {
	@Autowired
    private FTWPService fTWPService;
    @Autowired
    private WaterService waterService;
    
    @Autowired
    private FishService fishService;
    
    @Autowired
    private WaterProposalService waterPropService;
    
    @Autowired
    private FishProposalService fishPropService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PictureService pictureService;
    
    
    @Autowired
    private FishProposalValidator fishProposalValidator;
    
    @Autowired
    private PictureValidator pictureValidator;
	
    @Autowired
    private WaterProposalValidator waterValidator;
    
    @Autowired
    private FishToWaterValidator fishToWaterValidator;
    
	 
	 
	    @GetMapping("/fishToWaterProp")
	    public String propFishToWater(Model model) {
	      
	        model.addAttribute("waters", waterService.findAll());
	        model.addAttribute("propForm", new FishToWaterProp());
	        model.addAttribute("fishesNot", new ArrayList<Fish>());
	        return "fishToWaterProp";
	    }
	 
	 
	    
	    @GetMapping("/fishesNot")
	    public String getFishesByNotWater(@RequestParam Long id,Model model) {
	    	
	    	Water waters=new Water();
	    	waters.setId(id);
	    	ArrayList<Fish> excludedFish= fishService.findAllByWaters(waters);
	    	ArrayList<Fish> allFish=(ArrayList<Fish>) fishService.findAll();
	 
	    	for(int i=0;i<excludedFish.size();i++) {
	    		
	    		allFish.remove(excludedFish.get(i));
	    		}
	    	
	    	  model.addAttribute("fishesNot", allFish);
	    	  model.addAttribute("propForm", new FishToWaterProp());
	    	
	    	 return "fishToWaterProp :: #fish";
	    		
	    	}
	    	
	    	
	    	
	    
	    
	    
	    
	    
	    
	    
	 
	    @PostMapping("/fishToWaterProp")
	    public String propFishToWater(@ModelAttribute("propForm") FishToWaterProp propForm,BindingResult bindingResult,@AuthenticationPrincipal Object principal) {
	    			
	    	fishToWaterValidator.validate(propForm, bindingResult);
			
			
		        if (bindingResult.hasErrors()) {
		        	
		        	  return "fishToWaterProp";
		        }
	       String  username = ((UserDetails)principal).getUsername();
	       propForm.setUsers(userService.findByUsername(username));
	       fTWPService.save(propForm);
	       

	        return "redirect:/";
	    }
	 
		@GetMapping("/fishProposal")
	    public String makeFishProposal(Model model) {
	     
	        model.addAttribute("fishForm", new FishProp());
	      
	        return "fishProposal";
	    }
	 
		
		
		@PostMapping("/fishProposal")
	    public String makeFishProposal(@ModelAttribute("fishForm") FishProp fishForm,  
	    		@RequestParam("image") MultipartFile multipartFile,BindingResult bindingResult,@AuthenticationPrincipal Object principal)throws IOException { {
	    			
	    			
	    			fishProposalValidator.validate(fishForm, bindingResult);
	    			 if(!multipartFile.isEmpty()) {
	    				 pictureValidator.validate(multipartFile, bindingResult);
	    			 }
	    			 
	    		        if (bindingResult.hasErrors()) {
	    		        	
	    		        	  return "fishProposal";
	    		        }			
	    			

	       fishForm.setPath(pictureService.getFishPicUploadPath(multipartFile));
	       String  username = ((UserDetails)principal).getUsername();
	       fishForm.setUsers(userService.findByUsername(username));
	       fishPropService.save(fishForm);
	      
	        return "redirect:/";
	    }
	    
	    
	    }  
		
		
		
		@GetMapping("/waterProposal")
	    public String makeWaterProposal(Model model) {
	     
	        model.addAttribute("waterForm", new WaterProp());
	      
	        return "waterProposal";
	    }
		
		
		@PostMapping("/waterProposal")
	    public String makeWaterProposal(@ModelAttribute("waterForm") WaterProp waterForm,  
	    		@RequestParam("image") MultipartFile multipartFile,BindingResult bindingResult,@AuthenticationPrincipal Object principal)throws IOException { {
	    			
	    			waterValidator.validate(waterForm, bindingResult);
	    			 if(!multipartFile.isEmpty()) {
	    				 pictureValidator.validate(multipartFile, bindingResult);
	    			 }
	    			 
	    		        if (bindingResult.hasErrors()) {
	    		        	
	    		        	  return "waterProposal";
	    		        }	
	       
	       waterForm.setPath(pictureService.getWaterPicUploadPath(multipartFile));
	       String  username = ((UserDetails)principal).getUsername();
	       waterForm.setUsers(userService.findByUsername(username));
	       waterPropService.save(waterForm);
	       

	        return "redirect:/";
	    }
	    
	    
	    }  
		
		
		@DeleteMapping("/admin/proposals/removeProposalFTW")
	    public String removeProposalFTW(@RequestParam Long id,Model model) {
	    	

	        
			fTWPService.deleteById(id);
		    model.addAttribute("fishToWaterProps", fTWPService.findAll());
	    	
			 return "browseProposals :: #fishToWaterTable";
	    }
		
		
		@PostMapping("/admin/proposals/acceptProposalFTW")
	    public String acceptProposalFTW(@RequestParam Long id,Model model) {
	    	
			FishToWaterProp current=fTWPService.findById(id);
			Water water=current.getWaters();
			 water.addFish(current.getFishes());
			waterService.saveWater(water);
			fTWPService.deleteById(id);
	    	
		    model.addAttribute("fishToWaterProps", fTWPService.findAll());
			  return "browseProposals :: #fishToWaterTable";
	    }
		
		
		
		@DeleteMapping("/admin/proposals/removeProposalFish")
	    public String removeProposalFish(@RequestParam Long id,Model model) {
	    	

	        
			fishPropService.deleteById(id);
			  model.addAttribute("fishProps", fishPropService.findAll());
	    	
			  return "browseProposals :: #fishTable";
	    }
		
		
		
		
		@PostMapping("/admin/proposals/acceptProposalFish")
	    public String acceptProposalFish(@RequestParam Long id,Model model) {
	    	
			FishProp fishProp=fishPropService.findById(id);
			Fish fish=fishPropService.proposalToRecord(fishProp);
			fishService.save(fish);
			fishPropService.deleteById(id);
	    	
			 model.addAttribute("fishProps", fishPropService.findAll());
			 
			  return "browseProposals :: #fishTable";
	    }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		@DeleteMapping("/admin/proposals/removeProposalWater")
	    public String removeProposalWater(@RequestParam Long id,Model model) {
	    	

	        
			waterPropService.deleteById(id);
		     model.addAttribute("waterProps", waterPropService.findAll());
	
		        
		     return "browseProposals :: #waterTable";
	    
	    }
		
		
		
		@PostMapping("/admin/proposals/acceptProposalWater")
	    public String acceptProposalWater(@RequestParam Long id,Model model) {
	    	
			
			 WaterProp prop= waterPropService.findById(id);
			 Water water=waterPropService.proposalToRecord(prop);
			 waterService.saveWater(water);
			 waterPropService.deleteById(id);
		     model.addAttribute("waterProps", waterPropService.findAll());
		   
		        
		     return "browseProposals :: #waterTable";
	    
	    }
		
		
		
	
		
		
		
	 
}
