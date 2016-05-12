package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorRSI extends MainIndicatorData {

	public IndikatorRSI(MainInstrumentData mainInstrumentData, int periyot, int[] color) {
		super("RSI", false,mainInstrumentData,periyot, color);
		setIndicatorType(Chart.INDICATOR_TYPE_RSI);
		setThresholds(new Float[]{30f,70f});
		setUseMyMaxMin(true);
		setMax(100f);
		setMin(0f);
	}

	@Override
	public void calculate() {
		chartData = new ChartData();
		chartData.setLineData(IndicatorCalculator.getRSI(getParentInstrumentData().getChartData().getKapanis(), getIndicatorPeriod()));
	}


}
