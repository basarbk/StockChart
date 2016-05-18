package com.bafoly.lib.stockcharts.iki.draw;

import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basarb on 5/8/2016.
 */
public class DrawGrid implements DrawStrategy<ChartModel> {

    @Override
    public void draw(Environment environment, ChartModel chartModel) {

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        Axis x = chartModel.getxAxis();
        Axis y = chartModel.getyAxis();

        int left = environment.getPaddingLeft();
        int buffer = left/2;
        int width = environment.getChartWidth();
        int top = environment.getPaddingTop();
        int height = environment.getChartHeight();

        canvasAdapter.drawLine(left-buffer, top, left+width+buffer, top, chartModel.getPainter().getPaint(Painter.AXIS_COLOR));
        canvasAdapter.drawLine(left-buffer, top+height, left+width+buffer, top+height, chartModel.getPainter().getPaint(Painter.AXIS_COLOR));

        int part = environment.getDataCount()/environment.verticalGrid;

        List<Number> xs = new ArrayList<>();
        List<Number> ys = new ArrayList<>();

        for(int i = 0;i<=environment.verticalGrid;i++){
            xs.add(i*part);
            float posX = environment.getX(i*part);
            canvasAdapter.drawLine(posX, top, posX, top+height, chartModel.getPainter().getPaint(Painter.AXIS_COLOR));
        }

        for(int i = 1;i<=environment.horizontalGrid;i++){
            float v = (float)(environment.max-i*environment.gridValueSteps);
            ys.add(v);
            float posY = environment.getY(v);
            canvasAdapter.drawLine(left-buffer, posY, left+width+buffer, posY, chartModel.getPainter().getPaint(Painter.AXIS_COLOR));

        }

        x.setIndexes(xs);
        y.setIndexes(ys);

    }
}
