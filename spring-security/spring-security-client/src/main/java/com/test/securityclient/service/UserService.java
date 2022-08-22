package com.test.securityclient.service;

import com.test.securityclient.entity.User;
import com.test.securityclient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationToken(String token, User user);
}
