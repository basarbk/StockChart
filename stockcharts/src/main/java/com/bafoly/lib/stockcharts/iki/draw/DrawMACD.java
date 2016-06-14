package com.bafoly.lib.stockcharts.iki.draw;

import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.PathAdapter;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.data.TripleData;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartModel;

import java.util.List;

/**
 * Created by basarb on 6/14/2016.
 */
public class DrawMACD implements DrawStrategy<ChartModel> {

    @Override
    public void draw(Environment environment, ChartModel drawableContent) {

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        List<SingleData> data = drawableContent.getData();

        int size = data.size();

        SingleData sd = data.get(size-1);

        if(!(sd instanceof TripleData)){
            return;
        }

        PathAdapter path12 = canvasAdapter.getPath();
        PathAdapter path26 = canvasAdapter.getPath();

        TripleData pos = null;

        boolean pathStarted = false;

        float zeroLine = environment.getY(0);
        if(zeroLine > environment.getChartHeight() - environment.getPaddingBottom()){
            zeroLine = environment.getChartHeight() - environment.getPaddingBottom();
        }

        float barWidth = environment.multiplierX/3;

        for(int i = environment.visibleXbegin;i<environment.visibleXend;i++){
            if(i>=size)
                break;

            if(data.get(i)==null)
                continue;

            pos = (TripleData) data.get(i);

            float yup = environment.getY(pos.getThree().floatValue());
            float ydown = environment.getY(pos.getTwo().floatValue());

            float histogram = pos.getOne().floatValue();
            float yhistogram = environment.getY(histogram);

            float x = environment.getX(i-environment.visibleXbegin);

            if(!pathStarted){
                path12.moveTo(x, yup);
                path26.moveTo(x, ydown);
                pathStarted = true;
            }
            else{
                path12.lineTo(x, yup);
                path26.lineTo(x, ydown);
            }

            if(histogram>0){
                canvasAdapter.drawRect(x-barWidth, yhistogram, x+barWidth, zeroLine, drawableContent.getPainter().getPaint(Painter.BAR_COLOR));
            } else {
                canvasAdapter.drawRect(x-barWidth, zeroLine, x+barWidth, yhistogram, drawableContent.getPainter().getPaint(Painter.BAR_COLOR));
            }

        }

        canvasAdapter.clip(environment);
        canvasAdapter.drawPath(path12, drawableContent.getPainter().getPaint(Painter.LINE_COLOR));
        canvasAdapter.drawPath(path26, drawableContent.getPainter().getPaint(Painter.LINE_COLOR));


    }
}
