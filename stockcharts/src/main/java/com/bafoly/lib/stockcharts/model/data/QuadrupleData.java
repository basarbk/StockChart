package com.bafoly.lib.stockcharts.model.data;

/**
 * Stock has four values for each Xth time
 * OHLC - (O)pen, (L)ow, (H)igh, (C)lose
 */
public class QuadrupleData<X, Y extends Number> extends TripleData<X, Y> {

    protected Y four;

    public QuadrupleData() {}

    public QuadrupleData(X x, Y one, Y two, Y three, Y four) {
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
