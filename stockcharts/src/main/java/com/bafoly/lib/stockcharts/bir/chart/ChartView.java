package com.bafoly.lib.stockcharts.bir.chart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;
import com.bafoly.lib.stockcharts.bir.chart.data.UserInputData;
import com.bafoly.lib.stockcharts.bir.chart.data.UserInputTechnicalAnalysisData;
import com.bafoly.lib.stockcharts.bir.chart.data.indicator.IndikatorBollingerBands;
import com.bafoly.lib.stockcharts.bir.chart.data.indicator.IndikatorMACD;
import com.bafoly.lib.stockcharts.bir.chart.data.technicalanalysis.TechnicalAnalysisFibonacci;
import com.bafoly.lib.stockcharts.bir.chart.data.technicalanalysis.TechnicalAnalysisSupportResistance;
import com.bafoly.lib.stockcharts.bir.chart.data.technicalanalysis.TechnicalAnalysisTrendline;
import com.bafoly.lib.stockcharts.bir.chart.data.userhelper.UserComment;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartBar;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartCandleStick;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartGrid;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartInspector;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartLine;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartOHLC;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartOverlay;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartTeknikAnaliz;
import com.bafoly.lib.stockcharts.bir.chart.draw.ChartUserInput;

/**
 * Created by basarb on 10/4/2015.
 */
public class ChartView  extends View {

    /**
     * There can be multiple charts in one activity. ID is used to transfer actions from one view to another
     */
    int chartID;


    /**
     * if there are multiple charts, since the x line will be same for all, we'll just draw the x values to the one at bottom.
     */
    boolean drawXValues = true;

    /**
     * If this is first time, we'll calculate multiplierX and multiplierY based on the initial data provided
     * After calculation completed and chart drawn to screen, we'll update <b>multiplierX</b> and <b>multiplierY</b> values based on
     * touch inputs.
     */
    private boolean firstDrawCompleted = false;


    /**
     * this is storing the positioning reference values. it will be used with the drawing objects
     * also this object will be passed to other views to synch the positions if there are multiple chart views in an application
     */
    ChartPosition chartPosition;

    /**
     * If activity has multiple ChartView, we'll pass the user's touch inputs through this listener to other views
     */
    OnChartTouchListener touchTransferActivity;

    private MainData mainData;

    private ChartData baseChart;

    MainIndicatorData currentIndikatorData;
    IndikatorMACD currentIndikatorMACD;
    IndikatorBollingerBands bollingerIndikator;


    ChartTeknikAnaliz chartTeknikAnaliz;
    ChartUserInput chartUserInput;

    ChartGrid chartGrid;
    ChartCandleStick chartCandleStick;
    ChartOHLC chartOHLC;
    ChartBar chartBar;
    ChartLine chartLine;
    ChartOverlay chartOverlay;
    ChartInspector chartInspector;

    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector mGestureDetector;

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTouchDetectors(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTouchDetectors(context);
    }

    public ChartView(Context context) {
        super(context);
        initTouchDetectors(context);
    }

