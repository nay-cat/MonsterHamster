package com.gmail.nayra.boss;

import java.util.List;
import java.util.Random;

public class GetBoss {

    public String getRandomBoss(List<String> bossList) {
        Random r = new Random();
        return bossList.get(r.nextInt(bossList.size()));
    }
}
