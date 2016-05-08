package com.bafoly.lib.stockcharts.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by basarb on 5/6/2016.
 */
public class AndroidCanvas extends CanvasAdapter<Canvas, Paint> {

    Path path;

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

    @Override
    public void createPath() {
        path = new Path();
    }

    @Override
    public <T extends Number> void moveTo(T t1, T t2) {
        path.moveTo(t1.floatValue(), t2.floatValue());
    }

    @Override
    public <T extends Number> void lineTo(T t1, T t2) {
        path.lineTo(t1.floatValue(), t2.floatValue());
    }

    @Override
    public void drawPath(Paint paint) {
        canvas.drawPath(path, paint);
    }
}
