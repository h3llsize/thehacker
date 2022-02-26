package com.example.webapp.telegram.service;

import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.generators.UserGenerator;
import com.example.webapp.telegram.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;

@Service
public class UserService {
    private static UserRepository userRepository;

    @Autowired
    private UserRepository tUserRepository;

    @PostConstruct
    public void init() {
        userRepository = tUserRepository;
    }

    public static UserEntity getUser(Update update) {
        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findById(update.getMessage().getChatId()).get();
        }

        catch (Exception e)
        {
            userEntity = new UserGenerator(update).userEntity;
            userRepository.save(userEntity);
            System.out.println("User was generating, ID : " + userEntity.getChatId());
        }

        return userEntity;
    }
    public static UserEntity getUser(Update update, boolean callback) throws Exception {
        UserEntity userEntity = null;

        if(callback) {
            try {
                userEntity = userRepository.findById(update.getMessage().getChatId()).get();
            }

            catch (Exception e)
            {
                userEntity = new UserGenerator(update.getCallbackQuery()).userEntity;
                userRepository.save(userEntity);
            }

        } else {
            getUser(update);
        }
        throw new Exception("User don't found at repository");
    }

    public static UserEntity getUser(Long Id) throws Exception {
        UserEntity userEntity = null;
            try {
                userEntity = userRepository.findById(Id).get();
                return userEntity;
            }

            catch (Exception e)
            {
                throw new Exception("Invalid user ID");
            }
    }

    public static UserEntity findUserByReferralCode(String referralCode) throws Exception {
        UserEntity userEntity = null;
        try {
            Iterable<UserEntity> userList = userRepository.findAll();
            for (UserEntity user : userList)
            {
                if(user.getReferralCode().equals(referralCode)){
                    return user;
                }
            }
            throw new Exception("Didn't find this user");
        }

        catch (Exception e)
        {
            throw new Exception("Invalid user ID");
        }
    }

    public static Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public static void saveUser(UserEntity user) {
        userRepository.save(user);
    }
}
