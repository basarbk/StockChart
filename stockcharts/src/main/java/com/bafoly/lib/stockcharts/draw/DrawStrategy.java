package com.bafoly.lib.stockcharts.draw;

import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.Draw;
import com.bafoly.lib.stockcharts.model.PaintAdapter;

/**
 * Draw strategy can be implemented for different shapes
 * Line, Candle Stick, Histogram etc
 */
public abstract class DrawStrategy {

    public abstract void draw(CanvasAdapter canvasAdapter, Draw drawableContent);

}
