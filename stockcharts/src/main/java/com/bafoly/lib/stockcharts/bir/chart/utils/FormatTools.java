package com.bafoly.lib.stockcharts.bir.chart.utils;

import android.graphics.Paint;

import com.bafoly.lib.stockcharts.bir.chart.data.ChartLegend;

import java.text.DecimalFormat;

/**
 * Created by basarb on 10/4/2015.
 */
public class FormatTools {
    static DecimalFormat form = new DecimalFormat("#.##");
    static DecimalFormat form_macd = new DecimalFormat("#.###");

    public FormatTools() {
    }

    public static String formatFloat(float val){
        return form.format(val);
    }

    public static String formatMACD(float val){
        return form_macd.format(val);
    }

    public static float getWidthForLegend(ChartLegend[] list, Paint p){
        float width = 0;
        for(ChartLegend c:list){
            float curWidth = p.measureText(c.toString());
            if(curWidth>width){
                width = curWidth;
            }
        }
        return width;

    }
}
