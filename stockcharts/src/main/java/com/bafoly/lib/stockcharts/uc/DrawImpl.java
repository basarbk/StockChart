package com.bafoly.lib.stockcharts.uc;

import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.*;
import com.bafoly.lib.stockcharts.iki.model.drawable.XYData;

import java.util.List;

/**
 * Created by basarb on 6/19/2016.
 */
public class DrawImpl implements DrawStrategy {

    @Override
    public void draw(Environment environment, Timeline timeline, Drawable drawable) {
        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        DataSet<XYPair> set = drawable.getDataSet();


        DataSet<List<Double>> values = drawable.getDataSet();



        for(int i = environment.getXBegin() ; i <environment.getXEnd() ; i++){




            //environment.getX(open.get(i));



        }


    }
}
