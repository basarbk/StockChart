package com.bafoly.lib.stockcharts.iki.draw;

import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;

import java.util.List;

/**
 * Created by basarb on 5/3/2016.
 */
public class DrawLine implements DrawStrategy<ChartData> {

    @Override
    public void draw(Environment environment, ChartData chartData) {

        Axis<? extends Number> axisY = chartData.getyAxis();
        Axis axisX = chartData.getxAxis();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        List<SingleData> sd = chartData.getData();

        canvasAdapter.createPath();

        for(int i = 0;i<sd.size();i++){

            float y = (float)(axisY.getMax().doubleValue()-sd.get(i).getLineData().doubleValue())*(environment.multiplierY);

            float x = (float)(i+1)*environment.multiplierX;
            if(i==0){
                canvasAdapter.moveTo(x, y);
            }
            else{
                canvasAdapter.lineTo(x,y);
            }
            // draw x axis data
            //axisX.draw(canvasAdapter, sd.get(i).getX());

        }

        // draw y axis data
        //axisY.draw(canvasAdapter);

        canvasAdapter.drawPath(chartData.getPainter().getPaint(Painter.LINE_COLOR));


    }
}
