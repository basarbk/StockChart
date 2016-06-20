package com.bafoly.lib.stockcharts.uc;

/**
 * Y is the type of data set. It's either List (array of Stock data) or XYPair (Technical Analysis)
 */
public interface Drawable<Y> {

    boolean highlightAxisX();

    boolean highlightAxisY();

    void invalidate(Environment environment, Timeline timeline);

    PaintAdapter getPaint();

    DataSet<Y> getDataSet();
}
