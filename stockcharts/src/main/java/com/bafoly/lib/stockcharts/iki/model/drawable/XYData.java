package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;

/**
 * XYData is the abstract class for Chart and Technical Analysis data.<br>
 * <br>
 * Each drawable data has its own Axises which is for styling<br>
 * Axis X can be String, Date or maybe an Integer. It's up to designer's decision.<br>
 * Axis Y's type is Number.<br>
 * <br>
 * Each chart has it's own color set. If this is not set, then default (which is coming with the Environment)<br>
 * is used for painting.
 */
public abstract class XYData<X, Y extends Number> {

    private Axis xAxis;
    private Axis yAxis;

    DrawStrategy dataDrawStrategy;

    DrawStrategy axisDrawStrategy;

    Painter painter;

    public XYData(Axis xAxis, Axis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Axis getxAxis() {
        return xAxis;
    }

    public void setxAxis(Axis<X> xAxis) {
        this.xAxis = xAxis;
    }

    public Axis<Y> getyAxis() {
        return yAxis;
    }

    public void setyAxis(Axis yAxis) {
        this.yAxis = yAxis;
    }

    public DrawStrategy getDataDrawStrategy() {
        return dataDrawStrategy;
    }

    public void setDataDrawStrategy(DrawStrategy dataDrawStrategy) {
        this.dataDrawStrategy = dataDrawStrategy;
    }

    public DrawStrategy getAxisDrawStrategy() {
        return axisDrawStrategy;
    }

    public void setAxisDrawStrategy(DrawStrategy axisDrawStrategy) {
        this.axisDrawStrategy = axisDrawStrategy;
    }

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    /**
     * This method will be implemented to call the draw strategy
     */
    public abstract void draw(Environment environment);
}
