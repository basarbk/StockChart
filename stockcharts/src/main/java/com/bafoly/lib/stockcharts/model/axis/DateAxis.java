package com.bafoly.lib.stockcharts.model.axis;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@inheritDoc}
 */
public class DateAxis extends Axis<Date> {

    SimpleDateFormat sdf;

    public DateAxis(String format) {
        super(format);
        if(format!=null){
            sdf = new SimpleDateFormat(format);
        }

    }

    @Override
    public void setMax(Date max) {

    }

    @Override
    public void setMin(Date min) {

    }

    @Override
    public Date getDiff() {
        return null;
    }

    @Override
    public String getTextValue(Date value) {
        if(value!=null && sdf!=null){
            sdf.format(value);
        }
        return value.toString();
    }

    @Override
    public void draw(CanvasAdapter canvasAdapter) {

    }

    @Override
    public void draw(CanvasAdapter canvasAdapter, Date value) {

    }
}
