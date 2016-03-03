package com.geverson.signin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.POST)
    public Principal authenticate(Principal user){
        return user;
    }

}
