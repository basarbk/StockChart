package com.bafoly.lib.stockcharts.uc;

/**
 * Y is the type of data set. It's either List (array of Stock data) or XYPair (Technical Analysis)
 */
public abstract class Drawable<Y> {

    PaintAdapter paintAdapter;

    DataSet<Y> dataSet;

    DrawStrategy drawStrategy;

    boolean highlightAxisX;

    boolean highlightAxisY;

    abstract void invalidate(Environment environment, Timeline timeline);

    public boolean isHighlightAxisX() {
        return highlightAxisX;
    }

    public void setHighlightAxisX(boolean highlightAxisX) {
        this.highlightAxisX = highlightAxisX;
    }

    public boolean isHighlightAxisY() {
        return highlightAxisY;
    }

    public void setHighlightAxisY(boolean highlightAxisY) {
        this.highlightAxisY = highlightAxisY;
    }

    public PaintAdapter getPaintAdapter() {
        return paintAdapter;
    }

    public void setPaintAdapter(PaintAdapter paintAdapter) {
        this.paintAdapter = paintAdapter;
    }

    public DataSet<Y> getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet<Y> dataSet) {
        this.dataSet = dataSet;
    }
}
