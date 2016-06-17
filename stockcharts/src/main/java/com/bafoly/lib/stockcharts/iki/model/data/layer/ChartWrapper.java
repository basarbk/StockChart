package com.bafoly.lib.stockcharts.iki.model.data.layer;

import com.bafoly.lib.stockcharts.iki.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;

import java.util.Collections;
import java.util.List;

/**
 * This is the base for Stock, Indicator and Technical Analysis
 */
public abstract class ChartWrapper<X, Y extends Number> {

    Legend rootLegend;

    /** This is the x axis data which is generally the timeline */
    List<X> axis = Collections.emptyList();

    DataLayer<Y> dataLayer = new DataLayer<>();

    DrawStrategy axisDrawStrategy;

    DrawStrategy grid;

    /* Each layer has it's own draw strategy. But in case there is a need to override those behaviors
    * root strategy can be set*/
    DrawStrategy rootDrawStrategy;


    Painter defaultPainter;

    /**
     * Since this is the base for the chart, it's going to have the information about the environment
     */
    protected Environment environment;

    public List<X> getAxis() {
        return axis;
    }

    public void setAxis(List<X> axis) {
        this.axis = axis;
    }

    public DataLayer<Y> getDataLayer() {
        return dataLayer;
    }

    public void setDataLayer(DataLayer<Y> dataLayer) {
        this.dataLayer = dataLayer;
    }

    public DrawStrategy getAxisDrawStrategy() {
        return axisDrawStrategy;
    }

    public void setAxisDrawStrategy(DrawStrategy axisDrawStrategy) {
        this.axisDrawStrategy = axisDrawStrategy;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * This method will be implemented to call the draw strategy
     */
    public abstract void draw(Environment environment);

    public abstract void addData(X x, Y... y);

}
