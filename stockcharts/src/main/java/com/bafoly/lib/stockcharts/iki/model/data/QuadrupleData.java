package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * Stock has four values for each Xth time
 * OHLC - (O)pen, (L)ow, (H)igh, (C)lose
 */
public class QuadrupleData<X, Y extends Number> extends TripleData<X, Y> {

    protected Y four;

    public QuadrupleData() {}

    public QuadrupleData(X x, Y open, Y low, Y high, Y close) {
        super(x, close, low, high);
        this.four = open;
    }

    public Y getFour() {
        return four;
    }

    public void setFour(Y four) {
        this.four = four;
    }

    public Y getOpenData() {
        return four;
    }

    public void setOpenData(Y open) {
        this.four = open;
    }


    @Override
    public double getMin() {
        return two == null ? Double.NaN : two.doubleValue();
    }

    @Override
    public double getMax() {
        return three == null ? Double.NaN : three.doubleValue();
    }
}
