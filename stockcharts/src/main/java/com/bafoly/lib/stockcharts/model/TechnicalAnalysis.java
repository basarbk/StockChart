package com.bafoly.lib.stockcharts.model;

import android.graphics.Canvas;

import com.bafoly.lib.stockcharts.draw.DrawStrategy;

/**
 * Created by basarb on 5/6/2016.
 */
public abstract class TechnicalAnalysis implements Draw {

    private DrawStrategy drawStrategy;

    public TechnicalAnalysis(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    @Override
    public void draw(CanvasAdapter canvasAdapter) {
        drawStrategy.draw(canvasAdapter, this);
    }
}
