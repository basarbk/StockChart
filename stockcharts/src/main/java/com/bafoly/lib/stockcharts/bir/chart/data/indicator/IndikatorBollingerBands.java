package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

import java.util.List;

public class IndikatorBollingerBands extends MainIndicatorData {
	
	ChartData up, middle, down;
	List<List<Float>> all;

	public IndikatorBollingerBands(MainInstrumentData mainInstrumentData, int period, int[] color) {
		super("Bollinger", true, mainInstrumentData, period, color);
		setIndicatorType(Chart.INDICATOR_TYPE_BOLLINGER);
	}

	@Override
	public void calculate() {
		if(up==null){
			up = new ChartData();
			down = new ChartData();
			middle = new ChartData();
		}
		all = IndicatorCalculator.getBollingerBands(getParentInstrumentData().getChartData().getKapanis(), getIndicatorPeriod());
		up.setLineData(all.get(0));
		down.setLineData(all.get(1));
		middle.setLineData(all.get(2));
	}

	public ChartData getUp() {
		return up;
	}

	public void setUp(ChartData up) {
		this.up = up;
	}

	public ChartData getMiddle() {
		return middle;
	}

	public void setMiddle(ChartData middle) {
		this.middle = middle;
	}

	public ChartData getDown() {
		return down;
	}

	public void setDown(ChartData down) {
		this.down = down;
	}

	
	

}
