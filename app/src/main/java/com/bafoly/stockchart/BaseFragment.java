package com.bafoly.stockchart;

import android.support.v4.app.Fragment;

import com.bafoly.lib.stockcharts.android.ChartView;
import com.bafoly.lib.stockcharts.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.model.drawable.Indicator;
import com.bafoly.lib.stockcharts.model.drawable.Stock;

public abstract class BaseFragment extends Fragment {

    ChartView chartView;

    Stock<String, Double> stock;

    public abstract DrawStrategy getDrawStrategy(int idx);

    public abstract Indicator getIndicator(Stock stock, int idx);

    public void setDrawStrategy(DrawStrategy drawStrategy){
        stock.setDataDrawStrategy(drawStrategy);
        chartView.invalidate();
    }
}
