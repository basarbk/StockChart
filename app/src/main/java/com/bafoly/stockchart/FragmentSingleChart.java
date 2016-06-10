package com.bafoly.stockchart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bafoly.lib.stockcharts.iki.android.ChartView;
import com.bafoly.lib.stockcharts.iki.model.axis.DateAxis;
import com.bafoly.lib.stockcharts.iki.model.axis.NumberAxis;
import com.bafoly.lib.stockcharts.iki.model.axis.StringDateAxis;
import com.bafoly.lib.stockcharts.iki.model.drawable.Stock;

import java.util.Date;

/**
 * Created by basarb on 6/10/2016.
 */
public class FragmentSingleChart extends Fragment {

    ChartView chartView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_chart, container, false);

        chartView = (ChartView) view.findViewById(R.id.chart);

        Stock stock = new Stock.Builder<String,Double>().setData(Data.getData()).setAxisX(new StringDateAxis("MMM dd, yyyy")).setAxisY(new NumberAxis("#.##")).build();

        chartView.draw(stock);


        return view;
    }
}
