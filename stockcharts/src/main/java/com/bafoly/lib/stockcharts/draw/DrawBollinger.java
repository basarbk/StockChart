package com.bafoly.lib.stockcharts.draw;

import com.bafoly.lib.stockcharts.model.adapter.CanvasAdapter;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.adapter.Painter;
import com.bafoly.lib.stockcharts.model.adapter.PathAdapter;
import com.bafoly.lib.stockcharts.model.data.SingleData;
import com.bafoly.lib.stockcharts.model.data.TripleData;
import com.bafoly.lib.stockcharts.model.drawable.ChartModel;

import java.util.List;

/**
 * Created by basarb on 6/14/2016.
 */
public class DrawBollinger implements DrawStrategy<ChartModel> {

    @Override
    public void draw(Environment environment, ChartModel drawableContent) {

        CanvasAdapter canvasAdapter = environment.getCanvasAdapter();

        List<SingleData> data = drawableContent.getData();

        int size = data.size();
        SingleData sd = data.get(size-1);

        if(!(sd instanceof TripleData)){
            return;
        }

        PathAdapter pathUp = canvasAdapter.getPath();
        PathAdapter pathDown = canvasAdapter.getPath();

        TripleData pos = null;

        boolean pathStarted = false;

        for(int i = environment.visibleXbegin; i<environment.visibleXend; i++){
            if(i>=size)
                break;

            if(data.get(i)==null)
                continue;

            pos = (TripleData) data.get(i);


            float yup = environment.getY(pos.getThree().floatValue());
            float ydown = environment.getY(pos.getTwo().floatValue());
            float x = environment.getX(i-environment.visibleXbegin);

            if(!pathStarted){
                pathUp.moveTo(x, yup);
                pathDown.moveTo(x, ydown);
                pathStarted = true;
            }
            else{
                pathUp.lineTo(x, yup);
                pathDown.lineTo(x, ydown);
            }
        }

        canvasAdapter.clip(environment);
        canvasAdapter.drawPath(pathUp, drawableContent.getPainter().getPaint(Painter.LINE_COLOR));
        canvasAdapter.drawPath(pathDown, drawableContent.getPainter().getPaint(Painter.LINE_COLOR));

    }
}
