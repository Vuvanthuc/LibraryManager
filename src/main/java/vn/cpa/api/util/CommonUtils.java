package vn.cpa.api.util;

import java.util.regex.Pattern;

public class CommonUtils {

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static int getTotalDayInMonth(int month, int year) {
        int day = 0;
        switch (month) {
            case 1: case 3: case 5:  case 7: case 8: case 10: case 12:
                day = 31;
                break;
            case 4: case 6: case 9:  case 11:
                day = 30;
                break;
            case 2:
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                    day = 29;
                else
                    day = 28;
                break;
            default:
                break;
        }
        return day;
    }
}
