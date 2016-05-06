package com.bafoly.lib.stockcharts.draw;

import android.util.Log;

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
public class DrawCandleStick extends DrawStrategy {

    private static final String TAG = "StockChart-CandleStick";

    @Override
    public void draw(CanvasAdapter canvasAdapter, Draw drawableContent) {


        ChartProperties properties = ((ChartData)drawableContent).getChartProperties();
        Axis<? extends Number> axis = ((ChartData)drawableContent).getyAxis();

        // This must be quadruple data to draw candle stick
        List<QuadrupleData> sd = ((ChartData)drawableContent).getData();
        if(!(sd.get(0) instanceof QuadrupleData)){
            Log.d(TAG, "this is not quadruple data. can't draw with this");
            return;
        }

        float barWidth = properties.multiplierX/3;

        for(int i = 0;i<sd.size();i++){
            double open = sd.get(i).getOne().doubleValue();
            double close = sd.get(i).getFour().doubleValue();

            float yOpen = (float)(axis.getMax().doubleValue()-open)*(properties.multiplierY);
            float yLow = (float)(axis.getMax().doubleValue()-sd.get(i).getTwo().doubleValue())*(properties.multiplierY);
            float yHigh = (float)(axis.getMax().doubleValue()-sd.get(i).getThree().doubleValue())*(properties.multiplierY);
            float yClose = (float)(axis.getMax().doubleValue()-close)*(properties.multiplierY);
            float x = (float)(i+1)*properties.multiplierX;

            canvasAdapter.drawLine(x, yHigh, x, yLow, properties.getPaint(PaintAdapter.FRAME_COLOR));
            if(open>close){
                canvasAdapter.drawRect(x-barWidth, yOpen, x+barWidth, yClose, properties.getPaint(PaintAdapter.LOW_COLOR));
                canvasAdapter.drawRect(x-barWidth, yOpen, x+barWidth, yClose, properties.getPaint(PaintAdapter.FRAME_COLOR));
            } else if (open<close){
                canvasAdapter.drawRect(x-barWidth, yClose, x+barWidth, yOpen, properties.getPaint(PaintAdapter.HIGH_COLOR));
                canvasAdapter.drawRect(x-barWidth, yClose, x+barWidth, yOpen, properties.getPaint(PaintAdapter.FRAME_COLOR));
            } else {
                canvasAdapter.drawLine(x-barWidth, yClose, x+barWidth, yOpen, properties.getPaint(PaintAdapter.FRAME_COLOR));
            }

        }

    }
}
