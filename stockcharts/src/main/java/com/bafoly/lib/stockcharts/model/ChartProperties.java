package com.bafoly.lib.stockcharts.model;

import com.bafoly.lib.stockcharts.model.axis.Axis;

/**
 * Created by basarb on 5/3/2016.
 */
public class ChartProperties {

    // this must be less or equal to the length of the available data set
    private int visibleDataCount;

    // we shouldn't be displaying less than this much entity
    private int minimumVisibleData = 20;

    private int visibleXbegin;

    private int visibleXend;

    int paddingTop;
    int paddingBottom;
    int paddingLeft;
    int paddingRight;

    private int chartWidth;
    private int chartHeight;

    private int dataCount;

    private float[] referenceValues;

    private boolean drawX = false;


    PaintAdapter<?> paintAdapter;

    public float multiplierX = 1f;

    public float multiplierY = 1f;

    public ChartProperties(PaintAdapter paintAdapter){
        this.paintAdapter = paintAdapter;
    }


    public <T extends SingleData> void calculateMaxMin(ChartData chartData){
        dataCount = chartData.getData().size();
        for(int i = 0; i<dataCount; i++){
            chartData.getyAxis().setMax(((T)chartData.getData().get(i)).getMax());
            chartData.getyAxis().setMin(((T)chartData.getData().get(i)).getMin());
        }
    }

    public void calculateMargins(ChartData chartData){
    }


    public void calculateXYgaps(CanvasAdapter canvasAdapter, Axis<? extends Number> axis){
        multiplierX = canvasAdapter.getWidth()/(float)(dataCount+1); // - padding left, right
        multiplierY = (float)(canvasAdapter.getHeight()/(axis.getDiff().doubleValue())); // - pedding bottom top
    }



    //---------

    public void setColor(String colorName, int color){
        paintAdapter.setColor(colorName, color);
    }

    public Object getPaint(String colorName){
        return paintAdapter.getPaint(colorName);
    }



}
