package com.bafoly.lib.stockcharts.iki.model;

import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartModel;

/**
 * Chart Environment is containing the canvas, paint adapters and measurement related variables.<br>
 * This object will be responsible about the scroll positions and available content range limitations.<br>
 * The reference values are calculated and stored here.<br>
 * Canvas, Paint adapters and the other view related positioning references will be used inside DrawStrategy<br>
 *<br>
 * Canvas and Paint adapters are framework independent definitions. Those adapters will be instantiated with the<br>
 * corresponding objects.<br>
 * Android: <code>android.graphics.Canvas</code> and <code>android.graphics.Paint</code><br>
 * Java AWT: <code>java.awt.Graphics</code> and <code>java.awt.Color</code><br>
 * JavaFX: <code>javafx.scene.canvas</code> and <code>javafx.scene.paint.Color</code>
 */
public class Environment {

    /** Base Model data count */
    private int dataCount;

    /** this must be less or equal to the length of the available data set */
    public int visibleDataCount = 50;

    /** we shouldn't be displaying less than this much entity */
    private int minimumVisibleData = 20;

    /** index of begin */
    public int visibleXbegin;

    /** index of end */
    public int visibleXend;

    /** padding inside canvas */
    int paddingTop = 60;

    /** padding inside canvas */
    int paddingBottom = 150;

    /** padding inside canvas */
    int paddingLeft = 20;

    /** padding inside canvas */
    int paddingRight = 80;

    /** view width */
    private int chartWidth;

    /** view height */
    private int chartHeight;

    /** data positioning on X */
    public float multiplierX = 1f;

    /** data positioning on Y */
    public float multiplierY = 1f;

    /** max Y value in visible data count range */
    public double max = Double.NaN;

    /** min Y value in visible data count range */
    public double min = Double.NaN;

    /** axis Y grid price differences*/
    public double gridValueSteps = Double.NaN;

    /** background vertical grid count */
    public int verticalGrid = 5;

    /** background horizontal grid count */
    public int horizontalGrid = 5;

    private float[] referenceValues;

    private boolean drawX = false;

    private boolean firstTime = true;

    /** Default Painter for the chart */
    Painter painter;

    /** Canvas of the framework */
    CanvasAdapter canvasAdapter;

    public Environment(){}

    public Environment(Painter painter, CanvasAdapter canvasAdapter){
        this.painter = painter;
        this.canvasAdapter = canvasAdapter;
    }

    // SETTERS n GETTERS
    public CanvasAdapter getCanvasAdapter() {
        return canvasAdapter;
    }

    public void setCanvasAdapter(CanvasAdapter canvasAdapter) {
        this.canvasAdapter = canvasAdapter;
    }


    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getChartWidth() {
        return chartWidth;
    }

    public void setChartWidth(int chartWidth) {
        this.chartWidth = chartWidth;
    }

    public int getChartHeight() {
        return chartHeight;
    }

    public void setChartHeight(int chartHeight) {
        this.chartHeight = chartHeight;
    }

    // <--- SETTERS n GETTERS END


    // ---> POSITION METHODS

    public void calculateMaxMin(ChartModel chartModel){
        dataCount = chartModel.getData().size();
        if(firstTime){
            visibleXend = dataCount;
            visibleDataCount = dataCount;
            firstTime = false;
        }
        max = Double.NaN;
        min = Double.NaN;

        for(int i = visibleXbegin; i<visibleXend; i++){
            if(i>=dataCount)
                break;
            double currentMax = ((SingleData) chartModel.getData().get(i)).getMax();
            double currentMin = ((SingleData) chartModel.getData().get(i)).getMin();

            if(Double.isNaN(max) || max<currentMax){
                max = currentMax;
            }

            if(Double.isNaN(min) || min>currentMin){
                min = currentMin;
            }
        }



        gridValueSteps = (max - min)/horizontalGrid;

        multiplierX = (canvasAdapter.getWidth() - paddingLeft - paddingRight)/(float)(dataCount+1); // - padding left, right
        multiplierY = (float)((canvasAdapter.getHeight()-paddingTop - paddingBottom)/(max-min)); // - pedding bottom top

        chartWidth = canvasAdapter.getWidth() - paddingLeft - paddingRight;
        chartHeight = canvasAdapter.getHeight() - paddingTop - paddingBottom;
    }

    public boolean scroll(int count){

        if(visibleXbegin+count<0)
            return false;

        if(visibleXend+count>dataCount+visibleDataCount-1)
            return false;

        visibleXend += count;
        visibleXbegin += count;

        return true;
    }

    public float getX(int index){
        return ((index+1)*multiplierX)+paddingLeft;
    }

    public float getY(float y){
        return (float)((max-y)*multiplierY)+paddingTop;
    }

}
