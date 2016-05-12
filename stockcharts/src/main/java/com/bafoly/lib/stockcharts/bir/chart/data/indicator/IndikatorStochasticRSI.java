package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorStochasticRSI extends MainIndicatorData {

	public IndikatorStochasticRSI(MainInstrumentData mainInstrumentData, int periyot, int[] color) {
		super("StochRSI", false, mainInstrumentData, periyot, color);
		setIndicatorType(Chart.INDICATOR_TYPE_STOCHASTIC_RSI);
		setThresholds(new Float[]{0.2f,0.8f});
		setUseMyMaxMin(true);
		setMax(1f);
		setMin(0f);
	}

	@Override
	public void calculate() {
		if(chartData==null){
			chartData = new ChartData();
		}
		chartData.setLineData(IndicatorCalculator.getStochasticRSI(getParentInstrumentData().getChartData().getKapanis(), getIndicatorPeriod()));

	}



}