    private void initTouchDetectors(Context context){
        if(chartPosition==null){
            chartPosition = new ChartPosition();
        }

        setFocusable(true);
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleGestureListener);
        mGestureDetector = new GestureDetector(context, mGestureListener);

//        mScrollAnimator = ValueAnimator.ofFloat(0, 1);
//        mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//            	myfling();
//            }
//        });


//        scroller = new Scroller(getContext(), null, true);

//        zoomer = new Zoomer(context);
    }

    boolean enableTouch = true;
    public void enableTouch(boolean enableTouch){
        this.enableTouch = enableTouch;
    }

    public boolean isDrawXValues() {
        return drawXValues;
    }

    public void setDrawXValues(boolean drawXValues) {
        this.drawXValues = drawXValues;
    }

    public void setChartId(int id){
        this.chartID = id;
    }

    public int getChartId(){
        return this.chartID;
    }

    public ChartPosition getChartPosition() {
        return chartPosition;
    }

    public void setChartPosition(ChartPosition chartPosition) {
        this.chartPosition = chartPosition;
    }

    public boolean isFirstDrawCompleted() {
        return firstDrawCompleted;
    }

    public void setFirstDrawCompleted(boolean firstDrawCompleted) {
        this.firstDrawCompleted = firstDrawCompleted;
    }

    public void setBaseData(MainData mainData){
        this.mainData = mainData;
        if(mainData==null){
            this.baseChart = null;
            return;
        } else {
            this.baseChart = mainData.getChartData();

        }
        if (chartGrid != null) {
            chartGrid.setMainData(mainData);
        }

        if (chartBar != null) {
            chartBar.setMainData(mainData);
        }

        if (chartCandleStick != null) {
            chartCandleStick.setMainData(mainData);
        }

        if (chartOHLC != null) {
            chartOHLC.setMainData(mainData);
        }

        if (chartLine != null) {
            chartLine.setMainData(mainData);
        }

        if(chartOverlay!=null){
            chartOverlay.setMainData(mainData);
        }
    }

    public MainData getBaseData(){
        return this.mainData;
    }

    Paint emptyScreenPaint;
    boolean emptyScreenDataLoaded = false;
    private void loadEmptyScreen(){
        if(!emptyScreenDataLoaded){
            emptyScreenPaint = new Paint();
            emptyScreenPaint.setAntiAlias(true);
            emptyScreenPaint.setColor(Color.BLACK);
            emptyScreenPaint.setTextSize(42);

            emptyScreenDataLoaded = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(baseChart!=null){

            // chartPosition icin temel hesaplari yapalim
            if(!firstDrawCompleted){
                // grafigin bittigi nokta, elimizdeki son datanin idx olmali
                chartPosition.setVisibleXend(mainData.getChartData().getKapanis().size());
                if(chartPosition.getVisibleDataCount()>mainData.getChartData().getKapanis().size()){
                    chartPosition.setVisibleDataCount(mainData.getChartData().getKapanis().size());
                }
                firstDrawCompleted = true;
            }
            if(mainData.isInstrument()){
                if(baseChart.getKapanis().size()<chartPosition.getVisibleDataCount()){
                    // eger elimizdeki data, set ettigimiz limitten daha dusukse, o zaman ekrana elimizdeki kadarini cizmemiz gerekiyor..
                    chartPosition.setVisibleDataCount(mainData.getChartData().getKapanis().size());
                    chartPosition.setVisibleXbegin(0);
                } else {
                    // cizilecek data sayisi ekran limitinden fazla, bu durumda startIdx i cizilecek data sayisina gore hesaplayalim
                    chartPosition.setVisibleXbegin(chartPosition.getVisibleXend()-chartPosition.getVisibleDataCount());
                }
            }


            calculateXY(mainData);



            // layer 2 - coordinates
//			drawXY(canvas);
            if(chartGrid==null){
                chartGrid = new ChartGrid(chartPosition, mainData);
            }
            chartGrid.setDrawXY(isDrawXValues());
            chartGrid.draw(canvas);



            // burada volume cizilmeli..
            if(mainData.getInstrumentType()== Chart.INSTRUMENT_TYPE_STOCK){
                if(mainData.getChartData().getIslem()!=null){
                    if(chartBar==null){
                        chartBar = new ChartBar(chartPosition, mainData);
                    }
                    chartBar.setMyPaddigns(0, 0, chartPosition.getViewHeight()/5, 0);
                    chartBar.drawVolume(canvas);
                }
            }


            // layer 3 - main data
            drawDataChart(canvas, mainData);


            // layer 4 - indicator
            if(mainData.isContainsIndicator()){
                for(MainIndicatorData id : mainData.getIndicators()){
                    if(id.isOverlay()){
                        calculateXY(id);
                        drawDataChart(canvas, id);
                    }
                }
            }
            if(mainData.isContainsTechnicalAnalysis()){
                for(UserInputTechnicalAnalysisData td:mainData.getTechnicalAnalysis()){
                    if(chartTeknikAnaliz==null){
                        chartTeknikAnaliz = new ChartTeknikAnaliz(chartPosition, mainData);
                    }
                    chartTeknikAnaliz.draw(canvas, td, false);
                }
            }
            if(mainData.isContainsUserHelper()){
                for(UserComment ui:mainData.getUserInput()){
                    if(chartUserInput==null){
                        chartUserInput = new ChartUserInput(chartPosition, mainData);
                    }
                    chartUserInput.draw(canvas, ui);
                }
            }

            if(mainData.isContainsOverlayInstrument()){
                for(MainInstrumentData mid : mainData.getOverlayInstruments()){
                    calculateXY(mid);
                    drawDataChart(canvas, mid);
                }
            }

            if(mainData.getActiveTechnicalAnalysis()!=null){
                if(chartTeknikAnaliz==null){
                    chartTeknikAnaliz = new ChartTeknikAnaliz(chartPosition, mainData);
                }
                chartTeknikAnaliz.draw(canvas, mainData.getActiveTechnicalAnalysis(), true);
            }

            if(mainData.getActiveUserInput()!=null){
                if(chartUserInput==null){
                    chartUserInput = new ChartUserInput(chartPosition, mainData);
                }
                chartUserInput.draw(canvas, mainData.getActiveUserInput());
            }


            if(touchActivity==TOUCH_INSPECT){
                chartInspector.drawHightlight(canvas);
            } else {
                if(chartOverlay == null){
                    chartOverlay = new ChartOverlay(chartPosition, mainData);
                }
                chartOverlay.draw(canvas);
            }


        } else {
            loadEmptyScreen();
            String contentEmpty = "There is nothing to draw yet..";
            int width = (int) (emptyScreenPaint.measureText(contentEmpty)/2);
            canvas.drawText(contentEmpty, (chartPosition.getViewWidth()/2)-width, chartPosition.getViewHeight()/2, emptyScreenPaint);
        }
    }

    private void calculateXY(MainData mData){
        if(mData.getGraphType()==Chart.GRAPH_TYPE_OHLC || mData.getGraphType()== Chart.GRAPH_TYPE_CANDLE){
            if(!mData.isOverlay()){
                // main data uzerinde islem yapiyoruz..
                mData.setMax(chartPosition.getMax(mData.getChartData().getYuksek()));
                mData.setMin(chartPosition.getMin(mData.getChartData().getDusuk()));
                float buffer = (mData.getMax()-mData.getMin())/20;
                mData.setMax(mData.getMax()+buffer);
                mData.setMin(mData.getMin()-buffer);

            } else {
                // bu durumda overlay bir data olmali
                if(mData.isUseMyMaxMin()){
                    // baska bir hisseyle kiyaslama durumu
                    mData.setMax(chartPosition.getMax(mData.getChartData().getYuksek()));
                    mData.setMin(chartPosition.getMin(mData.getChartData().getDusuk()));
                    float buffer = (mData.getMax()-mData.getMin())/20;
                    mData.setMax(mData.getMax()+buffer);
                    mData.setMin(mData.getMin()-buffer);
                } else {
                    // EMA, Bollinger gibi seyler cizerken.. bu durumda main datanin max min degerlerini kullanicaz..
                    mData.setMax(mainData.getMax());
                    mData.setMin(mainData.getMin());
                }
            }
        } else {
            if(mData.getMainType()==Chart.MAIN_TYPE_INDICATOR){ // indikator icin max min degerleri farkli olabilir
                currentIndikatorData = (MainIndicatorData) mData;
                if(mData.getIndicatorType()==Chart.INDICATOR_TYPE_MACD){
                    currentIndikatorMACD = (IndikatorMACD) currentIndikatorData;
                    currentIndikatorMACD.calculateMaxMin(chartPosition);
                }else if(!currentIndikatorData.isUseMyMaxMin()){ // edge value tanimli degilse onlari hesaplayalim.. tanimliysa max min degerleri zaten bellidir.
                    mData.setMax(chartPosition.getMax(mainData.getChartData().getLineData()));
                    mData.setMin(chartPosition.getMin(mainData.getChartData().getLineData()));
                }
            } else {
                if(!mData.isOverlay()){
                    // main data uzerinde islem yapiyoruz..
                    mData.setMax(chartPosition.getMax(mData.getChartData().getLineData()));
                    mData.setMin(chartPosition.getMin(mData.getChartData().getLineData()));
                    float buffer = (mData.getMax()-mData.getMin())/20;
                    mData.setMax(mData.getMax()+buffer);
                    mData.setMin(mData.getMin()-buffer);
                } else {
                    // bu durumda overlay bir data olmali
                    if(mData.isUseMyMaxMin()){
                        // baska bir hisseyle kiyaslama durumu
                        mData.setMax(chartPosition.getMax(mData.getChartData().getYuksek()));
                        mData.setMin(chartPosition.getMin(mData.getChartData().getDusuk()));
                    } else {
                        // EMA, Bollinger gibi seyler cizerken.. bu durumda main datanin max min degerlerini kullanicaz..
                        mData.setMax(mainData.getMax());
                        mData.setMin(mainData.getMin());
                    }
                }
            }
        }

        // yatayda sigdiracagimiz datalar icin pozisyon referansi
        mData.setMultiplierX((chartPosition.getChartGraphArea().width())/(float)(chartPosition.getVisibleDataCount()+1));

        // dikeyde pozisyonlamak icin gereken referans
        mData.setMultiplierY((chartPosition.getChartGraphArea().height())/(mData.getMax()-mData.getMin()));

    }

    private void drawDataChart(Canvas canvas, MainData mData){
        switch(mData.getGraphType()){
            case Chart.GRAPH_TYPE_CANDLE:
                if(chartCandleStick==null){
                    chartCandleStick = new ChartCandleStick(chartPosition, mData);
                }
                chartCandleStick.draw(canvas);
                break;
            case Chart.GRAPH_TYPE_OHLC:
                if(chartOHLC == null){
                    chartOHLC = new ChartOHLC(chartPosition, mData);
                }
                chartOHLC.draw(canvas);
                break;
            case Chart.GRAPH_TYPE_BAR:
                if(chartBar == null){
                    chartBar = new ChartBar(chartPosition, mData);
                }
                chartBar.draw(canvas);
                break;
            case Chart.GRAPH_TYPE_LINE:
                // TODO baska bir main type gelirse???
                if(chartLine==null){
                    chartLine = new ChartLine(chartPosition, mData);
                } else {
                    chartLine.setMainData(mData);
                }
                if(mData.getMainType() == Chart.MAIN_TYPE_INSTRUMENT){
                    chartLine.draw(canvas);

                } else {
                    currentIndikatorData = (MainIndicatorData) mData;
                    switch(currentIndikatorData.getIndicatorType()){
                        case Chart.INDICATOR_TYPE_BOLLINGER:

                            bollingerIndikator = (IndikatorBollingerBands) mData;

                            bollingerIndikator.setChartData(new ChartData());
                            bollingerIndikator.getChartData().setLineData(bollingerIndikator.getUp().getLineData());
                            if(chartLine==null){
                                chartLine = new ChartLine(chartPosition, mData);
                            }
                            chartLine.setSkip(bollingerIndikator.getIndicatorPeriod());
                            chartLine.draw(canvas);
                            bollingerIndikator.getChartData().setLineData(bollingerIndikator.getDown().getLineData());
                            chartLine.draw(canvas);
                            break;
                        default:
                            chartLine.setSkip(currentIndikatorData.getIndicatorPeriod());
                            chartLine.draw(canvas);
                            break;
                    }


                }
                break;
        }

    }


    // ==========================================================================================//
    // =========================    TOUCH ISLEMLERI    ==========================================//
    // ==========================================================================================//

    long commentID;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!enableTouch){
            return false;
        } else if(this.mainData==null){
            return false;
        } else {
            switch(touchActivity){
                case TOUCH_NONE:
                    commentID = commentTouchDetector(event);
                    if(commentID>=0){
                        touchTransferActivity.commentTouchListener(getChartId(), commentID);
                        invalidate();
                    } else{
                        mGestureDetector.onTouchEvent(event);
                        mScaleGestureDetector.onTouchEvent(event);
                    }
                    break;
                case TOUCH_TECHNICAL_ANALYSIS:
                    handleUserInputTouch(event,mainData.getActiveTechnicalAnalysis());
                    touchTransferActivity.touchedView(chartID);
                    break;
                case TOUCH_USER_INPUT:
                    handleUserInputTouch(event,mainData.getActiveUserInput());
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        touchTransferActivity.touchedView(chartID);
                        touchTransferActivity.commentTouchListener(getChartId(), -1);
                    }
                    break;
                case TOUCH_INSPECT:
                    chartInspector.setX(event.getX());
                    touchTransferActivity.passInspectData(chartID, event.getX());
                    invalidate();
                    break;
            }
            return true;
        }
    }


    private void handleUserInputTouch(MotionEvent event, UserInputData userInputData){
        switch(event.getAction()& MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                if(userInputData.isInEditMode()){
                    if(userInputData.isTouchingToEditPoints((int) event.getX(),(int)event.getY())){
                        userInputData.handleEditTouch(chartPosition, event.getX(), event.getY(), mainData);
                    }
                } else {
                    userInputData.handleFirstTouch(chartPosition, event.getX(), event.getY(), mainData);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(userInputData.isInEditMode()){
                    userInputData.handleEditTouch(chartPosition, event.getX(), event.getY(), mainData);
                } else {
                    userInputData.handleLastTouch(chartPosition, event.getX(), event.getY(), mainData);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(userInputData.isInEditMode()){
                    userInputData.handleEditTouch(chartPosition, event.getX(), event.getY(), mainData);
                } else {
                    userInputData.handleLastTouch(chartPosition, event.getX(), event.getY(), mainData);
                    userInputData.setInEditMode(true);
                }
                break;
        }

        invalidate();
    }


    private long commentTouchDetector(MotionEvent event){
        long id = -1L;
        if(this.mainData.isContainsUserHelper()){
            for(UserComment ui:this.mainData.getUserInput()){
                if(ui.isSelected()){
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        if(ui.isTouching((int)event.getX(), (int)event.getY())){
                            ui.setSelected(false);
                            id = ui.getId();
                        }
                    }
                } else {
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        if(ui.isTouching((int)event.getX(), (int)event.getY())){
                            ui.setSelected(true);
                            id = ui.getId();
                        }
                    }
                }
            }
        }
        if(id>=0){
//			digerlerinin highlightini silelim
            for(UserComment ui:this.mainData.getUserInput()){
                if(ui.getId()!=id){
                    ui.setSelected(false);
                }
            }
        }

        return id;
    }

    /**
     * scroller is used for fling action
     */
