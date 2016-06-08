package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * Stock has four values for each Xth time
 * OHLC - (O)pen, (H)igh, (L)ow, (C)lose
 */
public class QuadrupleData<X, Y extends Number> extends TripleData<X, Y> {

    protected Y four;

    public QuadrupleData() {}

    public QuadrupleData(X x, Y four, Y three, Y two, Y one) {
        super(x, one, two, three);
        this.four = four;
    }

    public Y getFour() {
        return four;
    }

    public void setFour(Y four) {
        this.four = four;
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
