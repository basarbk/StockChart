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

import com.bafoly.lib.stockcharts.iki.model.CanvasAdapter;
import com.bafoly.lib.stockcharts.iki.model.drawable.BaseModel;
import com.bafoly.lib.stockcharts.iki.model.drawable.ChartData;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.DateAxis;

/**
 * Created by basarb on 4/28/2016.
 */
public class ChartView extends View {

    Context context;

    BaseModel main;

    ChartData chartData;

    Environment environment;

    CanvasAdapter canvasAdapter;

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
        this.context = context;
        init(context);
    }

    public void init(Context context){
        setFocusable(true);
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);
        mGestureDetector = new GestureDetector(context, mGestureListener);
    }

    public void draw(BaseModel main){
        this.main = main;

        chartData = main.getChartData();
        chartData.getPainter().setDensity(context.getResources().getDisplayMetrics().density);

        chartData.getPainter().init();

        environment = main.getEnvironment();

        canvasAdapter = new AndroidCanvas();

        environment.setCanvasAdapter(canvasAdapter);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(main!=null){
            canvasAdapter.setCanvas(canvas);
            environment.calculateMaxMin(chartData);
            main.draw();

        } else {
            // empty
        }

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
