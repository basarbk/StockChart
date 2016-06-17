package com.bafoly.lib.stockcharts.iki.model.data.layer;

import com.bafoly.lib.stockcharts.iki.model.Environment;

/**
 * Created by basarb on 6/17/2016.
 */
public class Indicatorr<X, Y extends Number> extends ChartWrapper<X, Y> {

    boolean overlay = false;

    public boolean isOverlay() {
        return overlay;
    }

    public void setOverlay(boolean overlay) {
        this.overlay = overlay;
    }

    @Override
    public void draw(Environment environment) {

    }

    @Override
    public void addData(X x, Y... y) {

    }
}
