package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.adapter.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.adapter.Painter;
import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.model.drawable.ChartModel;

import java.util.List;

/**
 * Created by basarb on 5/5/2016.
 */
public class DrawOHLC implements DrawStrategy<ChartModel> {

    @Override
    public void draw(Environment environment, ChartModel chartModel) {

        Axis<Number> axisY = chartModel.getyAxis();
        Axis axisX = chartModel.getxAxis();
        List<QuadrupleData> sd = chartModel.getData();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        float barWidth = environment.multiplierX/3;

        for(int i = environment.visibleXbegin;i<environment.visibleXend;i++){
            if(i>=sd.size())
                break;
            float close = sd.get(i).getOne().floatValue();
            float open = sd.get(i).getFour().floatValue();
            float high = sd.get(i).getThree().floatValue();
            float low = sd.get(i).getTwo().floatValue();

            float yOpen = environment.getY(open);
            float yLow = environment.getY(low);
            float yHigh = environment.getY(high);
            float yClose = environment.getY(close);
            float x = environment.getX(i-environment.visibleXbegin);

            if(open>close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, chartModel.getPainter().getPaint(Painter.LOW_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, chartModel.getPainter().getPaint(Painter.LOW_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, chartModel.getPainter().getPaint(Painter.LOW_COLOR));
            } else if (open<close){
                canvasAdapter.drawLine(x, yHigh, x, yLow, chartModel.getPainter().getPaint(Painter.HIGH_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, chartModel.getPainter().getPaint(Painter.HIGH_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, chartModel.getPainter().getPaint(Painter.HIGH_COLOR));
            } else {
                canvasAdapter.drawLine(x, yHigh, x, yLow, chartModel.getPainter().getPaint(Painter.FRAME_COLOR));
                canvasAdapter.drawLine(x-barWidth, yOpen, x, yOpen, chartModel.getPainter().getPaint(Painter.FRAME_COLOR));
                canvasAdapter.drawLine(x, yClose, x+barWidth, yClose, chartModel.getPainter().getPaint(Painter.FRAME_COLOR));
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
    }
}
