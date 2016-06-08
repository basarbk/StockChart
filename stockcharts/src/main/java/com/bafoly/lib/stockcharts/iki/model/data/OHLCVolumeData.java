package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * Created by basarb on 6/7/2016.
 */
public class OHLCVolumeData<X, Y extends Number> extends QuadrupleData<X, Y> {

    protected Y five;

    public OHLCVolumeData() {}

    public OHLCVolumeData(X x, Y four, Y three, Y two, Y one, Y five) {
        super(x, four, three, two, one);
        this.five = five;
    }

    public Y getFive() {
        return five;
    }

    public void setFive(Y five) {
        this.five = five;
    }

}
