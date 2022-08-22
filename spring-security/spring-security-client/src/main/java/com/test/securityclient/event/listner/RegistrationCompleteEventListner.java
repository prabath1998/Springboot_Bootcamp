package com.test.securityclient.event.listner;

import com.test.securityclient.entity.User;
import com.test.securityclient.event.RegistrationCompleteEvent;
import com.test.securityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListner implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token email
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token,user);
        //send mail to user

        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;

//        send verification email method
        log.info("Click the link for verify your account: {}",url);
    }
}
