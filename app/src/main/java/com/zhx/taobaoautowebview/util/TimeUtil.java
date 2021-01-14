package com.zhx.taobaoautowebview.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeUtil {

    public static String getCurrentTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
