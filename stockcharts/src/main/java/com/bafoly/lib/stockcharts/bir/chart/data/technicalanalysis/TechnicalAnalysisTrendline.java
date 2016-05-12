package com.bafoly.lib.stockcharts.bir.chart.data.technicalanalysis;

import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.Coordinate;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.UserInputTechnicalAnalysisData;

public class TechnicalAnalysisTrendline extends UserInputTechnicalAnalysisData {
		
	float egim;
	
	public TechnicalAnalysisTrendline(MainData parentMainData) {
		super(Chart.ANALYSIS_TYPE_TRENDLINE, parentMainData);
	}

	
	@Override
	public void setEncodeData(String encodedData) {
		String[] vals = encodedData.split(Chart.DELIMITER);
		this.price1= Float.valueOf(vals[0]);
		this.price2= Float.valueOf(vals[1]);
		this.dateFirstHalfday=vals[2];
		this.dateLastHalfday=vals[3];
		this.dateFirstDay=vals[4];
		this.dateLastDay=vals[5];
		this.dateFirstWeek=vals[6];
		this.dateLastWeek=vals[7];
		this.dateFirstMonth=vals[8];
		this.dateLastMonth=vals[9];
		
	}


	@Override
	public String getEncodedData() {
		return String.valueOf(this.price1)+Chart.DELIMITER+ String.valueOf(this.price2)+Chart.DELIMITER+
				this.dateFirstHalfday+Chart.DELIMITER+this.dateLastHalfday+Chart.DELIMITER+
				this.dateFirstDay+Chart.DELIMITER+this.dateLastDay+Chart.DELIMITER+
				this.dateFirstWeek+Chart.DELIMITER+this.dateLastWeek+Chart.DELIMITER+
				this.dateFirstMonth+Chart.DELIMITER+this.dateLastMonth;
	}


	@Override
	public void updateDataWithRatio(float ratio) {
		this.price1 = this.price1/ratio;
		this.price2 = this.price2/ratio;		
	}
	
	
	@Override
	public void handleFirstTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.price1 = chartPosition.getPositionToPrice(y, mainData);
		super.handleFirstTouch(chartPosition, x, y, mainData);
	}

	@Override
	public void handleLastTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.price2 = chartPosition.getPositionToPrice(y, mainData);
		super.handleLastTouch(chartPosition, x, y, mainData);
	}

	
	Rect firstTouchArea;
	boolean touchingToFirst = false;
	Rect lastTouchArea;
	boolean touchingToLast = false;


	@Override
	public Coordinate[] getPoints(ChartPosition chartPosition, MainData mainData) {
		float idxBegin = 0;
		float idxEnd = 0;
		
		switch(mainData.getMainChartPeriod()){
		case Chart.DATA_PERIOD_HALF_DAY:
			idxBegin = chartPosition.getDateToPosition(dateFirstHalfday, mainData);
			idxEnd = chartPosition.getDateToPosition(dateLastHalfday, mainData);
			break;
		case Chart.DATA_PERIOD_DAY:
			idxBegin = chartPosition.getDateToPosition(dateFirstDay, mainData);
			idxEnd = chartPosition.getDateToPosition(dateLastDay, mainData);
			break;
		case Chart.DATA_PERIOD_WEEK:
			idxBegin = chartPosition.getDateToPosition(dateFirstWeek, mainData);
			idxEnd = chartPosition.getDateToPosition(dateLastWeek, mainData);
			break;
		case Chart.DATA_PERIOD_MONTH:
			idxBegin = chartPosition.getDateToPosition(dateFirstMonth, mainData);
			idxEnd = chartPosition.getDateToPosition(dateLastMonth, mainData);
			break;
		}
		
		
		Coordinate c = new Coordinate();
		c.x1 = idxBegin;
		c.x2 = idxEnd;
		c.y1 = chartPosition.getPriceToPoisiton(price1, mainData);
		c.y2 = chartPosition.getPriceToPoisiton(price2, mainData);

		firstTouchArea = new Rect((int)idxBegin-touchWidth, (int)c.y1-touchWidth, (int)idxBegin+touchWidth, (int)c.y1+touchWidth);
		lastTouchArea = new Rect((int)idxEnd-touchWidth, (int)c.y2-touchWidth, (int)idxEnd+touchWidth, (int)c.y2+touchWidth);
		
		return new Coordinate[]{c};
	}



	@Override
	public boolean isTouchingToEditPoints(int x, int y) {
		if(firstTouchArea!=null && (firstTouchArea.contains(x, y))){
			touchingToFirst = true;
			touchingToLast = false;
			return true;
		} else if (lastTouchArea!=null && (lastTouchArea.contains(x, y))) {
			touchingToFirst = false;
			touchingToLast = true;
			return true;
		}
		touchingToFirst = false;
		touchingToLast = false;
		return false;
	}


	@Override
	public void handleEditTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		if(touchingToFirst){
			this.price1 = chartPosition.getPositionToPrice(y, mainData);
			super.handleFirstTouch(chartPosition, x, y, mainData);
		} else if(touchingToLast){
			this.price2 = chartPosition.getPositionToPrice(y, mainData);
			super.handleLastTouch(chartPosition, x, y, mainData);
		}	
		
	}


	@Override
	public Rect[] getTouchingAreas() {
		return new Rect[]{firstTouchArea,lastTouchArea};
	}
	
}
