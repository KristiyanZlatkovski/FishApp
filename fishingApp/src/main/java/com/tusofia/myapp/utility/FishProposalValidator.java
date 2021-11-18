package com.tusofia.myapp.utility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.tusofia.myapp.model.FishProp;


@Component
public class FishProposalValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return FishProp.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FishProp fishProp = (FishProp) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

        if (fishProp.getMaxSize() < 1.25) {
            errors.rejectValue("maxSize", "Size.fishPropForm.maxSizeLow");
        }

        if (fishProp.getMaxSize() > 600) {
            errors.rejectValue("maxSize", "Size.fishPropForm.maxSizeHigh");
        }

        if (fishProp.getMaxWeight() < 0.01) {
            errors.rejectValue("maxWeight", "Size.fishPropForm.maxWeightLow");
        }

        if (fishProp.getMaxWeight() > 1000) {
            errors.rejectValue("maxWeight", "Size.fishPropForm.maxWeightHigh");
        }


    }
}