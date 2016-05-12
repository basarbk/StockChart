package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class ChartOHLC extends ChartDraw{

	public ChartOHLC(ChartPosition chartPosition, MainData mainData) {
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
	
	Paint mumOHLCPaint;

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
//		
//		if(chartView.ohlc == null){			
//			chartView.creatOHLCinstance();
//		}
		barWidth = mainData.getMultiplierX()/3;		

		
	}

	@Override
	public void draw(Canvas canvas) {

		preChecks();
		for(int i = chartPosition.getVisibleXbegin();i<chartPosition.getVisibleXend();i++){
			if(chartPosition.calculateOHLCpositions(mainData, i)){

				chartIdxPositionX = chartPosition.getXChartIDXPosition(i, mainData.getMultiplierX());
				barXBaslangic = chartIdxPositionX - (barWidth);
				barXBitis = chartIdxPositionX + (barWidth);
				
				switch(((MainInstrumentData)mainData).getGunlukDegisim(i)){
				case Chart.DIRECTION_UP:
					mumOHLCPaint = mumPozitifPaint;
					break;
				case Chart.DIRECTION_DOWN:
					mumOHLCPaint = mumNegatifPaint;
					break;
				default:
					mumOHLCPaint = mumShadowPaint;
					break;
				}	
				canvas.drawLine(chartIdxPositionX, chartPosition.getOhlc().h, chartIdxPositionX, chartPosition.getOhlc().l, mumOHLCPaint);
				canvas.drawLine(chartIdxPositionX, chartPosition.getOhlc().c, barXBitis, chartPosition.getOhlc().c, mumOHLCPaint);
				canvas.drawLine(barXBaslangic, chartPosition.getOhlc().o, chartIdxPositionX, chartPosition.getOhlc().o, mumOHLCPaint);
			}
		}
	
		
	}
	
}
