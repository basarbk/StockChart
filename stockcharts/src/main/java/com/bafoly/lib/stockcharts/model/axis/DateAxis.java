package com.bafoly.lib.stockcharts.model.axis;

import java.text.SimpleDateFormat;

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
