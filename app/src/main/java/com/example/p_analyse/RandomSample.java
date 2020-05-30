package com.example.p_analyse;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//denne klasse opretter nogle random værdier, da vi ikke kunne skabe værdier ud fra opencv
public class RandomSample {


    public RandomSample(){

    }
    //blev brugt førhen da vi ville teste vores sytem
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
        String dateStr = "04/05/2010";
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        return formattedDate;

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
        double random = Math.floor(Math.random() * 2);
        int rando = (int)random;
        return rando;
    }



}
