package com.bafoly.lib.stockcharts.draw;

import android.util.Log;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.ChartData;
import com.bafoly.lib.stockcharts.model.ChartProperties;
import com.bafoly.lib.stockcharts.model.Draw;
import com.bafoly.lib.stockcharts.model.PaintAdapter;
import com.bafoly.lib.stockcharts.model.SingleData;
import com.bafoly.lib.stockcharts.model.axis.Axis;

import java.util.List;

/**
 * Created by basarb on 5/3/2016.
 */
public class DrawLine implements DrawStrategy {

    private static final String TAG = "StockChart-DrawLine";

    @Override
    public void draw(CanvasAdapter canvasAdapter, Draw drawableContent) {
        Log.d(TAG, "drawing the content");

        ChartProperties properties = ((ChartData)drawableContent).getChartProperties();
        Axis<? extends Number> axisY = ((ChartData)drawableContent).getyAxis();
        Axis axisX = ((ChartData)drawableContent).getxAxis();

        List<SingleData> sd = ((ChartData)drawableContent).getData();
        if(sd!=null){
            Log.d(TAG, "sd: "+sd.size());
        } else {
            Log.d(TAG, "data list is empty");
        }

        canvasAdapter.createPath();

        for(int i = 0;i<sd.size();i++){

            float y = (float)(axisY.getMax().doubleValue()-sd.get(i).getLineData().doubleValue())*(properties.multiplierY);

            float x = (float)(i+1)*properties.multiplierX;
            if(i==0){
                canvasAdapter.moveTo(x, y);
            }
            else{
                canvasAdapter.lineTo(x,y);
            }
            // draw x axis data
            axisX.draw(canvasAdapter, sd.get(i).getX());

        }

        // draw y axis data
        axisY.draw(canvasAdapter);

        canvasAdapter.drawPath(properties.getPaint(PaintAdapter.LINE_COLOR));


    }
}
