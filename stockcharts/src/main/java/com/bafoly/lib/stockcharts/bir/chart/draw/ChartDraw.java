package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;


public abstract class ChartDraw {
	
	ChartPosition chartPosition;
	MainData mainData;
	
	float chartIdxPositionX;
	float chartValuePositionY;
	
	boolean useMyPositioningMethods = false;
	
	public int myPaddingLeft = 0;
	public int myPaddingRight = 0;
	public int myPaddingTop = 0;
	public int myPaddingBottom = 0;

	
	public ChartDraw(ChartPosition chartPosition, MainData mainData){
		this.chartPosition = chartPosition;
		this.mainData = mainData;
	}

	public MainData getMainData() {
		return mainData;
	}

	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}
	
	public void setMyPaddigns(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom){
		this.myPaddingLeft = paddingLeft;
		this.myPaddingRight = paddingRight;
		this.myPaddingTop = paddingTop;
		this.myPaddingBottom = paddingBottom;
		useMyPositioningMethods = true;
	}
	
	public abstract void preChecks();
	
	public abstract void draw(Canvas canvas);

}
