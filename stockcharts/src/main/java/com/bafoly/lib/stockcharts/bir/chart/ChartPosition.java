package com.bafoly.lib.stockcharts.bir.chart;

import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.data.MainData;

import java.util.List;

/**
 * Created by basarb on 10/4/2015.
 */
public class ChartPosition {

    /**
     * whole width of our view
     */
    public int viewWidth = 0;

    /**
     * whole height of our view
     */
    public int viewHeight = 0;

    /**
     *
     */
    public int paddingLeft = 10;
    public int paddingRight = 60;
    public int paddingTop = 10;
    public int paddingBottom = 30;

    /**
     * chart area used for touch detection
     */
    public Rect chartGraphArea;

    /**
     * default data count to draw on to screen
     */
    public int visibleDataCount = 50;

    public final int visibleDataCountLowestLimit=10;

    /**
     * Open High Low Close values are stored
     */
    public OHLC ohlc;

    /**
     * Visual X begin index
     */
    public int visibleXbegin = 0;

    /**
     * Visual X end index
     */
    public int visibleXend = 0;


    public int drawWidht = 0;
    public int drawHeight = 0;

    private int marginChangeCounter = -1;
    private int currentvisualcalculationcounter = 0;

    public ChartPosition() {}


    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
        changeVisualCounter();
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
        changeVisualCounter();
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        changeVisualCounter();
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        changeVisualCounter();
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        changeVisualCounter();
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        changeVisualCounter();
    }

    private void changeVisualCounter(){
        currentvisualcalculationcounter++;
    }

    public Rect getChartGraphArea() {
        if(currentvisualcalculationcounter!=marginChangeCounter){
            createChartGraphArea();
            marginChangeCounter = currentvisualcalculationcounter;
        }
        return chartGraphArea;
    }

    public void setChartGraphArea(Rect chartGraphArea) {
        this.chartGraphArea = chartGraphArea;
    }

    public void createChartGraphArea(){
        this.chartGraphArea = new Rect(paddingLeft, paddingTop, viewWidth-paddingRight, viewHeight-paddingBottom);
    }

    public int getVisibleDataCount() {
        return visibleDataCount;
    }

    public void setVisibleDataCount(int visibleDataCount) {
        if(visibleDataCount<visibleDataCountLowestLimit){
            this.visibleDataCount = visibleDataCountLowestLimit;
        } else {
            this.visibleDataCount = visibleDataCount;
        }
    }



    public int getVisibleDataCountLowestLimit() {
        return visibleDataCountLowestLimit;
    }


    public OHLC getOhlc() {
        if(this.ohlc==null){
            createOHLC();
        }
        return ohlc;
    }

    public void setOhlc(OHLC ohlc) {
        this.ohlc = ohlc;
    }

    public void createOHLC(){
        if(this.ohlc == null){
            this.ohlc = new OHLC();
        }
    }

    public int getVisibleXbegin() {
        return visibleXbegin;
    }

    public void setVisibleXbegin(int visibleXbegin) {
        if(visibleXbegin<0){
            this.visibleXbegin = 0;
        } else {
            this.visibleXbegin = visibleXbegin;
        }
    }

    public int getVisibleXend() {
        return visibleXend;
    }

    public void setVisibleXend(int visibleXend) {
        this.visibleXend = visibleXend;
    }

    public void scroll(int x){
        this.visibleXbegin = this.visibleXbegin + x;
        if(this.visibleXbegin<0){
            this.visibleXbegin = 0;
        }
        this.visibleXend = this.visibleXend + x;
    }




    public int getDrawWidht() {
        if(drawWidht==0){
            drawWidht = viewWidth - paddingLeft - paddingRight;
        }
        return drawWidht;
    }


    public int getDrawHeight() {
        if(drawHeight==0){
            drawHeight = viewHeight - paddingBottom - paddingTop;
        }
        return drawHeight;
    }


    /**
     *
     * @param mData - data to draw
     * @param idx - idx of data
     */
    public boolean calculateOHLCpositions(MainData mData, int idx){
        if(idx<0 || idx>=mData.getChartData().getKapanis().size()){
            return false;
        } else {
            getOhlc().set(((mData.getMax()-mData.getChartData().getAcilis().get(idx))*mData.getMultiplierY())+paddingTop, // open
                    ((mData.getMax()-mData.getChartData().getYuksek().get(idx))*mData.getMultiplierY())+paddingTop, // high
                    ((mData.getMax()-mData.getChartData().getDusuk().get(idx))*mData.getMultiplierY())+paddingTop, // low
                    ((mData.getMax()-mData.getChartData().getKapanis().get(idx))*mData.getMultiplierY())+paddingTop // close
            );
            return true;
        }
    }

    public float getMax(List<Float> highs){
        float topVal = 0;
        int arraySize = highs.size();
        for (int i=visibleXbegin; i<visibleXend;i++){
            if(i>=0 && i<arraySize){
                if(highs.get(i)>topVal){
                    topVal = highs.get(i);
                }
            }
        }
        return topVal;
    }

    public float getMin(List<Float> mins){
        float minVal = 1000000000;
        int arraySize = mins.size();
        for (int i=visibleXbegin; i<visibleXend;i++){
            if(i>=0 && i<arraySize){
                if(mins.get(i)<minVal){
                    minVal = mins.get(i);
                }
            }
        }
        return minVal;
    }


    public int getTouchToDayIndex(float x, float multiplierX){
        if(x>(getViewWidth()-paddingRight)){
            return getVisibleXbegin()+(int) ((getViewWidth()-paddingRight-paddingLeft)/multiplierX);
        } else {
            return getVisibleXbegin()+(int) ((x-paddingLeft)/multiplierX);
        }
    }

    /**
     *
     * @param i - index of element
     * @param multiplierX - shift multiplier
     * @return x - position of x
     */
    public float getXChartIDXPosition(int i, float multiplierX){
        return paddingLeft+(((i+1)-visibleXbegin)*multiplierX);
    }

    /**
     *
     * @param price - current price
     * @param mData - main data
     * @return y - position of the price
     */
    public float getPriceToPoisiton(float price, MainData mData){
        return ((mData.getMax()-price)*mData.getMultiplierY())+paddingTop;
    }

    /**
     *
     * @param y - touched position y
     * @param mData - main data
     * @return price - value of touched position
     */
    public float getPositionToPrice(float y, MainData mData){
        return mData.getMax() - ((y - paddingTop)/mData.getMultiplierY());
    }


    public float getDateToPosition(String date, MainData mData){
        int idx = mData.getDates().indexOf(date);
        if(idx<0){
            return 0;
        } else {
            return getXChartIDXPosition(idx, mData.getMultiplierX());
        }
    }


    public class OHLC{
        public float o;
        public float h;
        public float l;
        public float c;
        public OHLC(){
        }

        public void set(float o, float h, float l, float c){
            this.o = o;
            this.h = h;
            this.l = l;
            this.c = c;
        }

    }



    @Override
    public String toString() {
        return "ChartPosition [visibleDataCount=" + visibleDataCount
                + ", visibleXbegin=" + visibleXbegin + ", visibleXend="
                + visibleXend + "]";
    }


}
