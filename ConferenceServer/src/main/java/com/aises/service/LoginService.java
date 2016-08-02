package com.aises.service;

import com.aises.domain.User;
import com.aises.repository.UserRepository;
import com.aises.service.interfaces.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by Brendan on 8/1/2016.
 */
public class LoginService implements Service {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static LoginService instance;
    private static final UserRepository userRepository;

    static {
        userRepository = UserRepository.getInstance();
    }

    private LoginService() {
        logger.debug("Creating a service for Logins");
    }

    public static LoginService getInstance() {
        if(instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public boolean checkExistingSocialLogin(User user, String type) {
        String socialId = user.getSocialMediaId();
        try {
            return userRepository.socialMediaIdExists(socialId, type);
        } catch (SQLException e) {
            logger.error("Error trying to query Users!", e);
            return false;
        }
    }

    public User createNewUser(User user) {
        return null;
    }
}
