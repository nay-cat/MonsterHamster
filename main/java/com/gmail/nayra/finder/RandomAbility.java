package com.gmail.nayra.finder;

import java.util.List;
import java.util.Random;

public class RandomAbility {

    public String getRandomAbility(List<String> abilityList) {
        Random rar = new Random();
        String randomElement = abilityList.get(rar.nextInt(abilityList.size()));
        return randomElement;
    }

}
