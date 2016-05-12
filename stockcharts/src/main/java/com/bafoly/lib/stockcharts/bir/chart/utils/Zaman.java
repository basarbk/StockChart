package com.bafoly.lib.stockcharts.bir.chart.utils;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by basarb on 10/4/2015.
 */
public class Zaman {

    static String[] Tatil_TamGun = { "1-1", "4-23", "5-1", "5-19", "8-30",
            "10-29", "2008-9-30", "2008-10-1", "2008-10-2", "2008-12-8",
            "2008-12-9", "2008-12-10", "2008-12-11", "2009-9-21", "2009-9-22",
            "2009-11-27", "2009-11-30", "2010-9-9", "2010-9-10", "2010-9-11",
            "2010-11-16", "2010-11-17", "2010-11-18", "2010-11-19",
            "2011-8-31", "2011-9-1", "2011-11-7", "2011-11-8", "2011-11-9",
            "2012-8-20", "2012-8-21", "2012-10-25", "2012-10-26",
            "2013-8-8","2013-8-9","2013-8-10","2013-10-15","2013-10-16","2013-10-17","2013-10-18",
            "2014-7-28","2014-7-29","2014-7-30","2014-10-4","2014-10-5","2014-10-6","2014-10-7",
            "2015-7-17","2015-7-18","2015-7-19","2015-9-23","2015-9-24","2015-9-25","2015-9-26"};
    static String[] Tatil_YarimGun = { "10-28", "2008-9-29", "2008-12-7",
            "2009-11-26", "2010-9-8", "2010-11-15", "2011-8-29", "2012-3-2",
            "2012-3-2", "2012-10-24","2013-8-7","2013-10-14",
            "2014-7-27","2014-10-3", "2015-7-16","2015-9-22"};


    public static final int ZAMAN_SEANS_ONCESI = 0;
    public static final int ZAMAN_BIRINCI_SEANS_ICI = 1;
    public static final int ZAMAN_SEANS_ARASI_DOWNLOAD_ONCESI = 2;
    public static final int ZAMAN_SEANS_ARASI_DOWNLOAD=3;
    public static final int ZAMAN_IKINCI_SEANS_ICI = 4;
    public static final int ZAMAN_IKINCI_SEANS_SONU_DOWNLOAD_ONCESI = 5;
    public static final int ZAMAN_GUN_SONU_DOWNLOAD=6;
    public static final int ZAMAN_TATIL=7;
    public static final String[] ZamanIsimleri = {"ZAMAN_SEANS_ONCESI","ZAMAN_BIRINCI_SEANS_ICI","ZAMAN_SEANS_ARASI_DOWNLOAD_ONCESI",
            "ZAMAN_SEANS_ARASI_DOWNLOAD","ZAMAN_IKINCI_SEANS_ICI","ZAMAN_IKINCI_SEANS_SONU_DOWNLOAD_ONCESI",
            "ZAMAN_GUN_SONU_DOWNLOAD","ZAMAN_TATIL"};

    public static Calendar c = Calendar.getInstance();

    public static final int time_format_db = 0;
    public static final int time_format_liste = 1;
    public static final int time_format_ekinvest = 2;
    public static final int time_format_withHour = 3;
    public static final int time_format_for_fragment_display = 4;



