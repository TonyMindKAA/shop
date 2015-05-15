package com.epam.student.krynytskyi.util.number;

/**
 * Created by Anton_Krynytskyi on 5/15/2015.
 */
public class NumberUtil {
    private NumberUtil() {}

    public static boolean isNumber(String number) {
        try
        {
            Long.parseLong(number);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }
}
