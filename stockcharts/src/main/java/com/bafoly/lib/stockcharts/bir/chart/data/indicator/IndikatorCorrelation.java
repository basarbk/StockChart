package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorCorrelation extends MainIndicatorData {

	ChartData refData2;
	public IndikatorCorrelation(MainInstrumentData mainInstrumentData, ChartData refData2, int period, int[] color) {
		super("Correlation", false, mainInstrumentData, period, color);
		setIndicatorType(Chart.INDICATOR_TYPE_CORRELATION);
		setUseMyMaxMin(true);
		setMax(1f);
		setMin(-1f);
		this.refData2 = refData2;
	}

	@Override
	public void calculate() {
		if(chartData==null){
			chartData = new ChartData();
		}
		chartData.setLineData(IndicatorCalculator.getCorrelation(getParentInstrumentData().getChartData().getKapanis(),
				refData2.getKapanis(), getIndicatorPeriod()));


	}

}
