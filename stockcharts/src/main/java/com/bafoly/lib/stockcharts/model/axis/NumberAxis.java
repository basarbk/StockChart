package com.bafoly.lib.stockcharts.model.axis;

import java.text.DecimalFormat;

/**
 * {@inheritDoc}
 */
public class NumberAxis extends Axis<Number> {

    DecimalFormat decimalFormat;

    public NumberAxis(String format) {
        super(format);
        if(format!=null){
            decimalFormat = new DecimalFormat(format);
        }
    }

    @Override
    public void setMax(Number max) {
        if(this.max==null) {
            this.max = max;
        } else {
            if(this.max.doubleValue()<max.doubleValue()){
                this.max = max;
            }
        }
    }

    @Override
    public void setMin(Number min) {
        if(this.min==null) {
            this.min = min;
        } else {
            if(this.min.doubleValue()>min.doubleValue()){
                this.min = min;
            }
        }
    }

    @Override
    public Number getDiff() {
        return this.max.doubleValue()-this.min.doubleValue();
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
