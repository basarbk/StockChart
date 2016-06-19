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
}
