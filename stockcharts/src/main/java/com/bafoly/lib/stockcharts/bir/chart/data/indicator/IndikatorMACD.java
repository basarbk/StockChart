package com.bafoly.lib.stockcharts.bir.chart.data.indicator;


import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.ChartData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainInstrumentData;

import java.util.ArrayList;
import java.util.List;

public class IndikatorMACD extends MainIndicatorData {
	
	int[] macdLineColor = {0,0,255};
	int[] signalLineColor = {255,0,0};

	public IndikatorMACD(MainInstrumentData mainInstrumentData, int[] color) {
		super("MACD", false, mainInstrumentData, 0, color);
		setIndicatorType(Chart.INDICATOR_TYPE_MACD);
		setGraphType(Chart.GRAPH_TYPE_BAR);
		setUseMyMaxMin(true);
		setMax(0.2f);
		setMin(-0.2f);
		
	}
	
	List<Float> dummyList;

	MainIndicatorData macdLine, signalLine;
	
	float max = 0;
	float min = 0;
	@Override
	public void calculate() {
		macdLine = new IndikatorMA(getParentInstrumentData(), 12, macdLineColor);
		macdLine.setName("MACD Line");
		macdLine.setChartData(new ChartData());

		signalLine = new IndikatorMA(getParentInstrumentData(), 9, signalLineColor);
		signalLine.setName("Signal Line");
		signalLine.setChartData(new ChartData());

		macdLine.getChartData().setLineData(IndicatorCalculator.getMACDLine(getParentInstrumentData().getChartData().getKapanis()));
		signalLine.getChartData().setLineData(IndicatorCalculator.getEMA(macdLine.getChartData().getLineData(), signalLine.getIndicatorPeriod()));
		
		
		dummyList = new ArrayList<Float>();
		for(int i=0;i<macdLine.getChartData().getLineData().size();i++){
			dummyList.add(macdLine.getChartData().getLineData().get(i)-signalLine.getChartData().getLineData().get(i));
		}
		
		
		if(chartData==null){
			chartData = new ChartData();
		}
//		chartModel.setLineData(IndicatorCalculator.getMACD(referenceData.getKapanis()));
		chartData.setLineData(dummyList);

		setIndicators(null);
		setOverlayCounter(0);

		addIndicator(macdLine);
		addIndicator(signalLine);
		

	}
	
	public void calculateMaxMin(ChartPosition chartPosition){
		max = 0;
		min = 0;
		for(int i=chartPosition.getVisibleXbegin();i<chartPosition.getVisibleXend();i++){
			if(i>=0 && i<macdLine.getChartData().getLineData().size()){
				if(macdLine.getChartData().getLineData().get(i)>max){
					max = macdLine.getChartData().getLineData().get(i);
				}else if(signalLine.getChartData().getLineData().get(i)>max){
					max = signalLine.getChartData().getLineData().get(i);
				}else if(chartData.getLineData().get(i)>max){
					max = chartData.getLineData().get(i);
				}
				
				if(macdLine.getChartData().getLineData().get(i)<min){
					min = macdLine.getChartData().getLineData().get(i);
				} else if(signalLine.getChartData().getLineData().get(i)<min){
					min = signalLine.getChartData().getLineData().get(i);
				}else if(chartData.getLineData().get(i)<min){
					min = chartData.getLineData().get(i);
				}
				
			}
		}
		
		setMin(min);
		setMax(max);
		
		macdLine.setMax(getMax());
		macdLine.setMin(getMin());
		macdLine.setUseMyMaxMin(true);
		signalLine.setMax(getMax());
		signalLine.setMin(getMin());
		signalLine.setUseMyMaxMin(true);
	}



}
