package com.bafoly.stockchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bafoly.lib.stockcharts.iki.android.ChartView;
import com.bafoly.lib.stockcharts.iki.android.DefaultPainter;
import com.bafoly.lib.stockcharts.iki.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.iki.draw.DrawLine;
import com.bafoly.lib.stockcharts.iki.draw.DrawOHLC;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.NumberAxis;
import com.bafoly.lib.stockcharts.iki.model.axis.StringDateAxis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.Indicator;
import com.bafoly.lib.stockcharts.iki.model.drawable.Stock;
import com.bafoly.lib.stockcharts.iki.util.IndicatorCalculator;

import java.util.List;

public class ActivityMain extends AppCompatActivity {

    ChartView chartView;

    Stock<String, Double> stock;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        chartView = (ChartView) findViewById(R.id.chart);


        DefaultPainter dp = new DefaultPainter();
        dp.setColor(Painter.HIGH_COLOR, Color.rgb(38, 127, 200));

        stock = new Stock.Builder<String, Double>()
                .setData(Data.getData())
                .setDrawStrategy(new DrawCandleStick())
                .setAxisX(new StringDateAxis("MMM dd, yyy"))
                .setAxisY(new NumberAxis("#.##"))
                .setPainter(dp)
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


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
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

    }



}
