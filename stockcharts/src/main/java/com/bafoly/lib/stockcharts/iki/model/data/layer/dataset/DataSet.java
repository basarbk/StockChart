package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

/**
 * Each chart thick may dependent on to single or multiple values<br>
 * to draw OHLC or Candle chart, we need arrays of open, high, low and close values<br>
 * to draw line graph, we need only one array of data (could be open, close or smt else)<br>
 * <br>
 * For technical analysis, DataSet will be holding XYPair(s).
 */
public interface DataSet<T> {

    static final int OPEN = 0;
    static final int HIGH = 1;
    static final int LOW = 2;
    static final int CLOSE = 3;
    static final int VOLUME = 4;

    static final int FIRST = 20;
    static final int SECOND = 21;
    static final int THIRD = 22;
    static final int FOURTH = 23;
    static final int FIFTH = 24;
    static final int SIXTH = 25;
    static final int SEVENTH = 26;

    <T> T getData(int index);

    void setData(int index, T t);

    void add(Number... t);

    void add(int index, T t);

    int getCount();

}
