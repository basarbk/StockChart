package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

import java.util.List;

/**
 * Each chart thick may dependent on to single or multiple values<br>
 * e.g to draw OHLC or Candle chart, we need open, high, low and close values<br>
 * e.g to draw line graph, we need only one parameter (could be close or smt else)
 */
public interface DataSet<T> {

    public static final int OPEN = 0;
    public static final int HIGH = 1;
    public static final int LOW = 2;
    public static final int CLOSE = 3;
    public static final int VOLUME = 4;

    public static final int FIRST = 20;
    public static final int SECOND = 21;
    public static final int THIRD = 22;
    public static final int FOURTH = 23;
    public static final int FIFTH = 24;
    public static final int SIXTH = 25;
    public static final int SEVENTH = 26;

    public <T> T getData(int index);

    public void setData(int index, T t);

    public void add(Number... t);

    public void add(int index, T t);

    public int getCount();

}
