package com.bafoly.lib.stockcharts.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by basarb on 5/3/2016.
 */
public class BaseModel implements Draw {

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
    public void draw(CanvasAdapter canvas) {
        chartData.draw(canvas);
    }
}
