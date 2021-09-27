package com.tusofia.myapp.service;



import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tusofia.myapp.model.FishToWaterProp;
import com.tusofia.myapp.repository.FTWPRep;


@Service
public class FTWPServiceImpl implements FTWPService {
    @Autowired
    private FTWPRep fTWPRepository;
 

	


	@Override
	public void save(FishToWaterProp fishToWaterProp) {
		fTWPRepository.save(fishToWaterProp);
		
	}





	@Override
	public ArrayList<FishToWaterProp> findAll() {
		return fTWPRepository.findAll();
	}





	@Override
	public void deleteById(Long id) {
		fTWPRepository.deleteById(id);
		
	}





	@Override
	public FishToWaterProp findById(Long id) {
	
		return fTWPRepository.findById(id).get();
	}



}
