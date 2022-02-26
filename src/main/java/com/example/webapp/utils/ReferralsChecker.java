package com.example.webapp.utils;


import com.example.webapp.telegram.entities.UserEntity;
import com.example.webapp.telegram.query.referral.CheckReferralsQuery;
import com.example.webapp.telegram.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class ReferralsChecker {
    public static void check() {
        for(UserEntity user : UserService.getAllUsers())
        {
            new CheckReferralsQuery(user);
        }
    }
}
