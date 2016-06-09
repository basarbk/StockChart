package com.bafoly.lib.stockcharts.iki.model.data;

/**
 * Two data on the Xth time
 */
public class DoubleData<X, Y extends Number> extends SingleData<X, Y> {

    protected Y two;

    public DoubleData() {}

    public DoubleData(X x, Y one, Y two) {
        super(x, one);
        this.two = two;
    }

    public Y getTwo() {
        return two;
    }

    public void setTwo(Y two) {
        this.two = two;
    }

    @Override
    public double getMin() {

        Double superValue = super.getMin();

        if(two == null && superValue == null)
            return Double.NaN;

        if(two != null && superValue == null)
            return two.doubleValue();

        if(superValue!=null && two == null)
            return superValue.doubleValue();

        if(!Double.isNaN(two.doubleValue()) && Double.isNaN(superValue.doubleValue()))
            return two.doubleValue();

        if(Double.isNaN(two.doubleValue()) && !Double.isNaN(superValue.doubleValue()))
            return superValue.doubleValue();

        return two.doubleValue() < superValue.doubleValue() ? two.doubleValue() : superValue.doubleValue();
    }

    @Override
    public double getMax() {
        Double superValue = super.getMax();

        if(two == null && superValue == null)
            return Double.NaN;

        if(two != null && superValue == null)
            return two.doubleValue();

        if(superValue!=null && two == null)
            return superValue.doubleValue();

        if(!Double.isNaN(two.doubleValue()) && Double.isNaN(superValue.doubleValue()))
            return two.doubleValue();

        if(Double.isNaN(two.doubleValue()) && !Double.isNaN(superValue.doubleValue()))
            return superValue.doubleValue();

        return two.doubleValue() > superValue.doubleValue() ? two.doubleValue() : superValue.doubleValue();
    }

    @Override
    public DoubleData copy() {
        return new DoubleData(getX(), getOne(), getTwo());
    }
}
