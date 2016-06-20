package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/20/2016.
 */
public class LineD<Y> extends Drawable<Y> {

    @Override
    public void invalidate(Environment environment, Timeline timeline) {
        drawStrategy.draw(environment, timeline, this);
    }

}
