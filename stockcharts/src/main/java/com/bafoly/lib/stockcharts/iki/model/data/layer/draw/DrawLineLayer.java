package com.bafoly.lib.stockcharts.iki.model.data.layer.draw;

import com.bafoly.lib.stockcharts.iki.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.data.layer.DataLayer;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.DataSet;

import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class DrawLineLayer implements DrawStrategy<DataLayer> {

    @Override
    public void draw(Environment environment, DataLayer drawableContent) {

        DataLayer<List<Double>> dataLayer = new DataLayer();
        DataSet<List<Double>> set = dataLayer.getDataSet();
        List<Double> d = set.getData(0);


    }
}
