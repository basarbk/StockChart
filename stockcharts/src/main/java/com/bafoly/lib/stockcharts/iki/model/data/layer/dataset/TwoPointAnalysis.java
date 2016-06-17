package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

/**
 * Created by basarb on 6/17/2016.
 */
public class TwoPointAnalysis<X, Y extends Number> extends SingleAnalysisSet<X,Y> {

    X secondX;

    Y secondY;

    @Override
    public XYPair<X, Y> getData(int index) {
        if(index == FIRST){
            return new XYPair<>(firstX, firstY);
        } else {
            return new XYPair<>(secondX, secondY);
        }
    }

    @Override
    public void setData(int index, XYPair<X, Y> xyPair) {
        if(index == FIRST) {
            super.setData(index, xyPair);
        } else {
            secondX = xyPair.getX();
            secondY = xyPair.getY();
        }

    }


    @Override
    public void add(int index, XYPair<X, Y> xyPair) {
        if(index == FIRST){
            super.add(index, xyPair);
        } else {
            secondX = xyPair.getX();
            secondY = xyPair.getY();
        }
    }


    @Override
    public int getCount() {
        return 2;
    }
}
