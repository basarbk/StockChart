package com.bafoly.stockchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bafoly.lib.stockcharts.iki.android.ChartView;
import com.bafoly.lib.stockcharts.iki.android.DefaultPainter;
import com.bafoly.lib.stockcharts.iki.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.iki.draw.DrawLine;
import com.bafoly.lib.stockcharts.iki.draw.DrawOHLC;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.DateAxis;
import com.bafoly.lib.stockcharts.iki.model.axis.NumberAxis;
import com.bafoly.lib.stockcharts.iki.model.axis.StringDateAxis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.Indicator;
import com.bafoly.lib.stockcharts.iki.model.drawable.Stock;
import com.bafoly.lib.stockcharts.iki.util.IndicatorCalculator;

import java.util.Date;
import java.util.List;

/**
 * Created by basarb on 6/10/2016.
 */
public class FragmentSingleChart extends Fragment {

    ChartView chartView;

    Stock<String, Double> stock;

    int counter = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_chart, container, false);

        chartView = (ChartView) view.findViewById(R.id.chart);

        stock = new Stock.Builder<String, Double>()
                .setData(Data.getData())
                .setDrawStrategy(new DrawCandleStick())
                .setAxisX(new StringDateAxis("MMM dd, yyy"))
                .setAxisY(new NumberAxis("#.##"))
                .build();


        Indicator<String, Double> indicator = new Indicator(new StringDateAxis("MMM dd, yyyy"), new NumberAxis("#.##"));

        List<SingleData<String, Double>> singleData = IndicatorCalculator.getSSMA(stock.getData(),14);
        indicator.setData(singleData);
        indicator.setPainter(new DefaultPainter());
        indicator.getPainter().setColor(Painter.LINE_COLOR, Color.RED);
        indicator.setDataDrawStrategy(new DrawLine());



        stock.addIndicator(indicator);


        chartView.draw(stock);
        chartView.invalidate();


        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                counter++;
                if(counter>2)
                    counter = 0;

                switch (counter){
                    case 0:
                        stock.setDataDrawStrategy(new DrawCandleStick());
                        break;
                    case 1:
                        stock.setDataDrawStrategy(new DrawLine());
                        break;
                    default:
                        stock.setDataDrawStrategy(new DrawOHLC());
                }

                chartView.invalidate();
            }
        });




        return view;
    }
}
