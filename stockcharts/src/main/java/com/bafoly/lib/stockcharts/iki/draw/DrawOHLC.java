package com.bafoly.lib.stockcharts.iki.draw;

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

        Axis<? extends Number> axis = chartData.getyAxis();
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



        }
    }
}
