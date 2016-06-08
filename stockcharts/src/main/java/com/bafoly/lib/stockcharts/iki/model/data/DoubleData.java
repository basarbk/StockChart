package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * Two data on the Xth time
 */
public class DoubleData<X, Y extends Number> extends SingleData<X, Y> {

    protected Y two;

    public DoubleData() {}

    public DoubleData(X x, Y close, Y low) {
        super(x, close);
        this.two = low;
    }

    public Y getTwo() {
        return two;
    }

    public void setTwo(Y two) {
        this.two = two;
    }

    @Override
    public double getMin() {
        if(two == null && one == null)
            return Double.NaN;

        if(two != null && one == null)
            return two.doubleValue();

        if(one!=null && two == null)
            return one.doubleValue();

        return two.doubleValue() < one.doubleValue() ? two.doubleValue() : one.doubleValue();
    }

    @Override
    public double getMax() {
        if(two == null && one == null)
            return Double.NaN;

        if(two != null && one == null)
            return two.doubleValue();

        if(one!=null && two == null)
            return one.doubleValue();

        return two.doubleValue() > one.doubleValue() ? two.doubleValue() : one.doubleValue();
    }

    @Override
    public DoubleData copy() {
        return new DoubleData(getX(), getOne(), getTwo());
    }
}
