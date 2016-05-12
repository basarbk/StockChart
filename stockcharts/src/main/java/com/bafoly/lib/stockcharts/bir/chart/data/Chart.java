package com.bafoly.lib.stockcharts.bir.chart.data;

/**
 * Created by basarb on 10/4/2015.
 */
public class Chart {

    // Main Chart Types
    public static final int MAIN_TYPE_INSTRUMENT = 0;
    public static final int MAIN_TYPE_INDICATOR = 1;
    public static final String[] mainTypes = {"MAIN_TYPE_INSTRUMENT","MAIN_TYPE_INDICATOR"};

    public static String getMainTypeString(int i){
        return mainTypes[i];
    }


    // MAIN_TYPE_ENSTRUMAN
//	public static final int INSTRUMENT_TYPE_NONE = 0;
    public static final int INSTRUMENT_TYPE_STOCK = 0;
    public static final int INSTRUMENT_TYPE_INDEX = 1;
    public static final int INSTRUMENT_TYPE_FOREX = 2;
    public static final int INSTRUMENT_TYPE_COMMODITIES = 3;
    public static final String[] instrumentTypes = {"INSTRUMENT_TYPE_STOCK","INSTRUMENT_TYPE_INDEX",
            "INSTRUMENT_TYPE_FOREX","INSTRUMENT_TYPE_COMMODITIES"};

    public static String getEnstrumanTypeString(int i){
        return instrumentTypes[i];
    }


    // MAIN_TYPE_INDIKATOR
    public static final int INDICATOR_TYPE_NONE = 0;
    public static final int INDICATOR_TYPE_MACD = 1;
    public static final int INDICATOR_TYPE_RSI = 2;
    public static final int INDICATOR_TYPE_STOCHASTIC_RSI = 3;
    public static final int INDICATOR_TYPE_CCI = 4;
    public static final int INDICATOR_TYPE_MFI = 5;
    public static final int INDICATOR_TYPE_WILLIAMSR = 6;
    public static final int INDICATOR_TYPE_MOVING_AVERAGE = 7;
    public static final int INDICATOR_TYPE_BOLLINGER = 8;
    public static final int INDICATOR_TYPE_MOMENTUM = 9;
    public static final int INDICATOR_TYPE_VOLUME = 10;
    public static final int INDICATOR_TYPE_CORRELATION = 11;
    public static final int INDICATOR_TYPE_STOCHASTIC_OSCILLATOR = 12;
    public static final String[] indikatorTypes = {"INDICATOR_TYPE_NONE", "INDICATOR_TYPE_MACD", "INDICATOR_TYPE_RSI", "INDICATOR_TYPE_STOCHASTIC_RSI",
            "INDICATOR_TYPE_CCI", "INDICATOR_TYPE_MFI", "INDICATOR_TYPE_WILLIAMSR", "INDICATOR_TYPE_MOVING_AVERAGE",
            "INDICATOR_TYPE_BOLLINGER", "INDICATOR_TYPE_MOMENTUM", "INDICATOR_TYPE_VOLUME","INDICATOR_TYPE_CORRELATION",
            "INDICATOR_TYPE_STOCHASTIC_OSCILLATOR"};

    public static String getIndikatorTypeString(int i){
        return indikatorTypes[i];
    }

    // Graph Types
    public static final int GRAPH_TYPE_LINE = 0;
    public static final int GRAPH_TYPE_OHLC = 1;
    public static final int GRAPH_TYPE_CANDLE = 2;
    public static final int GRAPH_TYPE_BAR = 3;
    public static final int GRAPH_TYPE_AREA = 4;
    public static final int GRAPH_TYPE_USER_INPUT = 5;
    public static final String[] graphTypes = {"GRAPH_TYPE_LINE","GRAPH_TYPE_OHLC","GRAPH_TYPE_CANDLE",
            "GRAPH_TYPE_BAR","GRAPH_TYPE_AREA","GRAPH_TYPE_USER_INPUT"};

    public static String getGraphTypeString(int i){
        return graphTypes[i];
    }


    // USER_INPUT_TYPES
    public static final int USER_INPUT_TYPE_TECHNICAL_ANALYSIS = 0;
    public static final int USER_INPUT_TYPE_HELPER = 1;
    public static final String[] userInputTypes={"USER_INPUT_TYPE_TECHNICAL_ANALYSIS","USER_INPUT_TYPE_HELPER"};



    // TECHNICAL_ANALYSIS_TYPES
    public static final int ANALYSIS_TYPE_NONE = 0;
    public static final int ANALYSIS_TYPE_SUPPORT_RESISTANCE = 1;
    public static final int ANALYSIS_TYPE_TRENDLINE = 2;
    public static final int ANALYSIS_TYPE_PARALLEL = 3;
    public static final int ANALYSIS_TYPE_FIBONACCI = 4; // this will have sub options like Line, Fan or Circle or maybe all of them
    public static final int ANALYSIS_TYPE_FIBONACCI_TIME_LINES = 5;
    public static final int ANALYSIS_TYPE_ANDREWS_PITCHFORK = 6;
    public static final int ANALYSIS_TYPE_RAFF_REGRESSION = 7;
    public static final int ANALYSIS_TYPE_SPEED_RESISTANCE = 8;
    public static final String[] technicalAnalysisTypes={"ANALYSIS_TYPE_NONE",
            "ANALYSIS_TYPE_SUPPORT_RESISTANCE",
            "ANALYSIS_TYPE_TRENDLINE",
            "ANALYSIS_TYPE_PARALLEL",
            "ANALYSIS_TYPE_FIBONACCI",
            "ANALYSIS_TYPE_FIBONACCI_TIME_LINES",
            "ANALYSIS_TYPE_ANDREWS_PITCHFORK",
            "ANALYSIS_TYPE_RAFF_REGRESSION",
            "ANALYSIS_TYPE_SPEED_RESISTANCE"};


    // HELPER_TYPES
    public static final int HELPER_TYPE_NONE = 9;
    public static final int HELPER_TYPE_USER_INPUT = 10;
    public static final int HELPER_TYPE_USER_HIGHLIGHT = 11;




    // INSTRUMENT DAILY DIRECTION
    // user input
    public static final int DIRECTION_NONE = 0;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final String[] directions = {"Notr","Positive","Negative"};

    // data periyot
    public static final int DATA_PERIOD_HALF_DAY = 0;
    public static final int DATA_PERIOD_DAY = 1;
    public static final int DATA_PERIOD_WEEK = 2;
    public static final int DATA_PERIOD_MONTH = 3;

    public static final String[] periods = {"DATA_PERIOD_HALF_DAY","DATA_PERIOD_DAY","DATA_PERIOD_WEEK","DATA_PERIOD_MONTH"};


    // Encode icin kullandigim delimiter
    public static final String DELIMITER = ";";


    // NOT USED YET
    // Process Result codes
    public static final int RESULT_OPERATION_SUCCESFULL = 0;
    public static final int RESULT_LINE_TYPE_DOESNT_MATCH_CHART_DATA=1;



}
