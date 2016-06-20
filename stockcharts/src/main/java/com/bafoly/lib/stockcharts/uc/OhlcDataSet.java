package com.bafoly.lib.stockcharts.uc;

import java.util.List;

/**
 * Created by basarb on 6/20/2016.
 */
public class OhlcDataSet<Y extends Number> extends DataSet<List<Y>> {

    public OhlcDataSet() {
        super(DATA_LIST);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
