package com.bafoly.lib.stockcharts.iki.model.data.layer.draw;

import com.bafoly.lib.stockcharts.iki.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.data.layer.DataLayer;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.DataSet;
import com.bafoly.lib.stockcharts.iki.model.drawable.XYData;

import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class DrawBarLayer implements DrawStrategy<DataLayer> {

    @Override
    public void draw(Environment environment, DataLayer drawableContent) {

        DataSet<List<Number>> content = drawableContent.getDataSet();
        List<Number> data = content.getData(DataSet.CLOSE);

        for(Number n : data){
            
        }

    }
}
