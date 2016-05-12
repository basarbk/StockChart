package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartLegend;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.utils.FormatTools;
import com.bafoly.lib.stockcharts.bir.chart.utils.Zaman;

public class ChartInspector extends ChartDraw {

	public ChartInspector(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}

	Paint bgcolor,textTarihPaint,overlayBG,overlayCerceve;
	boolean isPaintLoaded = false;
	float barWidth;
	
	@Override
	public void preChecks() {
		if(!isPaintLoaded){
			bgcolor = new Paint();
			bgcolor.setColor(Color.argb(64, 255, 255, 0));
			
			overlayBG = new Paint();
			overlayBG.setColor(Color.argb(128, 255, 255, 100));
			
			overlayCerceve = new Paint();
			overlayCerceve.setColor(Color.BLACK);
			overlayCerceve.setStrokeWidth(1);
			overlayCerceve.setStyle(Paint.Style.STROKE);
			
			textTarihPaint = new Paint();
			textTarihPaint.setAntiAlias(true);
			textTarihPaint.setColor(Color.BLACK);
			textTarihPaint.setTextSize(12);
			
			isPaintLoaded = true;
		}

		barWidth = mainData.getMultiplierX()/3;		
	}

	@Override
	public void draw(Canvas canvas) {
		preChecks();
	}
	
	float xToDraw;
	public void setX(float x){
		this.xToDraw = x;
	}
	
	float chartIdxPositionX;
	String curDate;
	int idx;
	float textWidth;
	ChartLegend[] chartLegends;
	float textAreaWidth=290;
	int left, right, top, bottom;
	Rect bounds;
	int heightText = 0;
	public void drawHightlight(Canvas canvas){
		preChecks();
		idx = chartPosition.getTouchToDayIndex(xToDraw, mainData.getMultiplierX());
		chartIdxPositionX = chartPosition.getXChartIDXPosition(idx, mainData.getMultiplierX());
		if(mainData.isIndicator()){
			curDate = Zaman.getDate(chartPosition, idx, mainData.getParentInstrumentData());
		} else {			
			curDate = Zaman.getDate(chartPosition, idx, mainData);
		}
		bounds = new Rect();
		textTarihPaint.getTextBounds("AKBNK", 0, 2, bounds);
		
		heightText = bounds.height();

		canvas.drawRect(chartIdxPositionX-barWidth, chartPosition.paddingTop, chartIdxPositionX+barWidth, chartPosition.viewHeight-chartPosition.paddingBottom , bgcolor);

		
		if(idx>=mainData.getDates().size()){
			
			textAreaWidth= textTarihPaint.measureText(curDate);

			left =chartPosition.getChartGraphArea().left+5;
			right = (int) (left + textAreaWidth + 10);
			top = chartPosition.getChartGraphArea().top+5;
			bottom = top + 5 + (heightText+5);

			canvas.drawRect(left, top , right, bottom, overlayBG);
			canvas.drawRect(left, top , right, bottom, overlayCerceve);

			canvas.drawText(curDate, left+5, top + heightText+5, textTarihPaint);
			
		} else {
			chartLegends = mainData.getChartLegend(idx);
			

			
			chartLegends = mainData.getChartLegend(idx);
			textAreaWidth = FormatTools.getWidthForLegend(chartLegends, textTarihPaint);
			
			left =chartPosition.getChartGraphArea().left+5;
			right = (int) (left + textAreaWidth + 10);
			top = chartPosition.getChartGraphArea().top+5;
			bottom = top + 5 + ((chartLegends.length)*(heightText+5));
			
			canvas.drawRect(left, top , right, bottom, overlayBG);
			canvas.drawRect(left, top , right, bottom, overlayCerceve);

			
			for(int i = 0;i<chartLegends.length;i++){
				canvas.drawText(chartLegends[i].toString(), left+5, top + ((i+1)*(heightText+5)), textTarihPaint);
			}
		}
		
	}
	
}
