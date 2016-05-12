package com.bafoly.lib.stockcharts.bir.chart.data;


public abstract class UserInputUserHelperData extends UserInputData {
	
	
	public UserInputUserHelperData(int analysisType, MainData parentMainData) {
		super(Chart.USER_INPUT_TYPE_HELPER, parentMainData);
		setName(parentMainData.getName());
		setAnalysisType(analysisType);
//		setIndicatorID(indicatorType);
	}
	

}
