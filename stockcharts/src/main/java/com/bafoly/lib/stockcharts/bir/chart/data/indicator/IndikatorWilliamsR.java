package com.bafoly.lib.stockcharts.bir.chart.data.indicator;

import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorWilliamsR extends MainIndicatorData {

	float lastMax, lastMin, curMax, curMin;
	
	public IndikatorWilliamsR(MainInstrumentData mainInstrumentData, int periyot, int[] color) {
		super("WilliamsR", false, mainInstrumentData, periyot, color);
		setIndicatorType(Chart.INDICATOR_TYPE_WILLIAMSR);
		setThresholds(new Float[]{-80f,-20f});
		setUseMyMaxMin(true);
		setMax(0f);
		setMin(-100f);
	}

	
	@Override
	public void calculate() {
		chartData = new ChartData();
		chartData.setLineData(IndicatorCalculator.calculateWilliamsR(this, getIndicatorPeriod()));
		
	}

	public float getLastMax() {
		return lastMax;
	}

	public void setLastMax(float lastMax) {
		this.lastMax = lastMax;
	}

	public float getLastMin() {
		return lastMin;
	}

	public void setLastMin(float lastMin) {
		this.lastMin = lastMin;
	}


	
	
	
	

}
