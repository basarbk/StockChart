package com.bafoly.lib.stockcharts;

import com.bafoly.lib.stockcharts.model.data.DoubleData;
import com.bafoly.lib.stockcharts.model.data.OHLCVolumeData;
import com.bafoly.lib.stockcharts.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.model.data.SingleData;
import com.bafoly.lib.stockcharts.model.data.TripleData;
import com.bafoly.lib.stockcharts.util.IndicatorCalculator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void calculateSmaFloat() throws Exception {
        List<SingleData<String, Float>> ref = getSingleFloatData();
        List<SingleData> sma = IndicatorCalculator.getSMA(ref, 9);
        log(ref, sma);
    }

    @Test
    public void calculateSsmaFloat() throws Exception {
        List<SingleData<String, Float>> ref = getSingleFloatData();
        List<SingleData> sma = IndicatorCalculator.getSSMA(ref, 9);
        log(ref, sma);
    }


    @Test
    public void calculateSmaInteger() throws Exception {
        List<SingleData<String, Integer>> ref = getSingleIntegerData();
        List<SingleData> sma = IndicatorCalculator.getSMA(ref, 9);
        log(ref, sma);
    }

    @Test
    public void calculateEmaFloat() throws Exception{
//        List<SingleData<String, Float>> ref = getSingleFloatData();
//        List<SingleData> sma = IndicatorCalculator.getEMA(ref, 9);
//        log(ref, sma);
    }

    @Test
    public void calculateRSIFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<SingleData> result = IndicatorCalculator.getRSI(ref, 14);

        int size = result.size();
        double t1 = result.get(size - 1).getOne().doubleValue();
        double t2 = result.get(size - 2).getOne().doubleValue();
        double t3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(49.60d, t1, 0.01d);
        assertEquals(55.63d, t2, 0.01d);
        assertEquals(53.59d, t3, 0.01d);

        log(ref, result);
    }

    @Test
    public void calculateStochRSIFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<SingleData> result = IndicatorCalculator.getStochasticRSI(ref, 14);

        int size = result.size();
        double t1 = result.get(size - 1).getOne().doubleValue();
        double t2 = result.get(size - 2).getOne().doubleValue();
        double t3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(0.66d, t1, 0.01d);
        assertEquals(1.00d, t2, 0.01d);
        assertEquals(0.92d, t3, 0.01d);

        log(ref, result);
    }

    @Test
    public void calculateCCIFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<SingleData<String, Double>> result = IndicatorCalculator.getCCI(ref, 20);

        int size = result.size();
        double t1 = result.get(size - 1).getOne().doubleValue();
        double t2 = result.get(size - 2).getOne().doubleValue();
        double t3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(66.88d, t1, 0.01d);
        assertEquals(113.13d, t2, 0.01d);
        assertEquals(78.91d, t3, 0.01d);

        log(ref, result);
    }

    @Test
    public void calculateWilliamRFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<SingleData<String, Double>> result = IndicatorCalculator.getWilliamsR(ref, 14);

        int size = result.size();
        double t1 = result.get(size - 1).getOne().doubleValue();
        double t2 = result.get(size - 2).getOne().doubleValue();
        double t3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(-39.062d, t1, 0.001d);
        assertEquals(-10.447d, t2, 0.001d);
        assertEquals(-20.895d, t3, 0.001d);

        log(ref, result);
    }

    @Test
    public void calculateBollingerFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<TripleData<String, Double>> result = IndicatorCalculator.getBollinger(ref, 20);

        int size = result.size();
        double sma1 = result.get(size - 1).getOne().doubleValue();
        double sma2 = result.get(size - 2).getOne().doubleValue();
        double sma3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(7.81d, sma1, 0.01d);
        assertEquals(7.80d, sma2, 0.01d);
        assertEquals(7.78d, sma3, 0.01d);

        double low1 = result.get(size - 1).getTwo().doubleValue();
        double low2 = result.get(size - 2).getTwo().doubleValue();
        double low3 = result.get(size - 3).getTwo().doubleValue();

        assertEquals(7.464d, low1, 0.001d);
        assertEquals(7.458d, low2, 0.001d);
        assertEquals(7.451d, low3, 0.001d);

        double up1 = result.get(size - 1).getThree().doubleValue();
        double up2 = result.get(size - 2).getThree().doubleValue();
        double up3 = result.get(size - 3).getThree().doubleValue();

        assertEquals(8.156d, up1, 0.001d);
        assertEquals(8.148d, up2, 0.001d);
        assertEquals(8.111d, up3, 0.001d);






        logTriple(ref, result);
    }

    @Test
    public void calculateMFIFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<SingleData<String, Double>> result = IndicatorCalculator.getMFI(ref, 14);

        int size = result.size();
        double t1 = result.get(size - 1).getOne().doubleValue();
        double t2 = result.get(size - 2).getOne().doubleValue();
        double t3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(62.403d, t1, 0.001d);
        assertEquals(63.445d, t2, 0.001d);
        assertEquals(57.438d, t3, 0.001d);

        log(ref, result);
    }

    @Test
    public void calculateMACDFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<TripleData<String, Double>> result = IndicatorCalculator.getMACD(ref, 12, 26, 9);

        int size = result.size();
        double histogram1 = result.get(size - 1).getOne().doubleValue();
        double histogram2 = result.get(size - 2).getOne().doubleValue();
        double histogram3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(0.045d, histogram1, 0.001d);
        assertEquals(0.055d, histogram2, 0.001d);
        assertEquals(0.050d, histogram3, 0.001d);

        double signal1 = result.get(size - 1).getTwo().doubleValue();
        double signal2 = result.get(size - 2).getTwo().doubleValue();
        double signal3 = result.get(size - 3).getTwo().doubleValue();

        assertEquals(-0.083d, signal1, 0.001d);
        assertEquals(-0.095d, signal2, 0.001d);
        assertEquals(-0.109d, signal3, 0.001d);

        double macd1 = result.get(size - 1).getThree().doubleValue();
        double macd2 = result.get(size - 2).getThree().doubleValue();
        double macd3 = result.get(size - 3).getThree().doubleValue();

        assertEquals(-0.038d, macd1, 0.001d);
        assertEquals(-0.039d, macd2, 0.001d);
        assertEquals(-0.058d, macd3, 0.001d);

        logTriple(ref, result);
    }

    @Test
    public void calculateStochasticFloat() throws Exception{
        List<OHLCVolumeData<String, Double>> ref = getOHLCVolumeFloatData();
        List<DoubleData<String, Double>> result = IndicatorCalculator.getStochasticOscillator(ref, 14, 3);

        int size = result.size();
        double k1 = result.get(size - 1).getOne().doubleValue();
        double k2 = result.get(size - 2).getOne().doubleValue();
        double k3 = result.get(size - 3).getOne().doubleValue();

        assertEquals(60.937d, k1, 0.001d);
        assertEquals(89.552d, k2, 0.001d);
        assertEquals(79.104d, k3, 0.001d);

        double s1 = result.get(size - 1).getTwo().doubleValue();
        double s2 = result.get(size - 2).getTwo().doubleValue();
        double s3 = result.get(size - 3).getTwo().doubleValue();

        assertEquals(76.531d, s1, 0.001d);
        assertEquals(72.139d, s2, 0.001d);
        assertEquals(63.681d, s3, 0.001d);

        logDouble(ref, result);
    }

    private static List<OHLCVolumeData<String, Double>> getOHLCVolumeFloatData(){
        List<OHLCVolumeData<String, Double>> data = new ArrayList<>();
        data.add(new OHLCVolumeData("Apr 05, 2016", 8.03, 8.04, 7.89, 7.98, 19940000));
        data.add(new OHLCVolumeData("Apr 06, 2016", 8.02, 8.02, 7.84, 7.84, 21400000));
        data.add(new OHLCVolumeData("Apr 07, 2016", 7.90, 7.94, 7.81, 7.85, 16460000));
        data.add(new OHLCVolumeData("Apr 08, 2016", 7.87, 7.96, 7.82, 7.96, 23190000));
        data.add(new OHLCVolumeData("Apr 11, 2016", 7.97, 8.16, 7.94, 8.12, 27490000));
        data.add(new OHLCVolumeData("Apr 12, 2016", 8.13, 8.21, 8.03, 8.12, 20760000));
        data.add(new OHLCVolumeData("Apr 13, 2016", 8.18, 8.45, 8.15, 8.43, 33150000));
        data.add(new OHLCVolumeData("Apr 14, 2016", 8.47, 8.48, 8.37, 8.46, 17800000));
        data.add(new OHLCVolumeData("Apr 15, 2016", 8.48, 8.49, 8.34, 8.49, 19190000));
        data.add(new OHLCVolumeData("Apr 18, 2016", 8.43, 8.71, 8.41, 8.71, 23340000));
        data.add(new OHLCVolumeData("Apr 19, 2016", 8.73, 8.80, 8.64, 8.69, 24400000));
        data.add(new OHLCVolumeData("Apr 20, 2016", 8.65, 8.78, 8.50, 8.51, 33380000));
        data.add(new OHLCVolumeData("Apr 21, 2016", 8.56, 8.65, 8.46, 8.54, 19210000));
        data.add(new OHLCVolumeData("Apr 22, 2016", 8.56, 8.58, 8.44, 8.49, 21480000));
        data.add(new OHLCVolumeData("Apr 25, 2016", 8.49, 8.49, 8.31, 8.35, 15850000));
        data.add(new OHLCVolumeData("Apr 26, 2016", 8.38, 8.58, 8.31, 8.57, 25060000));
        data.add(new OHLCVolumeData("Apr 27, 2016", 8.55, 8.70, 8.54, 8.56, 19460000));
        data.add(new OHLCVolumeData("Apr 28, 2016", 8.56, 8.62, 8.45, 8.62, 22320000));
        data.add(new OHLCVolumeData("Apr 29, 2016", 8.65, 8.65, 8.53, 8.60, 19430000));
        data.add(new OHLCVolumeData("May 02, 2016", 8.59, 8.59, 8.38, 8.40, 14940000));
        data.add(new OHLCVolumeData("May 03, 2016", 8.43, 8.46, 8.02, 8.06, 34850000));
        data.add(new OHLCVolumeData("May 04, 2016", 8.05, 8.17, 7.77, 7.78, 50040000));
        data.add(new OHLCVolumeData("May 05, 2016", 7.59, 7.85, 7.51, 7.64, 44770000));
        data.add(new OHLCVolumeData("May 06, 2016", 7.65, 7.71, 7.43, 7.63, 36330000));
        data.add(new OHLCVolumeData("May 09, 2016", 7.73, 7.83, 7.69, 7.75, 18360000));
        data.add(new OHLCVolumeData("May 10, 2016", 7.74, 7.84, 7.66, 7.66, 20180000));
        data.add(new OHLCVolumeData("May 11, 2016", 7.68, 7.76, 7.52, 7.73, 32360000));
        data.add(new OHLCVolumeData("May 12, 2016", 7.72, 7.86, 7.69, 7.70, 27290000));
        data.add(new OHLCVolumeData("May 13, 2016", 7.65, 7.80, 7.63, 7.69, 20690000));
        data.add(new OHLCVolumeData("May 16, 2016", 7.74, 7.76, 7.48, 7.50, 20870000));
        data.add(new OHLCVolumeData("May 17, 2016", 7.55, 7.64, 7.47, 7.61, 24210000));
        data.add(new OHLCVolumeData("May 18, 2016", 7.57, 7.71, 7.52, 7.71, 25750000));
        data.add(new OHLCVolumeData("May 20, 2016", 7.70, 7.76, 7.50, 7.50, 30630000));
        data.add(new OHLCVolumeData("May 23, 2016", 7.52, 7.69, 7.50, 7.66, 18150000));
        data.add(new OHLCVolumeData("May 24, 2016", 7.62, 8.09, 7.62, 8.05, 37740000));
        data.add(new OHLCVolumeData("May 25, 2016", 8.06, 8.14, 7.93, 7.93, 20350000));
        data.add(new OHLCVolumeData("May 26, 2016", 7.90, 7.97, 7.78, 7.89, 20320000));
        data.add(new OHLCVolumeData("May 27, 2016", 7.90, 8.01, 7.88, 7.95, 14370000));
        data.add(new OHLCVolumeData("May 30, 2016", 7.95, 8.07, 7.94, 8.00, 11180000));
        data.add(new OHLCVolumeData("May 31, 2016", 8.06, 8.12, 7.97, 7.97, 23270000));
        data.add(new OHLCVolumeData("Jun 01, 2016", 7.98, 8.00, 7.87, 7.90, 17650000));
        data.add(new OHLCVolumeData("Jun 02, 2016", 7.93, 7.96, 7.77, 7.79, 21490000));
        data.add(new OHLCVolumeData("Jun 03, 2016", 7.82, 8.06, 7.80, 8.00, 27610000));
        data.add(new OHLCVolumeData("Jun 06, 2016", 8.04, 8.10, 8.00, 8.07, 15870000));
        data.add(new OHLCVolumeData("Jun 07, 2016", 8.11, 8.13, 7.89, 7.89, 28070000));


        return data;
    }

    private static List<SingleData<String, Float>> getSingleFloatData(){
        List<SingleData<String, Float>> data = new ArrayList<>();

        data.add(new SingleData("Apr 05, 2016", 7.98));
        data.add(new SingleData("Apr 06, 2016", 7.84));
        data.add(new SingleData("Apr 07, 2016", 7.85));
        data.add(new SingleData("Apr 08, 2016", 7.96));
        data.add(new SingleData("Apr 11, 2016", 8.12));
        data.add(new SingleData("Apr 12, 2016", 8.12));
        data.add(new SingleData("Apr 13, 2016", 8.43));
        data.add(new SingleData("Apr 14, 2016", 8.46));
        data.add(new SingleData("Apr 15, 2016", 8.49));
        data.add(new SingleData("Apr 18, 2016", 8.71));
        data.add(new SingleData("Apr 19, 2016", 8.69));
        data.add(new SingleData("Apr 20, 2016", 8.51));
        data.add(new SingleData("Apr 21, 2016", 8.54));
        data.add(new SingleData("Apr 22, 2016", 8.49));
        data.add(new SingleData("Apr 25, 2016", 8.35));
        data.add(new SingleData("Apr 26, 2016", 8.57));
        data.add(new SingleData("Apr 27, 2016", 8.56));
        data.add(new SingleData("Apr 28, 2016", 8.62));
        data.add(new SingleData("Apr 29, 2016", 8.60));
        data.add(new SingleData("May 02, 2016", 8.40));
        data.add(new SingleData("May 03, 2016", 8.06));
        data.add(new SingleData("May 04, 2016", 7.78));
        data.add(new SingleData("May 05, 2016", 7.64));
        data.add(new SingleData("May 06, 2016", 7.63));
        data.add(new SingleData("May 09, 2016", 7.75));
        data.add(new SingleData("May 10, 2016", 7.66));
        data.add(new SingleData("May 11, 2016", 7.73));
        data.add(new SingleData("May 12, 2016", 7.70));
        data.add(new SingleData("May 13, 2016", 7.69));
        data.add(new SingleData("May 16, 2016", 7.50));
        data.add(new SingleData("May 17, 2016", 7.61));
        data.add(new SingleData("May 18, 2016", 7.53));
        data.add(new SingleData("May 20, 2016", 7.50));
        data.add(new SingleData("May 23, 2016", 7.66));
        data.add(new SingleData("May 24, 2016", 8.05));
        data.add(new SingleData("May 25, 2016", 7.93));
        data.add(new SingleData("May 26, 2016", 7.89));
        data.add(new SingleData("May 27, 2016", 7.95));
        data.add(new SingleData("May 30, 2016", 8.00));
        data.add(new SingleData("May 31, 2016", 7.97));


        return data;
    }

    private static List<SingleData<String, Integer>> getSingleIntegerData(){
        List<SingleData<String, Integer>> data = new ArrayList<>();

        data.add(new SingleData("Apr 05, 2016", 7));
        data.add(new SingleData("Apr 06, 2016", 7));
        data.add(new SingleData("Apr 07, 2016", 7));
        data.add(new SingleData("Apr 08, 2016", 7));
        data.add(new SingleData("Apr 11, 2016", 8));
        data.add(new SingleData("Apr 12, 2016", 8));
        data.add(new SingleData("Apr 13, 2016", 8));
        data.add(new SingleData("Apr 14, 2016", 8));
        data.add(new SingleData("Apr 15, 2016", 8));
        data.add(new SingleData("Apr 18, 2016", 8));
        data.add(new SingleData("Apr 19, 2016", 8));
        data.add(new SingleData("Apr 20, 2016", 8));
        data.add(new SingleData("Apr 21, 2016", 8));
        data.add(new SingleData("Apr 22, 2016", 8));
        data.add(new SingleData("Apr 25, 2016", 8));
        data.add(new SingleData("Apr 26, 2016", 8));
        data.add(new SingleData("Apr 27, 2016", 8));
        data.add(new SingleData("Apr 28, 2016", 8));
        data.add(new SingleData("Apr 29, 2016", 8));
        data.add(new SingleData("May 02, 2016", 8));
        data.add(new SingleData("May 03, 2016", 8));
        data.add(new SingleData("May 04, 2016", 7));
        data.add(new SingleData("May 05, 2016", 7));
        data.add(new SingleData("May 06, 2016", 7));
        data.add(new SingleData("May 09, 2016", 7));
        data.add(new SingleData("May 10, 2016", 7));
        data.add(new SingleData("May 11, 2016", 7));
        data.add(new SingleData("May 12, 2016", 7));
        data.add(new SingleData("May 13, 2016", 7));
        data.add(new SingleData("May 16, 2016", 7));
        data.add(new SingleData("May 17, 2016", 7));
        data.add(new SingleData("May 18, 2016", 7));
        data.add(new SingleData("May 20, 2016", 7));
        data.add(new SingleData("May 23, 2016", 7));
        data.add(new SingleData("May 24, 2016", 8));
        data.add(new SingleData("May 25, 2016", 7));
        data.add(new SingleData("May 26, 2016", 7));
        data.add(new SingleData("May 27, 2016", 7));
        data.add(new SingleData("May 30, 2016", 8));
        data.add(new SingleData("May 31, 2016", 7));


        return data;
    }


    private static List<QuadrupleData<String, Float>> getFloatData(){
        List<QuadrupleData<String, Float>> data = new ArrayList<>();

        data.add(new QuadrupleData("Apr 05, 2016", 8.03, 7.89, 8.04, 7.98));
        data.add(new QuadrupleData("Apr 06, 2016", 8.02, 7.84, 8.02, 7.84));
        data.add(new QuadrupleData("Apr 07, 2016", 7.90, 7.81, 7.94, 7.85));
        data.add(new QuadrupleData("Apr 08, 2016", 7.87, 7.82, 7.96, 7.96));
        data.add(new QuadrupleData("Apr 11, 2016", 7.97, 7.94, 8.16, 8.12));
        data.add(new QuadrupleData("Apr 12, 2016", 8.13, 8.03, 8.21, 8.12));
        data.add(new QuadrupleData("Apr 13, 2016", 8.18, 8.15, 8.45, 8.43));
        data.add(new QuadrupleData("Apr 14, 2016", 8.47, 8.37, 8.48, 8.46));
        data.add(new QuadrupleData("Apr 15, 2016", 8.48, 8.34, 8.49, 8.49));
        data.add(new QuadrupleData("Apr 18, 2016", 8.43, 8.41, 8.71, 8.71));
        data.add(new QuadrupleData("Apr 19, 2016", 8.73, 8.64, 8.80, 8.69));
        data.add(new QuadrupleData("Apr 20, 2016", 8.65, 8.50, 8.78, 8.51));
        data.add(new QuadrupleData("Apr 21, 2016", 8.56, 8.46, 8.65, 8.54));
        data.add(new QuadrupleData("Apr 22, 2016", 8.56, 8.44, 8.58, 8.49));
        data.add(new QuadrupleData("Apr 25, 2016", 8.49, 8.31, 8.49, 8.35));
        data.add(new QuadrupleData("Apr 26, 2016", 8.38, 8.31, 8.58, 8.57));
        data.add(new QuadrupleData("Apr 27, 2016", 8.55, 8.54, 8.70, 8.56));
        data.add(new QuadrupleData("Apr 28, 2016", 8.56, 8.45, 8.62, 8.62));
        data.add(new QuadrupleData("Apr 29, 2016", 8.65, 8.53, 8.65, 8.60));
        data.add(new QuadrupleData("May 02, 2016", 8.59, 8.38, 8.59, 8.40));
        data.add(new QuadrupleData("May 03, 2016", 8.43, 8.02, 8.46, 8.06));
        data.add(new QuadrupleData("May 04, 2016", 8.05, 7.77, 8.17, 7.78));
        data.add(new QuadrupleData("May 05, 2016", 7.59, 7.51, 7.85, 7.64));
        data.add(new QuadrupleData("May 06, 2016", 7.65, 7.43, 7.71, 7.63));
        data.add(new QuadrupleData("May 09, 2016", 7.73, 7.69, 7.83, 7.75));
        data.add(new QuadrupleData("May 10, 2016", 7.74, 7.66, 7.84, 7.66));
        data.add(new QuadrupleData("May 11, 2016", 7.68, 7.52, 7.76, 7.73));
        data.add(new QuadrupleData("May 12, 2016", 7.72, 7.69, 7.86, 7.70));
        data.add(new QuadrupleData("May 13, 2016", 7.65, 7.63, 7.80, 7.69));
        data.add(new QuadrupleData("May 16, 2016", 7.74, 7.48, 7.76, 7.50));
        data.add(new QuadrupleData("May 17, 2016", 7.55, 7.47, 7.64, 7.61));
        data.add(new QuadrupleData("May 18, 2016", 7.57, 7.52, 7.57, 7.53));
        data.add(new QuadrupleData("May 20, 2016", 7.70, 7.50, 7.76, 7.50));
        data.add(new QuadrupleData("May 23, 2016", 7.52, 7.50, 7.69, 7.66));
        data.add(new QuadrupleData("May 24, 2016", 7.62, 7.62, 8.09, 8.05));
        data.add(new QuadrupleData("May 25, 2016", 8.06, 7.93, 8.14, 7.93));
        data.add(new QuadrupleData("May 26, 2016", 7.90, 7.78, 7.97, 7.89));
        data.add(new QuadrupleData("May 27, 2016", 7.90, 7.88, 8.01, 7.95));
        data.add(new QuadrupleData("May 30, 2016", 7.95, 7.94, 8.07, 8.00));
        data.add(new QuadrupleData("May 31, 2016", 8.06, 7.97, 8.12, 7.97));


        return data;
    }

    private static List<QuadrupleData<String, Integer>> getIntegerData(){
        List<QuadrupleData<String, Integer>> data = new ArrayList<>();

        data.add(new QuadrupleData("Apr 05, 2016", 8, 7, 8, 7));
        data.add(new QuadrupleData("Apr 06, 2016", 8, 7, 8, 7));
        data.add(new QuadrupleData("Apr 07, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("Apr 08, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("Apr 11, 2016", 7, 7, 8, 8));
        data.add(new QuadrupleData("Apr 12, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 13, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 14, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 15, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 18, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 19, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 20, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 21, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 22, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 25, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 26, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 27, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 28, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("Apr 29, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("May 02, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("May 03, 2016", 8, 8, 8, 8));
        data.add(new QuadrupleData("May 04, 2016", 8, 7, 8, 7));
        data.add(new QuadrupleData("May 05, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 06, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 09, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 10, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 11, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 12, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 13, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 16, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 17, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 18, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 20, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 23, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 24, 2016", 7, 7, 8, 8));
        data.add(new QuadrupleData("May 25, 2016", 8, 7, 8, 7));
        data.add(new QuadrupleData("May 26, 2016", 7, 7, 7, 7));
        data.add(new QuadrupleData("May 27, 2016", 7, 7, 8, 7));
        data.add(new QuadrupleData("May 30, 2016", 7, 7, 8, 8));
        data.add(new QuadrupleData("May 31, 2016", 8, 7, 8, 7));


        return data;
    }


    private static <T extends Number> void log(List<? extends SingleData<String, T>> ref, List<? extends SingleData> target){
        for(int i = 0;i<ref.size();i++){
            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
            if(target.get(i)!=null){
                line+="\t"+target.get(i).getX()+"\t"+target.get(i).getOne();
            }
            System.out.println(line);
        }
    }

    private static <T extends Number> void logDouble(List<? extends QuadrupleData<String, T>> ref, List<DoubleData<String, T>> target){
        for(int i = 0;i<ref.size();i++){
            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
            if(target.get(i)!=null){
                line+="\t"+target.get(i).getX()+"\t"+target.get(i).getOne()+"\t"+target.get(i).getTwo();
            }
            System.out.println(line);
        }
    }

    private static <T extends Number> void logTriple(List<? extends QuadrupleData<String, T>> ref, List<TripleData<String, T>> target){
        for(int i = 0;i<ref.size();i++){
            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
            if(target.get(i)!=null){
                line+="\t"+target.get(i).getX()+"\t"+target.get(i).getOne()+"\t"+target.get(i).getTwo()+"\t"+target.get(i).getThree();
            }
            System.out.println(line);
        }
    }

}