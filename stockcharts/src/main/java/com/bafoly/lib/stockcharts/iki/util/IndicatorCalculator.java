package com.bafoly.lib.stockcharts.iki.util;

import android.util.Log;

import com.bafoly.lib.stockcharts.iki.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.model.data.TripleData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by basarb on 5/11/2016.
 */
public class IndicatorCalculator {

    // common methods
    private static double getAverage(List<Double> data, int period, int index){
        index++;
        double currentSum = 0;
        for(double singleData : data.subList(index-period, index)){
            currentSum=currentSum+singleData;
        }
        return currentSum/period;
    }

    private static double getMeanDeviation(List<Double> tp, double tpma, int period, int index){
        index++;
        double meanDeviation = 0;
        for(int k = index-period;k<index;k++){
            meanDeviation = meanDeviation+Math.abs(tpma-tp.get(k));
        }
        return meanDeviation/period;
    }

    private static List<Double> getStdDev(List<Double> data, int period){
        List<Double> average = new ArrayList<>();
        List<Double> deviationSquare = new ArrayList<>();
        List<Double> standardDeviation = new ArrayList<>();

        for(int i=0;i<data.size();i++){
            if(i<period-1){
                average.add(data.get(i));
                deviationSquare.add(0d);
                standardDeviation.add(0d);
            } else if(i>=period-1){
                average.add(getAverage(data, period, i));

                double currentDeviation = data.get(i) - average.get(i);

                deviationSquare.add(currentDeviation*currentDeviation);
                for(int j = i - period + 1; j<i; j++){
                    currentDeviation = data.get(j)-average.get(i);
                    deviationSquare.set(j, currentDeviation * currentDeviation);
                }

                standardDeviation.add(Math.sqrt(getAverage(deviationSquare, period, i)));
            }
        }
        return standardDeviation;
    }

    // indicator calculation methods
    public static <Z extends SingleData> List<Z> getEMA(List<? extends SingleData> data, int period){
        List<Z> emaData = new ArrayList<>(data.size());

        double multiplier = 2 / ((double)period + 1);

        List<Double> values = new ArrayList<>();

        for(int i = 0;i<data.size();i++){
            values.add(data.get(i).getOne().doubleValue());
            emaData.add((Z)(data.get(i)).copy());
            if(i==period-1){
                emaData.get(i).setOne(getAverage(values, period, i));
            } else if(i>=period-1){
                double previousEma = emaData.get(i-1).getOne().doubleValue();
                double value = ((data.get(i).getOne().doubleValue() - previousEma)*multiplier)+previousEma;
                emaData.get(i).setOne(value);
            }
        }

        for(int i = 0;i<period-1;i++){
            emaData.set(i, null);
        }
        return emaData;
    }



    public static <Z extends SingleData> List<Z> getSMA(List<? extends SingleData> data, int period){
        List<Z> smaData = new ArrayList<>(data.size());
        List<Double> values = new ArrayList<>();
        for(int i = 0;i<data.size();i++){
            smaData.add((Z)(data.get(i)).copy());
            values.add(data.get(i).getOne().doubleValue());
            if(i>=period-1){
                smaData.get(i).setOne(getAverage(values, period, i));
            }
        }

        for(int i = 0;i<period-1;i++){
            smaData.set(i, null);
        }

        return smaData;
    }

