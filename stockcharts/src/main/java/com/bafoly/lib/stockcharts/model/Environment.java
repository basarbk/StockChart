package com.bafoly.lib.stockcharts.model;

import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.data.SingleData;
import com.bafoly.lib.stockcharts.model.drawable.ChartData;

/**
 * Chart Environment is containing the canvas, paint adapters and measurement related variables
 * The reference values are calculated and stored here.
 * Drawable content will do it's work based ont Environment data
 */
public class Environment {

    // this must be less or equal to the length of the available data set
    private int visibleDataCount;

    // we shouldn't be displaying less than this much entity
    private int minimumVisibleData = 20;

    private int visibleXbegin;

    private int visibleXend;

    int paddingTop = 30;
    int paddingBottom = 50;
    int paddingLeft = 40;
    int paddingRight = 40;

    private int chartWidth;
    private int chartHeight;

    private int dataCount;

    private float[] referenceValues;

    private boolean drawX = false;

    public float multiplierX = 1f;

    public float multiplierY = 1f;

    // ==== ADAPTERS
    PaintAdapter paintAdapter;

    // ChartView will be generating the instance for this variable
    CanvasAdapter canvasAdapter;

    public Environment(){}

    public Environment(PaintAdapter paintAdapter, CanvasAdapter canvasAdapter){
        this.paintAdapter = paintAdapter;
        this.canvasAdapter = canvasAdapter;
    }

    public CanvasAdapter getCanvasAdapter() {
        return canvasAdapter;
    }

    public void setCanvasAdapter(CanvasAdapter canvasAdapter) {
        this.canvasAdapter = canvasAdapter;
    }

    public PaintAdapter getPaintAdapter() {
        return paintAdapter;
    }

    public void setPaintAdapter(PaintAdapter paintAdapter) {
        this.paintAdapter = paintAdapter;
    }

    public <T extends SingleData> void calculateMaxMin(ChartData chartData){
        dataCount = chartData.getData().size();
        for(int i = 0; i<dataCount; i++){
            chartData.getyAxis().setMax(((T)chartData.getData().get(i)).getMax());
            chartData.getyAxis().setMin(((T)chartData.getData().get(i)).getMin());

            // TODO check x axis
        }
    }

    public void calculateXYgaps(CanvasAdapter canvasAdapter, Axis<? extends Number> axis){
        multiplierX = canvasAdapter.getWidth()/(float)(dataCount+1); // - padding left, right
        multiplierY = (float)(canvasAdapter.getHeight()/(axis.getDiff().doubleValue())); // - pedding bottom top
    }


    public void calculateAxisProperties(CanvasAdapter canvasAdapter){

        String s = "01 Jan 16";

        Bounds bounds = canvasAdapter.getBounds(s, paintAdapter.getPaint(PaintAdapter.AXIS_COLOR));

        paddingBottom = (bounds.bottom-bounds.top)*2;
        chartWidth = canvasAdapter.getWidth() - paddingLeft - paddingRight;
        chartHeight = canvasAdapter.getHeight() - paddingTop - paddingBottom;
    }


    //---------

    public void setColor(String colorName, int color){
        paintAdapter.setColor(colorName, color);
    }

    public Object getPaint(String colorName){
        return paintAdapter.getPaint(colorName);
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
}
