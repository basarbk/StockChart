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
public class DrawCandleStick implements DrawStrategy<ChartModel> {

    @Override
    public void draw(Environment environment, ChartModel chartModel) {

        Axis<Number> axisY = chartModel.getyAxis();
        Axis axisX = chartModel.getxAxis();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();


        // This must be quadruple data to draw candle stick
        List<QuadrupleData> sd = chartModel.getData();
        if(!(sd.get(sd.size()-1) instanceof QuadrupleData)){
            return;
        }

        float barWidth = environment.multiplierX/3;

        for(int i = environment.visibleXbegin;i<environment.visibleXend;i++){
            if(i>=sd.size())
                break;
            double close = sd.get(i).getOne().doubleValue();
            double open = sd.get(i).getFour().doubleValue();

            float yClose = environment.getY(sd.get(i).getOne().floatValue());
            float yLow = environment.getY(sd.get(i).getTwo().floatValue());
            float yHigh = environment.getY(sd.get(i).getThree().floatValue());
            float yOpen = environment.getY(sd.get(i).getFour().floatValue());
            float x = environment.getX(i-environment.visibleXbegin);

            if(axisX.isPrintable(i)){
                float yy = environment.getPaddingTop()+environment.getChartHeight();
                String val = axisX.getTextValue(sd.get(i).getX());

                float d = canvasAdapter.getTextHeight(val, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR))*1.5f;
                canvasAdapter.drawText(val, x, yy+d, chartModel.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            }

            canvasAdapter.drawLine(x, yHigh, x, yLow, chartModel.getPainter().getPaint(Painter.FRAME_COLOR));
            if(open>close){
                canvasAdapter.drawRectWithFrame(x-barWidth, yOpen, x+barWidth, yClose, chartModel.getPainter().getPaint(Painter.LOW_COLOR), chartModel.getPainter().getPaint(Painter.FRAME_COLOR));
            } else if (open<close){
                canvasAdapter.drawRectWithFrame(x-barWidth, yClose, x+barWidth, yOpen, chartModel.getPainter().getPaint(Painter.HIGH_COLOR), chartModel.getPainter().getPaint(Painter.FRAME_COLOR));
            } else {
                canvasAdapter.drawLine(x-barWidth, yClose, x+barWidth, yOpen, chartModel.getPainter().getPaint(Painter.FRAME_COLOR));
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
