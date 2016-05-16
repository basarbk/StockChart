package com.bafoly.lib.stockcharts.iki.draw;

import android.util.Log;

import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;

/**
 * Created by basarb on 5/8/2016.
 */
public class DrawGrid implements DrawStrategy<ChartData> {

    @Override
    public void draw(Environment environment, ChartData chartData) {

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        Axis x = chartData.getxAxis();
        Axis y = chartData.getyAxis();

        int left = environment.getPaddingLeft();
        int buffer = left/2;
        int width = environment.getChartWidth();
        int top = environment.getPaddingTop();
        int height = environment.getChartHeight();

        canvasAdapter.drawLine(left-buffer, top, left+width+buffer, top, chartData.getPainter().getPaint(Painter.AXIS_COLOR));
        canvasAdapter.drawLine(left-buffer, top+height, left+width+buffer, top+height, chartData.getPainter().getPaint(Painter.AXIS_COLOR));

        int part = environment.getDataCount()/environment.verticalGrid;

        for(int i = 0;i<=environment.verticalGrid;i++){
            x.addIndex(i*part);
            float posX = environment.getX(i*part);
            canvasAdapter.drawLine(posX, top, posX, top+height, chartData.getPainter().getPaint(Painter.AXIS_COLOR));
        }

        for(int i = 1;i<=environment.horizontalGrid;i++){
            float v = (float)(environment.max-i*environment.gridValueSteps);
            y.addIndex(v);
            float posY = environment.getY(v);
            canvasAdapter.drawLine(left-buffer, posY, left+width+buffer, posY, chartData.getPainter().getPaint(Painter.AXIS_COLOR));

        }

    }
}
