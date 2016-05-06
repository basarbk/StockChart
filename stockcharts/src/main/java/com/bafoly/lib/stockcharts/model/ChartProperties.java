package com.bafoly.lib.stockcharts.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;

import com.bafoly.lib.stockcharts.model.axis.Axis;

/**
 * Created by basarb on 5/3/2016.
 */
public class ChartProperties {

    private int xBegin;

    private int xCurrent;

    private int dataCount;

    private float[] referenceValues;

    private boolean drawX = false;

    private int lineColor = 0xff7c7c7c;
    private int highColor = 0xff00ff00;
    private int lowColor = 0xffff0000;;
    private int frameColor = 0xff000000;;

    int paddingTop;
    int paddingBottom;
    int paddingLeft;
    int paddingRight;


    private Paint linePaint;

    private Paint highPaint;

    private Paint lowPaint;

    private Paint framePaint;

    public float multiplierX = 1f;

    public float multiplierY = 1f;

    public int getxBegin() {
        return xBegin;
    }

    public void setxBegin(int xBegin) {
        this.xBegin = xBegin;
    }

    public int getxCurrent() {
        return xCurrent;
    }

    public void setxCurrent(int xCurrent) {
        this.xCurrent = xCurrent;
    }

    public float[] getReferenceValues() {
        return referenceValues;
    }

    public void setReferenceValues(float[] referenceValues) {
        this.referenceValues = referenceValues;
    }

    public boolean isDrawX() {
        return drawX;
    }

    public void setDrawX(boolean drawX) {
        this.drawX = drawX;
    }

    public Paint getLinePaint() {
        if(linePaint==null){
            linePaint = new Paint();
            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setStrokeWidth(2);
            linePaint.setAntiAlias(true);
            linePaint.setStrokeJoin(Paint.Join.ROUND);
            linePaint.setStrokeCap(Paint.Cap.ROUND);
            linePaint.setPathEffect(new CornerPathEffect(5) );
            linePaint.setShadowLayer(1, 1, 1, Color.BLACK);
            linePaint.setColor(lineColor);

        }
        return linePaint;
    }

    public void setLinePaint(Paint linePaint) {
        this.linePaint = linePaint;
    }

    public Paint getHighPaint() {
        if(highPaint==null){
            highPaint = new Paint();
            highPaint.setColor(highColor);
            highPaint.setStrokeWidth(2);
        }
        return highPaint;
    }

    public void setHighPaint(Paint highPaint) {
        this.highPaint = highPaint;
    }

    public Paint getLowPaint() {
        if(lowPaint==null){
            lowPaint = new Paint();
            lowPaint.setColor(lowColor);
            lowPaint.setStrokeWidth(2);
        }
        return lowPaint;
    }

    public void setLowPaint(Paint lowPaint) {
        this.lowPaint = lowPaint;
    }

    public Paint getFramePaint() {
        if(framePaint==null){
            framePaint = new Paint();
            framePaint.setColor(frameColor);
            framePaint.setStrokeWidth(1);
            framePaint.setStyle(Paint.Style.STROKE);
        }

        return framePaint;
    }

    public void setFramePaint(Paint framePaint) {
        this.framePaint = framePaint;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getHighColor() {
        return highColor;
    }

    public void setHighColor(int highColor) {
        this.highColor = highColor;
    }

    public int getLowColor() {
        return lowColor;
    }

    public void setLowColor(int lowColor) {
        this.lowColor = lowColor;
    }

    public int getFrameColor() {
        return frameColor;
    }

    public void setFrameColor(int frameColor) {
        this.frameColor = frameColor;
    }

    public <T extends SingleData> void calculateMaxMin(ChartData chartData){
        dataCount = chartData.getData().size();
        for(int i = 0; i<dataCount; i++){
            chartData.getyAxis().setMax(((T)chartData.getData().get(i)).getMax());
            chartData.getyAxis().setMin(((T)chartData.getData().get(i)).getMin());
        }
    }


    public void calculateXYgaps(Canvas canvas, Axis<? extends Number> axis){
        multiplierX = canvas.getWidth()/(float)(dataCount+1);
        multiplierY = (float)(canvas.getHeight()/(axis.getDiff().doubleValue()));
    }


}
