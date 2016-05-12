package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.model.Environment;

/**
 * Abstract technical analysis class
 * Fibonacci Retracement, Support & Resistance Lines, Trendlines etc will be extending this class
 * Each technical analysis has it's own drawing algorithm
 * It has X and Y values
 * Generally X will be the date
 * Y will be Number
 * Also each technical analysis has its own paint style
 */
public abstract class TechnicalAnalysis<X, Y extends Number> extends XYData<X, Y> {

    X x1;

    Y y;


    @Override
    public void draw(Environment environment) {

    }
}
