package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;

/**
 * This is base class for Stock instruments or indicators
 */
public abstract class BaseModel {

    /**
     * Name will be used in the Legend
     */
    private String name;

    /**
     * Legend
     */
    private String legend;

    /**
     * Each model must have at least one chart data.
     */
    protected ChartData chartData;

    /**
     * Since this is the base for the chart, it's going to have the information about the environment
     */
    private Environment environment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public ChartData getChartData() {
        return chartData;
    }

    public void setChartData(ChartData chartData) {
        this.chartData = chartData;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    // this is a proxy method
    public abstract void draw();

}