    // Smoothed / Modified / Running Moving Average
    // https://en.wikipedia.org/wiki/Moving_average#Modified_moving_average
    public static <Z extends SingleData> List<Z> getSSMA(List<? extends SingleData> data, int period){
        List<Z> ssmaData = new ArrayList<>(data.size());
        List<Double> values = new ArrayList<>();
        for(int i = 0;i<data.size();i++){
            ssmaData.add((Z)(data.get(i)).copy());
            values.add(data.get(i).getOne().doubleValue());
            if(i==period-1){
                ssmaData.get(i).setOne(getAverage(values, period, i));
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
                rsiAverageGain.add(getAverage(gain, period, i));
                rsiAverageLoss.add(getAverage(loss, period, i));
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
                curMin = Collections.min(rsiDouble.subList(i+1-period,i+1));
                curMax = Collections.max(rsiDouble.subList(i+1-period,i+1));

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

    public static <Z extends SingleData> List<Z> getBollinger(List<? extends SingleData> data, int period) throws IllegalArgumentException{

        if(data != null && data.size()>0){
            if(!(data.get(0) instanceof TripleData) || !(data.get(0) instanceof QuadrupleData))
                throw new IllegalArgumentException("You must supply Triple or QuadrupleData");
        }



        return null;
    }

//    public static List<Float> calculateMFI(IndikatorMFI indMFI, int periyot){
//        List<Float> close = indMFI.getParentInstrumentData().getChartModel().getKapanis();
//        List<Float> highs = indMFI.getParentInstrumentData().getChartModel().getYuksek();
//        List<Float> lows = indMFI.getParentInstrumentData().getChartModel().getDusuk();
//        List<Float> volume = indMFI.getParentInstrumentData().getChartModel().getIslem();
//
//        List<Float> mfi = new ArrayList<Float>();
//        List<Float> tp = new ArrayList<Float>();
//        List<Float> positiveFlow = new ArrayList<Float>();
//        List<Float> negativeFlow = new ArrayList<Float>();
////		float flowRatio = 0f;
//        mfi.add(0f);
//        tp.add((close.get(0)+highs.get(0)+lows.get(0))/3);
//        positiveFlow.add(0f);
//        negativeFlow.add(0f);
//        for(int i = 1;i<close.size();i++){
//            tp.add((close.get(i)+highs.get(i)+lows.get(i))/3);
//            if(tp.get(i)>tp.get(i-1)){
//                positiveFlow.add(tp.get(i)*volume.get(i));
//                negativeFlow.add(0f);
//            } else if (tp.get(i)<tp.get(i-1)){
//                positiveFlow.add(0f);
//                negativeFlow.add(tp.get(i)*volume.get(i));
//            } else {
//                positiveFlow.add(0f);
//                negativeFlow.add(0f);
//            }
//
//            if(i<periyot-1){
//                mfi.add(0f);
//            } else {
//                indMFI.setLastPositiveFlow(getAverage(positiveFlow, periyot, i));
//                indMFI.setLastNegativeFlow(getAverage(negativeFlow, periyot, i));
//                indMFI.setLastTP(tp.get(i));
//                mfi.add(100-(100/(1+(indMFI.getLastPositiveFlow()/indMFI.getLastNegativeFlow()))));
//            }
//        }
//
//        return mfi;
//
//    }

//    public static List<Float> getMFI(List<Float> close, List<Float> highs, List<Float> lows, List<Float> volume, int periyot){
//        List<Float> mfi = new ArrayList<Float>();
//        List<Float> tp = new ArrayList<Float>();
//        List<Float> positiveFlow = new ArrayList<Float>();
//        List<Float> negativeFlow = new ArrayList<Float>();
//        float flowRatio = 0f;
//        mfi.add(0f);
//        tp.add((close.get(0)+highs.get(0)+lows.get(0))/3);
//        positiveFlow.add(0f);
//        negativeFlow.add(0f);
//        for(int i = 1;i<close.size();i++){
//            tp.add((close.get(i)+highs.get(i)+lows.get(i))/3);
//            if(tp.get(i)>tp.get(i-1)){
//                positiveFlow.add(tp.get(i)*volume.get(i));
//                negativeFlow.add(0f);
//            } else if (tp.get(i)<tp.get(i-1)){
//                positiveFlow.add(0f);
//                negativeFlow.add(tp.get(i)*volume.get(i));
//            } else {
//                positiveFlow.add(0f);
//                negativeFlow.add(0f);
//            }
//
//            if(i<periyot-1){
//                mfi.add(0f);
//            } else {
//                flowRatio = getAverage(positiveFlow, periyot, i)/getAverage(negativeFlow, periyot, i);
//                mfi.add(100-(100/(1+flowRatio)));
//            }
//        }
//        return mfi;
//    }
//
//
//
////    public static List<Float> calculateWilliamsR(IndikatorWilliamsR indW, int periyot){
////        List<Float> williamR = new ArrayList<Float>();
////        float curMin, curMax;
////        for(int i = 0;i<indW.getParentInstrumentData().getChartModel().getKapanis().size();i++){
////            if(i<periyot-1){
////                williamR.add(indW.getParentInstrumentData().getChartModel().getKapanis().get(i));
////            }else if(i>=periyot-1){
////                curMin = getMin(indW.getParentInstrumentData().getChartModel().getDusuk(),periyot,i);
////                curMax = getMax(indW.getParentInstrumentData().getChartModel().getYuksek(),periyot,i);
////                indW.setLastMax(curMax);
////                indW.setLastMin(curMin);
////                williamR.add((-100*(curMax-indW.getParentInstrumentData().getChartModel().getKapanis().get(i))/(curMax-curMin)));
////            }
////        }
////        return williamR;
////    }
//
//    public static List<Float> getWilliamsR(List<Float> close, List<Float> highs, List<Float> lows, int periyot){
//        List<Float> williamR = new ArrayList<Float>();
//        float curMin, curMax;
//        for(int i = 0;i<close.size();i++){
//            if(i<periyot-1){
//                williamR.add(close.get(i));
//            }else if(i>=periyot-1){
//                curMin = getMin(lows,periyot,i);
//                curMax = getMax(highs,periyot,i);
//                williamR.add((-100*(curMax-close.get(i))/(curMax-curMin)));
//            }
//        }
//        return williamR;
//    }
//
//
//
//
//    public static List<Float> getCCI(List<Float> close, List<Float> highs, List<Float> lows, int periyot){
//        float constant = 0.015f;
//        List<Float> tp = new ArrayList<Float>();
//        List<Float> tpma = new ArrayList<Float>();
//        List<Float> meandev = new ArrayList<Float>();
//        List<Float> cci = new ArrayList<Float>();
//        for(int i = 0;i<close.size();i++){
//            tp.add((close.get(i)+highs.get(i)+lows.get(i))/3);
//            if(i<periyot-1){
//                tpma.add(tp.get(i));
//                meandev.add(0f);
//                cci.add(0f);
//            } else {
//                tpma.add(getAverage(tp, periyot, i));
//                meandev.add(getMeanDeviation(tp, tpma.get(i), periyot, i));
//                cci.add((tp.get(i)-tpma.get(i))/(constant*meandev.get(i)));
//            }
//        }
//        return cci;
//    }
//
//    public static List<Float> getMACDLine(List<Float> vals){
//        List<Float> ema12 = getEMA(vals,12);
//        List<Float> ema26 = getEMA(vals,26);
//        List<Float> macd = new ArrayList<Float>();
//        for(int i=0;i<ema12.size();i++){
//            macd.add(ema12.get(i)-ema26.get(i));
//        }
//        return macd;
//    }
//    public static List<Float> getMACD(List<Float> vals){
//        List<Float> ema12 = getEMA(vals,12);
//        List<Float> ema26 = getEMA(vals,26);
//        List<Float> macd = new ArrayList<Float>();
//        for(int i=0;i<ema12.size();i++){
//            macd.add(ema12.get(i)-ema26.get(i));
//        }
//        return getEMA(macd, 9);
//    }
//
//
//
//
//    public static List<List<Float>> getBollingerBands(List<Float> vals, int periyot){
//
//        List<Float> bollingerUpperBand = new ArrayList<Float>();
//        List<Float> bollingerLowerBand = new ArrayList<Float>();
//        List<Float> bollingerBandWidth = new ArrayList<Float>();
//
//        List<Float> stdDev = getStdDev(vals, periyot);
//        List<Float> sma = getSMA(vals, periyot);
//
//        for(int i = 0;i<vals.size();i++){
//            bollingerUpperBand.add(sma.get(i)+(stdDev.get(i)*2));
//            bollingerLowerBand.add(sma.get(i)-(stdDev.get(i)*2));
//            bollingerBandWidth.add(stdDev.get(i)*4);
//
//        }
//
//        List<List<Float>> bollinger = new ArrayList<List<Float>>();
//        bollinger.add(bollingerUpperBand);
//        bollinger.add(bollingerLowerBand);
//        bollinger.add(bollingerBandWidth);
//
//        return bollinger;
//    }
//
//    public static float getSum(Float[] vals){
//        float val = 0f;
//        for(Float f:vals){
//            val = val + f;
//        }
//        return val;
//    }
//
//    public static List<Float> getCorrelation(List<Float> valsA, List<Float> valsB, int periyot){
//        List<Float> correlation = new ArrayList<Float>();
//        float meanA=0f;
//        float meanB=0f;
//        float curA = 0f;
//        float curB = 0;
//        Float[] sqrA = new Float[periyot];
//        Float[] sqrB = new Float[periyot];
//        Float[] AtimesB = new Float[periyot];
//
//
//        for(int i=0;i<valsA.size();i++){
//            if(i<periyot-1){
//                correlation.add(0f);
//            }else if(i>=periyot-1){
//                meanA = getAverage(valsA, periyot, i);
//                meanB = getAverage(valsB, periyot, i);
//                for(int k = 0;k<periyot;k++){
//                    curA = meanA - valsA.get(i-k);
//                    curB = meanB - valsB.get(i-k);
//                    sqrA[k] = curA*curA;
//                    sqrB[k] = curB*curB;
//                    AtimesB[k] = curA*curB;
//                }
//                correlation.add((float) (getSum(AtimesB)/Math.sqrt(getSum(sqrA)*getSum(sqrB))));
//            }
//        }
//        return correlation;
//    }
//
//    public static List<Float> getStochasticK(List<Float> close,List<Float> highs,List<Float> mins, int periyot){
//        List<Float> stochK = new ArrayList<Float>();
//        float curMin, curMax;
//        for(int i = 0;i<close.size();i++){
//            if(i<periyot-1){
//                stochK.add(0f);
//            } else {
//                curMin = getMin(mins,periyot,i);
//                curMax = getMax(highs,periyot,i);
//                stochK.add(((close.get(i)-curMin)/(curMax-curMin)) * 100);
//            }
//        }
//        return stochK;
//    }
}
