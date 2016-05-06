package com.bafoly.lib.stockcharts.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.bafoly.lib.stockcharts.model.ChartData;
import com.bafoly.lib.stockcharts.model.ChartProperties;
import com.bafoly.lib.stockcharts.model.Draw;
import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.QuadrupleData;
import com.bafoly.lib.stockcharts.model.BaseModel;

import java.util.List;

/**
 * Created by basarb on 5/5/2016.
 */
public class DrawOHLC implements DrawStrategy {

    @Override
    public void draw(Canvas canvas, Draw drawableContent) {
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

            Paint p = null;
            if(open>close){
                p = properties.getLowPaint();
            } else if (open<close){
                p = properties.getHighPaint();
            } else {
                p = properties.getFramePaint();
            }

            canvas.drawLine(x, yHigh, x, yLow, p);
            canvas.drawLine(x-barWidth, yOpen, x, yOpen, p);
            canvas.drawLine(x, yClose, x+barWidth, yClose, p);

        }
    }
}
