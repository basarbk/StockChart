package com.bafoly.lib.stockcharts.model.drawable;

import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.axis.Axis;

/**
 * Technical Analysis is a custom chart data which has a limited set of X and Y values.<br>
 * Fibonacci Retracement, Support & Resistance Lines, Trendlines etc will be extending this class<br>
 * Each technical analysis has it's own drawing algorithm<br>
 * It has X and Y values<br>
 * Generally X will be the date<br>
 * Y will be Number<br>
 * Also each technical analysis has its own paint style<br>
 */
public class TechnicalAnalysis<X, Y extends Number> extends XYData<X, Y> {

    X x1;

    Y y1;

    public TechnicalAnalysis() {
        super(null, null);
    }

    public TechnicalAnalysis(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    @Override
    public void draw(Environment environment) {
        dataDrawStrategy.draw(environment, this);
    }
}
