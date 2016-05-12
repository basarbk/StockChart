package com.bafoly.lib.stockcharts.bir.chart.data.indicator;

import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorMA extends MainIndicatorData {
	
	public IndikatorMA(MainInstrumentData mainInstrumentData, int periyot, int[] color) {
		super("MA", true, mainInstrumentData, periyot, color);
		setIndicatorType(Chart.INDICATOR_TYPE_MOVING_AVERAGE);
	}


	@Override
	public void calculate() {
		if(chartData==null){				
			chartData = new ChartData();
//			carpan = (float)2.0 / (float)(periyot+1);
		}
		chartData.setLineData(IndicatorCalculator.getEMA(getParentInstrumentData().getChartData().getKapanis(), getIndicatorPeriod()));


	}



	
	
	

}
