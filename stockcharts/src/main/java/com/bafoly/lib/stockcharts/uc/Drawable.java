package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/19/2016.
 */
public interface Drawable<X, Y extends Number> {

    boolean hasXaxis();

    boolean isOverlay();

    X getAxisX();

    Y getAxisY();

    void invalidate();

    PaintAdapter getPaint();
}
