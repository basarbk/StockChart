package com.bafoly.lib.stockcharts.model.adapter;

/**
 * Created by basarb on 6/14/2016.
 */
public abstract class PathAdapter<T> {

    protected T path;

    public PathAdapter() {}

    public T getPath() {
        return path;
    }

    public void setPath(T path) {
        this.path = path;
    }

    public abstract void moveTo(float x, float y);

    public abstract void lineTo(float x, float y);
}
