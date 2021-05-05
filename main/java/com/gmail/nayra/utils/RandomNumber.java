package com.gmail.nayra.utils;

import java.util.Random;

public class RandomNumber {

    public int getRandonNumber(int max){
        Random rar= new Random();
        return rar.nextInt(max);
    }
}
