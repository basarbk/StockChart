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
}
