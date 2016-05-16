package com.bafoly.lib.stockcharts.iki.model.axis;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by basarb on 5/6/2016.
 */
public class StringDateAxis extends Axis<String> {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
    SimpleDateFormat sdfDefault;
    public StringDateAxis(String format) {
        super(format);
        sdfDefault = new SimpleDateFormat(format);
    }

    @Override
    public String getTextValue(String value) {
        try {
            return sdf.format(sdfDefault.parse(value));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }

}
