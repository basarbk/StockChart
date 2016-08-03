package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.adapter.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.adapter.Painter;
import com.bafoly.lib.stockcharts.model.adapter.PathAdapter;
import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.data.SingleData;
import com.bafoly.lib.stockcharts.model.drawable.ChartModel;

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

        PathAdapter path = canvasAdapter.getPath();

        for(int i = environment.visibleXbegin;i<environment.visibleXend;i++){
            if(i>=sd.size())
                break;

            if(sd.get(i) == null)
                continue;


            float y = environment.getY(sd.get(i).getOne().floatValue());
            float x = environment.getX(i-environment.visibleXbegin);

            if(!pathStarted){
                path.moveTo(x, y);
                pathStarted = true;
            }
            else{
                path.lineTo(x,y);
            }

            if(axisX.isPrintable(i)){
                float yy = environment.getPaddingTop()+environment.getChartHeight();
                String val = axisX.getTextValue(sd.get(i).getX());

                float d = canvasAdapter.getTextHeight(val, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR))*1.5f;
                canvasAdapter.drawText(val, x, yy+d, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            }
        }

        for(Number n : axisY.getIndexes()){
            float yyy = environment.getY(n.floatValue());
            String val = axisY.getTextValue(n);
            int d = (int)canvasAdapter.getTextHeight(val, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            canvasAdapter.drawText(val, environment.getPaddingLeft()+environment.getChartWidth()+5, yyy+(d/2), chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
        }

        canvasAdapter.clip(environment);
        canvasAdapter.drawPath(path, chartModel.getPainter().getPaint(Painter.LINE_COLOR));

    }
}
