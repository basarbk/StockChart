package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.utils.FormatTools;
import com.bafoly.lib.stockcharts.bir.chart.utils.Zaman;

import java.util.Calendar;

public class ChartGrid extends ChartDraw {

	public ChartGrid(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}
	
	boolean drawXY = false;
	
	// temel paint objeleri
	Paint cercevePaint;
	int cerceveColor = Color.LTGRAY;
	float cerceveWidth = 1;
	
	Paint chartValuesText;
	int chartValuesTextColor = Color.BLACK;
	float chartValuesTextSize = 16;

	boolean xyPaintsLoaded = false;

	Rect textBounds;
	Rect chartBounds;
	
	/**
	 * X grid line count 
	 */
//	int xSplitCount = 5;
	
	/**
	 * Y grid line count
	 */
	int ySplitCount = 5;

	
	
	
	public boolean isDrawXY() {
		return drawXY;
	}

	public void setDrawXY(boolean drawXY) {
		this.drawXY = drawXY;
	}

	int digitLength=0;
	int monthLength=0;
	
	MainIndicatorData indikatorData;
	
	@Override
	public void preChecks() {
		if(!xyPaintsLoaded){			
			cercevePaint = new Paint();
			cercevePaint.setColor(cerceveColor);
			cercevePaint.setStrokeWidth(cerceveWidth);
			cercevePaint.setStyle(Paint.Style.STROKE);
			
			chartValuesText = new Paint();
			chartValuesText.setAntiAlias(true);
			chartValuesText.setColor(chartValuesTextColor);
			chartValuesText.setTextSize(chartValuesTextSize);
			
			textBounds = new Rect();
			
			xyPaintsLoaded = true;
			
			digitLength = (int) chartValuesText.measureText("01");
			chartValuesText.setTypeface(Typeface.DEFAULT_BOLD);
			monthLength = (int) chartValuesText.measureText("Nov 01");
			chartValuesText.setTypeface(Typeface.DEFAULT);
			if(mainData.getMainType() == Chart.MAIN_TYPE_INDICATOR){
				indikatorData = (MainIndicatorData) mainData;
			}
			
		}

	}
	
	int range = -1;
	float posX, posY;
	float tempYRatio;
	String val;
	
	int xCount = 0;
	
	Calendar lastKnownDate;
	Calendar previousDate = Calendar.getInstance();
	String curDate;
	
	@Override
	public void draw(Canvas canvas) {
		preChecks();
		
		
		canvas.drawLine(chartPosition.getChartGraphArea().left, chartPosition.getChartGraphArea().top, chartPosition.getChartGraphArea().right, chartPosition.getChartGraphArea().top, cercevePaint); // top line
//		canvas.drawLine(leftX, topY, leftX, bottomY, cercevePaint); // left line
//		canvas.drawLine(rightX, topY, rightX, bottomY, cercevePaint); // right line
		canvas.drawLine(chartPosition.getChartGraphArea().left, chartPosition.getChartGraphArea().bottom, chartPosition.getChartGraphArea().right, chartPosition.getChartGraphArea().bottom, cercevePaint); // bottom line
		
		xCount = (int) (chartPosition.getDrawWidht() / (monthLength*3));
				
		range = (chartPosition.getVisibleDataCount()/xCount)-1;
		
		if(range>0){
			for(int i = chartPosition.getVisibleXbegin();i<chartPosition.getVisibleXend();i+=range){				
				if(i<mainData.getDates().size()){
					curDate = mainData.getDates().get(i);
					lastKnownDate = Zaman.getCalendarVersion(curDate);
				} else {
					lastKnownDate.add(Calendar.DATE, range*mainData.getPeriodMultiplier());
				}
				if(lastKnownDate.get(Calendar.MONTH) == previousDate.get(Calendar.MONTH)){
					curDate = Zaman.sdf_day.format(lastKnownDate.getTime()); 
				} else {
					if(lastKnownDate.get(Calendar.YEAR) == previousDate.get(Calendar.YEAR)){
						curDate = Zaman.sdf_month.format(lastKnownDate.getTime());
					} else {
						curDate = Zaman.sdf_year.format(lastKnownDate.getTime());
					}
					
				}
				


				previousDate.setTime(lastKnownDate.getTime());
				
//				posX = ((i-chartView.visibleXbegin)*mainData.getMultiplierX())+chartView.chartGraphArea.left;
				posX = chartPosition.getXChartIDXPosition(i, mainData.getMultiplierX());					
				canvas.drawLine(posX, chartPosition.getChartGraphArea().top, posX, chartPosition.getChartGraphArea().bottom, cercevePaint);
				if(isDrawXY()){
					if(i!=chartPosition.getVisibleXbegin()){
						posX = posX-(chartValuesText.measureText(curDate)/2);
					}
					canvas.drawText(curDate, posX, chartPosition.getChartGraphArea().bottom+15, chartValuesText);
				}
			}
		}
		

		tempYRatio = (chartPosition.getChartGraphArea().bottom-chartPosition.getChartGraphArea().top)/(float) ySplitCount;
		
		for(int i=0;i<=ySplitCount;i++){
			posY = chartPosition.getChartGraphArea().top + (tempYRatio*i);
			canvas.drawLine(chartPosition.getChartGraphArea().left, posY, chartPosition.getChartGraphArea().right, posY, cercevePaint);
			val = FormatTools.formatFloat(chartPosition.getPositionToPrice(posY, mainData));
			chartValuesText.getTextBounds(val, 0, val.length(), textBounds);
			
			if(i==0){				
				canvas.drawText(val, chartPosition.getViewWidth()-(chartPosition.getPaddingRight()*2/3), posY+ textBounds.height()+3, chartValuesText);
			} else {
				canvas.drawText(val, chartPosition.getViewWidth()-(chartPosition.getPaddingRight()*2/3), posY -1, chartValuesText);
			}
		}
		
		if(mainData.getMax()>0 && mainData.getMin()<0){ // MACD gibi max min degerleri poizitif negatif olabilecek grafikler icin..
			posY = chartPosition.getPriceToPoisiton(0, mainData);
			canvas.drawLine(chartPosition.getChartGraphArea().left, posY, chartPosition.getChartGraphArea().right, posY, cercevePaint);
			val = "0";
			chartValuesText.getTextBounds(val, 0, val.length(), textBounds);
			canvas.drawText(val, chartPosition.getViewWidth()-chartPosition.getPaddingRight(), posY + (textBounds.height()/2), chartValuesText);
		}

	}

}
