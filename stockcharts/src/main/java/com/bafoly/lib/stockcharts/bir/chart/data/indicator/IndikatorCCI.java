package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorCCI extends MainIndicatorData {

	public IndikatorCCI(MainInstrumentData mainInstrumentData, int period, int[] color) {
		super("CCI", false, mainInstrumentData, period, color);
		setIndicatorType(Chart.INDICATOR_TYPE_CCI);
		setThresholds(new Float[]{-200f,200f});
		setUseMyMaxMin(true);
		setMax(300f);
		setMin(-300f);
	}

	@Override
	public void calculate() {
		if(chartData==null){
			chartData = new ChartData();
		}
		
		chartData.setLineData(IndicatorCalculator.getCCI(getParentInstrumentData().getChartData().getKapanis(), 
				getParentInstrumentData().getChartData().getYuksek(), getParentInstrumentData().getChartData().getDusuk(), getIndicatorPeriod()));
	}


}
