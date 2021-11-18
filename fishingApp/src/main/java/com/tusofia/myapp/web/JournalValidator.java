package com.tusofia.myapp.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tusofia.myapp.model.FishJournal;


@Component
public class JournalValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return FishJournal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        @SuppressWarnings("unused")
        FishJournal journal = (FishJournal) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "waters", "NotEmpty");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fishes", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baits", "NotEmpty");


    }
}