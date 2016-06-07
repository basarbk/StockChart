package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * Created by basarb on 6/7/2016.
 */
public class OHLCVolumeData<X, Y extends Number> extends QuadrupleData<X, Y> {

    protected Y five;

    public OHLCVolumeData() {}

    public OHLCVolumeData(X x, Y open, Y high, Y low, Y close, Y volume) {
        super(x, open, high, low, close);
        this.five = volume;
    }

    public Y getFive() {
        return five;
    }

    public void setFive(Y five) {
        this.five = five;
    }

    public Y getVolumeData(){
        return five;
    }

    public void setVolumeData(Y volume){
        this.five = volume;
    }
}
