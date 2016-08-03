package com.bafoly.lib.stockcharts.android;

import android.graphics.Path;

import com.bafoly.lib.stockcharts.model.adapter.PathAdapter;

/**
 * Created by basarb on 6/14/2016.
 */
public class AndroidPath extends PathAdapter<Path> {

    public AndroidPath() {
        this.path = new Path();
    }

    @Override
    public void moveTo(float x, float y) {
        this.path.moveTo(x, y);
    }

    @Override
    public void lineTo(float x, float y) {
        this.path.lineTo(x, y);
    }
}
