package com.bafoly.lib.stockcharts.iki.model.axis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@inheritDoc}
 */
public class DateAxis<Date> extends Axis<Date> {

    SimpleDateFormat sdf;

    public DateAxis(String format) {
        super(format);
        if(format!=null){
            sdf = new SimpleDateFormat(format);
        }

    }

    @Override
    public String getTextValue(Date value) {
        if(value!=null && sdf!=null){
            sdf.format(value);
        }
        return value.toString();
    }

}
