package com.bafoly.lib.stockcharts.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by basarb on 5/6/2016.
 */
public class AndroidCanvas extends CanvasAdapter<Canvas, Paint> {

    public AndroidCanvas(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2, Paint paint) {
        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    @Override
    public void drawRect(float x1, float y1, float x2, float y2, Paint paint) {
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    @Override
    public void drawPath(Object object, Paint paint) {
        canvas.drawPath((Path)object, paint);
    }

    @Override
    public int getWidth() {
        return canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return canvas.getHeight();
    }
}
