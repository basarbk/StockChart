package com.bafoly.lib.stockcharts.bir.chart.data;


public abstract class UserInputTechnicalAnalysisData extends UserInputData {
	

	public UserInputTechnicalAnalysisData(int analysisType, MainData parentMainData) {
		super(Chart.USER_INPUT_TYPE_TECHNICAL_ANALYSIS, parentMainData);
		setName(parentMainData.getName());
		setAnalysisType(analysisType);
//		setIndicatorID(indicatorType);
	}


}
