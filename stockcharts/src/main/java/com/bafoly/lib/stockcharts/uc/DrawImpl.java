package com.bafoly.lib.stockcharts.uc;

import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.*;
import com.bafoly.lib.stockcharts.iki.model.drawable.XYData;

import java.util.List;

/**
 * Created by basarb on 6/19/2016.
 */
public class DrawImpl implements DrawStrategy<Drawable> {

    @Override
    public void draw(Environment environment, Timeline timeline, Drawable drawable) {
        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        if(drawable.getDataSet().is(DataSet.DATA_LIST)){

        }

        DataSet<XYPair> set = drawable.getDataSet();


        DataSet<List<Double>> values = drawable.getDataSet();

        TimeSeries<String, List<Double>> series = new OhlcSeries<>();






        for(int i = environment.getXBegin() ; i <environment.getXEnd() ; i++){




            //environment.getX(open.get(i));



        }


    }
}
