package com.bafoly.lib.stockcharts.bir.chart.data;

import com.bafoly.lib.stockcharts.bir.chart.utils.FormatTools;
import com.bafoly.lib.stockcharts.bir.chart.utils.Zaman;

/**
 * Created by basarb on 10/4/2015.
 */
public class MainInstrumentData extends MainData {

    /**
     * Each instrument has data period<br>
     * This can be HALF_DAY, DAY, WEEK or MONTH. by Default it's HALF_DAY
     */
    int period = Chart.DATA_PERIOD_HALF_DAY;

    /**
     * We have instrument types as
     * INSTRUMENT_TYPE_<b>STOCK</b>, INSTRUMENT_TYPE_<b>INDEX</b>, INSTRUMENT_TYPE_<b>FOREX</b>, INSTRUMENT_TYPE_<b>COMMODITIES</b>
     */
    int instrumentType = Chart.INSTRUMENT_TYPE_STOCK;


    //TODO dili duzelt
    ChartData seanslik;
    ChartData gunluk;
    ChartData haftalik;
    ChartData aylik;

    /**
     * this is to track the data count changes. at initial load, this will be set then in every change, dependent data will be recalculated
     */
    int refDataCount = 0;

//	TODO buradaki dili duzelt
    /**
     *
     * @param name Enstruman adi.. "AKBNK", "USD" etc
     * @param graphType Grafigin gorsel tipi <br>
     * <b>Chart.GRAPH_TYPE_CANDLE</b> CandleChart<br>
     * <b>Chart.GRAPH_TYPE_OHLC</b> Open High Low Close cizgileri<br>
     * <b>Chart.GRAPH_TYPE_LINE</b> Single line tipi grafik (EMA, RSI vs turu seyler icin)<br>
     * <b>Chart.GRAPH_TYPE_BAR</b> MACD gibi cizimlerde kullanilan bar chart<br>
     * @param instrumentType main data hangi tipte<br>
     * <b>Chart.TYPE_ENSTRUMAN_HISSE</b><br>
     * <b>Chart.TYPE_ENSTRUMAN_ENDEKS</b><br>
     */
    public MainInstrumentData(String name, int graphType, int instrumentType) {
        super(Chart.MAIN_TYPE_INSTRUMENT);
        setGraphType(graphType);
        setName(name);
        setInstrumentType(instrumentType);
    }

