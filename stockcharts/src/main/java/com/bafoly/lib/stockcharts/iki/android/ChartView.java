package com.bafoly.lib.stockcharts.iki.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.bafoly.lib.stockcharts.iki.model.drawable.BaseModel;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.DateAxis;

/**
 * Created by basarb on 4/28/2016.
 */
public class ChartView extends View {

    // chart data is basically X and Y values
    // X is the timeline
    // Y is the actual data

    // base position holders


    BaseModel main;

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

    public void draw(BaseModel main){
        this.main = main;
        this.main.getEnvironment().setCanvasAdapter(new AndroidCanvas());
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        if(main!=null){
            main.getEnvironment().getCanvasAdapter().setCanvas(canvas);
            main.getEnvironment().calculateMaxMin(main.getChartData());
            main.getEnvironment().calculateXYgaps(main.getChartData().getyAxis());
            main.getEnvironment().calculateAxisProperties(main.getChartData().getPainter());
            main.draw();



        } else {
            // empty
        }

    }

    private void drawTemp(Canvas canvas){

        AndroidCanvas ac = new AndroidCanvas(canvas);

        DefaultPainter ap = new DefaultPainter();

        DateAxis axis = new DateAxis(null);

        Paint axisPaint = new Paint();
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStyle(Paint.Style.STROKE);
        axisPaint.setTextSize(36);

        ap.setPaint(Painter.AXIS_COLOR, axisPaint);


        Environment cp = new Environment();

        cp.setDataCount(20);

//        cp.calculateAxisProperties(ac);
//
//        ChartData cd = new ChartData(axis, null);
//        cd.setEnvironment(cp);

        //cp.drawGrid(ac, cd);
//
//
//        int labelCount = chartWidth/(bounds.width()+space);
//
//        float multiplierX = chartWidth/(float)labelCount;
//
//        Paint red = new Paint();
//        red.setColor(Color.RED);
//        red.setStyle(Paint.Style.STROKE);
//        red.setStrokeWidth(1);
//
//
//        canvas.drawText("count: "+labelCount, 0, 100, axisPaint);
//        for(int i = 0;i<labelCount;i++){
//            int diff = 0;
//            if(i!=0){
//                diff = bounds.width()/2;
//            }
//            canvas.drawLine(padL+(i*multiplierX), padT, padL+(i*multiplierX), padT+chartHeight, red);
//
//            canvas.drawLine(padL+(i*multiplierX), padT+chartHeight, padL+(i*multiplierX), padT+chartHeight+(bounds.height()/2), axisPaint);
//
//            canvas.drawText(s, padL+(i*multiplierX)-diff, padT+chartHeight+(bounds.height()/2)+bounds.height(), axisPaint);
//        }
//
//        canvas.drawLine(padL+((labelCount+1)*multiplierX), padT, padL+((labelCount+1)*multiplierX), padT+chartHeight, red);
//        canvas.drawLine(padL+((labelCount+1)*multiplierX), padT+chartHeight, padL+((labelCount+1)*multiplierX), padT+chartHeight+(bounds.height()/2), axisPaint);
//

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
