package com.bafoly.lib.stockcharts;

import com.bafoly.lib.stockcharts.iki.model.data.QuadrupleData;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;
import com.bafoly.lib.stockcharts.iki.util.IndicatorCalculator;

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

//        assertTrue(sma.get(0)==null);
//        assertEquals(7.75222222222222d, sma.get(sma.size()-1).getOne().doubleValue(), 0.0001d);

        for(int i = 0;i<ref.size();i++){
            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
            if(sma.get(i)!=null){
                line+="\t"+sma.get(i).getX()+"\t"+sma.get(i).getOne();
            }
            System.out.println(line);
        }
    }

    @Test
    public void calculateSmaInteger() throws Exception {

        List<SingleData<String, Integer>> ref = getSingleIntegerData();
        List<SingleData> sma = IndicatorCalculator.getSMA(ref, 9);

        assertTrue(sma.get(0)==null);
        assertEquals(7.1111111111d, sma.get(sma.size()-1).getOne().doubleValue(), 0.0001d);
//
//        for(int i = 0;i<ref.size();i++){
//            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
//            if(sma.get(i)!=null){
//                line+="\t"+sma.get(i).getX()+"\t"+sma.get(i).getOne();
//            }
//            System.out.println(line);
//        }
    }

    @Test
    public void calculateEmaFloat() throws Exception{
        List<SingleData<String, Float>> ref = getSingleFloatData();
        List<SingleData> sma = IndicatorCalculator.getEMA(ref, 9);


        for(int i = 0;i<ref.size();i++){
            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
            if(sma.get(i)!=null){
                line+="\t"+sma.get(i).getX()+"\t"+sma.get(i).getOne();
            }
            System.out.println(line);
        }

    }

    @Test
    public void calculateRSIFloat() throws Exception{
        List<SingleData<String, Float>> ref = getSingleFloatData();
        List<SingleData> rsi = IndicatorCalculator.getRSI(ref, 14);


        for(int i = 0;i<ref.size();i++){
            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
            if(rsi.get(i)!=null){
                line+="\t"+rsi.get(i).getX()+"\t"+rsi.get(i).getOne();
            }
            System.out.println(line);
        }

    }

    @Test
    public void calculateStochRSIFloat() throws Exception{
        List<SingleData<String, Float>> ref = getSingleFloatData();
        List<SingleData> rsi = IndicatorCalculator.getStochasticRSI(ref, 14);


        for(int i = 0;i<ref.size();i++){
            String line = ref.get(i).getX()+"\t"+ref.get(i).getOne();
            if(rsi.get(i)!=null){
                line+="\t"+rsi.get(i).getX()+"\t"+rsi.get(i).getOne();
            }
            System.out.println(line);
        }

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


}