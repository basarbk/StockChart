package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.PaintAdapter;
import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.model.drawable.ChartData;
import com.bafoly.lib.stockcharts.model.drawable.Drawable;

import java.util.List;

/**
 * Created by basarb on 5/5/2016.
 */
public class DrawCandleStick implements DrawStrategy {

    private static final String TAG = "StockChart-CandleStick";

    @Override
    public void draw(Environment environment, Drawable drawableContent) {

        Axis<? extends Number> axis = ((ChartData)drawableContent).getyAxis();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();


        // This must be quadruple data to draw candle stick
        List<QuadrupleData> sd = ((ChartData)drawableContent).getData();
        if(!(sd.get(0) instanceof QuadrupleData)){
            return;
        }

        float barWidth = environment.multiplierX/3;

        for(int i = 0;i<sd.size();i++){
            double open = sd.get(i).getOne().doubleValue();
            double close = sd.get(i).getFour().doubleValue();

            float yOpen = (float)(axis.getMax().doubleValue()-open)*(environment.multiplierY);
            float yLow = (float)(axis.getMax().doubleValue()-sd.get(i).getTwo().doubleValue())*(environment.multiplierY);
            float yHigh = (float)(axis.getMax().doubleValue()-sd.get(i).getThree().doubleValue())*(environment.multiplierY);
            float yClose = (float)(axis.getMax().doubleValue()-close)*(environment.multiplierY);
            float x = (float)(i+1)*environment.multiplierX;

            canvasAdapter.drawLine(x, yHigh, x, yLow, environment.getPaint(PaintAdapter.FRAME_COLOR));
            if(open>close){
                canvasAdapter.drawRect(x-barWidth, yOpen, x+barWidth, yClose, environment.getPaint(PaintAdapter.LOW_COLOR));
                canvasAdapter.drawRect(x-barWidth, yOpen, x+barWidth, yClose, environment.getPaint(PaintAdapter.FRAME_COLOR));
            } else if (open<close){
                canvasAdapter.drawRect(x-barWidth, yClose, x+barWidth, yOpen, environment.getPaint(PaintAdapter.HIGH_COLOR));
                canvasAdapter.drawRect(x-barWidth, yClose, x+barWidth, yOpen, environment.getPaint(PaintAdapter.FRAME_COLOR));
            } else {
                canvasAdapter.drawLine(x-barWidth, yClose, x+barWidth, yOpen, environment.getPaint(PaintAdapter.FRAME_COLOR));
            }

        }

    }
}
