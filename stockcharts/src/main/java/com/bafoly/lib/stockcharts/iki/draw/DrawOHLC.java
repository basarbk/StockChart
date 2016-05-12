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
    public void draw(Environment environment, Painter painter, ChartData chartData) {

        Axis<? extends Number> axis = chartData.getyAxis();
        List<QuadrupleData> sd = chartData.getData();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        float barWidth = environment.multiplierX/3;

        for(int i = 0;i<sd.size();i++){
            double open = sd.get(i).getOne().doubleValue();
            double close = sd.get(i).getFour().doubleValue();

            float yOpen = (float)(axis.getMax().doubleValue()-open)*(environment.multiplierY);
            float yLow = (float)(axis.getMax().doubleValue()-sd.get(i).getTwo().doubleValue())*(environment.multiplierY);
            float yHigh = (float)(axis.getMax().doubleValue()-sd.get(i).getThree().doubleValue())*(environment.multiplierY);
            float yClose = (float)(axis.getMax().doubleValue()-close)*(environment.multiplierY);
            float x = (float)(i+1)*environment.multiplierX;

            if(open>close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, painter.getPaint(Painter.LOW_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, painter.getPaint(Painter.LOW_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, painter.getPaint(Painter.LOW_COLOR));
            } else if (open<close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, painter.getPaint(Painter.HIGH_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, painter.getPaint(Painter.HIGH_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, painter.getPaint(Painter.HIGH_COLOR));
            } else {
                canvasAdapter.drawLine(x, yHigh, x, yLow, painter.getPaint(Painter.FRAME_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, painter.getPaint(Painter.FRAME_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, painter.getPaint(Painter.FRAME_COLOR));
            }



        }
    }
}
