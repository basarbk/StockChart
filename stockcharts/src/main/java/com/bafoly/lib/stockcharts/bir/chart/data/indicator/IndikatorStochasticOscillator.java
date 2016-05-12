package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

public class IndikatorStochasticOscillator extends MainIndicatorData {

	int[] colorD;
	
	public IndikatorStochasticOscillator(MainInstrumentData mainInstrumentData, int periyot, int[] colorK, int[] colorD) {
		super("Stochastic Oscillator", false, mainInstrumentData, periyot, colorK);
		setIndicatorType(Chart.INDICATOR_TYPE_STOCHASTIC_OSCILLATOR);
		setThresholds(new Float[]{20f,80f});
		setUseMyMaxMin(true);
		setMax(100f);
		setMin(0f);
		this.colorD=colorD; 
	}


	MainIndicatorData sma;
	@Override
	public void calculate() {

		chartData = new ChartData();
		chartData.setLineData(IndicatorCalculator.getStochasticK(getParentInstrumentData().getChartData().getKapanis(), 
				getParentInstrumentData().getChartData().getYuksek(), getParentInstrumentData().getChartData().getDusuk(), getIndicatorPeriod()));
		
		if(sma==null){
			sma = new IndikatorMA(getParentInstrumentData(), 3, this.colorD);
			sma.setOverlay(true);
			sma.setName("%D");
		}
		
		sma.setChartData(new ChartData());
		sma.getChartData().setLineData(IndicatorCalculator.getSMA(chartData.getLineData(), 3));
		
		setIndicators(null);
		setOverlayCounter(0);
		addIndicator(sma);
	}




}
