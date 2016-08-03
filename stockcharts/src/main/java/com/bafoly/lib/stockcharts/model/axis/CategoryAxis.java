package com.bafoly.lib.stockcharts.model.axis;

/**
 * Created by basarb on 5/5/2016.
 */
public class CategoryAxis extends Axis<String> {

    public CategoryAxis(String format) {
        super(format);
    }

    @Override
    public String getTextValue(String value) {
        return value;
    }

}
