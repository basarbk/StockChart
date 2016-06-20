package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/20/2016.
 */
public class LineD<Y extends Number> implements Drawable<DataSet<Y>> {

    @Override
    public boolean hasXaxis() {
        return false;
    }

    @Override
    public boolean isOverlay() {
        return false;
    }

    @Override
    public void invalidate() {

    }

    @Override
    public PaintAdapter getPaint() {
        return null;
    }

    @Override
    public DataSet<Y> getDataSet() {
        return null;
    }
}
