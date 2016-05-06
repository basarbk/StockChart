package com.bafoly.lib.stockcharts.draw;

import android.graphics.Canvas;

import com.bafoly.lib.stockcharts.model.BaseModel;
import com.bafoly.lib.stockcharts.model.ChartData;
import com.bafoly.lib.stockcharts.model.Draw;

/**
 * Draw strategy can be implemented for different shapes
 * Line, Candle Stick, Histogram etc
 */
public interface DrawStrategy {

    /**
     * @param canvas
     * @param drawableContent
     */
    void draw(Canvas canvas, Draw drawableContent);
}
