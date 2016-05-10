package com.bafoly.lib.stockcharts.model;

/**
 * Created by basarb on 5/6/2016.
 */
public interface PaintAdapter<T> {

    String LINE_COLOR = "lineColor";
    String HIGH_COLOR = "highColor";
    String LOW_COLOR = "lowColor";
    String FRAME_COLOR = "frameColor";
    String AXIS_COLOR = "axisColor";

    T getPaint(String color);

    void setPaint(String color, T t);

    void setColor(String color, int c);
}
