package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;

import java.util.Collections;
import java.util.List;

/**
 * ChartData is for main chart content.
 */
public class ChartData<X, Y extends Number> extends XYData<X, Y> {

    private List<? extends SingleData<X, Y>> data = Collections.emptyList();



    public List<? extends SingleData> getData() {
        return data;
    }

    public void setData(List<? extends SingleData<X, Y>> data) {
        this.data = data;
    }

    @Override
    public void draw(Environment environment){
        //axisDrawStrategy.draw(environment, this);

        dataDrawStrategy.draw(environment, this);

//        this.axisDrawStrategy.draw(environment, painter, this);
//
//        this.drawStrategy.draw(environment, painter, this);
    }

}
