package com.bafoly.lib.stockcharts.model;

import android.graphics.Canvas;

import com.bafoly.lib.stockcharts.draw.DrawLine;
import com.bafoly.lib.stockcharts.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.model.axis.Axis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ChartData is having the content of XY values and Axises.
 * Axis types and Single Data types must match. Otherwise we can't draw the chart
 * Axis X can be String, Date or maybe an Integer
 * Axis Y's type is Number
 */
public class ChartData<X, Y extends Number> implements Draw{

    private final Axis<X> xAxis;
    private final Axis<? extends Number> yAxis;

    private List<? extends SingleData<X, Y>> data = Collections.emptyList();

    private ChartProperties chartProperties;

    // this part is related to strategy pattern. we may set this to OHLC drawer etc
    private DrawStrategy drawStrategy = new DrawLine();

    public ChartData(Axis xAxis, Axis<? extends Number> yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        data = new ArrayList<>();
    }

    public Axis<X> getxAxis() {
        return xAxis;
    }

    public Axis<? extends Number> getyAxis() {
        return yAxis;
    }

    public List<? extends SingleData> getData() {
        return data;
    }

    public void setData(List<? extends SingleData<X, Y>> data) {
        this.data = data;
    }

    public ChartProperties getChartProperties() {
        return chartProperties;
    }

    public void setChartProperties(ChartProperties chartProperties) {
        this.chartProperties = chartProperties;
    }

    public DrawStrategy getDrawStrategy() {
        return drawStrategy;
    }

    public void setDrawStrategy(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    @Override
    public void draw(Canvas canvas){
        this.drawStrategy.draw(canvas, this);
    }

    public void calculatePositionReferences(Canvas canvas){
        this.chartProperties.calculateMaxMin(this);
        this.chartProperties.calculateXYgaps(canvas, getyAxis());
    }
}
