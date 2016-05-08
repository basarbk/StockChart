package com.bafoly.lib.stockcharts.model.axis;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.ChartData;

/**
 * Chart has two Axis'
 * If the data range is not set for the Axis, the data will be taken from the {@link ChartData}
 */
public abstract class Axis<T> {

    protected String format;

    protected T max = null;

    protected T min = null;

    // orientation either vertical or horizontal
    boolean isVertical = false;

    // major and minor ticks

    float minSpace;


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

    //public abstract void calculateMargins();

    public abstract void draw(CanvasAdapter canvasAdapter);

    public abstract void draw(CanvasAdapter canvasAdapter, T value);
}
