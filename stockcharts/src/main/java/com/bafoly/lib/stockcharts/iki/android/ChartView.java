package com.bafoly.lib.stockcharts.iki.android;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Scroller;

import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartModel;

/**
 * Created by basarb on 4/28/2016.
 */
public class ChartView extends View {

    Context context;

    ChartModel chartModel;

    Environment environment;

    CanvasAdapter canvasAdapter;

    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector mGestureDetector;

    private Scroller mScroller;


    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    public void init(Context context){
        setFocusable(true);

        mScroller = new Scroller(context, null, true);

        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);
        mGestureDetector = new GestureDetector(context, mGestureListener);
    }

    public void draw(ChartModel main){

        chartModel = main;
        chartModel.getPainter().setDensity(context.getResources().getDisplayMetrics().density);

//        chartModel.getPainter().init();

        environment = main.getEnvironment();

        canvasAdapter = new AndroidCanvas();

        environment.setCanvasAdapter(canvasAdapter);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(chartModel!=null){
            canvasAdapter.setCanvas(canvas);
            environment.calculateMaxMin(chartModel);
            chartModel.draw(null);

        } else {
            // empty
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mScaleGestureDetector.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);

        return true;
    }

    private final GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        float xfirst;
        boolean touched = false;

        @Override
        public boolean onDown(MotionEvent e) {
            touched = environment.touchingToChart(e.getX(), e.getY());
            if(!touched)
                return false;

            xfirst = e.getX();
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //TODO
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(!touched)
                return false;

            if(Math.abs(xfirst-e2.getX())>environment.multiplierX){
                scrollMe((int)((xfirst-e2.getX())/environment.multiplierX));
                xfirst = e2.getX();
                return true;
            }
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //TODO
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    private void scrollMe(int count){
        if(environment.scroll(count)) {
            postInvalidate();
        }
    }

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
