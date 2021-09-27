package com.tusofia.myapp.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tusofia.myapp.model.WaterProp;


@Component
public class WaterProposalValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return WaterProp.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	WaterProp waterProp = (WaterProp) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "latitude", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longtitude", "NotEmpty");
        
        if (waterProp.getLatitude() <=0 ) {
            errors.rejectValue("latitude", "Location.waterProp.latitude");
        }
        
        if (waterProp.getLongtitude()  <=0) {
            errors.rejectValue("longtitude", "Location.waterProp.longtitude");
        }
        
     
        
        
        
        
    }
}