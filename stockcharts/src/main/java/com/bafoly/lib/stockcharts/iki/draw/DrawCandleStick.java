package com.bafoly.lib.stockcharts.iki.draw;

import android.util.Log;

import com.bafoly.lib.stockcharts.iki.model.Bounds;
import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;

import java.util.List;

/**
 * Created by basarb on 5/5/2016.
 */
public class DrawCandleStick implements DrawStrategy<ChartData> {

    private static final String TAG = "StockChart-CandleStick";

    @Override
    public void draw(Environment environment, ChartData chartData) {

        Axis<Number> axisY = chartData.getyAxis();
        Axis axisX = chartData.getxAxis();

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();


        // This must be quadruple data to draw candle stick
        List<QuadrupleData> sd = chartData.getData();
        if(!(sd.get(0) instanceof QuadrupleData)){
            return;
        }

        float barWidth = environment.multiplierX/3;

        for(int i = environment.visibleXbegin;i<environment.visibleXend;i++){
            if(i>=sd.size())
                break;
            double open = sd.get(i).getOne().doubleValue();
            double close = sd.get(i).getFour().doubleValue();

            float yOpen = environment.getY(sd.get(i).getOne().floatValue());
            float yLow = environment.getY(sd.get(i).getTwo().floatValue());
            float yHigh = environment.getY(sd.get(i).getThree().floatValue());
            float yClose = environment.getY(sd.get(i).getFour().floatValue());
            float x = environment.getX(i-environment.visibleXbegin);

            if(axisX.isPaintable(i)){
                float yy = environment.getPaddingTop()+environment.getChartHeight();
                String val = axisX.getTextValue(sd.get(i).getX());

                Bounds b = canvasAdapter.getBounds(val, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
                float d = Math.abs(b.top-b.bottom)*1.5f;
                canvasAdapter.drawText(val, x, yy+d, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            }

            canvasAdapter.drawLine(x, yHigh, x, yLow, chartData.getPainter().getPaint(Painter.FRAME_COLOR));
            if(open>close){
                canvasAdapter.drawRectWithFrame(x-barWidth, yOpen, x+barWidth, yClose, chartData.getPainter().getPaint(Painter.LOW_COLOR), chartData.getPainter().getPaint(Painter.FRAME_COLOR));
            } else if (open<close){
                canvasAdapter.drawRectWithFrame(x-barWidth, yClose, x+barWidth, yOpen, chartData.getPainter().getPaint(Painter.HIGH_COLOR), chartData.getPainter().getPaint(Painter.FRAME_COLOR));
            } else {
                canvasAdapter.drawLine(x-barWidth, yClose, x+barWidth, yOpen, chartData.getPainter().getPaint(Painter.FRAME_COLOR));
            }

        }

        for(Number n : axisY.getIndexes()){
            float yyy = environment.getY(n.floatValue());
            String val = axisY.getTextValue(n);
            Bounds b = canvasAdapter.getBounds(val, chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
            int d = Math.abs(b.top-b.bottom);
            canvasAdapter.drawText(val, environment.getPaddingLeft()+environment.getChartWidth()+5, yyy+(d/2), chartData.getPainter().getPaint(Painter.AXIS_TEXT_COLOR));
        }

    }
}
