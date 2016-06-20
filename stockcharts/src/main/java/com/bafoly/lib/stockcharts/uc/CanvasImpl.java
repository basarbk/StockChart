package com.bafoly.lib.stockcharts.uc;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by basarb on 6/19/2016.
 */
public class CanvasImpl extends CanvasAdapter<Canvas, Paint> {

    public CanvasImpl(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawCandleStick(float open, float high, float low, float close, float barWidth, Paint candle, Paint frame) {

    }

    @Override
    public void drawOhlc(float open, float high, float low, float close, float barWidth, Paint line) {

    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2, Paint paint) {

    }

    @Override
    public void drawRect(float x1, float y1, float x2, float y2, Paint paint) {

    }

    @Override
    public void drawText(String text, float x1, float y1, Paint paint) {

    }
}
