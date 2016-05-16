package com.bafoly.lib.stockcharts.iki.model;

/**
 * This is for abstraction of Canvas and Paint object for the Stock Chart library algorithms
 */
public abstract class CanvasAdapter<X, Y> {

    protected X canvas;

    public CanvasAdapter(X canvas) {
        this.canvas = canvas;
    }

    public X getCanvas() {
        return canvas;
    }

    public void setCanvas(X canvas) {
        this.canvas = canvas;
    }

    public abstract void drawLine(float x1, float y1, float x2, float y2, Y paint);

    public abstract void drawRect(float x1, float y1, float x2, float y2, Y paint);

    public abstract void drawRectWithFrame(float x1, float y1, float x2, float y2, Y paintContent, Y paintFrame);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void createPath();

    public abstract <T extends Number> void moveTo(T t1, T t2);

    public abstract <T extends Number> void lineTo(T t1, T t2);

    public abstract void drawPath(Object object, Y paint);

    public abstract void drawPath(Y paint);

    public abstract Bounds getBounds(String text, Y paint);

    public abstract void drawText(String text, float x1, float y1, Y paint);
}
