package com.bafoly.lib.stockcharts.iki.model;

import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;

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

    // this must be less or equal to the length of the available data set
    private int visibleDataCount = 50;

    // we shouldn't be displaying less than this much entity
    private int minimumVisibleData = 20;

    private int visibleXbegin;

    private int visibleXend;

    int paddingTop = 60;
    int paddingBottom = 150;
    int paddingLeft = 20;
    int paddingRight = 80;

    private int chartWidth;
    private int chartHeight;

    private int dataCount;

    private float[] referenceValues;

    private boolean drawX = false;

    public float multiplierX = 1f;

    public float multiplierY = 1f;

    public int verticalGrid = 5;

    public int horizontalGrid = 5;

    public double max = Double.NaN;

    public double min = Double.NaN;

    // ==== ADAPTERS
    Painter painter;

    // ChartView will be generating the instance for this variable
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

    public void calculateMaxMin(ChartData chartData){
        dataCount = chartData.getData().size();
        for(int i = visibleXbegin; i<dataCount; i++){
            double currentMax = ((SingleData)chartData.getData().get(i)).getMax();
            double currentMin = ((SingleData)chartData.getData().get(i)).getMin();

            if(Double.isNaN(max) || max<currentMax){
                max = currentMax;
            }

            if(Double.isNaN(min) || min>currentMin){
                min = currentMin;
            }
        }
    }

    public void calculateXYgaps(Axis<? extends Number> axis){
        multiplierX = (canvasAdapter.getWidth() - paddingLeft - paddingRight)/(float)(dataCount+1); // - padding left, right
        multiplierY = (float)((canvasAdapter.getHeight()-paddingTop - paddingBottom)/(max-min)); // - pedding bottom top
    }


    public void calculateAxisProperties(Painter painter){

        String s = "01 Jan 16";

        Bounds bounds = canvasAdapter.getBounds(s, painter.getPaint(Painter.AXIS_COLOR));

        //paddingBottom = (bounds.bottom-bounds.top)*2;
        chartWidth = canvasAdapter.getWidth() - paddingLeft - paddingRight;
        chartHeight = canvasAdapter.getHeight() - paddingTop - paddingBottom;
    }


    public float getX(int index){
        return ((index+1)*multiplierX)+paddingLeft;
    }

    public float getY(float y){
        return (float)((max-y)*multiplierY)+paddingTop;
    }

}
