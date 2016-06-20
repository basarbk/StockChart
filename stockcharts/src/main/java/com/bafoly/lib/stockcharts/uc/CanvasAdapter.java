package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/19/2016.
 */
public abstract class CanvasAdapter<C, P> {

    C canvas;

    public CanvasAdapter(C canvas){
        this.canvas = canvas;
    }

    public abstract void drawCandleStick(float open, float high, float low, float close, float barWidth, P candle, P frame);

    public abstract void drawOhlc(float open, float high, float low, float close, float barWidth, P line);

    public abstract void drawLine(float x1, float y1, float x2, float y2, P paint);

    public abstract void drawRect(float x1, float y1, float x2, float y2, P paint);

    public abstract void drawText(String text, float x1, float y1, P paint);
}
