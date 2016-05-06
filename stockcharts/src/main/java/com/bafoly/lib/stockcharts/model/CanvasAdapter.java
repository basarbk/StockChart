package com.bafoly.lib.stockcharts.model;

/**
 * Created by basarb on 5/6/2016.
 */
public abstract class CanvasAdapter<X, Y> {

    X canvas;

    public CanvasAdapter(X canvas) {
        this.canvas = canvas;
    }

    public abstract void drawLine(float x1, float y1, float x2, float y2, Y paint);

    public abstract void drawRect(float x1, float y1, float x2, float y2, Y paint);

    public abstract void drawPath(Object object, Y paint);

    public abstract int getWidth();

    public abstract int getHeight();

    public X getCanvas() {
        return canvas;
    }

    public void setCanvas(X canvas) {
        this.canvas = canvas;
    }
}
