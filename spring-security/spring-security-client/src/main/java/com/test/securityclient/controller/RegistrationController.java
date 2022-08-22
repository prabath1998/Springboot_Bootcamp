package com.test.securityclient.controller;

import com.test.securityclient.entity.User;
import com.test.securityclient.event.RegistrationCompleteEvent;
import com.test.securityclient.model.UserModel;
import com.test.securityclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                appicationUrl(request)
        ));
        return "User registered successfully";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String resilt =userService.validateVerificationToken(token);

        if (resilt.equalsIgnoreCase("valid")){
            return "User Verifies Successfully..!";
        }else{
            return "Bad user..!";
        }
    }

    private String appicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