    public int getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(int instrumentType) {
        this.instrumentType = instrumentType;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public void setChartData(ChartData chartData) {
        switch(period){
            case Chart.DATA_PERIOD_HALF_DAY:
                setSeanslik(chartData);
                break;
            case Chart.DATA_PERIOD_DAY:
                setGunluk(chartData);
                break;
            case Chart.DATA_PERIOD_WEEK:
                setHaftalik(chartData);
                break;
            case Chart.DATA_PERIOD_MONTH:
                setAylik(chartData);
                break;
        }
    }

    @Override
    public ChartData getChartData() {
        switch(period){
            case Chart.DATA_PERIOD_HALF_DAY:
                return getSeanslik();
            case Chart.DATA_PERIOD_DAY:
                return getGunluk();
            case Chart.DATA_PERIOD_WEEK:
                return getHaftalik();
            case Chart.DATA_PERIOD_MONTH:
                return getAylik();
        }
        return super.getChartData();
    }

    public ChartLegend getRootDataLegend(int idx){
        if(idx>getChartData().getLineData().size()-1){
            idx = getChartData().getLineData().size()-1;
        }
        ChartLegend chartLegend = new ChartLegend(getName());
        if(getGraphType()==Chart.GRAPH_TYPE_OHLC || getGraphType()==Chart.GRAPH_TYPE_CANDLE){
            chartLegend.addElement("Date", getChartData().getTarih().get(idx));
            chartLegend.addElement("Open", FormatTools.formatFloat(getChartData().getAcilis().get(idx)));
            chartLegend.addElement("Low", FormatTools.formatFloat(getChartData().getDusuk().get(idx)));
            chartLegend.addElement("High", FormatTools.formatFloat(getChartData().getYuksek().get(idx)));
            chartLegend.addElement("Close", FormatTools.formatFloat(getChartData().getKapanis().get(idx)));
            if(getInstrumentType()==Chart.INSTRUMENT_TYPE_STOCK){
                chartLegend.addElement("Volume", FormatTools.formatFloat(getChartData().getIslem().get(idx)));
            }
        } else {
            chartLegend.addElement("Date", getChartData().getTarih().get(idx));
            chartLegend.addElement("Close", FormatTools.formatFloat(getChartData().getLineData().get(idx)));
        }
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
        if(isContainsOverlayInstrument()){
            for(int i = 0;i<getOverlayInstruments().size();i++){
                counteridx++;
                content[counteridx] = getOverlayInstruments().get(i).getRootDataLegend(idx);
            }
        }
        return content;
    }


    public void changePeriyot(int period) {
        if(this.period!=period){
            switch(period){
                case Chart.DATA_PERIOD_HALF_DAY:
                    if(getSeanslik()==null){
                        // there is nothing to do here
                        return;
                    } else {
                        setPeriod(period);
                        updateIndikatorlerWithPeriyot();
                        return;
                    }
                case Chart.DATA_PERIOD_DAY:
                    setPeriod(period);
                    updateChartDataWithGunluk();
                    updateIndikatorlerWithPeriyot();
                    break;
                case Chart.DATA_PERIOD_WEEK:
                    setPeriod(period);
                    updateChartDataWithHaftalik();
                    updateIndikatorlerWithPeriyot();
                    break;
                case Chart.DATA_PERIOD_MONTH:
                    setPeriod(period);
                    updateChartDataWithAylik();
                    updateIndikatorlerWithPeriyot();
                    break;
            }

        }
    }

    private String tempDate;
    private float prevMax, prevMin, prevIslem;
    private boolean takeIslem;
    int idxTargetPer,idxRefPer;
    private void updateChartDataWithGunluk(){
        if(getSeanslik()!=null){
            setGunluk(new ChartData());
            idxRefPer = 0;
            idxTargetPer = 0;
            getGunluk().addTarih(Zaman.getDayPeriodFormatOfDate(getSeanslik().getTarih().get(idxRefPer)));
            getGunluk().addAcilis(getSeanslik().getAcilis().get(idxRefPer));
            getGunluk().addDusuk(getSeanslik().getDusuk().get(idxRefPer));
            getGunluk().addYuksek(getSeanslik().getYuksek().get(idxRefPer));
            getGunluk().addKapanis(getSeanslik().getKapanis().get(idxRefPer));
            takeIslem = (getSeanslik().getIslem()!=null && getSeanslik().getIslem().size()>0);
            if(takeIslem){
                getGunluk().addIslem(getSeanslik().getIslem().get(idxRefPer));
            }
            idxRefPer++;
            loadTargetWithReferance(idxRefPer, getSeanslik(), idxTargetPer, getGunluk());
        }
    }

    public void updateChartDataWithHaftalik(){
        updateChartDataWithGunluk();
        setHaftalik(new ChartData());
        idxRefPer = 0;
        idxTargetPer = 0;
        getHaftalik().addTarih(Zaman.getWeekPeriodFormatOfDate(getGunluk().getTarih().get(idxRefPer)));
        getHaftalik().addAcilis(getGunluk().getAcilis().get(idxRefPer));
        getHaftalik().addDusuk(getGunluk().getDusuk().get(idxRefPer));
        getHaftalik().addYuksek(getGunluk().getYuksek().get(idxRefPer));
        getHaftalik().addKapanis(getGunluk().getKapanis().get(idxRefPer));
        takeIslem = (getGunluk().getIslem()!=null && getGunluk().getIslem().size()>0);
        if(takeIslem){
            getHaftalik().addIslem(getGunluk().getIslem().get(idxRefPer));
        }
        idxRefPer++;
        loadTargetWithReferance(idxRefPer, getGunluk(), idxTargetPer, getHaftalik());
    }

    public void updateChartDataWithAylik(){
        updateChartDataWithGunluk();
        updateChartDataWithHaftalik();
        setAylik(new ChartData());
        idxRefPer = 0;
        idxTargetPer = 0;
        getAylik().addTarih(Zaman.getMonthPeriodFormatOfDate(getHaftalik().getTarih().get(idxRefPer)));
        getAylik().addAcilis(getHaftalik().getAcilis().get(idxRefPer));
        getAylik().addDusuk(getHaftalik().getDusuk().get(idxRefPer));
        getAylik().addYuksek(getHaftalik().getYuksek().get(idxRefPer));
        getAylik().addKapanis(getHaftalik().getKapanis().get(idxRefPer));
        takeIslem = (getHaftalik().getIslem()!=null && getHaftalik().getIslem().size()>0);
        if(takeIslem){
            getAylik().addIslem(getHaftalik().getIslem().get(idxRefPer));
        }
        idxRefPer++;
        loadTargetWithReferance(idxRefPer, getHaftalik(), idxTargetPer, getAylik());

    }

    private void loadTargetWithReferance(int refIndex, ChartData refChartData, int targetIndex, ChartData targetChartData){
        for(int i=refIndex;i<refChartData.getTarih().size();i++){
            switch(getPeriod()){
                case Chart.DATA_PERIOD_DAY:
                    tempDate = Zaman.getDayPeriodFormatOfDate(refChartData.getTarih().get(i));
                    break;
                case Chart.DATA_PERIOD_WEEK:
                    tempDate = Zaman.getWeekPeriodFormatOfDate(refChartData.getTarih().get(i));
                    break;
                case Chart.DATA_PERIOD_MONTH:
                    tempDate = Zaman.getMonthPeriodFormatOfDate(refChartData.getTarih().get(i));
                    break;
            }
            if(tempDate.equals(targetChartData.getTarih().get(targetIndex))){ // same day
                prevMax = targetChartData.getYuksek().get(targetIndex);
                prevMin = targetChartData.getDusuk().get(targetIndex);
                if(refChartData.getDusuk().get(i)<prevMin){
                    targetChartData.getDusuk().set(targetIndex,refChartData.getDusuk().get(i));
                }
                if(refChartData.getYuksek().get(i)>prevMax){
                    targetChartData.getYuksek().set(targetIndex,refChartData.getYuksek().get(i));
                }
                targetChartData.getKapanis().set(targetIndex,refChartData.getKapanis().get(i));
                if(takeIslem){
                    prevIslem = targetChartData.getIslem().get(targetIndex);
                    targetChartData.getIslem().set(targetIndex, prevIslem+refChartData.getIslem().get(i));
                }

            } else {
                targetChartData.addTarih(tempDate);
                targetChartData.addAcilis(refChartData.getAcilis().get(i));
                targetChartData.addDusuk(refChartData.getDusuk().get(i));
                targetChartData.addYuksek(refChartData.getYuksek().get(i));
                targetChartData.addKapanis(refChartData.getKapanis().get(i));
                if(takeIslem){
                    targetChartData.addIslem(refChartData.getIslem().get(i));
                }
                targetIndex = targetChartData.getTarih().size()-1;
            }
        }
        refDataCount = refChartData.getTarih().size();

    }

    public void addEntryToChartData(){
        //TODO fake hisse analizi icin data uretirken bu kismi kullanicaz
    }

    private void updateIndikatorlerWithPeriyot(){
        if(getIndicators()!=null && getIndicators().size()>0){
            for(MainIndicatorData mid:getIndicators()){
                mid.calculate();
            }
        }
    }

    public ChartData getSeanslik() {
        return seanslik;
    }

    public void setSeanslik(ChartData seanslik) {
        this.seanslik = seanslik;
    }

    public ChartData getGunluk() {
        return gunluk;
    }

    public void setGunluk(ChartData gunluk) {
        this.gunluk = gunluk;
    }

    public ChartData getHaftalik() {
        return haftalik;
    }

    public void setHaftalik(ChartData haftalik) {
        this.haftalik = haftalik;
    }

    public ChartData getAylik() {
        return aylik;
    }

    public void setAylik(ChartData aylik) {
        this.aylik = aylik;
    }

    public int getGunlukDegisim(int idx) {
        if (getChartData().getAcilis().get(idx) < getChartData().getKapanis().get(idx)) {
            return Chart.DIRECTION_UP;
        } else if (getChartData().getAcilis().get(idx) > getChartData().getKapanis().get(idx)) {
            return Chart.DIRECTION_DOWN;
        } else {
            return Chart.DIRECTION_NONE;
        }
    }

    public int getPeriodMultiplier(){
        if(this.period==Chart.DATA_PERIOD_HALF_DAY || this.period==Chart.DATA_PERIOD_DAY){
            return 1;
        } else if (this.period==Chart.DATA_PERIOD_WEEK){
            return 7;
        } else {
            return 30;
        }
    }
}
