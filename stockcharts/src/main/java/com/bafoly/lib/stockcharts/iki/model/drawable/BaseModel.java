package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.model.Environment;

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
    private ChartData chartData;

    /**
     * Since this is the base for the chart, it's going to have the information about the environment
     */
    private Environment environment;

    // this is a proxy method
    public void draw() {

    }

    public void draw(Environment environment){

    }
}
