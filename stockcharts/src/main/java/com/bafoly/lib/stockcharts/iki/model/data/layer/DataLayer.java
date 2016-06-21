package com.bafoly.lib.stockcharts.iki.model.data.layer;

import com.bafoly.lib.stockcharts.iki.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.DataSet;
import com.bafoly.lib.stockcharts.uc.Timeline;

/**
 * A Financial Stock chart can have multiple sub charts on it.<br>
 * From this library's point of view, these sub charts are considered as a Layer<br>
 * DataLayer object is the base class for layer's drawing algorithms, styles and legends.
 */
public class DataLayer<T> {

    private Legend legend;

    private DrawStrategy drawStrategy;

    private Painter painter;

    private DataSet<T> dataSet;

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public DrawStrategy getDrawStrategy() {
        return drawStrategy;
    }

    public void setDrawStrategy(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public DataSet<T> getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public void draw(Environment environment){
        drawStrategy.draw(environment, this);
    }

    public void invalidate(Environment environment, Timeline timeline){

    }

}
