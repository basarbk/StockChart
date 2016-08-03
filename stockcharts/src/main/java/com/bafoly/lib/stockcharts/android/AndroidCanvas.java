package com.bafoly.lib.stockcharts.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import com.bafoly.lib.stockcharts.model.adapter.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.adapter.PathAdapter;

/**
 * Created by basarb on 5/6/2016.
 */
public class AndroidCanvas extends CanvasAdapter<Canvas, Paint> {


    public AndroidCanvas(){
        super(null);
    }

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
    public int getWidth() {
        return canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return canvas.getHeight();
    }

    @Override
    public float getTextHeight(String text, Paint paint) {
        Rect r = new Rect();
        paint.getTextBounds(text, 0, text.length(), r);
        return Math.abs(r.top-r.bottom);
    }

    @Override
    public void drawRectWithFrame(float x1, float y1, float x2, float y2, Paint paintContent, Paint paintFrame) {
        canvas.drawRect(x1, y1, x2, y2, paintContent);
        canvas.drawRect(x1, y1, x2, y2, paintFrame);
    }

    @Override
    public void drawText(String text, float x1, float y1, Paint paint) {
        canvas.drawText(text, x1, y1, paint);
    }

    @Override
    public void restoreCanvas() {
        canvas.restore();
    }

    @Override
    public void clip(Environment environment) {
        float left = environment.getPaddingLeft();
        float top = environment.getPaddingTop();
        float right = environment.getChartWidth()+environment.getPaddingLeft();
        float bottom = environment.getPaddingTop()+environment.getChartHeight();
        canvas.clipRect(left, top, right, bottom);
    }

    @Override
    public PathAdapter getPath() {
        return new AndroidPath();
    }

    @Override
    public void drawPath(PathAdapter pathAdapter, Paint paint) {
        canvas.drawPath(((Path)pathAdapter.getPath()), paint);
    }

}
