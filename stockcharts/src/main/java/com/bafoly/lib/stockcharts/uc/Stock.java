package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/20/2016.
 */
public class Stock<T, Y> extends TimeSeries<T, Y> {

    Timeline timeline;

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override
    public void draw(Environment environment, Timeline timeline) {

    }

    @Override
    public void add(T t, Number... n) {

    }
}
