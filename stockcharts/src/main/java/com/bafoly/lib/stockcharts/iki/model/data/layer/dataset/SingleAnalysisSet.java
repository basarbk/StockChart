package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class SingleAnalysisSet<X, Y extends Number> implements DataSet<XYPair<X, Y>> {

    X firstX;

    Y firstY;

    @Override
    public XYPair<X, Y> getData(int index) {
        return new XYPair<>(firstX, firstY);
    }

    @Override
    public void setData(int index, XYPair<X, Y> xyPair) {
        firstX = xyPair.getX();
        firstY = xyPair.getY();
    }

    @Override
    public final void add(Number... t) {

    }

    @Override
    public void add(int index, XYPair<X, Y> xyPair) {
        firstX = xyPair.getX();
        firstY = xyPair.getY();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
