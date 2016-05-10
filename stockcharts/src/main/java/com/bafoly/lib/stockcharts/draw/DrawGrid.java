package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.drawable.ChartData;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.drawable.Drawable;
import com.bafoly.lib.stockcharts.model.PaintAdapter;

/**
 * Created by basarb on 5/8/2016.
 */
public class DrawGrid implements DrawStrategy {

    @Override
    public void draw(Environment environment, Drawable drawableContent) {

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        int left = environment.getPaddingLeft();
        int buffer = left/2;
        int width = environment.getChartWidth();
        int top = environment.getPaddingTop();
        int height = environment.getChartHeight();

        canvasAdapter.drawLine(left-buffer, top, left+width+buffer, top, environment.getPaint(PaintAdapter.AXIS_COLOR));
        canvasAdapter.drawLine(left-buffer, top+height, left+width+buffer, top+height, environment.getPaint(PaintAdapter.AXIS_COLOR));


    }
}
