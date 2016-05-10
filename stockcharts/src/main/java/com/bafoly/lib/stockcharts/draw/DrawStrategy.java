package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.drawable.Drawable;

/**
 * Drawable strategy can be implemented for different shapes
 * Line, Candle Stick, Histogram etc
 */
public interface DrawStrategy {

    void draw(Environment environment, Drawable drawableContent);

}
