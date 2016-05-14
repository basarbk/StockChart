package com.bafoly.lib.stockcharts.iki.model.axis;

/**
 * Chart has axises
 * This class should be extended when customization is needed for axis styling
 */
public abstract class Axis<T> {

    // if this is a Date axis, the format is a data like "dd MM yyyy", etc
    // if this is a float axis, the format could be like #.##
    protected String format;

    // orientation either vertical or horizontal
    boolean isVertical = false;

    // major and minor ticks

    float minSpace = 50;


    public Axis() {
    }

    public Axis(String format) {
        this.format = format;
    }

    public abstract String getTextValue(T value);

}
