package com.bafoly.lib.stockcharts.iki.model.drawable;

import java.util.Collections;
import java.util.List;

/**
 * Stock object has it's own Chart Data.<br>
 * Stock may have indicators and technical analyses<br>
 * When the draw method is called from the framework, this instance will be responsible for<br>
 * drawing the sub instances.
 */
public class Instrument extends BaseModel{

    private List<Indicator> indicators = Collections.emptyList();

    private List<TechnicalAnalysis> technicalAnalyses = Collections.emptyList();

    public void addIndicator(Indicator indicator){
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
        chartData.draw(getEnvironment());

        for(Indicator indicator:indicators){
            if(indicator.isOverlay())
                indicator.draw();
        }

        for(TechnicalAnalysis technicalAnalysis: technicalAnalyses){
            technicalAnalysis.draw(getEnvironment());
        }
    }
}
