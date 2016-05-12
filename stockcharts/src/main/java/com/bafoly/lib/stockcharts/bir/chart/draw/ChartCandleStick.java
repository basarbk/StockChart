package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class ChartCandleStick extends ChartDraw{	
		
	
	public ChartCandleStick(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}
	

	boolean candlePaintLoaded = false;
	
	Paint mumCercevePaint;
	int mumCerceveColor = Color.BLACK;
	float mumCerceveWidth = 1;
	
	
	Paint mumPozitifPaint;
	int mumPozitifColor = Color.GREEN;
	
	Paint mumNegatifPaint;
	int mumNegatifColor = Color.RED;
	
	Paint mumShadowPaint;
	int mumShadowColor = Color.BLACK;
	float mumShadowWidth = 1;

	float barXBaslangic, barXBitis;
	float barWidth; // hem candle hem barchartta kullaniyoruz
	
	

	@Override
	public void preChecks() {
		if(!candlePaintLoaded){
			mumCercevePaint = new Paint();
			mumCercevePaint.setColor(mumCerceveColor);
			mumCercevePaint.setStrokeWidth(mumCerceveWidth);
			mumCercevePaint.setStyle(Paint.Style.STROKE);
			
			mumPozitifPaint = new Paint();
			mumPozitifPaint.setStrokeWidth(2);
			mumPozitifPaint.setColor(mumPozitifColor);
			
			mumNegatifPaint = new Paint();
			mumNegatifPaint.setStrokeWidth(2);
			mumNegatifPaint.setColor(mumNegatifColor);
			
			mumShadowPaint = new Paint();
			mumShadowPaint.setStrokeWidth(mumShadowWidth);
			mumShadowPaint.setColor(mumShadowColor);
			mumShadowPaint.setStyle(Paint.Style.STROKE);
			mumShadowPaint.setAntiAlias(true);		
			candlePaintLoaded = true;
		}
		
//		if(chartView.ohlc == null){			
//			chartView.creatOHLCinstance();
//		}
		barWidth = mainData.getMultiplierX()/3;		

		
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		preChecks();
		
		for (int i=chartPosition.getVisibleXbegin(); i<chartPosition.getVisibleXend();i++){
//		for(int i = chartView.visibleXbegin;i<chartView.visibleXend;i++){
			if(chartPosition.calculateOHLCpositions(mainData, i)){
				chartIdxPositionX = chartPosition.getXChartIDXPosition(i, mainData.getMultiplierX());
				barXBaslangic = chartIdxPositionX - (barWidth);
				barXBitis = chartIdxPositionX + (barWidth);
				
				// once yuksekten dusuge duz cizgi ciziyoruz..
				canvas.drawLine(chartIdxPositionX, chartPosition.getOhlc().h, chartIdxPositionX, chartPosition.getOhlc().l, mumShadowPaint);
				
				switch(((MainInstrumentData)mainData).getGunlukDegisim(i)){
				case Chart.DIRECTION_UP:
					canvas.drawRect(barXBaslangic,chartPosition.getOhlc().c,barXBitis,chartPosition.getOhlc().o,mumPozitifPaint);
					canvas.drawRect(barXBaslangic,chartPosition.getOhlc().c,barXBitis,chartPosition.getOhlc().o,mumCercevePaint);
					break;
				case Chart.DIRECTION_DOWN:
					canvas.drawRect(barXBaslangic,chartPosition.getOhlc().o,barXBitis,chartPosition.getOhlc().c,mumNegatifPaint);
					canvas.drawRect(barXBaslangic,chartPosition.getOhlc().o,barXBitis,chartPosition.getOhlc().c,mumCercevePaint);
					break;
				default:
					canvas.drawLine(barXBaslangic,chartPosition.getOhlc().c,barXBitis,chartPosition.getOhlc().c,mumShadowPaint);
					break;
				}
			}
		}
		
	}

	

}
