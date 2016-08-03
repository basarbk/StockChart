package com.bafoly.stockchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bafoly.lib.stockcharts.android.ChartView;
import com.bafoly.lib.stockcharts.android.DefaultPainter;
import com.bafoly.lib.stockcharts.draw.DrawBollinger;
import com.bafoly.lib.stockcharts.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.draw.DrawLine;
import com.bafoly.lib.stockcharts.draw.DrawOHLC;
import com.bafoly.lib.stockcharts.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.model.adapter.Painter;
import com.bafoly.lib.stockcharts.model.axis.CategoryAxis;
import com.bafoly.lib.stockcharts.model.axis.NumberAxis;
import com.bafoly.lib.stockcharts.model.axis.StringDateAxis;
import com.bafoly.lib.stockcharts.model.data.SingleData;
import com.bafoly.lib.stockcharts.model.data.TripleData;
import com.bafoly.lib.stockcharts.model.drawable.Indicator;
import com.bafoly.lib.stockcharts.model.drawable.Stock;
import com.bafoly.lib.stockcharts.util.IndicatorCalculator;

import java.util.List;

/**
 * Created by basarb on 6/10/2016.
 */
public class FragmentSingleChart extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_chart, container, false);

        chartView = (ChartView) view.findViewById(R.id.chart);
        int item = getArguments().getInt("item",0);

        stock = new Stock.Builder<String, Double>()
                .setData(Data.getData())
                .setDrawStrategy(getDrawStrategy(item))
                .setAxisX(new CategoryAxis("MMM dd, yyy"))
                .setAxisY(new NumberAxis(""))
                .build();


        stock.addIndicator(getIndicator(stock, item));

        chartView.draw(stock);
        chartView.invalidate();

        return view;
    }

    @Override
    public DrawStrategy getDrawStrategy(int idx){
        switch (idx){
            case ActivityMain.CANDLE_CHART:
            case ActivityMain.CANDLE_WITH_SMA_CHART:
                return new DrawCandleStick();
            case ActivityMain.OHLC_CHART:
            case ActivityMain.OHLC_WITH_BOLLINGER:
                return new DrawOHLC();
            case ActivityMain.LINE_CHART:
                return new DrawLine();
            default:
                return new DrawLine();
        }
    }

    @Override
    public Indicator getIndicator(Stock stock, int idx){

        if(idx == ActivityMain.CANDLE_WITH_SMA_CHART) {
            Indicator<String, Double> indicator = new Indicator(new StringDateAxis("MMM dd, yyyy"), new NumberAxis("#.##"));
            List<SingleData<String, Double>> singleData = IndicatorCalculator.getSMA(stock.getData(),14);
            indicator.setData(singleData);
            indicator.setPainter(new DefaultPainter());
            indicator.getPainter().setColor(Painter.LINE_COLOR, Color.RED);
            indicator.setDataDrawStrategy(new DrawLine());
            return indicator;
        } else if( idx == ActivityMain.OHLC_WITH_BOLLINGER){
            Indicator<String, Double> indicator = new Indicator(new StringDateAxis("MMM dd, yyyy"), new NumberAxis("#.##"));
            List<TripleData<String, Double>> tripleData = IndicatorCalculator.getBollinger(stock.getData(), 14);
            indicator.setData(tripleData);
            indicator.setPainter(new DefaultPainter());
            indicator.getPainter().setColor(Painter.LINE_COLOR, Color.BLUE);
            indicator.getPainter().setColor(Painter.HIGH_COLOR, Color.BLUE);
            indicator.getPainter().setColor(Painter.LOW_COLOR, Color.BLUE);
            indicator.setDataDrawStrategy(new DrawBollinger());
            return indicator;
        }
        return null;
    }

}
