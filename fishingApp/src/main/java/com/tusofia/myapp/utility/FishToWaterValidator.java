package com.tusofia.myapp.utility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.tusofia.myapp.model.FishToWaterProp;


@Component
public class FishToWaterValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return FishToWaterProp.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        @SuppressWarnings("unused")
        FishToWaterProp waterProp = (FishToWaterProp) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "waters", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fishes", "NotEmpty");


    }
}