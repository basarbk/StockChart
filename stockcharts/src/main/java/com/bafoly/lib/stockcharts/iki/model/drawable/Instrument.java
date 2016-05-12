package com.bafoly.lib.stockcharts.iki.model.drawable;

import java.util.Collections;
import java.util.List;

/**
 * Stock object has it's own Chart Data.
 * Stock may have indicators and technical analyses
 */
public class Instrument extends BaseModel{

    private List<Indicator> indicators = Collections.emptyList();

    private List<TechnicalAnalysis> technicalAnalyses = Collections.emptyList();

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }
}
