package com.nalovma.kittyday.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {

    public static String getUniqueID() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat curFormater = new SimpleDateFormat("yyyyMMddhhmmss", Locale.ENGLISH);
        String time = curFormater.format(currentDate);
        // random 4 numbers
        Random rand = new Random();
        String randomNumber = String.format(Locale.ENGLISH, "%06d", rand.nextInt(100000));
        return time + randomNumber;
    }
}
