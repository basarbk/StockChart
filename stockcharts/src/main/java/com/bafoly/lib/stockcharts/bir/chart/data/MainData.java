package com.bafoly.lib.stockcharts.bir.chart.data;

/**
 * Created by basarb on 10/4/2015.
 */

import android.graphics.Color;

import com.bafoly.lib.stockcharts.bir.chart.data.userhelper.UserComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Base object for instrument or indicator which will be displayed on screen
 * All those objects will extend this
 */
public abstract class MainData {

    /**
     * This is either the name of the instrument or name of the indicator
     */
    private String name;

    /**
     * Type we are processing<br>
     *<b>Chart.MAIN_TYPE_ENSTRUMAN</b><br>
     *<b>Chart.MAIN_TYPE_INDIKATOR</b><br>
     */
    private int mainType; // data tipi.. enstruman (hisse, doviz etc) ya da indikator (RSI, CCI etc)

    /**
     * If this is an instrument like Stock, Commoditie etc, this will be storing the info about it.<br>
     * By default, this is set to <b>Chart.INSTRUMENT_TYPE_NONE</b>
     */
    private int instrumentType = Chart.INSTRUMENT_TYPE_STOCK; // instrument ID


    /**
     * If this is an indicator like RSI, CCI etc this will be storing the info about it.<br>
     * By default, this is set to <b>Chart.INDICATOR_TYPE_NONE</b>
     */
    private int indicatorType = Chart.INDICATOR_TYPE_NONE; // indikator tipi

    /**
     * This is storing the graphic shape<br>
     * By default, this is set to <b>Chart.GRAPH_TYPE_LINE</b><br>
     * This can be changed in runtime.
     */
    private int graphType = Chart.GRAPH_TYPE_LINE; // her grafigin bir cizgi tipi var.. candle, ohlc, line, bar

    /**
     * ChartData is storing the values to be drawn onto screen
     */
    protected ChartData chartData;


    /**
     * There must be always a base InstrumentData.
     */
    private MainInstrumentData parentInstrumentData;


    /**
     * This MainData might be on top of another one. This boolean is used to check it
     */
    private boolean overlay = false;

    /**
     * red green blue
     */
    private int red = 0;
    private int green = 0;
    private int blue = 0;


    /**
     * ChartData's maximum high value in range
     */
    private float max;

    /**
     * ChartData's minimum low value in range
     */
    private float min;


    /**
     * If this is an overlay chart on another chart, then we might want to fit this chart to screen based on it's max and min values.
     */
    boolean useMyMaxMin = false;


    /**
     * This is used in chartPosition to calculate the position of each chartData element in X axis
     */
    float multiplierX;

    /**
     * This is used in chartPosition to calculate the position of each chartData element in Y axis
     */
    float multiplierY;

    /**
     * This data may have indicators connected to it
     */
    private boolean containsIndicator = false;

    /**
     * List of the connected indicators
     */
    private List<MainIndicatorData> indicators;

    /**
     * Instrument may contain other instruments
     */
    private boolean containsOverlayInstrument = false;

    /**
     * Overlay instrument data list
     */
    private List<MainInstrumentData> instruments;

    /**
     * This data may have technical analysis
     */
    private boolean containsTechnicalAnalysis = false;

    /**
     * List of thtechnical analysis
     */
    private List<UserInputTechnicalAnalysisData> technicalAnalysis;

    /**
     * This data may have user helper inputs connected to it
     */
    private boolean containsUserHelper = false;

    /**
     * List of the user helper inputs
     */ //TODO user input ile highlight i birbirinden ayirsam daha iyi olacak.. commentleri gosterecegim fragmentta daha kullanisli olacak boylece
    private List<UserComment> userInput;

    /**
     * There can be multiple overlay charts on this mainData. This counter is used to retrieve the data for chart legend
     */
    private int overlayCounter = 0;

    /**
     * this will be used in runtime to add/edit technicalAnalysisData
     */
    UserInputTechnicalAnalysisData activeTechnicalAnalysis;

