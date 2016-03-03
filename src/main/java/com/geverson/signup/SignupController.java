package com.geverson.signup;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geverson.account.*;
import com.geverson.support.web.*;

import java.util.List;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup.signup";

	@Inject
	private AccountService accountService;

	@Inject
	private SignupFormValidator signupFormValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(signupFormValidator);
	}
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		SignupForm signupForm = new SignupForm();
		model.addAttribute("signupForm", signupForm);
        return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, BindingResult bindingResult, RedirectAttributes ra) {
		if (bindingResult.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		Account account = accountService.save(signupForm.createAccount());
		System.out.println("Account created" + account.toString());
		accountService.signin(account);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.jsp
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}
}
