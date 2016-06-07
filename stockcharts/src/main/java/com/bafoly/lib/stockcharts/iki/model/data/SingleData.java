package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * single XY data pair
 * X is generally the timeline. It could be set as Date, String or Integer
 * Y is the value at X
 */
public class SingleData<X, Y extends Number> {

    protected X x;

    protected Y one;

    public SingleData() {}

    public SingleData(X x, Y one) {
        this.x = x;
        this.one = one;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getOne() {
        return one;
    }

    public void setOne(Y one) {
        this.one = one;
    }

    public Y getLineData(){
        return one;
    }

    public void setLineData(Y one) {
        this.one = one;
    }

    public Y getCloseData(){
        return one;
    }

    public void setCloseData(Y one) {
        this.one = one;
    }




    public double getMin(){
        return one == null ? Double.NaN : one.doubleValue();
    }

    public double getMax(){
        return one == null ? Double.NaN : one.doubleValue();
    }

    public SingleData copy(){
        return new SingleData(x, one);
    }
}
