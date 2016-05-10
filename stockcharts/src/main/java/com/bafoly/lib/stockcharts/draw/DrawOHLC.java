package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.drawable.ChartData;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.drawable.Drawable;
import com.bafoly.lib.stockcharts.model.PaintAdapter;
import com.bafoly.lib.stockcharts.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.model.axis.Axis;

import java.util.List;

/**
 * Created by basarb on 5/5/2016.
 */
public class DrawOHLC implements DrawStrategy {

    @Override
    public void draw(Environment environment, Drawable drawableContent) {

        Axis<? extends Number> axis = ((ChartData)drawableContent).getyAxis();
        List<QuadrupleData> sd = ((ChartData)drawableContent).getData();

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
                canvasAdapter.drawLine(x, yHigh, x, yLow, environment.getPaint(PaintAdapter.LOW_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, environment.getPaint(PaintAdapter.LOW_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, environment.getPaint(PaintAdapter.LOW_COLOR));
            } else if (open<close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, environment.getPaint(PaintAdapter.HIGH_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, environment.getPaint(PaintAdapter.HIGH_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, environment.getPaint(PaintAdapter.HIGH_COLOR));
            } else {
                canvasAdapter.drawLine(x, yHigh, x, yLow, environment.getPaint(PaintAdapter.FRAME_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, environment.getPaint(PaintAdapter.FRAME_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, environment.getPaint(PaintAdapter.FRAME_COLOR));
            }



        }
    }
}
