package com.bafoly.lib.stockcharts.iki.util;

import com.bafoly.lib.stockcharts.iki.model.data.DoubleData;
import com.bafoly.lib.stockcharts.iki.model.data.OHLCVolumeData;
import com.bafoly.lib.stockcharts.iki.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.data.TripleData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Utility class for Indicator Calculation<br>
 * Algorithm reference is at http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators
 */
public class IndicatorCalculator {

    /**
     * Returns the sum of array
     * @param data Double List
     * @return
     */
    private static double getSum(List<Double> data){
        double sum = 0;
        for(double current : data){
            sum += current;
        }
        return sum;
    }

    /**
     * Calculates the average of array
     * @param data Double List
     * @return
     */
    private static double getAverage(List<Double> data){
        return getSum(data) / data.size();
    }


    /**
     * Calculates the mean deviation of list
     * @param typicalPrice
     * @param typicalPriceMovingAverage
     * @return
     */
    private static double getMeanDeviation(List<Double> typicalPrice, double typicalPriceMovingAverage){
        double meanDeviation = 0;
        for(double current : typicalPrice){
            meanDeviation = meanDeviation+Math.abs(typicalPriceMovingAverage-current);
        }
        return meanDeviation/typicalPrice.size();
    }

    /**
     * Returns standard deviation of array
     * @param data
     * @return
     */
    private static double getStandardDeviation(List<Double> data){
        double average = getAverage(data);

        double squareSum = 0d;
        for(double d: data){
            squareSum += Math.pow(d-average,2);
        }
        return Math.sqrt(squareSum/data.size());
    }

    /**
     * Exponential Moving Average is calculated for the Data
     * @param data
     * @param period
     * @return Result is stored in a Double List
     */
    private static List<Double> getEMA(List<? extends SingleData> data, int period){
        List<Double> emaData = new ArrayList<>(data.size());

        double multiplier = 2 / ((double)period + 1);

        List<Double> values = new ArrayList<>();

        for(int i = 0;i<data.size();i++){
            if(data.get(i)!=null){
                values.add(data.get(i).getOne().doubleValue());
            } else {
                values.add(0d);
            }
            if(i<period-1){
                emaData.add(null);
            } else if(i==period-1){
                emaData.add(getAverage(values.subList(i+1-period, i+1)));
            } else if(i>=period-1){
                double previousEma = emaData.get(i-1);
                if(data.get(i)!=null){
                    double value = ((data.get(i).getOne().doubleValue() - previousEma)*multiplier)+previousEma;
                    emaData.add(value);
                } else {
                    emaData.add(0d);
                }
            }
        }

        return emaData;
    }

    /**
     * Simple Moving Average
     * @param data
     * @param period
     * @return
     */
    public static <Z extends SingleData> List<Z> getSMA(List<? extends SingleData> data, int period){
        List<Z> smaData = new ArrayList<>(data.size());
        List<Double> values = new ArrayList<>();
        for(int i = 0;i<data.size();i++){
            smaData.add((Z)(data.get(i)).copy());
            values.add(data.get(i).getOne().doubleValue());
            if(i>=period-1){
                smaData.get(i).setOne(getAverage(values.subList(i+1-period, i+1)));
            }
        }

        for(int i = 0;i<period-1;i++){
            smaData.set(i, null);
        }

        return smaData;
    }

