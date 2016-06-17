package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

import java.util.Collections;
import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class OHLCDataSet<T extends Number> extends SingleDataSet<T>{

    List<T> open = Collections.emptyList();
    List<T> high = Collections.emptyList();
    List<T> low = Collections.emptyList();

    public OHLCDataSet(){}

    public OHLCDataSet(T open, T high, T low, T close) {
        super(close);
        this.open.add(open);
        this.high.add(high);
        this.low.add(low);

    }

    public OHLCDataSet(List<T> open, List<T> high, List<T> low, List<T> close) {
        super(close);
        this.open = open;
        this.high = high;
        this.low = low;
    }

    public List<T> getOpen() {
        return open;
    }

    public void setOpen(List<T> open) {
        this.open = open;
    }

    public List<T> getHigh() {
        return high;
    }

    public void setHigh(List<T> high) {
        this.high = high;
    }

    public List<T> getLow() {
        return low;
    }

    public void setLow(List<T> low) {
        this.low = low;
    }

    @Override
    public void setData(int index, List<T> list) {
        switch (index){
            case OPEN:
                this.open = list;
                return;
            case HIGH:
                this.high = list;
                return;
            case LOW:
                this.low = list;
                return;
            case CLOSE:
                this.line = list;
                return;
            default:
                this.line = list;
        }
    }

    @Override
    public List<T> getData(int index) {
        switch (index){
            case OPEN:
                return open;
            case HIGH:
                return high;
            case LOW:
                return low;
            case CLOSE:
                return line;
            default:
                return line;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void add(Number... t) { //o h l c
        if(t.length>=4){
            this.open.add((T)t[0]);
            this.high.add((T)t[1]);
            this.low.add((T)t[2]);
            this.line.add((T)t[3]);
        }

    }


}
