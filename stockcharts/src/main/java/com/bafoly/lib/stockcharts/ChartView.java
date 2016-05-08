package com.bafoly.lib.stockcharts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.bafoly.lib.stockcharts.model.AndroidCanvas;
import com.bafoly.lib.stockcharts.model.BaseModel;
import com.bafoly.lib.stockcharts.model.ChartData;

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

    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector mGestureDetector;


    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public void init(Context context){
        setFocusable(true);
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);
        mGestureDetector = new GestureDetector(context, mGestureListener);
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

        //drawTemp(canvas);


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

    }

    private void drawTemp(Canvas canvas){

        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);

        p.setTextSize(45);

        int padL = 40;
        int padR = 40;
        int padT = 30;
        int padB = 50;

        int buffer = 10;

        int dataCount = 20;

        int space = 50;

        String s = "01 Jan 16";

        Rect bounds = new Rect();

        p.getTextBounds(s,0,s.length(),bounds);

        padB = bounds.height()*2;


        int chartWidth = canvas.getWidth()-padL- padR;
        int chartHeight = canvas.getHeight() - padT - padB;


        //canvas.drawRect(padL-buffer, padT, padL+chartWidth+buffer, padT+chartHeight, p);

        canvas.drawLines(
                new float[]{
                        (float)padL-buffer, (float) padT, (float) padL +chartWidth+buffer, (float) padT,
                        (float)padL-buffer, (float) padT+chartHeight, (float) padL +chartWidth+buffer, (float) padT+chartHeight
                }, p);


        int labelCount = chartWidth/(bounds.width()+space);

        float multiplierX = chartWidth/(float)labelCount;

        Paint red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.STROKE);
        red.setStrokeWidth(1);

        for(int i = 0;i<labelCount;i++){
            canvas.drawLine(padL+(i*multiplierX), padT, padL+(i*multiplierX), padT+chartHeight, red);

            canvas.drawLine(padL+(i*multiplierX), padT+chartHeight, padL+(i*multiplierX), padT+chartHeight+(bounds.height()/2), p);

            canvas.drawText(s, padL+(i*multiplierX), padT+chartHeight+(bounds.height()/2)+bounds.height(), p);
        }

        canvas.drawLine(padL+((labelCount+1)*multiplierX), padT, padL+((labelCount+1)*multiplierX), padT+chartHeight, red);
        canvas.drawLine(padL+((labelCount+1)*multiplierX), padT+chartHeight, padL+((labelCount+1)*multiplierX), padT+chartHeight+(bounds.height()/2), p);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private final GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            //TODO
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //TODO
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //TODO
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //TODO
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    private final ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //TODO
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            //TODO
            return super.onScaleBegin(detector);
        }
    };

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
