package com.geverson.signup;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignupFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return SignupForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o == null){
            errors.reject("empty.object");
        }
        SignupForm signupForm = (SignupForm)o;
        if(signupForm == null){
            errors.reject("invalid.object");
        }
    }

}
