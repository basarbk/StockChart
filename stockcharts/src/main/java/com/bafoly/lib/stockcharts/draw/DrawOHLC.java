package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.ChartData;
import com.bafoly.lib.stockcharts.model.ChartProperties;
import com.bafoly.lib.stockcharts.model.Draw;
import com.bafoly.lib.stockcharts.model.PaintAdapter;
import com.bafoly.lib.stockcharts.model.QuadrupleData;
import com.bafoly.lib.stockcharts.model.axis.Axis;

import java.util.List;

/**
 * Created by basarb on 5/5/2016.
 */
public class DrawOHLC extends DrawStrategy {

    @Override
    public void draw(CanvasAdapter canvasAdapter, Draw drawableContent) {
        ChartProperties properties = ((ChartData)drawableContent).getChartProperties();
        Axis<? extends Number> axis = ((ChartData)drawableContent).getyAxis();
        List<QuadrupleData> sd = ((ChartData)drawableContent).getData();

        float barWidth = properties.multiplierX/3;

        for(int i = 0;i<sd.size();i++){
            double open = sd.get(i).getOne().doubleValue();
            double close = sd.get(i).getFour().doubleValue();

            float yOpen = (float)(axis.getMax().doubleValue()-open)*(properties.multiplierY);
            float yLow = (float)(axis.getMax().doubleValue()-sd.get(i).getTwo().doubleValue())*(properties.multiplierY);
            float yHigh = (float)(axis.getMax().doubleValue()-sd.get(i).getThree().doubleValue())*(properties.multiplierY);
            float yClose = (float)(axis.getMax().doubleValue()-close)*(properties.multiplierY);
            float x = (float)(i+1)*properties.multiplierX;

            if(open>close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, properties.getPaint(PaintAdapter.LOW_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, properties.getPaint(PaintAdapter.LOW_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, properties.getPaint(PaintAdapter.LOW_COLOR));
            } else if (open<close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, properties.getPaint(PaintAdapter.HIGH_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, properties.getPaint(PaintAdapter.HIGH_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, properties.getPaint(PaintAdapter.HIGH_COLOR));
            } else {
                canvasAdapter.drawLine(x, yHigh, x, yLow, properties.getPaint(PaintAdapter.FRAME_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, properties.getPaint(PaintAdapter.FRAME_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, properties.getPaint(PaintAdapter.FRAME_COLOR));
            }



        }
    }
}
