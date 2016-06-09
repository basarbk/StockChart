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

        Double superValue = super.getMin();

        if(three == null && superValue == null)
            return Double.NaN;

        if(three != null && superValue == null)
            return three.doubleValue();

        if(superValue!=null && three == null)
            return superValue.doubleValue();

        if(!Double.isNaN(three.doubleValue()) && Double.isNaN(superValue.doubleValue()))
            return three.doubleValue();

        if(Double.isNaN(three.doubleValue()) && !Double.isNaN(superValue.doubleValue()))
            return superValue.doubleValue();

        return three.doubleValue() < superValue.doubleValue() ? three.doubleValue() : superValue.doubleValue();

    }

    @Override
    public double getMax() {
        Double superValue = super.getMax();

        if(three == null && superValue == null)
            return Double.NaN;

        if(three != null && superValue == null)
            return three.doubleValue();

        if(superValue!=null && three == null)
            return superValue.doubleValue();

        if(!Double.isNaN(three.doubleValue()) && Double.isNaN(superValue.doubleValue()))
            return three.doubleValue();

        if(Double.isNaN(three.doubleValue()) && !Double.isNaN(superValue.doubleValue()))
            return superValue.doubleValue();

        return three.doubleValue() > superValue.doubleValue() ? three.doubleValue() : superValue.doubleValue();
    }

    @Override
    public TripleData copy() {
        return new TripleData(getX(), getOne(), getTwo(), getThree());
    }
}
