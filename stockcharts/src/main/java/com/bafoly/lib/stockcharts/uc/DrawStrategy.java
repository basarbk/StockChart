package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/19/2016.
 */
public interface DrawStrategy<T> {

    public void draw(Environment environment, Timeline timeline, T drawable);
}
