package com.bafoly.lib.stockcharts.iki.model.axis;

import java.text.DecimalFormat;

/**
 * {@inheritDoc}
 */
public class NumberAxis<Number> extends Axis<Number> {

    DecimalFormat decimalFormat;

    public NumberAxis(String format) {
        super(format);
        if(format!=null){
            decimalFormat = new DecimalFormat(format);
        }
    }

    @Override
    public String getTextValue(Number value) {
        if(value!=null){
            if(format!=null){
                return decimalFormat.format(value);
            }
            return value.toString();
        }
        return null;
    }

}
