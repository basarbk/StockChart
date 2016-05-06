package com.bafoly.lib.stockcharts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.bafoly.lib.stockcharts.model.AndroidCanvas;
import com.bafoly.lib.stockcharts.model.BaseModel;
import com.bafoly.lib.stockcharts.model.ChartData;
import com.bafoly.lib.stockcharts.model.ChartProperties;

/**
 * Created by basarb on 4/28/2016.
 */
public class ChartView extends View {

    // chart data is basically X and Y values
    // X is the timeline
    // Y is the actual data

    // base position holders


    BaseModel main;

    ChartData chartData;

    AndroidCanvas androidCanvas;


    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init(){
    }

    public void draw(ChartData chartData){
        this.chartData = chartData;
        this.main = null;

        invalidate();
    }

    public void draw(BaseModel main){
        this.chartData = null;
        this.main = main;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(androidCanvas==null){
            androidCanvas = new AndroidCanvas(canvas);
        }

        androidCanvas.setCanvas(canvas);

        if(main!=null){
            main.getChartData().calculatePositionReferences(androidCanvas);
            main.draw(androidCanvas);



        } else if (chartData!=null){
            chartData.calculatePositionReferences(androidCanvas);
            chartData.draw(androidCanvas);
        } else {
            // empty
        }



        // measurements

        // draw background
//        background.draw(canvas);

        // draw bottom layer

        // draw main data

        // draw top layer

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //TODO
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }
}
