package com.bafoly.lib.stockcharts.uc;

import java.util.HashMap;

/**
 * The chart of Stock or indicator consist of an array of numeric dataMap. So we'll use List for T<br>
 * For technical analysis, we'll be using XYPair
 */
public abstract class DataSet<T> {

    static final int OPEN = 0;
    static final int HIGH = OPEN+1;
    static final int LOW = OPEN+2;
    static final int CLOSE = OPEN+3;
    static final int VOLUME = OPEN+4;

    static final int LINE = VOLUME + 1;

    private final int OHLC_LAST = LINE;


    // TECHNICAL ANALYSIS
    static final int FIRST = OHLC_LAST + 1;
    static final int SECOND = OHLC_LAST + 2;
    static final int THIRD = OHLC_LAST + 3;
    static final int FOURTH = OHLC_LAST + 4;
    static final int FIFTH = OHLC_LAST + 5;
    static final int SIXTH = OHLC_LAST + 6;
    static final int SEVENTH = OHLC_LAST + 7;

    // DATA TYPE
    static final int DATA_LIST = 0;
    static final int DATA_XYPAIR = 1;

    HashMap<Integer, T> dataMap = new HashMap<>();

    int dataType = DATA_LIST;

    public DataSet(int dataType) {
        this.dataType = dataType;
    }

    public boolean is(int type){
        return type == dataType;
    }

    public T getData(int id){
        if(dataMap.containsKey(id))
            return dataMap.get(id);
        return null;
    }

    public void setData(int id, T data){
        dataMap.put(id, data);
    }

    public int getCount(){
        return dataMap.keySet().size();
    }


}
