package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/19/2016.
 */
public interface Environment {

    CanvasAdapter getCanvasAdapter();

    int getXBegin();

    int getXEnd();

    <T extends Number> float getX(T value);

    <T extends Number> float getY(T value);
}
