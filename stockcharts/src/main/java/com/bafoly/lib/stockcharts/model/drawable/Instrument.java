package com.bafoly.lib.stockcharts.model.drawable;

import java.util.Collections;
import java.util.List;

/**
 * Created by basarb on 5/3/2016.
 */
public class Instrument extends BaseModel {

    private List<Indicator> indicators = Collections.emptyList();

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }
}
