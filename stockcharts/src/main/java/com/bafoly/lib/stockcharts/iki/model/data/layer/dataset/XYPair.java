package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

/**
 * This is the base map for an X-Y data
 */
public class XYPair<X, Y extends Number> {

    X x;

    Y y;

    public XYPair() {
    }

    public XYPair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }
}
