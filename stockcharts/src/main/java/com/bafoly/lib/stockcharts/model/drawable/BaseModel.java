package com.bafoly.lib.stockcharts.model.drawable;

import com.bafoly.lib.stockcharts.model.Environment;

import java.util.Collections;
import java.util.List;

/**
 * This is base class for Stock instruments or indicators
 *
 */
public abstract class BaseModel implements Drawable {

    private String name;

    private String legend;

    private ChartData chartData;

    private List<TechnicalAnalysis> technicalAnalysises = Collections.emptyList();

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


    @Override
    public void draw(Environment environment) {
        chartData.draw(environment);
    }
}
