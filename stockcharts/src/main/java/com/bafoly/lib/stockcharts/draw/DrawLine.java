package com.bafoly.lib.stockcharts.draw;

import android.graphics.Path;
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
public class DrawLine extends DrawStrategy {

    private static final String TAG = "StockChart-DrawLine";
    Path path;

    @Override
    public void draw(CanvasAdapter canvasAdapter, Draw drawableContent) {
        Log.d(TAG, "drawing the content");
        if(path==null)
            path = new Path();
        ChartProperties properties = ((ChartData)drawableContent).getChartProperties();
        Axis<? extends Number> axis = ((ChartData)drawableContent).getyAxis();

        List<SingleData> sd = ((ChartData)drawableContent).getData();
        if(sd!=null){
            Log.d(TAG, "sd: "+sd.size());
        } else {
            Log.d(TAG, "data list is empty");
        }

        for(int i = 0;i<sd.size();i++){

            float y = (float)(axis.getMax().doubleValue()-sd.get(i).getLineData().doubleValue())*(properties.multiplierY);

            float x = (float)(i+1)*properties.multiplierX;
            if(i==0){
                path.moveTo(x,y);
            }
            else{
                path.lineTo(x,y);
            }
        }

        canvasAdapter.drawPath(path, properties.getPaint(PaintAdapter.LINE_COLOR));


    }
}
