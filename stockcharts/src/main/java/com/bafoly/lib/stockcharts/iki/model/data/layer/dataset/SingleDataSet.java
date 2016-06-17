package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.DataSet;

import java.util.Collections;
import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class SingleDataSet<T extends Number> implements DataSet<List<T>> {

    List<T> line = Collections.emptyList();

    public SingleDataSet(){}

    public SingleDataSet(T line){
        this.line.add(line);
    }

    public SingleDataSet(List<T> line) {
        this.line = line;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public List<T> getData(int index) {
        return line;
    }

    @Override
    public void setData(int index, List<T> ts) {
        this.line = ts;
    }

    @Override
    public void add(Number... t) {
        this.line.add((T)t[0]);
    }

    @Override
    public void add(int index, List<T> ts) {
        this.line = ts;
    }
}
