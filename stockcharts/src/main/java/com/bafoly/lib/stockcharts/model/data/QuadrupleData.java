package com.bafoly.lib.stockcharts.model.data;

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
        Double superValue = super.getMin();

        if(four == null && superValue == null)
            return Double.NaN;

        if(four != null && superValue == null)
            return four.doubleValue();

        if(superValue!=null && four == null)
            return superValue.doubleValue();

        if(!Double.isNaN(four.doubleValue()) && Double.isNaN(superValue.doubleValue()))
            return four.doubleValue();

        if(Double.isNaN(four.doubleValue()) && !Double.isNaN(superValue.doubleValue()))
            return superValue.doubleValue();

        return four.doubleValue() < superValue.doubleValue() ? four.doubleValue() : superValue.doubleValue();
    }

    @Override
    public double getMax() {
        Double superValue = super.getMax();

        if(four == null && superValue == null)
            return Double.NaN;

        if(four != null && superValue == null)
            return four.doubleValue();

        if(superValue!=null && four == null)
            return superValue.doubleValue();

        if(!Double.isNaN(four.doubleValue()) && Double.isNaN(superValue.doubleValue()))
            return four.doubleValue();

        if(Double.isNaN(four.doubleValue()) && !Double.isNaN(superValue.doubleValue()))
            return superValue.doubleValue();

        return four.doubleValue() > superValue.doubleValue() ? four.doubleValue() : superValue.doubleValue();
    }
}
