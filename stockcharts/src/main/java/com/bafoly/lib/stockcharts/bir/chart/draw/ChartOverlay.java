package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartLegend;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.utils.FormatTools;

public class ChartOverlay extends ChartDraw {

	public ChartOverlay(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}

	Paint overlayBG,overlayCerceve,textTarihPaint;
	boolean isOverlayPaintLoaded = false;
	@Override
	public void preChecks() {
		if(!isOverlayPaintLoaded){			
			overlayBG = new Paint();
			overlayBG.setColor(Color.argb(220, 255, 255, 255));
			
			overlayCerceve = new Paint();
			overlayCerceve.setColor(Color.BLACK);
			overlayCerceve.setStrokeWidth(1);
			overlayCerceve.setStyle(Paint.Style.STROKE);
			
			textTarihPaint = new Paint();
			textTarihPaint.setAntiAlias(true);
			textTarihPaint.setColor(Color.BLACK);
			textTarihPaint.setTextSize(16);

			isOverlayPaintLoaded = true;
		}
	}

	int idx;
	float textAreaWidth=290;
	ChartLegend[] chartLegends;
	int left, right, top, bottom;
	Rect bounds;
	int heightText = 0;
	@Override
	public void draw(Canvas canvas) {
		preChecks();
		
		idx = chartPosition.getVisibleXend()-1;
		if(chartPosition.getVisibleXend()>=mainData.getChartData().getLineData().size()){
			idx = mainData.getChartData().getLineData().size()-1;
		}
		
		
		bounds = new Rect();
		chartLegends = mainData.getChartLegend(idx);
		textAreaWidth = FormatTools.getWidthForLegend(chartLegends, textTarihPaint);
		textTarihPaint.getTextBounds("AKBNK", 0, 2, bounds);
		
		heightText = bounds.height();
		
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
