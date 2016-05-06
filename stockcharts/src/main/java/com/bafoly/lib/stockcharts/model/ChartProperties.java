package com.bafoly.lib.stockcharts.model;

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

    int paddingTop;
    int paddingBottom;
    int paddingLeft;
    int paddingRight;

    PaintAdapter<?> paintAdapter;

    public float multiplierX = 1f;

    public float multiplierY = 1f;

    public ChartProperties(PaintAdapter paintAdapter){
        this.paintAdapter = paintAdapter;
    }

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



    public <T extends SingleData> void calculateMaxMin(ChartData chartData){
        dataCount = chartData.getData().size();
        for(int i = 0; i<dataCount; i++){
            chartData.getyAxis().setMax(((T)chartData.getData().get(i)).getMax());
            chartData.getyAxis().setMin(((T)chartData.getData().get(i)).getMin());
        }
    }


    public void calculateXYgaps(CanvasAdapter canvasAdapter, Axis<? extends Number> axis){
        multiplierX = canvasAdapter.getWidth()/(float)(dataCount+1);
        multiplierY = (float)(canvasAdapter.getHeight()/(axis.getDiff().doubleValue()));
    }



    //---------

    public void setColor(String colorName, int color){
        paintAdapter.setColor(colorName, color);
    }

    public Object getPaint(String colorName){
        return paintAdapter.getPaint(colorName);
    }



}
