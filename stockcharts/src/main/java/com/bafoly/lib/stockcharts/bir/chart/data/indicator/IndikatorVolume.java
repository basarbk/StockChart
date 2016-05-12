package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorVolume extends MainIndicatorData {

	public IndikatorVolume(MainInstrumentData mainInstrumentData) {
		super("Volume", true, mainInstrumentData, -1, null);
		setGraphType(Chart.GRAPH_TYPE_BAR);
		setIndicatorType(Chart.INDICATOR_TYPE_VOLUME);
	}

	@Override
	public void calculate() {
		if(chartData==null){			
			chartData = new ChartData();
		}
		chartData.setLineData(getParentInstrumentData().getChartData().getIslem());

	}

}
