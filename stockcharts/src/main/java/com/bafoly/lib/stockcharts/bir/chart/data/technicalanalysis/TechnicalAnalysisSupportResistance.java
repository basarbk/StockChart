package com.bafoly.lib.stockcharts.bir.chart.data.technicalanalysis;

import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.Coordinate;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.UserInputTechnicalAnalysisData;


public class TechnicalAnalysisSupportResistance extends UserInputTechnicalAnalysisData {

	public TechnicalAnalysisSupportResistance(MainData parentMainData) {
		super(Chart.ANALYSIS_TYPE_SUPPORT_RESISTANCE,parentMainData);
	}

	@Override
	public void updateDataWithRatio(float ratio) {
		this.price1 = this.price1/ratio;
	}
	
	@Override
	public void setEncodeData(String encodedData) {
		this.price1 = Float.valueOf(encodedData);
		
	}

	@Override
	public String getEncodedData() {
		return String.valueOf(price1);
	}

	@Override
	public void handleFirstTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.price1 = chartPosition.getPositionToPrice(y, mainData);
	}
	
	@Override
	public void handleLastTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.handleFirstTouch(chartPosition, x, y, mainData);
	}

	@Override
	public Coordinate[] getPoints(ChartPosition chartPosition, MainData mainData) {
		Coordinate c = new Coordinate();
		c.x1 = chartPosition.getPaddingLeft();
		c.x2 = chartPosition.getPaddingLeft()+chartPosition.getDrawWidht();
		c.y1 = chartPosition.getPriceToPoisiton(price1, mainData);
		c.y2 = c.y1;
		
		return new Coordinate[]{c};
	}

	@Override
	public boolean isTouchingToEditPoints(int x, int y) {
		return true;
	}

	@Override
	public void handleEditTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.handleFirstTouch(chartPosition, x, y, mainData);
	}

	@Override
	public Rect[] getTouchingAreas() {
		return null;
	}


}