//	private Scroller scroller;
//	private ValueAnimator mScrollAnimator;
//    private Zoomer zoomer;

    int touchActivity = TOUCH_NONE;

    public static final int TOUCH_NONE = -1;
    public static final int TOUCH_TECHNICAL_ANALYSIS = 0;
    public static final int TOUCH_USER_INPUT = 1;
    public static final int TOUCH_INSPECT = 2;

    /**
     *
     * @param touchActivity ChartView.TOUCH_TECHNICAL_ANALYSIS, ChartView.TOUCH_USER_INPUT or ChartView.TOUCH_INSPECT
     * @param touchType If this is technical analysis the this must be Chart.ANALYSIS_TYPE_...<br>
     * if this is user input, then value must be Chart.HELPER_TYPE_...<br>
     * if this is for inspect, then the value must be Chart.HELPER_TYPE_NONE
     */
    public void startUserTouch(int touchActivity, int touchType){
        this.touchActivity = touchActivity;
        switch(touchActivity){
            case TOUCH_TECHNICAL_ANALYSIS:
                chartTeknikAnaliz = new ChartTeknikAnaliz(chartPosition, mainData);
                if(mainData.getActiveTechnicalAnalysis()==null){
                    switch(touchType){
                        case Chart.ANALYSIS_TYPE_SUPPORT_RESISTANCE:
                            mainData.setActiveTechnicalAnalysis(new TechnicalAnalysisSupportResistance(mainData));
                            break;
                        case Chart.ANALYSIS_TYPE_FIBONACCI:
                            mainData.setActiveTechnicalAnalysis(new TechnicalAnalysisFibonacci(mainData));
                            break;
                        case Chart.ANALYSIS_TYPE_TRENDLINE:
                            mainData.setActiveTechnicalAnalysis(new TechnicalAnalysisTrendline(mainData));
                            break;
                    }
                } else {
                    mainData.getActiveTechnicalAnalysis().setInEditMode(true);
                }
                break;
            case TOUCH_USER_INPUT:
                chartUserInput = new ChartUserInput(chartPosition, mainData);
                if(mainData.getActiveUserInput()==null){
                    switch(touchType){
                        case Chart.HELPER_TYPE_USER_INPUT:
                            mainData.setActiveUserInput(new UserComment(mainData));
                            break;
                        case Chart.HELPER_TYPE_USER_HIGHLIGHT:
                            break;
                    }
                }
                break;
            case TOUCH_INSPECT:
                chartInspector = new ChartInspector(chartPosition, mainData);
                break;
        }
    }

    public void stopUserTouch(){
        touchActivity = TOUCH_NONE;
        chartTeknikAnaliz = null;
        chartUserInput = null;
        chartInspector = null;
    }


    public void highlight(float x){
        chartInspector.setX(x);
    }


    private boolean chartTouch(float x, float y){
        if(chartPosition.getChartGraphArea().contains((int)x, (int)y)){
            return true;
        }
        return false;
    }

    private final GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        float xfirst;
        public boolean onDown(MotionEvent e) {
            xfirst = e.getX();
            return true;
        };

        public boolean onDoubleTap(MotionEvent e) {
            chartPosition.setVisibleDataCount((int) ((int)chartPosition.getVisibleDataCount()*0.5f));
            touchTransferActivity.positionChanged(chartID, OnChartTouchListener.TOUCH_ZOOM, chartPosition);
            //ViewCompat.postInvalidateOnAnimation(ChartView.this);
            return true;
        };

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(chartTouch(e1.getX(),e1.getY())){
                if(Math.abs(xfirst-e2.getX())>mainData.getMultiplierX()){
                    scrollme((int)((xfirst-e2.getX())/mainData.getMultiplierX()));
                    xfirst = e2.getX();
                    return true;
                }

            }
            return false;
        };

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // TODO fling implement edilecek..
            return false;
        };




    };

    private final ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {

        float spanX;
        float fark;
        int tempCount;
        float oran;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            fark = spanX - detector.getCurrentSpanX();
            // negatifse kuculuyordur
            oran = (fark)/(25);
            if(oran>0){
                tempCount= (int) (chartPosition.getVisibleDataCount()+(oran*4));
                chartPosition.setVisibleDataCount(tempCount);

            }else {
                chartPosition.setVisibleDataCount((int) ((int)chartPosition.getVisibleDataCount()+(oran*4)));
            }
            spanX =detector.getCurrentSpanX();
            touchTransferActivity.positionChanged(chartID, OnChartTouchListener.TOUCH_ZOOM, chartPosition);
            //ViewCompat.postInvalidateOnAnimation(ChartView.this);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            spanX = detector.getCurrentSpanX();
            return true;
        }
    };

    private void scrollme(int count){
        if(chartPosition.getVisibleXbegin()+count>0 && chartPosition.getVisibleXend()+count<=baseChart.getLineData().size()+chartPosition.getVisibleDataCount()-1){
            chartPosition.scroll(count);
            touchTransferActivity.positionChanged(chartID, OnChartTouchListener.TOUCH_SCROLL, chartPosition);
            postInvalidate();
        }

    }



    // ==========================================================================================//
    // =========================    TOUCH ISLEMLERI END    ======================================//
    // ==========================================================================================//




    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 300;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            // Must be this size
            chartPosition.setViewWidth(widthSize);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            chartPosition.setViewWidth(Math.min(desiredWidth, widthSize));
        } else {
            // Be whatever you want
            chartPosition.setViewWidth(desiredWidth);
        }

        // Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            // Must be this size
            chartPosition.setViewHeight(heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            chartPosition.setViewHeight(Math.min(desiredHeight, heightSize));
        } else {
            // Be whatever you want
            chartPosition.setViewHeight(desiredHeight);
        }

        // MUST CALL THIS
        setMeasuredDimension(chartPosition.getViewWidth(), chartPosition.getViewHeight());

    }



    public void setTouchTransferActivity(Activity act){
        this.touchTransferActivity = (OnChartTouchListener) act;
    }



    public static interface OnChartTouchListener{

        public static final int TOUCH_ADJUST = 0;
        public static final int TOUCH_SCROLL = 1;
        public static final int TOUCH_ZOOM = 2;

        public void positionChanged(int viewID, int touchAction, ChartPosition chartPosition);
        public void touchedView(int viewID);
        public void passInspectData(int viewId, float x);
        public void commentTouchListener(int viewId, long commentId);
    }



    public void transferTouchAction(int action, ChartPosition cPos) {
        switch(action){
            case OnChartTouchListener.TOUCH_SCROLL:
                chartPosition.setVisibleXbegin(cPos.getVisibleXbegin());
                chartPosition.setVisibleXend(cPos.getVisibleXend());
                break;
            case OnChartTouchListener.TOUCH_ZOOM:
                chartPosition.setVisibleDataCount(cPos.getVisibleDataCount());
                break;
            case OnChartTouchListener.TOUCH_ADJUST:
                chartPosition.setVisibleXbegin(cPos.getVisibleXbegin());
                chartPosition.setVisibleXend(cPos.getVisibleXend());
                chartPosition.setVisibleDataCount(cPos.getVisibleDataCount());
        }
        invalidate();
//		postInvalidate(left, top, right, bottom);
    }

}
