package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * Three data on the Xth time
 * Some stock indicators (Bollinger, MACD etc) need three data set.
 */
public class TripleData<X, Y extends Number> extends DoubleData<X, Y> {

    protected Y three;

    public TripleData() {}

    public TripleData(X x, Y one, Y two, Y three) {
        super(x, one, two);
        this.three = three;
    }

    public Y getThree() {
        return three;
    }

    public void setThree(Y three) {
        this.three = three;
    }

    @Override
    public double getMin() {
        if(three == null && one == null)
            return Double.NaN;

        if(three != null && one == null)
            return three.doubleValue();

        if(one!=null && three == null)
            return one.doubleValue();

        return three.doubleValue() < one.doubleValue() ? three.doubleValue() : one.doubleValue();
    }

    @Override
    public double getMax() {
        if(three == null && one == null)
            return Double.NaN;

        if(three != null && one == null)
            return three.doubleValue();

        if(one!=null && three == null)
            return one.doubleValue();

        return three.doubleValue() > one.doubleValue() ? three.doubleValue() : one.doubleValue();
    }
}
