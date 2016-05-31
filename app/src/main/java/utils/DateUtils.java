package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getDateYYMMDD() {

        Date currentDate = new Date();

        //System.out.println(myDate);
        //System.out.println(new SimpleDateFormat("MM-dd-yyyy").format(myDate));
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(myDate));
        //System.out.println(myDate);

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = sdf.parse("2013-09-18");
        String date=sdf.format(convertedCurrentDate );
        System.out.println(date);*/

        String date = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        return date;
    }

    public static String getFormattedDateDUMMY(String res){
        String strDate = "";
        try{
            Date dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(res);
            strDate = new SimpleDateFormat("dd MMM yyyy").format(dateObj);
        }catch ( Exception e ){
            e.printStackTrace();
        }

        return strDate;
    }


    public static String getFormattedDateDUMMYWithTime(String res){

        String strDate = "";

        try{
            Date dateObj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res);
            strDate = new SimpleDateFormat("hh:mm a dd MMM yyyy").format(dateObj);
        }catch ( Exception e ){
            e.printStackTrace();
        }

        return strDate;
    }

    public static String getDate(String res){
        String strDate = "";
        try{
            Date dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(res);
            strDate = new SimpleDateFormat("dd").format(dateObj);
        }catch ( Exception e ){
            e.printStackTrace();
        }

        return strDate;
    }

    public static String getMonthYear(String res){
        String strDate = "";
        try{
            Date dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(res);
            strDate = new SimpleDateFormat("MMM yyyy").format(dateObj);
        }catch ( Exception e ){
            e.printStackTrace();
        }

        return strDate;
    }

}
