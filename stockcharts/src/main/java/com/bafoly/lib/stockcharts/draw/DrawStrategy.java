package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.Environment;

/**
 * Strategy Interface for drawing algorithm. <br>
 * Generic Type may be ChartModel or TechnicalAnalysis<br>
 * Chart may contain Line, Candle Stick, OHLC or Bar Graph.<br>
 */
public interface DrawStrategy<T> {

    void draw(Environment environment, T drawableContent);

}
