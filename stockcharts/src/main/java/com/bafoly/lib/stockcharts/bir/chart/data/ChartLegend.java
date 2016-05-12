package com.bafoly.lib.stockcharts.bir.chart.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by basarb on 10/4/2015.
 */
public class ChartLegend {
    String title = "";

    HashMap<String, String> headerNvalues;
    List<String> keyIdxHolder;

    public ChartLegend(String title){
        this.title = title;
        this.headerNvalues = new HashMap<String, String>();
        keyIdxHolder = new ArrayList<String>();
    }

    public void addElement(String header, String value){
        headerNvalues.put(header, value);
        keyIdxHolder.add(header);
    }

    @Override
    public String toString() {
        String content = "";
        content = content + title+"     ";
        for(int i=0;i<keyIdxHolder.size();i++){
            content = content + keyIdxHolder.get(i)+": "+headerNvalues.get(keyIdxHolder.get(i));
            if(i!=keyIdxHolder.size()-1){
                content = content +" - ";
            }
        }
        return content;
    }
}

