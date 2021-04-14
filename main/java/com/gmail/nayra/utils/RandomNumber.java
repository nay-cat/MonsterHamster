package com.gmail.nayra.utils;

import java.util.Random;

public class RandomNumber {

    public int getRandonNumber(int max){
        Random rar= new Random();
        int randonNumber = rar.nextInt(max);
        return randonNumber;
    }
}
