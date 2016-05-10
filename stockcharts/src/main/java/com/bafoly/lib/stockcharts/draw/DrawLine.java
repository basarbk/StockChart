package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.PaintAdapter;
import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.data.SingleData;
import com.bafoly.lib.stockcharts.model.drawable.ChartData;
import com.bafoly.lib.stockcharts.model.drawable.Drawable;

import java.util.List;

/**
 * Created by basarb on 5/3/2016.
 */
public class DrawLine implements DrawStrategy {

    @Override
    public void draw(Environment environment, Drawable drawableContent) {

        Axis<? extends Number> axisY = ((ChartData)drawableContent).getyAxis();
        Axis axisX = ((ChartData)drawableContent).getxAxis();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        List<SingleData> sd = ((ChartData)drawableContent).getData();

        canvasAdapter.createPath();

        for(int i = 0;i<sd.size();i++){

            float y = (float)(axisY.getMax().doubleValue()-sd.get(i).getLineData().doubleValue())*(environment.multiplierY);

            float x = (float)(i+1)*environment.multiplierX;
            if(i==0){
                canvasAdapter.moveTo(x, y);
            }
            else{
                canvasAdapter.lineTo(x,y);
            }
            // draw x axis data
            //axisX.draw(canvasAdapter, sd.get(i).getX());

        }

        // draw y axis data
        //axisY.draw(canvasAdapter);

        canvasAdapter.drawPath(environment.getPaint(PaintAdapter.LINE_COLOR));


    }
}
