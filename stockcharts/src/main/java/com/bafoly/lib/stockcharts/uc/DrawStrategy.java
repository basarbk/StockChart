package com.bafoly.lib.stockcharts.uc;

/**
 * Created by basarb on 6/19/2016.
 */
public interface DrawStrategy {

    /**
     * Calculate the position of each variable then draw it on to the screen
     * @param environment
     * @param drawable
     * @param parent
     */
    public void draw(Environment environment, Drawable drawable, Drawable parent);
}