    public static SimpleDateFormat sdfListe = new SimpleDateFormat("yyyy-M-d", Locale.US);
    public static SimpleDateFormat sdfEkinvest = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
    public static SimpleDateFormat sdfWithHour = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US);
    public static SimpleDateFormat sdfForFragment = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    public static SimpleDateFormat sdfDBGunluk = new SimpleDateFormat("yyyyMMdd", Locale.US);
    public static SimpleDateFormat sdfDBSeanslik = new SimpleDateFormat("yyyyMMddk", Locale.US);
    public static SimpleDateFormat sdf_long = new SimpleDateFormat("M.dd", Locale.US);
    public static SimpleDateFormat sdf_short = new SimpleDateFormat("dd", Locale.US);
    public static SimpleDateFormat sdf_investing_format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

    public static SimpleDateFormat sdf_year = new SimpleDateFormat("dd MMM yy", Locale.US);
    public static SimpleDateFormat sdf_month = new SimpleDateFormat("dd MMM", Locale.US);
    public static SimpleDateFormat sdf_day = new SimpleDateFormat("dd", Locale.US);

    public static String formatDate(long timeInMillis, int tip){
        Calendar temp = Calendar.getInstance();
        temp.setTimeInMillis(timeInMillis);
        return formatDate(temp, tip);
    }

    public static String formatDate(Calendar cal, int tip) {
        SimpleDateFormat sdf = sdfDBGunluk;
        switch (tip) {
            case time_format_liste:
                sdf = sdfListe;
                break;
            case time_format_ekinvest:
                sdf = sdfEkinvest;
                break;
            case time_format_withHour:
                sdf = sdfWithHour;
                break;
            case time_format_for_fragment_display:
                sdf = sdfForFragment;
                break;
        }
        return sdf.format(cal.getTime());
    }

    public static boolean TamGunTatilMi(Calendar k) {
        String gun = formatDate(k, time_format_liste);
        List<String> tarihler = Arrays.asList(Tatil_TamGun);
        String kisaTarih = gun.substring(5);
        if (k.get(Calendar.DAY_OF_WEEK) == 1
                || k.get(Calendar.DAY_OF_WEEK) == 7
                || tarihler.contains(kisaTarih) || tarihler.contains(gun)) {
            return true;
        }
        return false;
    }

    public static boolean YarimGunTatilMi(Calendar k) {
        String gun = formatDate(k, time_format_liste);
        if (1 < k.get(Calendar.DAY_OF_WEEK) && k.get(Calendar.DAY_OF_WEEK) < 7) {
            List<String> tarihler = Arrays.asList(Tatil_YarimGun);
            String kisaTarih = gun.substring(5);
            if (tarihler.contains(kisaTarih) || tarihler.contains(gun)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> isGunleri(Calendar dBaslangic, Calendar dBitis) {
        List<String> bakilacakGunler = new ArrayList<String>();
        String bugun;
        dBitis.add(Calendar.DATE, 1);
        while (dBaslangic.before(dBitis)) {
            if (1 < dBaslangic.get(Calendar.DAY_OF_WEEK) && dBaslangic.get(Calendar.DAY_OF_WEEK) < 7) {
                if (!TamGunTatilMi(dBaslangic)) {
                    bugun = formatDate(dBaslangic, time_format_db);
                    bakilacakGunler.add(bugun+ "1");
                    if (!YarimGunTatilMi(dBaslangic)) {
                        bakilacakGunler.add(bugun + "2");
                    }
                }
            }
            dBaslangic.add(Calendar.DATE, 1);
        }
        dBitis.add(Calendar.DATE, -1);
        return bakilacakGunler;
    }

    public static List<String> isGunleriOnly(Calendar dBaslangic, Calendar dBitis) {
        List<String> bakilacakGunler = new ArrayList<String>();
        String bugun;
        dBitis.add(Calendar.DATE, 1);
        while (dBaslangic.before(dBitis)) {
            if (1 < dBaslangic.get(Calendar.DAY_OF_WEEK) && dBaslangic.get(Calendar.DAY_OF_WEEK) < 7) {
                if (!TamGunTatilMi(dBaslangic)) {
                    bugun = formatDate(dBaslangic, time_format_db);
                    bakilacakGunler.add(bugun);
                }
            }
            dBaslangic.add(Calendar.DATE, 1);
        }
        dBitis.add(Calendar.DATE, -1);
        return bakilacakGunler;
    }

    public static String getSeans(Calendar c){
        String seans = "1";

        Calendar ref = Calendar.getInstance();
        ref.setTimeInMillis(c.getTimeInMillis());
        ref.set(Calendar.HOUR_OF_DAY, 14);
        ref.set(Calendar.MINUTE, 15);
        if(c.after(ref)){
            seans="2";
        }
        return seans;
    }

    public static Calendar getCalendarVersion(String day) {
        SimpleDateFormat sdf_db_string;
        if(day.length()==9){ // seanslik
//			sdf_db_string = new SimpleDateFormat("yyyyMMddk", Locale.US);
            sdf_db_string = sdfDBSeanslik;
        } else { // gunluk
//			sdf_db_string = new SimpleDateFormat("yyyyMMdd", Locale.US);
            sdf_db_string = sdfDBGunluk;
        }
        Calendar thisDate = Calendar.getInstance();
        try {
            thisDate.setTime(sdf_db_string.parse(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        thisDate.set(Calendar.HOUR_OF_DAY, 23);
        thisDate.set(Calendar.MINUTE, 59);
        return thisDate;
    }

    public static long getTimeInMillis(String day, int format){
        SimpleDateFormat sdf_db_string2 = sdfDBGunluk;
        switch(format){
            case time_format_for_fragment_display:
//			sdf_db_string2 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                sdf_db_string2 = sdfForFragment;
                break;
//		default:
//			sdf_db_string2 = new SimpleDateFormat("yyyyMMdd", Locale.US);
//			break;
        }
        Calendar thisDate = Calendar.getInstance();
        try {
            thisDate.setTime(sdf_db_string2.parse(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return thisDate.getTimeInMillis();
    }

    public static Calendar getNextWorkDay(Calendar today) {
        today.add(Calendar.DATE, 1);
        while (TamGunTatilMi(today)) {
            today.add(Calendar.DATE, 1);
        }
        today.set(Calendar.HOUR, 9);
        today.set(Calendar.MINUTE, 45);
        return today;
    }


    public static Calendar getPreviousXthWorkDay(Calendar today, int x){
        int dayCount = 0;
        while(dayCount!=x){
            today.add(Calendar.DATE,-1);
            while(TamGunTatilMi(today)){
                today.add(Calendar.DATE,-1);
            }
            dayCount++;
        }
        return today;
    }

    public static String getDayPeriodFormatOfDate(String chartDataTarih){
        Calendar theDate = getCalendarVersion(chartDataTarih);
        return formatDate(theDate, time_format_db);
    }

    public static String getWeekPeriodFormatOfDate(String chartDataTarih){
        Calendar theDate = getCalendarVersion(chartDataTarih);
        int currentWeek = theDate.get(Calendar.WEEK_OF_YEAR);
        while(true){
            if(theDate.get(Calendar.WEEK_OF_YEAR)==currentWeek){
                theDate.add(Calendar.DATE,-1);
            } else {
                break;
            }
        }
        theDate.add(Calendar.DATE,1);
        while(true){
            if(Zaman.TamGunTatilMi(theDate)){
                theDate.add(Calendar.DATE,1);
            } else {
                break;
            }
        }
        return formatDate(theDate, time_format_db);
    }

    public static String getMonthPeriodFormatOfDate(String chartDataTarih){
        Calendar theDate = getCalendarVersion(chartDataTarih);
        theDate.set(Calendar.DATE, 1);
        while(true){
            if(Zaman.TamGunTatilMi(theDate)){
                theDate.add(Calendar.DATE,1);
            } else {
                break;
            }
        }
        return formatDate(theDate, time_format_db);
    }


    public static boolean gunlerAyniMi(Calendar a, Calendar b){
        String formattedA = formatDate(a, time_format_ekinvest);
        String formattedB = formatDate(b, time_format_ekinvest);
        if(formattedA.equalsIgnoreCase(formattedB)){
            return true;
        }
        return false;
    }


    public static String getDate(ChartPosition chartPosition, int currentIDXX, MainData mainData){
        Calendar tempCal;
        int totalValCount = mainData.getDates().size();
        if(currentIDXX>=totalValCount){
            String lastChartDate = mainData.getDates().get(totalValCount-1);
            tempCal = getCalendarVersion(lastChartDate);
            if(mainData.isInstrument()){
                tempCal.add(Calendar.DATE,((currentIDXX-totalValCount)*mainData.getPeriodMultiplier()));
            }
            if(mainData.getPeriodMultiplier()==Chart.DATA_PERIOD_HALF_DAY){
                return sdfDBGunluk.format(tempCal.getTime());
            } else {
                return sdfDBGunluk.format(tempCal.getTime())+"1";
            }
        } else {
            if(mainData.isInstrument()){
                if(((MainInstrumentData)mainData).getPeriod()==Chart.DATA_PERIOD_HALF_DAY){
                    return mainData.getDates().get(currentIDXX);
                } else {
                    return mainData.getDates().get(currentIDXX)+"1";
                }
            } else {
                if(mainData.getParentInstrumentData().getPeriod() == Chart.DATA_PERIOD_HALF_DAY){
                    return mainData.getDates().get(currentIDXX);
                } else {
                    return mainData.getDates().get(currentIDXX)+"1";
                }
            }
        }
    }

}