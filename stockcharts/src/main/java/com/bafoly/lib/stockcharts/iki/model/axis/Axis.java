package com.bafoly.lib.stockcharts.iki.model.axis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Chart has axises
 * This class should be extended when customization is needed for axis styling
 */
public abstract class Axis<T> {

    // if this is a Date axis, the format is a data like "dd MM yyyy", etc
    // if this is a float axis, the format could be like #.##
    protected String format;

    // orientation either vertical or horizontal
    boolean isVertical = false;

    // major and minor ticks

    float minSpace = 50;

    /** indexes of the axis elements to be displayed on screen */
    List<Number> indexes = new ArrayList<>();

    public Axis() {
    }

    public Axis(String format) {
        this.format = format;
    }

    public abstract String getTextValue(T value);

    public List<Number> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Number> indexes) {
        this.indexes = indexes;
    }

    public void addIndex(Number index){
        this.indexes.add(index);
    }

    public boolean isPrintable(Number idx){
        if(indexes.contains(idx))
            return true;
        return false;
    }
}