    // Smoothed / Modified / Running Moving Average
    // https://en.wikipedia.org/wiki/Moving_average#Modified_moving_average
    /**
     * Smoother / Modified / Running Moving Average
     * @param data
     * @param period
     * @return
     */
    public static <Z extends SingleData> List<Z> getSSMA(List<? extends SingleData> data, int period){
        List<Z> ssmaData = new ArrayList<>(data.size());
        List<Double> values = new ArrayList<>();
        for(int i = 0;i<data.size();i++){
            ssmaData.add((Z)(data.get(i)).copy());
            values.add(data.get(i).getOne().doubleValue());
            if(i==period-1){
                ssmaData.get(i).setOne(getAverage(values.subList(i+1-period, i+1)));
            } else if (i > period -1 ){
                double result = (((period - 1) * ssmaData.get(i-1).getOne().doubleValue()) + data.get(i).getOne().doubleValue())/period;
                ssmaData.get(i).setOne(result);
            }
        }

        for(int i = 0;i<period-1;i++){
            ssmaData.set(i, null);
        }

        return ssmaData;
    }

    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:relative_strength_index_rsi
    // https://en.wikipedia.org/wiki/Relative_strength_index#Calculation
    public static <Z extends SingleData> List<Z> getRSI(List<? extends SingleData> data, int period){
        List<Z> rsiData = new ArrayList<>(data.size());

        List<Double> gain = new ArrayList<>();
        List<Double> loss = new ArrayList<>();
        List<Double> rsi = new ArrayList<>();
        List<Double> rsiAverageGain = new ArrayList<>();
        List<Double> rsiAverageLoss = new ArrayList<>();
        gain.add(0d);
        loss.add(0d);
        rsi.add(0d);
        rsiAverageGain.add(0d);
        rsiAverageLoss.add(0d);

        rsiData.add((Z)data.get(0).copy());

        double curVal, prevVal;
        for(int i = 1;i<data.size();i++){

            rsiData.add((Z)data.get(i).copy());

            curVal = data.get(i).getOne().doubleValue();
            prevVal = data.get(i-1).getOne().doubleValue();
            if(curVal>prevVal){
                gain.add(curVal-prevVal);
                loss.add(0d);
            } else if(prevVal>curVal){
                gain.add(0d);
                loss.add(prevVal-curVal);
            } else {
                gain.add(0d);
                loss.add(0d);
            }
            if(i<period){
                rsi.add(0d);
                rsiAverageGain.add(0d);
                rsiAverageLoss.add(0d);
            } else if(i==period){
                rsiAverageGain.add(getAverage(gain.subList(i+1-period, i+1)));
                rsiAverageLoss.add(getAverage(loss.subList(i+1-period, i+1)));
                rsi.add(100-(100/(1+(rsiAverageGain.get(i)/rsiAverageLoss.get(i)))));
            } else {
                rsiAverageGain.add(((rsiAverageGain.get(i-1)*(period-1))+gain.get(i))/period);
                rsiAverageLoss.add(((rsiAverageLoss.get(i-1)*(period-1))+loss.get(i))/period);
                rsi.add(100-(100/(1+(rsiAverageGain.get(i)/rsiAverageLoss.get(i)))));
            }

            rsiData.get(i).setOne(rsi.get(i));
        }


        for(int i = 0;i<period;i++){
            rsiData.set(i, null);
        }
        return rsiData;
    }


    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:stochrsi
    public static <Z extends SingleData> List<Z> getStochasticRSI(List<? extends SingleData> data, int period){
        List<Z> rsiData = getRSI(data, period);
        List<Z> stochasticRSIData = new ArrayList<>();

        List<Double> rsiDouble = new ArrayList<>();
        double curMin, curMax;
        for(int i = 0;i<data.size();i++){
            double val = rsiData.get(i) == null ? 0d : rsiData.get(i).getOne().doubleValue();
            rsiDouble.add(val);
            stochasticRSIData.add((Z)data.get(i).copy());
            if(i>=period-1){
                List<Double> temp = rsiDouble.subList(i+1-period,i+1);
                curMin = Collections.min(temp);
                curMax = Collections.max(temp);

                if(curMax-curMin>0){
                    double valHere = (rsiData.get(i).getOne().doubleValue()-curMin)/(curMax-curMin);
                    stochasticRSIData.get(i).setOne(valHere);
                } else {
                    stochasticRSIData.get(i).setOne(0d);
                }
            }
        }

        for(int i = 0;i<period;i++){
            stochasticRSIData.set(i, null);
        }

        return stochasticRSIData;
    }

    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:commodity_channel_index_cci
    public static <X, Y extends Number> List<SingleData<X, Y>> getCCI(List<? extends SingleData<X, Y>> data, int period) throws IllegalArgumentException{

        if(data != null && data.size()>0){
            if(!(data.get(0) instanceof TripleData) || !(data.get(0) instanceof QuadrupleData))
                throw new IllegalArgumentException("You must supply Triple or QuadrupleData");
        }

        List<SingleData<X, Y>> cciData = new ArrayList<>();

        double constant = 0.015d;
        List<Double> typicalPrice = new ArrayList<>();

        for(int i = 0;i<data.size();i++){
            cciData.add(((SingleData)data.get(i)).copy());

            TripleData td = (TripleData) data.get(i);

            double typicalVal = (td.getThree().doubleValue() + td.getTwo().doubleValue() + td.getOne().doubleValue())/3;
            typicalPrice.add(typicalVal);

            if(i>=period - 1){
                List<Double> temp = typicalPrice.subList(i+1-period, i+1);
                double typicalPriceSMA = getAverage(temp);
                double meanDeviation = getMeanDeviation(temp, typicalPriceSMA);
                Double cciResult = (typicalVal - typicalPriceSMA)/(constant*meanDeviation);
                cciData.get(i).setOne((Y)cciResult);
            }
        }

        for(int i = 0;i<period;i++){
            cciData.set(i, null);
        }

        return cciData;

    }

    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:williams_r
    public static <X, Y extends Number> List<SingleData<X, Y>> getWilliamsR(List<? extends SingleData<X, Y>> data, int period) throws IllegalArgumentException{

        if(data != null && data.size()>0){
            if(!(data.get(0) instanceof TripleData) || !(data.get(0) instanceof QuadrupleData))
                throw new IllegalArgumentException("You must supply Triple or QuadrupleData");
        }

        List<SingleData<X, Y>> williamsRData = new ArrayList<>();

        List<Double> highs = new ArrayList<>();
        List<Double> lows = new ArrayList<>();

        for(int i = 0;i<data.size();i++){
            williamsRData.add(((SingleData)data.get(i)).copy());

            TripleData td = (TripleData) data.get(i);

            highs.add(td.getThree().doubleValue());
            lows.add(td.getTwo().doubleValue());
            if(i>=period-1){
                double close = td.getOne().doubleValue();

                double highestHigh = Collections.max(highs.subList(i+1-period,i+1));
                double lowestLow = Collections.min(lows.subList(i+1-period,i+1));

                Double result = (highestHigh - close)/(highestHigh - lowestLow)*-100;

                williamsRData.get(i).setOne((Y) result);

            }
        }
        for(int i = 0;i<period;i++){
            williamsRData.set(i, null);
        }

        return williamsRData;
    }

    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:bollinger_bands
    public static <X, Y extends Number> List<TripleData<X, Y>> getBollinger(List<? extends SingleData> data, int period) throws IllegalArgumentException{

        if(data != null && data.size()>0){
            if(!(data.get(0) instanceof TripleData) || !(data.get(0) instanceof QuadrupleData))
                throw new IllegalArgumentException("You must supply Triple or QuadrupleData");
        }

        List<TripleData<X, Y>> bollingerData = new ArrayList<>();

        List<Double> close = new ArrayList<>();

        for(int i = 0 ; i<data.size();i++){
            TripleData td = (TripleData) data.get(i);
            bollingerData.add(td.copy());
            close.add(td.getOne().doubleValue());

            if(i>=period-1){
                List<Double> temp = close.subList(i+1-period, i+1);
                Double sma = getAverage(temp);
                double sdev = getStandardDeviation(temp);

                Double up = sma + (sdev*2);
                Double low = sma - (sdev*2);

                bollingerData.get(i).setThree((Y)up);
                bollingerData.get(i).setTwo((Y)low);
                bollingerData.get(i).setOne((Y)sma);
            }
        }

        for(int i = 0;i<period;i++){
            bollingerData.set(i, null);
        }

        return bollingerData;
    }


    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:money_flow_index_mfi
    public static <X, Y extends Number> List<SingleData<X, Y>> getMFI(List<? extends SingleData<X, Y>> data, int period) throws IllegalArgumentException{

        if(data != null && data.size()>0){
            if(!(data.get(0) instanceof OHLCVolumeData))
                throw new IllegalArgumentException("You must supply OHLCVolumeData");
        }

        List<SingleData<X, Y>> mfiData = new ArrayList<>();

        List<Double> typicalPrice = new ArrayList<>();
        List<Double> positiveMoneyFlow = new ArrayList<>();
        List<Double> negativeMoneyFlow = new ArrayList<>();

        mfiData.add(((SingleData)data.get(0)).copy());
        OHLCVolumeData td = (OHLCVolumeData) data.get(0);
        double tp = (td.getThree().doubleValue() + td.getTwo().doubleValue()+ td.getOne().doubleValue())/3;
        typicalPrice.add(tp);

        positiveMoneyFlow.add(0d);
        negativeMoneyFlow.add(0d);

        for(int i = 1;i<data.size();i++){
            mfiData.add(((SingleData)data.get(i)).copy());
            td = (OHLCVolumeData) data.get(i);
            tp = (td.getThree().doubleValue() + td.getTwo().doubleValue()+ td.getOne().doubleValue())/3;
            typicalPrice.add(tp);

            double previousTp = typicalPrice.get(i-1);
            if(tp>previousTp){
                positiveMoneyFlow.add(tp*td.getFive().doubleValue());
                negativeMoneyFlow.add(0d);
            } else {
                positiveMoneyFlow.add(0d);
                negativeMoneyFlow.add(tp*td.getFive().doubleValue());
            }
            if(i>=period-1) {
                double posFlow = getSum(positiveMoneyFlow.subList(i+1-period, i+1));
                double negFlow = getSum(negativeMoneyFlow.subList(i+1-period, i+1));

                double ratio = posFlow/negFlow;

                Double mfiValue = 100 - (100 / (ratio + 1));

                mfiData.get(i).setOne((Y)mfiValue);

            }

        }
        for(int i = 0;i<period;i++){
            mfiData.set(i, null);
        }

        return mfiData;
    }


    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:moving_average_convergence_divergence_macd
    public static <X, Y extends Number> List<TripleData<X, Y>> getMACD(List<? extends SingleData> data, int periodEmaFirst, int periodEmaSecond, int periodSignal){

        List<TripleData<X, Y>> macdData = new ArrayList<>();

        List<Double> firstEma = getEMA(data, periodEmaFirst);

        List<Double> secondEma = getEMA(data, periodEmaSecond);

        List<SingleData> macdLine = new ArrayList<>();

        for(int i = 0;i<data.size();i++){
            Double d = 0d;
            macdData.add(new TripleData<X, Y>((X)data.get(i).getX(),(Y)d, (Y)d, (Y)d));
            if(firstEma.get(i)!=null && secondEma.get(i)!=null){
                Double val = firstEma.get(i)-secondEma.get(i);
                macdLine.add(new SingleData(data.get(i).getX(), val));
                macdData.get(i).setThree((Y)val);
            } else {
                macdLine.add(null);
            }
        }

        List<Double> signalEma = getEMA(macdLine, periodSignal);

        for(int i = 0 ; i<data.size();i++){
            if(macdLine.get(i)!=null){
                Double val = macdLine.get(i).getOne().doubleValue() - signalEma.get(i);
                macdData.get(i).setTwo((Y)signalEma.get(i));
                macdData.get(i).setOne((Y)val);
            } else {
                macdData.set(i, null);
            }
        }

        return macdData;
    }

