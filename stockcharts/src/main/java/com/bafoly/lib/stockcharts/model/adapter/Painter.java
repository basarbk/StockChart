package com.bafoly.lib.stockcharts.model.adapter;

/**
 * This interface is for decoupling the frameworks Paint object with this Stock Chart library algorithms
 */
public interface Painter<T> {

    String LINE_COLOR = "lineColor";
    String SHADOW_COLOR = "shadowColor";
    String HIGH_COLOR = "highColor";
    String LOW_COLOR = "lowColor";
    String FRAME_COLOR = "frameColor";
    String AXIS_COLOR = "axisColor";
    String AXIS_TEXT_COLOR = "axisTextColor";
    String BAR_COLOR = "barColor";

    void init();

    T getPaint(String color);

    void setPaint(String color, T t);

    void setColor(String color, int c);

    void setDensity(float density);
}
