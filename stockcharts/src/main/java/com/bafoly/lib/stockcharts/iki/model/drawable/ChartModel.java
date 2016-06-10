package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;

import java.util.Collections;
import java.util.List;

/**
 * ChartModel is for main chart content which is either Stock or Indicator<br>
 * Each chart may have it's custom positioning references which is calculated & stored in Environment<br>
 * Indicators are dependent on to the Stocks therefore Stock can make draw call with it's own Environment<br>
 * object.
 */
public abstract class ChartModel<X, Y extends Number> extends XYData<X, Y> {

    /**
     * Name will be used in the Legend
     */
    private String name;

    /**
     * Legend
     */
    private String legend;

    /**
     * Since this is the base for the chart, it's going to have the information about the environment
     */
    private Environment environment;

    /**
     * This is the data array of chart
     */
    private List<? extends SingleData<X, Y>> data = Collections.emptyList();

    public ChartModel(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
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

    @Override
    public void draw(Environment environment){

        Environment env = environment==null ? this.environment : environment;

        if(axisDrawStrategy!=null){
            axisDrawStrategy.draw(env, this);
        }

        dataDrawStrategy.draw(env, this);

    }

}