    // http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:stochastic_oscillator_fast_slow_and_full
    public static <X, Y extends Number> List<DoubleData<X, Y>> getStochasticOscillator(List<? extends SingleData<X, Y>> data, int periodK, int periodS) throws IllegalArgumentException{

        if(data != null && data.size()>0){
            if(!(data.get(0) instanceof TripleData) || !(data.get(0) instanceof QuadrupleData || !(data.get(0) instanceof OHLCVolumeData)))
                throw new IllegalArgumentException("You must supply Triple or QuadrupleData");
        }

        List<DoubleData<X, Y>> stochasticOscillator = new ArrayList<>();

        List<Double> highs = new ArrayList<>();
        List<Double> lows = new ArrayList<>();
        List<Double> ks = new ArrayList<>();

        double high, low;

        for(int i = 0;i<data.size(); i++){
            stochasticOscillator.add((DoubleData) data.get(i).copy());
            TripleData td = (TripleData) data.get(i);

            highs.add(td.getThree().doubleValue());
            lows.add(td.getTwo().doubleValue());

            if(i>=periodK-1){
                high = Collections.max(highs.subList(i+1-periodK, i+1));
                low = Collections.min(lows.subList(i+1-periodK, i+1));

                Double valueK = 100 * ((td.getOne().doubleValue() - low)/(high-low));
                ks.add(valueK.doubleValue());
                stochasticOscillator.get(i).setOne((Y)valueK);
                Double valueS = 0d;
                if(ks.size()>=periodS){
                    valueS = getAverage(ks.subList(ks.size()-periodS, ks.size()));
                }
                stochasticOscillator.get(i).setTwo((Y)valueS);
            }
        }

        for(int i = 0;i<periodK+periodS;i++){
            stochasticOscillator.set(i, null);
        }

        return stochasticOscillator;
    }

}
