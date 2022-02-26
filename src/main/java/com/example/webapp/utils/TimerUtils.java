package com.example.webapp.utils;

import com.example.webapp.telegram.query.referral.BomberQuery;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class TimerUtils extends Thread{

    @PostConstruct
    private void init() {
        start();
    }

    @Override
    public void run() {
        startTimer();
    }

    public void startTimer() {
        while (true) {
            System.out.println("TimeUtils : Action");
            new PostChecker();
            BomberQuery.startAttack();
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
