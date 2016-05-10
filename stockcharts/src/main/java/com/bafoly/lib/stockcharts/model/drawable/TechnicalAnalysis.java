package com.bafoly.lib.stockcharts.model.drawable;

import com.bafoly.lib.stockcharts.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.model.Environment;

/**
 * Abstract technical analysis class
 * Fibonacci Retracement, Support & Resistance Lines, Trendlines etc will be extending this class
 * Each technical analysis has it's own drawing algorithm
 * It has X and Y values
 * Generally X will be the date
 * Y will be Number
 */
public abstract class TechnicalAnalysis<X, Y extends Number> implements Drawable {

    X x1;

    Y y;

    private DrawStrategy drawStrategy;

    public TechnicalAnalysis(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }


    @Override
    public void draw(Environment environment) {
        drawStrategy.draw(environment, this);
    }
}
