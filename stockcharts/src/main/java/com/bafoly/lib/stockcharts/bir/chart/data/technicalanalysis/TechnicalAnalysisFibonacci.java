package com.bafoly.lib.stockcharts.bir.chart.data.technicalanalysis;

import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.Coordinate;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.UserInputTechnicalAnalysisData;


public class TechnicalAnalysisFibonacci extends UserInputTechnicalAnalysisData {
	
	public static final int FIBONACCI_ALL = 0;
	public static final int FIBONACCI_LINES = 1;
	public static final int FIBONACCI_FAN = 2;
	public static final int FIBONACCI_CIRCLES = 3;
	
	public float fibo23, fibo38, fibo50, fibo61;

	public TechnicalAnalysisFibonacci(MainData parentMainData) {
		super(Chart.ANALYSIS_TYPE_FIBONACCI, parentMainData);
	}

	
	private void setData(float bottom, float top){
		float distance=top-bottom;
		fibo61 = top - (float) (distance*0.61);
		fibo50 = top - (float) (distance*0.50);
		fibo38 = top - (float) (distance*0.38);
		fibo23 = top - (float) (distance*0.23);
	}


	@Override
	public void setEncodeData(String encodedData) {
		String[] vals = encodedData.split(Chart.DELIMITER);
		this.price1= Float.valueOf(vals[0]);
		this.price2= Float.valueOf(vals[1]);
		setData(this.price1, this.price2);
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
		fibo61 = fibo61/ratio;
		fibo50 = fibo50/ratio;
		fibo38 = fibo38/ratio;
		fibo23 = fibo23/ratio;
		
	}
	
	@Override
	public void handleFirstTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.price1 = chartPosition.getPositionToPrice(y, mainData);
		setData(this.price1, this.price1);
		super.handleFirstTouch(chartPosition, x, y, mainData);
	}

	@Override
	public void handleLastTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.price2 = chartPosition.getPositionToPrice(y, mainData);
		setData(this.price1,this.price2);
		super.handleLastTouch(chartPosition, x, y, mainData);
	}
	

	Rect firstTouchArea;
	boolean touchingToFirst = false;
	Rect lastTouchArea;
	boolean touchingToLast = false;
	

	public Rect[] getTouchingAreas(){
		return new Rect[]{firstTouchArea,lastTouchArea};
	}
	
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
		
		
		Coordinate c1 = new Coordinate();//baseline
		Coordinate c2 = new Coordinate();//fibo61
		Coordinate c3 = new Coordinate();//fibo50
		Coordinate c4 = new Coordinate();//fibo38
		Coordinate c5 = new Coordinate();//fibo23
		Coordinate c6 = new Coordinate();//top line
		
		c1.x1 = idxBegin;
		c1.x2 = idxEnd;
		c1.y1 = chartPosition.getPriceToPoisiton(price1, mainData);
		c1.y2 = c1.y1;
		
		firstTouchArea = new Rect((int)idxBegin-touchWidth, (int)c1.y1-touchWidth, (int)idxBegin+touchWidth, (int)c1.y1+touchWidth);
		
		c2.x1 = idxBegin;
		c2.x2 = idxEnd;
		c2.y1 = chartPosition.getPriceToPoisiton(fibo61, mainData);
		c2.y2 = c2.y1;
		
		c3.x1 = idxBegin;
		c3.x2 = idxEnd;
		c3.y1 = chartPosition.getPriceToPoisiton(fibo50, mainData);
		c3.y2 = c3.y1;
		
		c4.x1 = idxBegin;
		c4.x2 = idxEnd;
		c4.y1 = chartPosition.getPriceToPoisiton(fibo38, mainData);
		c4.y2 = c4.y1;
		
		c5.x1 = idxBegin;
		c5.x2 = idxEnd;
		c5.y1 = chartPosition.getPriceToPoisiton(fibo23, mainData);
		c5.y2 = c5.y1;
		
		c6.x1 = idxBegin;
		c6.x2 = idxEnd;
		c6.y1 = chartPosition.getPriceToPoisiton(price2, mainData);
		c6.y2 = c6.y1;
		
		lastTouchArea = new Rect((int)idxEnd-touchWidth, (int)c6.y1-touchWidth, (int)idxEnd+touchWidth, (int)c6.y1+touchWidth);

		return new Coordinate[]{c1,c2,c3,c4,c5,c6};
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
			setData(this.price1, this.price2);
			super.handleFirstTouch(chartPosition, x, y, mainData);
		} else if(touchingToLast){
			this.price2 = chartPosition.getPositionToPrice(y, mainData);
			setData(this.price1, this.price2);
			super.handleLastTouch(chartPosition, x, y, mainData);
		}	
	}
}
