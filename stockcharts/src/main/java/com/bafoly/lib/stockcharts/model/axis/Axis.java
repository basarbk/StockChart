package com.bafoly.lib.stockcharts.model.axis;

/**
 * Chart has axises
 * This class should be extended when customization is needed for axis styling
 */
public abstract class Axis<T> {

    // if this is a Date axis, the format is a data like "dd MM yyyy", etc
    // if this is a float axis, the format could be like #.##
    protected String format;

    // the maximum value in the range
    protected T max = null;

    // the minimum value in the range
    protected T min = null;

    // orientation either vertical or horizontal
    boolean isVertical = false;

    // major and minor ticks

    float minSpace = 50;


    public Axis() {
    }

    public Axis(String format) {
        this.format = format;
    }

    public T getMax() {
        return max;
    }

    public abstract void setMax(T max);

    public T getMin() {
        return min;
    }

    public abstract void setMin(T min);

    public abstract T getDiff();

    public abstract String getTextValue(T value);

}
