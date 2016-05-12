package com.bafoly.lib.stockcharts.iki.draw;

import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;

/**
 * Strategy Interface for drawing algorithm. <br>
 * Generic Type may be ChartData or TechnicalAnalysis<br>
 * Chart may contain Line, Candle Stick, OHLC or Bar Graph.<br>
 */
public interface DrawStrategy<T> {

    void draw(Environment environment, Painter paint, T drawableContent);

}
