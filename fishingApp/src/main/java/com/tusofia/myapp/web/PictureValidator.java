package com.tusofia.myapp.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;



@Component
public class PictureValidator implements Validator {
 
	public static final long TEN_MB_IN_BYTES = 10485760;
	
    @Override
    public boolean supports(Class<?> aClass) {
        return MultipartFile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MultipartFile file = (MultipartFile) o;

        if(!(file.getContentType().toLowerCase().equals("image/jpg") 
                || file.getContentType().toLowerCase().equals("image/jpeg") 
                || file.getContentType().toLowerCase().equals("image/png"))){
               errors.reject("FileNotCorrectFormat.image");
           }
        
        if(file.getSize() > TEN_MB_IN_BYTES){
        	errors.reject("upload.exceeded.file.size");
        }
    }
}