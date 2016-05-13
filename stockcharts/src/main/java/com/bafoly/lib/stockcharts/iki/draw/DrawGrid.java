package com.bafoly.lib.stockcharts.iki.draw;

import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;

/**
 * Created by basarb on 5/8/2016.
 */
public class DrawGrid implements DrawStrategy<ChartData> {

    @Override
    public void draw(Environment environment, ChartData chartData) {

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        int left = environment.getPaddingLeft();
        int buffer = left/2;
        int width = environment.getChartWidth();
        int top = environment.getPaddingTop();
        int height = environment.getChartHeight();

        canvasAdapter.drawLine(left-buffer, top, left+width+buffer, top, chartData.getPainter().getPaint(Painter.AXIS_COLOR));
        canvasAdapter.drawLine(left-buffer, top+height, left+width+buffer, top+height, chartData.getPainter().getPaint(Painter.AXIS_COLOR));


    }
}
