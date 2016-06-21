package com.bafoly.lib.stockcharts.uc;

import java.util.Collections;
import java.util.List;

/**
 * T - String or Date or smt else..
 * Y - List or XYPair
 */
public abstract class TimeSeries<T, Y> {

    String legend;

    Environment environment;

    PaintAdapter paintAdapter;

    DrawStrategy rootDrawStrategy;

    DrawStrategy axisDrawStrategy;

    Timeline<T> timeline;

    Drawable<Y> mainDrawable;

    List<Drawable<Y>> overlayDrawable = Collections.emptyList();

    public void draw(Environment environment, Timeline timeline){
        Environment env = this.environment == null ? environment : this.environment;
        Timeline timeLine = this.timeline == null ? timeline : this.timeline;


        mainDrawable.invalidate(env, timeLine);

        for(Drawable d : overlayDrawable){
            if(d!=null)
                d.invalidate(env, timeLine);
        }
    }

    public void addOverlayDrawable(Drawable<Y> drawable){
        this.overlayDrawable.add(drawable);
    }

    public Drawable<Y> getMainDrawable() {
        return mainDrawable;
    }

    public void setMainDrawable(Drawable<Y> mainDrawable) {
        this.mainDrawable = mainDrawable;
    }

    public abstract void add(T t, Number... n);

}
