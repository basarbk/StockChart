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
import com.bafoly.lib.stockcharts.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.draw.DrawGrid;
import com.bafoly.lib.stockcharts.draw.DrawLine;
import com.bafoly.lib.stockcharts.draw.DrawMACD;
import com.bafoly.lib.stockcharts.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.adapter.Painter;
import com.bafoly.lib.stockcharts.model.axis.NumberAxis;
import com.bafoly.lib.stockcharts.model.axis.StringDateAxis;
import com.bafoly.lib.stockcharts.model.data.DoubleData;
import com.bafoly.lib.stockcharts.model.data.TripleData;
import com.bafoly.lib.stockcharts.model.drawable.Indicator;
import com.bafoly.lib.stockcharts.model.drawable.Stock;
import com.bafoly.lib.stockcharts.util.IndicatorCalculator;

import java.util.List;

/**
 * Created by basarb on 6/13/2016.
 */
public class FragmentTwoCharts extends BaseFragment {

    ChartView indicatorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_charts, container, false);

        int item = getArguments().getInt("item",0);

        chartView = (ChartView) view.findViewById(R.id.chart);

        indicatorView = (ChartView) view.findViewById(R.id.indicator_chart);

        stock = new Stock.Builder<String, Double>()
                .setData(Data.getData())
                .setDrawStrategy(getDrawStrategy(item))
                .setAxisX(new StringDateAxis("MMM dd, yyy"))
                .setAxisY(new NumberAxis("#.##"))
                .build();

        chartView.draw(stock);

        indicatorView.draw(getIndicator(stock, item));


        chartView.invalidate();
        indicatorView.invalidate();

        return view;
    }

    @Override
    public DrawStrategy getDrawStrategy(int idx){
        switch (idx){
            case ActivityMain.CANDLE_WITH_MACD_CHART:
            case ActivityMain.CANDLE_STOCHASTIC_OSCILLATOR:
                return new DrawCandleStick();
            default:
                return new DrawLine();
        }
    }

    @Override
    public Indicator getIndicator(Stock stock, int idx){

        if(idx == ActivityMain.CANDLE_WITH_MACD_CHART) {
            Indicator<String, Double> indicator = new Indicator(new StringDateAxis("MMM dd, yyyy"), new NumberAxis("#.##"));
            List<TripleData<String, Double>> singleData = IndicatorCalculator.getMACD(stock.getData(),12, 26, 9);
            indicator.setData(singleData);
            indicator.setPainter(new DefaultPainter());
            indicator.getPainter().setColor(Painter.LINE_COLOR, Color.BLUE);
            indicator.getPainter().setColor(Painter.BAR_COLOR, Color.RED);
            indicator.setAxisDrawStrategy(new DrawGrid());
            indicator.setDataDrawStrategy(new DrawMACD());
            indicator.setEnvironment(new Environment());
            return indicator;
        } else if( idx == ActivityMain.CANDLE_STOCHASTIC_OSCILLATOR){
            Indicator<String, Double> indicator = new Indicator(new StringDateAxis("MMM dd, yyyy"), new NumberAxis("#.##"));
            List<DoubleData<String, Double>> doubleData = IndicatorCalculator.getStochasticOscillator(stock.getData(), 14, 3);
            indicator.setData(doubleData);
            indicator.setPainter(new DefaultPainter());
            indicator.getPainter().setColor(Painter.HIGH_COLOR, Color.RED);
            indicator.getPainter().setColor(Painter.LOW_COLOR, Color.BLUE);
            indicator.setDataDrawStrategy(new DrawLine());
            indicator.setEnvironment(new Environment());
            indicator.setAxisDrawStrategy(new DrawGrid());
            return indicator;
        }
        return null;
    }
}
