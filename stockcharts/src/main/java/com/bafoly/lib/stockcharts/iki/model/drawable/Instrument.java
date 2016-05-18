package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.draw.DrawGrid;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;

import java.util.ArrayList;
import java.util.List;

/**
 * Stock object has it's own Chart Data.<br>
 * Stock may have indicators and technical analyses<br>
 * When the draw method is called from the framework, this instance will be responsible for<br>
 * drawing the sub instances.
 */
public class Instrument<X, Y extends Number> extends ChartModel<X, Y>{

    private List<Indicator> indicators = new ArrayList<>();

    private List<TechnicalAnalysis> technicalAnalyses = new ArrayList<>();

    public Instrument() {
        super(null, null);
        setEnvironment(new Environment());
    }

    public Instrument(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
        setEnvironment(new Environment());
        setAxisDrawStrategy(new DrawGrid());
    }

    public void addIndicator(Indicator indicator){
        indicator.setParent(this);
        indicators.add(indicator);
    }

    public List<TechnicalAnalysis> getTechnicalAnalyses() {
        return technicalAnalyses;
    }

    public void setTechnicalAnalyses(List<TechnicalAnalysis> technicalAnalyses) {
        this.technicalAnalyses = technicalAnalyses;
    }

    public void addTechnicalAnalyses(TechnicalAnalysis technicalAnalysis){
        this.technicalAnalyses.add(technicalAnalysis);
    }

    @Override
    public void draw() {
        draw(getEnvironment());

        for(Indicator indicator:indicators){
            if(indicator.isOverlay())
                indicator.draw();
        }

        for(TechnicalAnalysis technicalAnalysis: technicalAnalyses){
            technicalAnalysis.draw(getEnvironment());
        }
    }
}
