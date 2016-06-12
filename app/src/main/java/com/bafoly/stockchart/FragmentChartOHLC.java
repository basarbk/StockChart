package com.bafoly.stockchart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bafoly.lib.stockcharts.iki.android.ChartView;
import com.bafoly.lib.stockcharts.iki.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.iki.draw.DrawOHLC;
import com.bafoly.lib.stockcharts.iki.model.axis.NumberAxis;
import com.bafoly.lib.stockcharts.iki.model.axis.StringDateAxis;
import com.bafoly.lib.stockcharts.iki.model.drawable.Stock;

/**
 * Created by basarb on 6/12/2016.
 */
public class FragmentChartOHLC extends Fragment{

    ChartView chartView;

    Stock<String, Double> stock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        chartView = (ChartView) view.findViewById(R.id.chart);

        stock = new Stock.Builder<String, Double>()
                .setData(Data.getData())
                .setDrawStrategy(new DrawOHLC())
                .setAxisX(new StringDateAxis("MMM dd, yyy"))
                .setAxisY(new NumberAxis("#.##"))
                .build();
        chartView.draw(stock);
        return view;
    }
}
