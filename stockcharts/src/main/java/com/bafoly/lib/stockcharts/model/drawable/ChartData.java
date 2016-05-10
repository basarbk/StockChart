package com.bafoly.lib.stockcharts.model.drawable;

import com.bafoly.lib.stockcharts.draw.DrawGrid;
import com.bafoly.lib.stockcharts.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.data.SingleData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ChartData is having the content of XY values and Axises.
 * Axis types and Single Data types must match. Otherwise we can't draw the chart
 * Axis X can be String, Date or maybe an Integer
 * Axis Y's type is Number
 */
public class ChartData<X, Y extends Number> implements Drawable {

    private static final String TAG = "StockChart-ChartData";

    private String name;

    private String legend;

    private Axis<X> xAxis;
    private Axis<? extends Number> yAxis;

    private List<? extends SingleData<X, Y>> data = Collections.emptyList();

    private Environment environment;

    // this is the drawing algorithm
    private DrawStrategy dataDrawStrategy;

    // axises are being drawn with different algorithm
    private DrawStrategy axisDrawStrategy = new DrawGrid();

    private List<List<? extends SingleData<X, Y>>> overlay = Collections.emptyList();

    private List<? extends TechnicalAnalysis> technicalAnalyses = Collections.emptyList();

    boolean embeddable = false;

    public ChartData(Axis xAxis, Axis<? extends Number> yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        data = new ArrayList<>();
    }

    public Axis<X> getxAxis() {
        return xAxis;
    }

    public void setxAxis(Axis<X> xAxis) {
        this.xAxis = xAxis;
    }

    public Axis<? extends Number> getyAxis() {
        return yAxis;
    }

    public void setyAxis(Axis<? extends Number> yAxis) {
        this.yAxis = yAxis;
    }

    public List<? extends SingleData> getData() {
        return data;
    }

    public void setData(List<? extends SingleData<X, Y>> data) {
        this.data = data;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public DrawStrategy getDataDrawStrategy() {
        return dataDrawStrategy;
    }

    public void setDataDrawStrategy(DrawStrategy dataDrawStrategy) {
        this.dataDrawStrategy = dataDrawStrategy;
    }

    @Override
    public void draw(Environment env){
        if(this.dataDrawStrategy ==null){
            return;
        }

        Environment envv = env!=null ? env : environment;

        this.axisDrawStrategy.draw(envv, this);

        this.dataDrawStrategy.draw(envv, this);
    }


    public void calculatePositionReferences(){
        this.environment.calculateMaxMin(this);
        this.environment.calculateXYgaps(getEnvironment().getCanvasAdapter(), getyAxis());
    }

}
