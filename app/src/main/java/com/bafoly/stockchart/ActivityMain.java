package com.bafoly.stockchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bafoly.lib.stockcharts.ChartView;
import com.bafoly.lib.stockcharts.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.draw.DrawLine;
import com.bafoly.lib.stockcharts.model.AndroidPainter;
import com.bafoly.lib.stockcharts.model.ChartData;
import com.bafoly.lib.stockcharts.model.ChartProperties;
import com.bafoly.lib.stockcharts.model.QuadrupleData;
import com.bafoly.lib.stockcharts.model.SingleData;
import com.bafoly.lib.stockcharts.model.axis.CategoryAxis;
import com.bafoly.lib.stockcharts.model.axis.NumberAxis;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity {

    ChartView chartView;
    boolean isCandle = false;

    ChartData<String, Float> chartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        chartView = (ChartView) findViewById(R.id.chart);

        chartData = new ChartData<>(new CategoryAxis(null), new NumberAxis(null));
        chartData.setData(getSingleData());
        chartData.setDrawStrategy(new DrawLine());

        ChartProperties chartProperties = new ChartProperties(new AndroidPainter());

        chartData.setChartProperties(chartProperties);

        chartView.draw(chartData);
        chartView.invalidate();


        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCandle){
                    chartData.setDrawStrategy(new DrawLine());
                    isCandle = false;
                } else {

                    chartData.setData(getData());
                    chartData.setDrawStrategy(new DrawCandleStick());
                    isCandle = true;
                }

                chartView.invalidate();
            }
        });

    }

    private List<? extends SingleData<String, Float>> getSingleData() {
        List<SingleData<String, Float>> data = new ArrayList<>();

        data.add(new SingleData("Apr 05, 2016", 8.03));
        data.add(new SingleData("Apr 06, 2016", 8.02));
        data.add(new SingleData("Apr 07, 2016", 7.90));
        data.add(new SingleData("Apr 08, 2016", 7.87));
        data.add(new SingleData("Apr 11, 2016", 7.97));
        data.add(new SingleData("Apr 12, 2016", 8.13));
        data.add(new SingleData("Apr 13, 2016", 8.18));
        data.add(new SingleData("Apr 14, 2016", 8.47));
        data.add(new SingleData("Apr 15, 2016", 8.48));
        data.add(new SingleData("Apr 18, 2016", 8.43));
        data.add(new SingleData("Apr 19, 2016", 8.73));
        data.add(new SingleData("Apr 20, 2016", 8.65));
        data.add(new SingleData("Apr 21, 2016", 8.56));
        data.add(new SingleData("Apr 22, 2016", 8.56));
        data.add(new SingleData("Apr 25, 2016", 8.49));
        data.add(new SingleData("Apr 26, 2016", 8.38));
        data.add(new SingleData("Apr 27, 2016", 8.55));
        data.add(new SingleData("Apr 28, 2016", 8.56));
        data.add(new SingleData("Apr 29, 2016", 8.65));
        data.add(new SingleData("May 02, 2016", 8.59));
        data.add(new SingleData("May 03, 2016", 8.43));
        data.add(new SingleData("May 04, 2016", 8.05));
        data.add(new SingleData("May 05, 2016", 7.59));
        return data;
    }

    private List<? extends SingleData<String, Float>> getData(){
        List<SingleData<String, Float>> data = new ArrayList<>();

        data.add(new QuadrupleData("Apr 05, 2016", 8.03, 7.89, 8.04, 7.98));
        data.add(new QuadrupleData("Apr 06, 2016", 8.02, 7.84, 8.02, 7.84));
        data.add(new QuadrupleData("Apr 07, 2016", 7.90, 7.81, 7.94, 7.85));
        data.add(new QuadrupleData("Apr 08, 2016", 7.87, 7.82, 7.96, 7.96));
        data.add(new QuadrupleData("Apr 11, 2016", 7.97, 7.94, 8.16, 8.12));
        data.add(new QuadrupleData("Apr 12, 2016", 8.13, 8.03, 8.21, 8.12));
        data.add(new QuadrupleData("Apr 13, 2016", 8.18, 8.15, 8.45, 8.43));
        data.add(new QuadrupleData("Apr 14, 2016", 8.47, 8.37, 8.48, 8.46));
        data.add(new QuadrupleData("Apr 15, 2016", 8.48, 8.34, 8.49, 8.49));
        data.add(new QuadrupleData("Apr 18, 2016", 8.43, 8.41, 8.71, 8.71));
        data.add(new QuadrupleData("Apr 19, 2016", 8.73, 8.64, 8.80, 8.69));
        data.add(new QuadrupleData("Apr 20, 2016", 8.65, 8.50, 8.78, 8.51));
        data.add(new QuadrupleData("Apr 21, 2016", 8.56, 8.46, 8.65, 8.54));
        data.add(new QuadrupleData("Apr 22, 2016", 8.56, 8.44, 8.58, 8.49));
        data.add(new QuadrupleData("Apr 25, 2016", 8.49, 8.31, 8.49, 8.35));
        data.add(new QuadrupleData("Apr 26, 2016", 8.38, 8.31, 8.58, 8.57));
        data.add(new QuadrupleData("Apr 27, 2016", 8.55, 8.54, 8.70, 8.56));
        data.add(new QuadrupleData("Apr 28, 2016", 8.56, 8.45, 8.62, 8.62));
        data.add(new QuadrupleData("Apr 29, 2016", 8.65, 8.53, 8.65, 8.60));
        data.add(new QuadrupleData("May 02, 2016", 8.59, 8.38, 8.59, 8.40));
        data.add(new QuadrupleData("May 03, 2016", 8.43, 8.02, 8.46, 8.06));
        data.add(new QuadrupleData("May 04, 2016", 8.05, 7.77, 8.17, 7.78));
        data.add(new QuadrupleData("May 05, 2016", 7.59, 7.52, 7.62, 7.53));

        return data;
    }

}
