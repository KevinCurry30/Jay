package com.diligroup.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class BirthdayUtils {
    public static List<String> getYears(){
        List<String > yearList=new ArrayList<>();

        for (int i=1960;i<=2016;i++){
            StringBuilder  builder=new StringBuilder();
            builder.append(i).append("年");
            yearList.add(String.valueOf(builder));

        }
        return yearList;
    }
    public static List<String> getMonth(){
        List<String > monthList=new ArrayList<>();

        for (int i=1;i<=12;i++){
            StringBuilder  builder=new StringBuilder();
            builder.append(i).append("月");
            monthList.add(String.valueOf(builder));

        }
        return monthList;
    }

    /**
     *   1 3 5 7 9
     * @return
     */
    public static List<String> get31Day(){
        List<String > daysList=new ArrayList<>();

        for (int i=1;i<=31;i++){
            StringBuilder  builder=new StringBuilder();
            builder.append(i).append("日");
            daysList.add(String.valueOf(builder));

        }
        return daysList;
    }

    /**
     *   2  4  6  8 10
     * @return
     */
    public static List<String> get30Day(){
        List<String > daysList=new ArrayList<>();

        for (int i=1;i<=30;i++){
            StringBuilder  builder=new StringBuilder();
            builder.append(i).append("日");
            daysList.add(String.valueOf(builder));

        }
        return daysList;
    }

    public static List<String> get29Day(){
        List<String > daysList=new ArrayList<>();

        for (int i=1;i<=29;i++){
            StringBuilder  builder=new StringBuilder();
            builder.append(i).append("日");
            daysList.add(String.valueOf(builder));

        }
        return daysList;
    }
}
