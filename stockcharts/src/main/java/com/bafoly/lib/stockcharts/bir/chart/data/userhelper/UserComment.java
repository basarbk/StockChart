package com.bafoly.lib.stockcharts.bir.chart.data.userhelper;

import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.Coordinate;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.UserInputUserHelperData;

public class UserComment extends UserInputUserHelperData {
	
	String comment;
	
	int expectation = Chart.DIRECTION_NONE;
	
	boolean selected = false;
	
	boolean isTouching = false;
	
	public static final float padding = 20;
	public static final float paddingbottom = 17.32f;
	
	public UserComment(MainData parentMainData) {
		super(Chart.HELPER_TYPE_USER_INPUT, parentMainData);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getExpectation() {
		return expectation;
	}

	public void setExpectation(int expectation) {
		this.expectation = expectation;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public void setEncodeData(String encodedData) {
		String[] vals = encodedData.split(Chart.DELIMITER);
		setComment(vals[0]);
		this.price1= Float.valueOf(vals[1]);
		setExpectation(Integer.valueOf(vals[2]));
		this.dateFirstHalfday=vals[3];
		this.dateFirstDay=vals[4];
		this.dateFirstWeek=vals[5];
		this.dateFirstMonth=vals[6];

	}

	@Override
	public String getEncodedData() {
		return getComment()+Chart.DELIMITER+ String.valueOf(this.price1)+Chart.DELIMITER+ String.valueOf(getExpectation())+Chart.DELIMITER+
				this.dateFirstHalfday+Chart.DELIMITER+
				this.dateFirstDay+Chart.DELIMITER+
				this.dateFirstWeek+Chart.DELIMITER+
				this.dateFirstMonth;
	}

	@Override
	public void updateDataWithRatio(float ratio) {
		this.price1 = this.price1/ratio;
		
	}
	
	@Override
	public void handleFirstTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		this.price1 = chartPosition.getPositionToPrice(y, mainData);
		super.handleFirstTouch(chartPosition, x, y, mainData);
	}
	
	@Override
	public void handleLastTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
		handleFirstTouch(chartPosition, x, y, mainData);
	}
	
	public Rect touchArea;
	
	public boolean isTouching (int x, int y){
		if(touchArea!=null){			
			return touchArea.contains(x, y);
		}
		return false;
	}

	@Override
	public Coordinate[] getPoints(ChartPosition chartPosition, MainData mainData) {
		float idx = 0;
		switch(mainData.getMainChartPeriod()){
		case Chart.DATA_PERIOD_HALF_DAY:
			idx = chartPosition.getDateToPosition(dateFirstHalfday, mainData);
			break;
		case Chart.DATA_PERIOD_DAY:
			idx = chartPosition.getDateToPosition(dateFirstDay, mainData);
			break;
		case Chart.DATA_PERIOD_WEEK:
			idx = chartPosition.getDateToPosition(dateFirstWeek, mainData);
			break;
		case Chart.DATA_PERIOD_MONTH:
			idx = chartPosition.getDateToPosition(dateFirstMonth, mainData);
			break;
		}
		
		if(idx>0){
			touchArea = new Rect();
			Coordinate temp = new Coordinate(); // this is for referance
			temp.x1 = idx;
			temp.x2 = idx;
			temp.y1 = chartPosition.getPriceToPoisiton(price1, mainData);
			temp.y2 = chartPosition.getPriceToPoisiton(price1, mainData);
			
			touchArea.left = (int) (temp.x1-(padding+10));
			touchArea.right = (int) (temp.x2+(padding+10));
			touchArea.top = (int) (temp.y1-(padding+10));
			touchArea.bottom = (int) (temp.y2+(padding+10));
			
			
			if(getExpectation()==Chart.DIRECTION_UP){
				// yukari bakan ucgen cizilecek
				Coordinate c1 = new Coordinate();
				c1.x1 = idx - paddingbottom;
				c1.y1 = temp.y1+(padding/2); // sol alt
				
				Coordinate c2 = new Coordinate();
				c2.x1 = idx + paddingbottom;
				c2.y1 = temp.y1+(padding/2); // sag alt

				Coordinate c3 = new Coordinate();
				c3.x1 = idx;
				c3.y1 = temp.y1-(padding); // tepe
				
				return new Coordinate[]{c1,c2,c3};
				
			} else if(getExpectation()==Chart.DIRECTION_DOWN){
				// asagi bakan ucgen cizilecek
				Coordinate c1 = new Coordinate();
				c1.x1 = idx - paddingbottom;
				c1.y1 = temp.y1-(padding); // sol ust
				
				Coordinate c2 = new Coordinate();
				c2.x1 = idx + paddingbottom;
				c2.y1 = temp.y1-(padding); // sag ust

				Coordinate c3 = new Coordinate();
				c3.x1 = idx;
				c3.y1 = temp.y1+(padding/2); // tepe
				
				return new Coordinate[]{c1,c2,c3};
			} else {
				// diagonal cizilecek
				Coordinate c1 = new Coordinate();
				c1.x1 = idx - padding;
				c1.y1 = temp.y1; // sol kose
				
				Coordinate c2 = new Coordinate();
				c2.x1 = idx;
				c2.y1 = temp.y1-padding; // ust kose
				
				
				Coordinate c3 = new Coordinate();
				c3.x1 = idx + padding;
				c3.y1 = temp.y1; // sag kose
				
				Coordinate c4 = new Coordinate();
				c4.x1 = idx;
				c4.y1 = temp.y1+padding; // alt kose
				
				touchArea.left = (int) (temp.x1-(padding+10));
				touchArea.right = (int) (temp.x2+(padding+10));
				touchArea.top = (int) (temp.y1-(padding+10));
				touchArea.bottom = (int) (temp.y2+(padding+10));
				
				return new Coordinate[]{c1,c2,c3,c4};
			}
			
		}
		
		return null;
	}

	public String getDate(){
		return dateFirstDay;
	}
	
	public String getExpectationString(){
		return Chart.directions[getExpectation()];
	}

	@Override
	public boolean isTouchingToEditPoints(int x, int y) {
		return false;
	}

	@Override
	public void handleEditTouch(ChartPosition chartPosition, float x, float y, MainData mainData) {
	}

	@Override
	public Rect[] getTouchingAreas() {
		return null;
	}
	

}
