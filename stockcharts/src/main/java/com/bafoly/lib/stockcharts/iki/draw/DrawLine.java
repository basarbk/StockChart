package com.bafoly.lib.stockcharts.iki.draw;

import android.util.Log;

import com.bafoly.lib.stockcharts.iki.model.Bounds;
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

        Axis<Number> axisY = chartData.getyAxis();
        Axis axisX = chartData.getxAxis();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        List<SingleData> sd = chartData.getData();

        canvasAdapter.createPath();

        for(int i = 0;i<sd.size();i++){

            float y = environment.getY(sd.get(i).getLineData().floatValue());

            float x = environment.getX(i);
            if(i==0){
                canvasAdapter.moveTo(x, y);
            }
            else{
                canvasAdapter.lineTo(x,y);
            }

            if(axisX.isPaintable(i)){
                float yy = environment.getPaddingTop()+environment.getChartHeight();
                String val = axisX.getTextValue(sd.get(i).getX());

                Bounds b = canvasAdapter.getBounds(val, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
                float d = Math.abs(b.top-b.bottom)*1.5f;
                canvasAdapter.drawText(val, x, yy+d, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            }
            // draw x axis data
            //axisX.draw(canvasAdapter, sd.get(i).getX());

        }

        // draw y axis data
        //axisY.draw(canvasAdapter);

        for(Number n : axisY.getIndexes()){
            float yyy = environment.getY(n.floatValue());
            String val = axisY.getTextValue(n);
            Bounds b = canvasAdapter.getBounds(val, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            int d = Math.abs(b.top-b.bottom);
            canvasAdapter.drawText(val, environment.getPaddingLeft()+environment.getChartWidth()+5, yyy+(d/2), chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
        }

        canvasAdapter.drawPath(chartData.getPainter().getPaint(Painter.LINE_COLOR));


    }
}
