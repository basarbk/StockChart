package com.bafoly.lib.stockcharts.bir.chart.data.indicator;

import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorMFI extends MainIndicatorData {
	
	float lastPositiveFlow, lastNegativeFlow, lastTP;

	public IndikatorMFI(MainInstrumentData mainInstrumentData, int periyot, int[] color) {
		super("MFI", false, mainInstrumentData, periyot, color);
		setIndicatorType(Chart.INDICATOR_TYPE_MFI);
		setThresholds(new Float[]{20f,80f});
		setUseMyMaxMin(true);
		setMax(100f);
		setMin(0f);
	}


	@Override
	public void calculate() {
		if(chartData==null){
			chartData = new ChartData();
		}
		chartData.setLineData(IndicatorCalculator.getMFI(getParentInstrumentData().getChartData().getKapanis(), 
				getParentInstrumentData().getChartData().getYuksek(), getParentInstrumentData().getChartData().getDusuk(), 
				getParentInstrumentData().getChartData().getIslem(), getIndicatorPeriod()));;
		

	}

	public float getLastPositiveFlow() {
		return lastPositiveFlow;
	}

	public void setLastPositiveFlow(float lastPositiveFlow) {
		this.lastPositiveFlow = lastPositiveFlow;
	}

	public float getLastNegativeFlow() {
		return lastNegativeFlow;
	}

	public void setLastNegativeFlow(float lastNegativeFlow) {
		this.lastNegativeFlow = lastNegativeFlow;
	}

	public float getLastTP() {
		return lastTP;
	}

	public void setLastTP(float lastTP) {
		this.lastTP = lastTP;
	}


	
	

}