    /**
     * This will be used in runtime to add/edit userHelperData
     */
    UserComment activeUserInput;


    public MainData(int mainType){
        this.mainType = mainType;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getMainType() {
        return mainType;
    }


    public void setMainType(int mainType) {
        this.mainType = mainType;
    }


    public int getInstrumentType() {
        return instrumentType;
    }


    public void setInstrumentType(int instrumentType) {
        this.instrumentType = instrumentType;
    }


    public int getIndicatorType() {
        return indicatorType;
    }


    public void setIndicatorType(int indicatorType) {
        this.indicatorType = indicatorType;
    }


    public int getGraphType() {
        return graphType;
    }


    public void setGraphType(int graphType) {
        this.graphType = graphType;
    }


    public ChartData getChartData() {
        return chartData;
    }


    public void setChartData(ChartData chartData) {
        this.chartData = chartData;
    }


    public MainInstrumentData getParentInstrumentData() {
        return parentInstrumentData;
    }


    public void setParentMainData(MainInstrumentData parentInstrumentData) {
        this.parentInstrumentData = parentInstrumentData;
    }


    public boolean isOverlay() {
        return overlay;
    }


    public void setOverlay(boolean overlay) {
        this.overlay = overlay;
    }

    public void setColorFromDB(String colors){
        String[] s = colors.split(Chart.DELIMITER);
        setColor(Integer.valueOf(s[0]), Integer.valueOf(s[1]), Integer.valueOf(s[2]));
    }

    public void setColor(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getColor(){
        return Color.rgb(red, green, blue);
    }

    public String getColorForDB(){
        return String.valueOf(this.red)+Chart.DELIMITER+ String.valueOf(this.green)+Chart.DELIMITER+ String.valueOf(this.blue);
    }


    public float getMax() {
        return max;
    }


    public void setMax(float max) {
        this.max = max;
    }


    public float getMin() {
        return min;
    }


    public void setMin(float min) {
        this.min = min;
    }


    public boolean isUseMyMaxMin() {
        return useMyMaxMin;
    }


    public void setUseMyMaxMin(boolean useMyMaxMin) {
        this.useMyMaxMin = useMyMaxMin;
    }


    public float getMultiplierX() {
        return multiplierX;
    }


    public void setMultiplierX(float multiplierX) {
        this.multiplierX = multiplierX;
    }


    public float getMultiplierY() {
        return multiplierY;
    }


    public void setMultiplierY(float multiplierY) {
        this.multiplierY = multiplierY;
    }

    public boolean isInstrument(){
        if(this.mainType==Chart.MAIN_TYPE_INSTRUMENT){
            return true;
        }
        return false;
    }

    public boolean isIndicator(){
        if(this.mainType==Chart.MAIN_TYPE_INDICATOR){
            return true;
        }
        return false;
    }

    public boolean isContainsOverlayInstrument(){
        return containsOverlayInstrument;
    }

    public void setContainsOverlayInstrument(boolean containsOverlayInstrument){
        this.containsOverlayInstrument = containsOverlayInstrument;
    }

    public List<MainInstrumentData> getOverlayInstruments(){
        return instruments;
    }

    public void addOverlayInstrument(MainInstrumentData mainInstrumentData){
        if(instruments==null){
            instruments = new ArrayList<MainInstrumentData>();
        }
        setContainsOverlayInstrument(true);
        overlayCounter++;
        instruments.add(mainInstrumentData);
    }

    public void removeOverlayInstrument(String instrumentName){
        if(instruments!=null){
            for(int i =0;i<instruments.size();i++){
                if(instruments.get(i).getName().equals(instrumentName)){
                    instruments.remove(i);
                    overlayCounter--;
                    break;
                }
            }
        }
        if(instruments==null || instruments.size()==0){
            setContainsOverlayInstrument(false);
        }
    }

    public void clearOverlayInstruments(){
        instruments = null;
        overlayCounter = 0;
        setContainsOverlayInstrument(false);
    }

    // INDICATOR LIST HANDLING
    public boolean isContainsIndicator() {
        return containsIndicator;
    }


    public void setContainsIndicator(boolean containsIndicator) {
        this.containsIndicator = containsIndicator;
    }


    public List<MainIndicatorData> getIndicators() {
        return indicators;
    }


    public void addIndicator(MainIndicatorData indicatorData){
        if(indicators==null){
            indicators = new ArrayList<MainIndicatorData>();
            setContainsIndicator(true);
        }
        retrieveTechnicalAnalysisForIndicator(indicatorData);
        retrieveUserCommentForIndicator(indicatorData);
        indicators.add(indicatorData);
        if(indicatorData.isOverlay()){
            overlayCounter++;
        }

    }

    private void retrieveTechnicalAnalysisForIndicator(MainIndicatorData indicatorData){
        if(technicalAnalysis!=null){
            for(int i=0;i<technicalAnalysis.size();i++){
                if(indicatorData.getIndicatorType()==technicalAnalysis.get(i).getIndicatorID()){
                    indicatorData.addTechnicalAnalysisData(technicalAnalysis.get(i));
                }
            }
        }
    }

    private void retrieveUserCommentForIndicator(MainIndicatorData indicatorData){
        if(userInput!=null){
            for(int i=0;i<userInput.size();i++){
                if(indicatorData.getIndicatorType()==userInput.get(i).getIndicatorID()){
                    indicatorData.addUserHelperData(userInput.get(i));
                }
            }
        }
    }

    public void setIndicators(List<MainIndicatorData> indicators) {
        this.indicators = indicators;
    }

    public void removeIndicator(String indicatorName){
        if(indicators!=null){
            for(int i = indicators.size()-1;i>=-1;i--){
                if(indicators.get(i).getName().equals(indicatorName)){
                    if(indicators.get(i).isOverlay()){
                        overlayCounter--;
                    }
                    indicators.remove(i);
                }
            }
            if(indicators.size()==0){
                setContainsTechnicalAnalysis(false);
            }
        }
    }
    // INDICATOR LIST HANDLING DONE

    //USER INPUT DATA HANDLING
    public boolean isContainsTechnicalAnalysis() {
        return containsTechnicalAnalysis;
    }


    public void setContainsTechnicalAnalysis(boolean containsTechnicalAnalysis) {
        this.containsTechnicalAnalysis = containsTechnicalAnalysis;
    }


    public List<UserInputTechnicalAnalysisData> getTechnicalAnalysis() {
        return technicalAnalysis;
    }

    public void setTechnicalAnalysis(List<UserInputTechnicalAnalysisData> technicalAnalysis) {
        this.technicalAnalysis = technicalAnalysis;
    }

    public void addTechnicalAnalysisData(UserInputTechnicalAnalysisData technicalAnalysisData){
        addUserInputData(technicalAnalysisData);
        addUserInputToIndicator(technicalAnalysisData);
    }

    public void removeTechnicalAnalysisData(long id){
        if(technicalAnalysis!=null){
            for(int i = technicalAnalysis.size()-1;i>=-1;i--){
                if(technicalAnalysis.get(i).getId() == id){
                    technicalAnalysis.remove(i);
                    break;
                }
            }
            if(technicalAnalysis.size()==0){
                setContainsTechnicalAnalysis(false);
            }
        }
    }

    public boolean isContainsUserHelper() {
        return containsUserHelper;
    }


    public void setContainsUserHelper(boolean containsUserHelper) {
        this.containsUserHelper = containsUserHelper;
    }


    public List<UserComment> getUserInput() {
        return userInput;
    }


    public void setUserInput(List<UserComment> userInput) {
        this.userInput = userInput;
    }

    public void addUserHelperData(UserInputUserHelperData userHelperData){
        addUserInputData(userHelperData);
        addUserInputToIndicator(userHelperData);
    }

    public void removeUserInputData(long id){
        if(userInput!=null){
            for(int i = userInput.size()-1;i>=-1;i--){
                if(userInput.get(i).getId() == id){
                    userInput.remove(i);
                    break;
                }
            }
            if(userInput.size()==0){
                setContainsUserHelper(false);
            }
        }
    }


    private void addUserInputData(UserInputData userInputData){
        if(!updateUserInputData(userInputData)){
            if(userInputData.isTechnicalAnalysis()){
                if(technicalAnalysis==null){
                    technicalAnalysis = new ArrayList<UserInputTechnicalAnalysisData>();
                    setContainsTechnicalAnalysis(true);
                }
                technicalAnalysis.add((UserInputTechnicalAnalysisData)userInputData);
            } else if(userInputData.isUserHelper()){
                if(userInput==null){
                    userInput = new ArrayList<UserComment>();
                    setContainsUserHelper(true);
                }
                userInput.add((UserComment)userInputData);
            } else {

            }
        }
    }


    private boolean updateUserInputData(UserInputData userInputData) {
        if (userInputData.isTechnicalAnalysis()) {
            if (technicalAnalysis != null) {
                for (int i = 0; i < technicalAnalysis.size(); i++) {
                    if (technicalAnalysis.get(i).getId() == userInputData.getId()) {
                        technicalAnalysis.set(i, (UserInputTechnicalAnalysisData) userInputData);
                        return true;
                    }
                }
            } else if (userInputData.isUserHelper()) {
                if (userInput != null) {
                    for (int i = 0; i < userInput.size(); i++) {
                        if (userInput.get(i).getId() == userInputData.getId()) {
                            userInput.set(i, (UserComment) userInputData);
                            return true;
                        }
                    }

                }
            } else {

            }
        }
        return false;
    }

    private void addUserInputToIndicator(UserInputData userInputData){
        if(indicators!=null){
            for(int i = 0;i<indicators.size();i++){
                if(indicators.get(i).getIndicatorType()==userInputData.getIndicatorID()){
                    if(userInputData.isTechnicalAnalysis()){
                        indicators.get(i).addTechnicalAnalysisData((UserInputTechnicalAnalysisData)userInputData);
                    } else if (userInputData.isUserHelper()){
                        indicators.get(i).addUserHelperData((UserInputUserHelperData) userInputData);
                    } else {

                    }
                }
            }
        }
    }

    //USER INPUT DATA HANDLING DONE



    public int getOverlayCounter() {
        return overlayCounter;
    }


    public void setOverlayCounter(int overlayCounter) {
        this.overlayCounter = overlayCounter;
    }


    public UserInputTechnicalAnalysisData getActiveTechnicalAnalysis() {
        return activeTechnicalAnalysis;
    }


    public void setActiveTechnicalAnalysis(UserInputTechnicalAnalysisData activeTechnicalAnalysis) {
        this.activeTechnicalAnalysis = activeTechnicalAnalysis;
    }


    public UserComment getActiveUserInput() {
        return activeUserInput;
    }


    public void setActiveUserInput(UserComment activeUserInput) {
        this.activeUserInput = activeUserInput;
    }


    public List<String> getDates(){
        if(isInstrument()){
            return getChartData().getTarih();
        } else {
            if(parentInstrumentData!=null){
                return parentInstrumentData.getChartData().getTarih();
            } else {

                return null;
            }
        }
    }

    public int getPeriodMultiplier(){
        if(isInstrument()){
            return ((MainInstrumentData)this).getPeriodMultiplier();
        } else {
            return getParentInstrumentData().getPeriodMultiplier();
        }
    }

    public int getMainChartPeriod(){
        if(isInstrument()){
            return ((MainInstrumentData)this).getPeriod();
        } else {
            return getParentInstrumentData().getPeriod();
        }
    }
    public ChartLegend[] getChartLegend(int idx){
        return null;
    }

}
