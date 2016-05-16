package com.bafoly.lib.stockcharts.iki.draw;

import android.util.Log;

import com.bafoly.lib.stockcharts.iki.model.Bounds;
import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;

import java.util.List;

/**
 * Created by basarb on 5/5/2016.
 */
public class DrawOHLC implements DrawStrategy<ChartData> {

    @Override
    public void draw(Environment environment, ChartData chartData) {

        Axis<Number> axisY = chartData.getyAxis();
        Axis axisX = chartData.getxAxis();
        List<QuadrupleData> sd = chartData.getData();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        float barWidth = environment.multiplierX/3;

        for(int i = 0;i<sd.size();i++){
            float open = sd.get(i).getOne().floatValue();
            float close = sd.get(i).getFour().floatValue();
            float high = sd.get(i).getThree().floatValue();
            float low = sd.get(i).getTwo().floatValue();

            float yOpen = environment.getY(open);
            float yLow = environment.getY(low);
            float yHigh = environment.getY(high);
            float yClose = environment.getY(close);
            float x = environment.getX(i);

            if(open>close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, chartData.getPainter().getPaint(Painter.LOW_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, chartData.getPainter().getPaint(Painter.LOW_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, chartData.getPainter().getPaint(Painter.LOW_COLOR));
            } else if (open<close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, chartData.getPainter().getPaint(Painter.HIGH_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, chartData.getPainter().getPaint(Painter.HIGH_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, chartData.getPainter().getPaint(Painter.HIGH_COLOR));
            } else {
                canvasAdapter.drawLine(x, yHigh, x, yLow, chartData.getPainter().getPaint(Painter.FRAME_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, chartData.getPainter().getPaint(Painter.FRAME_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, chartData.getPainter().getPaint(Painter.FRAME_COLOR));
            }

            if(axisX.isPaintable(i)){
                float yy = environment.getPaddingTop()+environment.getChartHeight();
                String val = axisX.getTextValue(sd.get(i).getX());

                Bounds b = canvasAdapter.getBounds(val, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
                float d = Math.abs(b.top-b.bottom)*1.5f;
                canvasAdapter.drawText(val, x, yy+d, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            }


        }

        for(Number n : axisY.getIndexes()){
            float yyy = environment.getY(n.floatValue());
            String val = axisY.getTextValue(n);
            Bounds b = canvasAdapter.getBounds(val, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            int d = Math.abs(b.top-b.bottom);
            canvasAdapter.drawText(val, environment.getPaddingLeft()+environment.getChartWidth()+5, yyy+(d/2), chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
        }
    }
}
