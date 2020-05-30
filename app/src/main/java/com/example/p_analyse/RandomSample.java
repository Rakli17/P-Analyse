package com.example.p_analyse;

import java.util.ArrayList;

public class RandomSample {

    ArrayList<SampleClass> mSample;
    SampleClass sampleClass;


    public RandomSample(){

    }

    public RandomSample(ArrayList<SampleClass> sample){
        this.mSample = sample;
    }

    public String createName(){
      double random =  Math.floor(Math.random()*3)+1;

      if (random == 1){
          return "Simon";
      }
      else if(random == 2){
          return "Rasmus";
      }
      else{
          return "Lasse";
      }
    }
    public String createDate(){
        double random =  Math.floor(Math.random()*3)+1;

        if (random == 1){
            return "23-08-2020";
        }
        else if(random == 2){
            return "06-12-2019";
        }
        else{
            return "09-5-2020";
        }

    }
    public int createLeu(){
        double random =  Math.floor(Math.random()*4)+1;


        if (random == 1){
            return 15;
        }
        else if(random == 2){
            return 70;
        }
        else if(random == 3){
            return 125;
        }
        else{
            return 500;
        }


    }
    public int createPro(){

        double random =  Math.floor(Math.random()*4)+1;
        int value = (int)random;
        return value;

    }
    public int createBlo() {
        double random = Math.floor(Math.random() * 2) + 1;

        if (random == 1) {
            double randomValue = Math.floor(Math.random() * 2) + 1;
            if (randomValue == 1) {
                return 101;
            } else if (random == 2) {
                return 801;
            }
        }
        if (random == 2) {
            double randomValue = Math.floor(Math.random() * 4) + 1;
            if (randomValue == 1) {
                return 10;
            } else if (random == 2) {
                return 80;
            } else if (random == 3) {
                return 125;
            } else {
                return 500;
            }
        }
        return 0;
    }
    public int createGlu(){
        double random = Math.floor(Math.random() * 5) + 1;
        if (random == 1) {
                return 5;
            } else if (random == 2) {
                return 14;
            } else if (random == 3) {
                return 28;
            } else if (random == 4) {
            return 55;
        } else {
                return 111;
            }
        }


    public int createNit(){
        double random = Math.floor(Math.random() * 2) + 1;
        int rando = (int)random;
        return rando;
    }
//String name, String date, int leu, int pro, int blo, int glu, boolean nit
    public SampleClass createRndSample()
    {
        ArrayList<SampleClass> tempSample = new ArrayList<>();
        RandomSample randomSample = new RandomSample();
        int glu = randomSample.createGlu();
        int blo =  randomSample.createBlo();
        String date   = randomSample.createDate();
        int leu =  randomSample.createLeu();
        int nit = randomSample.createNit();
        String name = randomSample.createName();
        int pro = randomSample.createPro();

        SampleClass s = new SampleClass(name, date, leu, pro, blo, glu, nit);

        tempSample.add(s);
        return s;
    }

}
