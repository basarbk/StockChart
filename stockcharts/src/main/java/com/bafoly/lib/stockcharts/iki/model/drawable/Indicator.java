package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.model.axis.Axis;

/**
 * Created by basarb on 5/3/2016.
 */
public class Indicator<X, Y extends Number>  extends ChartModel<X,Y> {

    /**
     * Indicators must be assigned to Instruments. This is the reference for it.
     */
    ChartModel parent;

    /**
     * Indicators have their calculation algorithm which is based on a period of data.
     */
    int period;

    /**
     * Indicators can be drawn as standalone charts or Overlay charts to existing Instrument chart.
     * When it's drawn as overlay, the environment values will be taken from parent
     */
    boolean overlay = true;

    public Indicator() {
        super(null, null);
    }

    public Indicator(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    public ChartModel getParent() {
        return parent;
    }

    public void setParent(ChartModel parent) {
        this.parent = parent;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean isOverlay() {
        return overlay;
    }

    public void setOverlay(boolean overlay) {
        this.overlay = overlay;
    }

    @Override
    public void draw() {
        if(overlay){
            draw(parent.getEnvironment());
        } else {
            draw(getEnvironment());
        }
    }
}
