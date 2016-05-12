package com.bafoly.lib.stockcharts.bir.chart.data;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.utils.Zaman;

public abstract class UserInputData {
	
	/**
	 * This will be stored in database and it will have unique ID so that we can edit or remove this any time
	 */
	long id = -1L;
	
	/**
	 * We may need a name
	 */
	String name;
	
	/**
	 * input that will be processed
	 */
	int userInputType;
	
	/**
	 * This input either connected to instrument or indicator.
	 */
	int indicatorID = Chart.INDICATOR_TYPE_NONE;
	
	/**
	 * Positioning is dependent on to this mainData
	 */
	protected MainData parentMainData;
	
	/**
	 * This can be a Technical Analysis input.<br>
	 * By default the analysis type is set to <b>Chart.ANALYSIS_TYPE_NONE</b>
	 */
	int analysisType = Chart.ANALYSIS_TYPE_NONE;
	
	/**
	 * This can be a User Helper Input.<br>
	 * By default the input type is set to <b>Chart.HELPER_TYPE_NONE</b>
	 */
	int helperType = Chart.HELPER_TYPE_NONE;

	/**
	 * Since each analysis may have multiple variables, we'll be storing that variables after encoding them based on the input type
	 */
	String encodedData="";
	
	/**
	 * in runtime, we might edit this input
	 */
	boolean inEditMode = false;
	
	//TODO doc will be added
	protected float price1, price2;
	
	public String dateFirstHalfday, dateLastHalfday;
	public String dateFirstDay, dateLastDay;
	public String dateFirstWeek, dateLastWeek;
	public String dateFirstMonth, dateLastMonth;
	
	/**
	 * red green blue
	 */
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
	/**
	 * stores the coordinates of the points
	 */
	Point[] points;

	
	protected int touchWidth = 15;
	
	boolean ownedByMe = true;
	
	public UserInputData(int userInputType, MainData parentMainData) {
		this.userInputType = userInputType;
		this.parentMainData = parentMainData;
		this.indicatorID = parentMainData.getIndicatorType();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserInputType() {
		return userInputType;
	}

	public void setUserInputType(int userInputType) {
		this.userInputType = userInputType;
	}

	public int getIndicatorID() {
		return indicatorID;
	}

	public void setIndicatorID(int indicatorID) {
		this.indicatorID = indicatorID;
	}

	public int getAnalysisType() {
		return analysisType;
	}

	public void setAnalysisType(int analysisType) {
		this.analysisType = analysisType;
	}

	public int getHelperType() {
		return helperType;
	}

	public void setHelperType(int helperType) {
		this.helperType = helperType;
	}

	public boolean isInEditMode() {
		return inEditMode;
	}

	public void setInEditMode(boolean inEditMode) {
		this.inEditMode = inEditMode;
	}
	

	public void setColorFromDB(String colors){
		String[] s = colors.split(Chart.DELIMITER);
		setColor(Integer.valueOf(s[0]), Integer.valueOf(s[1]), Integer.valueOf(s[2]));
	}

	public void setColor(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getColor(){
		return Color.rgb(red, green, blue);
	}
	
	public String getColorForDB(){
		return String.valueOf(this.red)+Chart.DELIMITER+ String.valueOf(this.green)+Chart.DELIMITER+ String.valueOf(this.blue);
	}
	
	public boolean isTechnicalAnalysis(){
		if(userInputType==Chart.USER_INPUT_TYPE_TECHNICAL_ANALYSIS){
			return true;
		}
		return false;
	}
	
	public boolean isUserHelper(){
		if(userInputType == Chart.USER_INPUT_TYPE_HELPER){
			return true;
		}
		return false;
	}
	
	

	public boolean isOwnedByMe() {
		return ownedByMe;
	}

	public void setOwnedByMe(boolean ownedByMe) {
		this.ownedByMe = ownedByMe;
	}

	public abstract void setEncodeData(String encodedData);
	
	public abstract String getEncodedData();
	
	public abstract void updateDataWithRatio(float ratio);
	
	public abstract Coordinate[] getPoints(ChartPosition chartPosition, MainData mainData);	
	
	public abstract boolean isTouchingToEditPoints(int x, int y);
	
	public abstract void handleEditTouch(ChartPosition chartPosition, float x, float y, MainData mainData);
	
	public abstract Rect[] getTouchingAreas();
	
	public void handleFirstTouch(ChartPosition chartPosition, float x, float y, MainData mainData){
		int currentIDXX = chartPosition.getTouchToDayIndex(x, mainData.getMultiplierX());
		this.dateFirstHalfday = Zaman.getDate(chartPosition, currentIDXX, mainData);
		this.dateFirstDay = this.dateFirstHalfday.substring(0, this.dateFirstHalfday.length()-1);
		this.dateFirstWeek = Zaman.getWeekPeriodFormatOfDate(this.dateFirstHalfday);
		this.dateFirstMonth = Zaman.getMonthPeriodFormatOfDate(this.dateFirstHalfday);
		
		if(this.dateLastDay==null || this.dateLastDay.equalsIgnoreCase("")){			
			this.dateLastHalfday = this.dateFirstHalfday;
			this.dateLastDay = this.dateFirstDay;
			this.dateLastWeek = this.dateFirstWeek;
			this.dateLastMonth = this.dateFirstMonth;
		}
	}
	
	
	public void handleLastTouch(ChartPosition chartPosition, float x, float y, MainData mainData){
		int currentIDXX = chartPosition.getTouchToDayIndex(x, mainData.getMultiplierX());		
		this.dateLastHalfday = Zaman.getDate(chartPosition, currentIDXX, mainData);
		this.dateLastDay = this.dateLastHalfday.substring(0, this.dateLastHalfday.length()-1);
		this.dateLastWeek = Zaman.getWeekPeriodFormatOfDate(this.dateLastHalfday);
		this.dateLastMonth = Zaman.getMonthPeriodFormatOfDate(this.dateLastHalfday);
	}
	
	
}
