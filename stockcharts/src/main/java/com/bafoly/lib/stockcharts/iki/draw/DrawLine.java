package com.bafoly.lib.stockcharts.iki.draw;

import com.bafoly.lib.stockcharts.iki.model.Bounds;
import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartModel;

import java.util.List;

/**
 * Created by basarb on 5/3/2016.
 */
public class DrawLine implements DrawStrategy<ChartModel> {

    @Override
    public void draw(Environment environment, ChartModel chartModel) {

        Axis<Number> axisY = chartModel.getyAxis();
        Axis axisX = chartModel.getxAxis();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        List<SingleData> sd = chartModel.getData();
        boolean pathStarted = false;

        for(int i = environment.visibleXbegin;i<environment.visibleXend;i++){
            if(i>=sd.size())
                break;

            if(sd.get(i) == null)
                continue;


            float y = environment.getY(sd.get(i).getLineData().floatValue());
            float x = environment.getX(i-environment.visibleXbegin);

            if(!pathStarted){
                canvasAdapter.createPath();
                canvasAdapter.moveTo(x, y);
                pathStarted = true;
            }
            else{
                canvasAdapter.lineTo(x,y);
            }

            if(axisX.isPaintable(i)){
                float yy = environment.getPaddingTop()+environment.getChartHeight();
                String val = axisX.getTextValue(sd.get(i).getX());

                Bounds b = canvasAdapter.getBounds(val, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
                float d = Math.abs(b.top-b.bottom)*1.5f;
                canvasAdapter.drawText(val, x, yy+d, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            }
        }

        for(Number n : axisY.getIndexes()){
            float yyy = environment.getY(n.floatValue());
            String val = axisY.getTextValue(n);
            Bounds b = canvasAdapter.getBounds(val, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            int d = Math.abs(b.top-b.bottom);
            canvasAdapter.drawText(val, environment.getPaddingLeft()+environment.getChartWidth()+5, yyy+(d/2), chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
        }

        canvasAdapter.drawPath(chartModel.getPainter().getPaint(Painter.LINE_COLOR));


    }
}
