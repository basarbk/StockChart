package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class ChartBar extends ChartDraw{
	
	public ChartBar(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}
	
	boolean barPaintLoaded = false;
	// bar chart icin
	Paint barPaint;
	int barColor = Color.BLUE;
	Paint barCercevePaint;
	int barCerceveColor = Color.argb(128,128, 128, 128);
	float barXBaslangic, barXBitis;
	float barWidth; // hem candle hem barchartta kullaniyoruz
	
	float zeroLine;
	float curVal;
	
	int hacimPozitifColor =  Color.rgb(196, 255, 196);
	int hacimNegatifColor = Color.rgb(255, 196, 196);
	int hacimNotrColor = Color.rgb(128, 128, 128);
	
	
	
	
	@Override
	public void preChecks() {
		if(!barPaintLoaded){			
			barPaint = new Paint();
			barPaint.setStrokeWidth(2);
			barPaint.setColor(mainData.getColor());

			barCercevePaint = new Paint();
			barCercevePaint.setStrokeWidth(1);
			barCercevePaint.setStyle(Paint.Style.STROKE);
			barCercevePaint.setColor(barCerceveColor);
			barPaintLoaded = false;
		}
		barWidth = mainData.getMultiplierX()/3;		
	}

	@Override
	public void draw(Canvas canvas) {
		preChecks();
		zeroLine = chartPosition.getPriceToPoisiton(0, mainData);
		if(zeroLine>chartPosition.getViewHeight()-chartPosition.getPaddingBottom()){
			zeroLine = chartPosition.getViewHeight()-chartPosition.getPaddingBottom();
		}
		if(chartPosition.getVisibleXbegin()<0){
			chartPosition.setVisibleXbegin(0);
		}
		
		for (int i=chartPosition.getVisibleXbegin(); i<chartPosition.getVisibleXend();i++){
			if(i<mainData.getChartData().getLineData().size()){
				chartIdxPositionX = chartPosition.getXChartIDXPosition(i, mainData.getMultiplierX());
				curVal = mainData.getChartData().getLineData().get(i);
				chartValuePositionY = chartPosition.getPriceToPoisiton(curVal, mainData);
				barXBaslangic = chartIdxPositionX-(barWidth);
				barXBitis = chartIdxPositionX+(barWidth);	
				
				if(curVal>0){
					canvas.drawRect(barXBaslangic,chartValuePositionY,barXBitis,zeroLine,barPaint);
					canvas.drawRect(barXBaslangic,chartValuePositionY,barXBitis,zeroLine,barCercevePaint);					
				} else {
					canvas.drawRect(barXBaslangic,zeroLine,barXBitis,chartValuePositionY,barPaint);
					canvas.drawRect(barXBaslangic,zeroLine,barXBitis,chartValuePositionY,barCercevePaint);
				}
			}
		}
	}
	
	float max, multiplier;
	public void drawVolume(Canvas canvas){
		preChecks();
		max = chartPosition.getMax(mainData.getChartData().getIslem());
		multiplier = (myPaddingTop-myPaddingBottom)/max;
		
		zeroLine = getPriceToPosition(0);
		if(zeroLine>chartPosition.getViewHeight()-chartPosition.getPaddingBottom()){
			zeroLine = chartPosition.getViewHeight()-chartPosition.getPaddingBottom();
		}
		if(chartPosition.getVisibleXbegin()<0){
			chartPosition.setVisibleXbegin(0);
		}
		
		for (int i=chartPosition.getVisibleXbegin(); i<chartPosition.getVisibleXend();i++){
			if(i<mainData.getChartData().getIslem().size()){
				chartIdxPositionX = chartPosition.getXChartIDXPosition(i, mainData.getMultiplierX());
				curVal = mainData.getChartData().getIslem().get(i);
				chartValuePositionY = ((max-curVal)*multiplier)+chartPosition.getChartGraphArea().bottom-myPaddingTop;
				barXBaslangic = chartIdxPositionX-(barWidth);
				barXBitis = chartIdxPositionX+(barWidth);
				switch(((MainInstrumentData)mainData).getGunlukDegisim(i)){
				case Chart.DIRECTION_UP:
					barPaint.setColor(hacimPozitifColor);
					break;
				case Chart.DIRECTION_DOWN:
					barPaint.setColor(hacimNegatifColor);
					break;
					default:
						barPaint.setColor(hacimNotrColor);
				}
				
				if(curVal>0){
					canvas.drawRect(barXBaslangic,chartValuePositionY,barXBitis,zeroLine,barPaint);
				} else {
					canvas.drawRect(barXBaslangic,zeroLine,barXBitis,chartValuePositionY,barPaint);
				}
			}
		}
		
	}
	
	private float getPriceToPosition(float curVal){
		return ((max-curVal)*multiplier)+chartPosition.getChartGraphArea().bottom-myPaddingTop;
	}
	
	
}
