package com.bafoly.lib.stockcharts.iki.model.data.layer.dataset;

import java.util.Collections;
import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class OHLCVolumeDataSet<T extends Number> extends OHLCDataSet<T> {

    List<T> volume = Collections.emptyList();

    public OHLCVolumeDataSet() {}

    public OHLCVolumeDataSet(T open, T high, T low, T close, T volume) {
        super(open, high, low, close);
        this.volume.add(volume);
    }

    public OHLCVolumeDataSet(List<T> open, List<T> high, List<T> low, List<T> close, List<T> volume) {
        super(open, high, low, close);
        this.volume = volume;
    }

    @Override
    public void setData(int index, List<T> list) {
        if(index == VOLUME){
            this.volume = list;
        } else {
            super.setData(index, list);
        }
    }

    @Override
    public List getData(int index) {
        switch (index){
            case OPEN:
                return open;
            case HIGH:
                return high;
            case LOW:
                return low;
            case CLOSE:
                return line;
            case VOLUME:
                return volume;
            default:
                return line;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void add(Number... t) {
        if(t.length==5){
            super.add(t);
            this.volume.add((T)t[4]);
        }
    }
}
