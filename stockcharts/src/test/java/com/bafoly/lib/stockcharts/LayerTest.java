package com.bafoly.lib.stockcharts;

import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.DataSet;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.OHLCVolumeDataSet;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.SingleDataSet;
import com.bafoly.lib.stockcharts.iki.model.data.layer.dataset.TwoPointAnalysis;
import com.bafoly.lib.stockcharts.iki.model.drawable.XYData;

import org.junit.Test;

import java.util.List;

/**
 * Created by basarb on 6/17/2016.
 */
public class LayerTest {

    @Test
    public void t01() throws Exception {

        DataSet<List<Double>> singleDataSet = new OHLCVolumeDataSet<>();

        System.out.println(singleDataSet.getCount());

        singleDataSet = new SingleDataSet<>();
        System.out.println(singleDataSet.getCount());

        //DataSet<Double> techSet = new TwoPointAnalysis<>();



    }
}
