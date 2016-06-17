package com.bafoly.lib.stockcharts.iki.model.data.layer;

import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.OHLCDataSet;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.OHLCVolumeDataSet;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.SingleDataSet;

import java.util.Collections;
import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class Stockk<X, Y extends Number> extends ChartWrapper<X, Y> {

    List<Indicatorr> indicators = Collections.emptyList();

    List<TechnicalAnalysiss> technicalAnalysis = Collections.emptyList();

    @Override
    public void addData(X x, Y... y) {
        axis.add(x);
        if(dataLayer.getDataSet()==null){
            if(y.length==1){
                dataLayer.setDataSet(new SingleDataSet());
            } else if(y.length==4) {
                dataLayer.setDataSet(new OHLCDataSet());
            } else if (y.length ==5){
                dataLayer.setDataSet(new OHLCVolumeDataSet());
            }
        }
        dataLayer.getDataSet().add(y);
    }

    @Override
    public void draw(Environment environment) {

        Environment env = environment == null ? this.environment : environment;

        dataLayer.draw(env);

        for(Indicatorr indicator : indicators){
            if(indicator.isOverlay())
                indicator.draw(env);
        }

        for(TechnicalAnalysiss technicalAnalysiss : technicalAnalysis){
            technicalAnalysiss.draw(env);
        }

    }
}
