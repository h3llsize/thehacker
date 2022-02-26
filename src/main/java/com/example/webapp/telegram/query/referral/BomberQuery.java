package com.example.webapp.telegram.query.referral;

import com.example.webapp.telegram.bomber.Attack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BomberQuery extends Thread{
    private static final HashMap <Long, Attack> query = new HashMap<>();
    private static final List <Long> position = new ArrayList<>();
    private final Attack attack;

    public BomberQuery(Attack attack) {
        this.attack = attack;
        start();
    }


    public static String addAttack(Long userId, Attack attack) {
        if (!position.contains(userId)) {
            position.add(userId);
            query.put(userId, attack);
            return "Вы в очереди : " + position.size();
        }
        return "Вы уже стоите в очереди\\!";
    }

    public static void startAttack()
    {

        try {
            Long userId = position.get(0);
            Attack attack = query.get(userId);
            position.remove(userId);
            query.remove(userId);

            new BomberQuery(attack);
        } catch (Exception ignored)
        {
        }
    }

    @Override
    public void run() {
        attack.start();
    }

    public static int getQueryUser(Long userId)
    {
        for (int i = 0; i < position.size(); i++) {
            if(position.get(i).equals(userId))
                return i;
        }
        return -1;
    }
}
