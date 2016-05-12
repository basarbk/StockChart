package com.bafoly.lib.stockcharts.bir.chart.data;

import com.bafoly.lib.stockcharts.bir.chart.utils.FormatTools;

/**
 * Created by basarb on 10/4/2015.
 */
public abstract class MainIndicatorData extends MainData{

    /**
     * Each indicator has it's own calculation period
     */
    int indicatorPeriod;

    /**
     * Some indicators have threshold values<br>
     * e.g. RSI has threshold like 30 and 70
     */
    boolean hasThresholdValues = false;

    /**
     * Threshold values are stored here
     */
    Float[] thresholds;


    public MainIndicatorData(String name, boolean overlay, MainInstrumentData parentMainData, int period, int[] color){
        super(Chart.MAIN_TYPE_INDICATOR);
        setGraphType(Chart.GRAPH_TYPE_LINE);
        setName(name);
        setOverlay(overlay);
        this.indicatorPeriod = period;
        setColor(color[0], color[1], color[2]);
        setParentMainData(parentMainData);

    }

    public abstract void calculate();

    public int getIndicatorPeriod() {
        return indicatorPeriod;
    }

    public void setIndicatorPeriod(int indicatorPeriod) {
        this.indicatorPeriod = indicatorPeriod;
    }

    public boolean isHasThresholdValues() {
        return hasThresholdValues;
    }

    public Float[] getThresholds() {
        return thresholds;
    }

    public void setThresholds(Float[] thresholds) {
        this.thresholds = thresholds;
        this.hasThresholdValues = true;
    }


    public ChartLegend getRootDataLegend(int idx){
        if(idx>getChartData().getLineData().size()-1){
            idx = getChartData().getLineData().size()-1;
        }
        ChartLegend chartLegend = new ChartLegend(getName());
        chartLegend.addElement("("+getIndicatorPeriod()+")", FormatTools.formatFloat(getChartData().getLineData().get(idx)));
        return chartLegend;
    }

    public ChartLegend[] getChartLegend(int idx){
        ChartLegend[] content = new ChartLegend[getOverlayCounter()+1];
        content[0] = getRootDataLegend(idx);
        int counteridx = 0;
        if(getIndicators()!=null){
            for(int i = 0;i<getIndicators().size();i++){
                if(getIndicators().get(i).isOverlay()){
                    counteridx++;
                    content[counteridx] = getIndicators().get(i).getRootDataLegend(idx);
                }
            }
        }
        return content;
    }


}
