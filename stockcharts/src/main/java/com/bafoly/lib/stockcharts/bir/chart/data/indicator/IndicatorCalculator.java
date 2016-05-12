package com.bafoly.lib.stockcharts.bir.chart.data.indicator;

import java.util.ArrayList;
import java.util.List;


public class IndicatorCalculator {
	
	public static List<Float> getEMA(List<Float> vals, int periyot){
		List<Float> ema = new ArrayList<Float>();
		float currentSum;
		float carpan = (float)(2.0)/(float)(periyot+1);
		for(int i = 0;i<vals.size();i++){
			if(i<periyot){
				ema.add(vals.get(i));
			}else if(i==periyot){
				currentSum = 0;
				for(float t:vals.subList(i-periyot, i)){
					currentSum=currentSum+t;
				}
				ema.add(currentSum/periyot);
			}else{
				ema.add(((vals.get(i)-ema.get(ema.size()-1))*carpan)+ema.get(ema.size()-1));
			}
		}
		return ema;
	}
	
	private static float getAverage(List<Float> vals, int periyot, int idx){
		idx++;
		float currentSum = 0;
		for(float t:vals.subList(idx-periyot, idx)){
			currentSum=currentSum+t;
		}
		return currentSum/periyot;
	}
	
	public static List<Float> getSMA(List<Float> vals, int periyot){
		List<Float> sma = new ArrayList<Float>();
		for(int i = 0;i<vals.size();i++){
			if(i<periyot-1){
				sma.add(vals.get(i));
			}else if(i>=periyot-1){
				sma.add(getAverage(vals,periyot,i));
			}
		}
		return sma;
	}

	public static List<Float> calculateMFI(IndikatorMFI indMFI, int periyot){
		List<Float> close = indMFI.getParentInstrumentData().getChartData().getKapanis();
		List<Float> highs = indMFI.getParentInstrumentData().getChartData().getYuksek();
		List<Float> lows = indMFI.getParentInstrumentData().getChartData().getDusuk();
		List<Float> volume = indMFI.getParentInstrumentData().getChartData().getIslem();
		
		List<Float> mfi = new ArrayList<Float>();
		List<Float> tp = new ArrayList<Float>();
		List<Float> positiveFlow = new ArrayList<Float>();
		List<Float> negativeFlow = new ArrayList<Float>();
//		float flowRatio = 0f;
		mfi.add(0f);
		tp.add((close.get(0)+highs.get(0)+lows.get(0))/3);
		positiveFlow.add(0f);
		negativeFlow.add(0f);
		for(int i = 1;i<close.size();i++){
			tp.add((close.get(i)+highs.get(i)+lows.get(i))/3);
			if(tp.get(i)>tp.get(i-1)){
				positiveFlow.add(tp.get(i)*volume.get(i));
				negativeFlow.add(0f);
			} else if (tp.get(i)<tp.get(i-1)){
				positiveFlow.add(0f);
				negativeFlow.add(tp.get(i)*volume.get(i));
			} else {
				positiveFlow.add(0f);
				negativeFlow.add(0f);
			}

			if(i<periyot-1){
				mfi.add(0f);
			} else {
				indMFI.setLastPositiveFlow(getAverage(positiveFlow, periyot, i));
				indMFI.setLastNegativeFlow(getAverage(negativeFlow, periyot, i));
				indMFI.setLastTP(tp.get(i));
				mfi.add(100-(100/(1+(indMFI.getLastPositiveFlow()/indMFI.getLastNegativeFlow()))));
			}
		}

		return mfi;
		
	}
		
	public static List<Float> getMFI(List<Float> close, List<Float> highs, List<Float> lows, List<Float> volume, int periyot){
		List<Float> mfi = new ArrayList<Float>();
		List<Float> tp = new ArrayList<Float>();
		List<Float> positiveFlow = new ArrayList<Float>();
		List<Float> negativeFlow = new ArrayList<Float>();
		float flowRatio = 0f;
		mfi.add(0f);
		tp.add((close.get(0)+highs.get(0)+lows.get(0))/3);
		positiveFlow.add(0f);
		negativeFlow.add(0f);
		for(int i = 1;i<close.size();i++){
			tp.add((close.get(i)+highs.get(i)+lows.get(i))/3);
			if(tp.get(i)>tp.get(i-1)){
				positiveFlow.add(tp.get(i)*volume.get(i));
				negativeFlow.add(0f);
			} else if (tp.get(i)<tp.get(i-1)){
				positiveFlow.add(0f);
				negativeFlow.add(tp.get(i)*volume.get(i));
			} else {
				positiveFlow.add(0f);
				negativeFlow.add(0f);
			}

			if(i<periyot-1){
				mfi.add(0f);
			} else {
				flowRatio = getAverage(positiveFlow, periyot, i)/getAverage(negativeFlow, periyot, i);
				mfi.add(100-(100/(1+flowRatio)));
			}
		}
		return mfi;
	}

	private static float getMin(List<Float> vals, int periyot, int idx){
		idx++;
		float min = vals.get(idx-periyot);
		for(float v:vals.subList(idx-periyot, idx)){
			if(v<min){
				min = v;
			}
		}
		return min;
	}

	private static float getMax(List<Float> vals, int periyot, int idx){
		idx++;
		float max = vals.get(idx-periyot);
		for(float v:vals.subList(idx-periyot, idx)){
			if(v>max){
				max = v;
			}
		}
		return max;
	}
	
	public static List<Float> calculateWilliamsR(IndikatorWilliamsR indW, int periyot){
		List<Float> williamR = new ArrayList<Float>();
		float curMin, curMax;
		for(int i = 0;i<indW.getParentInstrumentData().getChartData().getKapanis().size();i++){
			if(i<periyot-1){
				williamR.add(indW.getParentInstrumentData().getChartData().getKapanis().get(i));
			}else if(i>=periyot-1){
				curMin = getMin(indW.getParentInstrumentData().getChartData().getDusuk(),periyot,i);
				curMax = getMax(indW.getParentInstrumentData().getChartData().getYuksek(),periyot,i);		
				indW.setLastMax(curMax);
				indW.setLastMin(curMin);
				williamR.add((-100*(curMax-indW.getParentInstrumentData().getChartData().getKapanis().get(i))/(curMax-curMin)));
			}
		}
		return williamR;
	}
	
	public static List<Float> getWilliamsR(List<Float> close, List<Float> highs, List<Float> lows, int periyot){
		List<Float> williamR = new ArrayList<Float>();
		float curMin, curMax;
		for(int i = 0;i<close.size();i++){
			if(i<periyot-1){
				williamR.add(close.get(i));
			}else if(i>=periyot-1){
				curMin = getMin(lows,periyot,i);
				curMax = getMax(highs,periyot,i);				
				williamR.add((-100*(curMax-close.get(i))/(curMax-curMin)));
			}
		}
		return williamR;
	}
	
	public static List<Float> rsiavaragegain;
	public static List<Float> rsiavarageloss;
	
	public static List<Float> getRSI(List<Float> vals, int periyot){
		List<Float> gain = new ArrayList<Float>();
		List<Float> loss = new ArrayList<Float>();
		List<Float> rsi = new ArrayList<Float>();
		rsiavaragegain = new ArrayList<Float>();
		rsiavarageloss = new ArrayList<Float>();
		gain.add(0f);
		loss.add(0f);
		rsi.add(0f);
		rsiavaragegain.add(0f);
		rsiavarageloss.add(0f);
		float curVal, prevVal;
		for(int i = 1;i<vals.size();i++){
			curVal = vals.get(i);
			prevVal = vals.get(i-1);
			if(curVal>prevVal){
				gain.add(curVal-prevVal);
				loss.add(0f);
			} else if(prevVal>curVal){
				gain.add(0f);
				loss.add(prevVal-curVal);
			} else {
				gain.add(0f);
				loss.add(0f);
			}
			if(i<periyot-1){
				rsi.add(0f);
				rsiavaragegain.add(0f);
				rsiavarageloss.add(0f);
			} else if(i==periyot-1){
				rsiavaragegain.add(getAverage(gain, periyot, i));
				rsiavarageloss.add(getAverage(loss, periyot, i));
				rsi.add(100-(100/(1+(rsiavaragegain.get(i)/rsiavarageloss.get(i)))));
			} else {
				rsiavaragegain.add(((rsiavaragegain.get(i-1)*(periyot-1))+gain.get(i))/periyot);
				rsiavarageloss.add(((rsiavarageloss.get(i-1)*(periyot-1))+loss.get(i))/periyot);
				rsi.add(100-(100/(1+(rsiavaragegain.get(i)/rsiavarageloss.get(i)))));
			}			
		}
		return rsi;
	}
	
	private static float getMeanDeviation(List<Float> tp, float tpma, int periyot, int i){
		i++;
		float meandev = 0f;
		for(int k = i-periyot;k<i;k++){
			meandev = meandev+ Math.abs(tpma-tp.get(k));
		}		
		return meandev/periyot;
	}
	
	public static List<Float> getCCI(List<Float> close, List<Float> highs, List<Float> lows, int periyot){
		float constant = 0.015f;
		List<Float> tp = new ArrayList<Float>();
		List<Float> tpma = new ArrayList<Float>();
		List<Float> meandev = new ArrayList<Float>();
		List<Float> cci = new ArrayList<Float>();
		for(int i = 0;i<close.size();i++){
			tp.add((close.get(i)+highs.get(i)+lows.get(i))/3);
			if(i<periyot-1){
				tpma.add(tp.get(i));
				meandev.add(0f);
				cci.add(0f);
			} else {
				tpma.add(getAverage(tp, periyot, i));
				meandev.add(getMeanDeviation(tp, tpma.get(i), periyot, i));
				cci.add((tp.get(i)-tpma.get(i))/(constant*meandev.get(i)));
			}
		}
		return cci;
	}
	
	public static List<Float> getMACDLine(List<Float> vals){
		List<Float> ema12 = getEMA(vals,12);
		List<Float> ema26 = getEMA(vals,26);
		List<Float> macd = new ArrayList<Float>();
		for(int i=0;i<ema12.size();i++){
			macd.add(ema12.get(i)-ema26.get(i));
		}
		return macd;
	}
	public static List<Float> getMACD(List<Float> vals){
		List<Float> ema12 = getEMA(vals,12);
		List<Float> ema26 = getEMA(vals,26);
		List<Float> macd = new ArrayList<Float>();
		for(int i=0;i<ema12.size();i++){
			macd.add(ema12.get(i)-ema26.get(i));
		}
		return getEMA(macd, 9);
	}
	
	public static List<Float> getStochasticRSI(List<Float> vals, int periyot){
		List<Float> rsi = getRSI(vals, periyot);
		List<Float> stochRSI = new ArrayList<Float>();
		float curMin, curMax;
		for(int i = 0;i<rsi.size();i++){
			if(i<periyot-1){
				stochRSI.add(0f);
			}else if(i>=periyot-1){
				curMin = getMin(rsi,periyot,i);
				curMax = getMax(rsi,periyot,i);
				stochRSI.add((rsi.get(i)-curMin)/(curMax-curMin));
			}
		}
		return stochRSI;
	}
		
	private static List<Float> getStdDev(List<Float> vals, int periyot){
		List<Float> avarage = new ArrayList<Float>();
		List<Float> deviationsquare = new ArrayList<Float>();
		List<Float> stddeviation = new ArrayList<Float>();
		for(int i=0;i<vals.size();i++){
			if(i<periyot-1){
				avarage.add(vals.get(i));
				deviationsquare.add(0f);
				stddeviation.add(0f);
			}else if(i>=periyot-1){
				avarage.add(getAverage(vals, periyot, i));
			}
		}
		
		float curDev;
		for(int k=periyot-1;k<vals.size();k++){
			curDev =vals.get(k)-avarage.get(k);
			deviationsquare.add(curDev*curDev);
			for(int u=k-periyot+1;u<k;u++){
				curDev = vals.get(u)-avarage.get(k);
				deviationsquare.set(u,curDev*curDev);
			}
			stddeviation.add((float) Math.sqrt(getAverage(deviationsquare, periyot, k)));
		}
		
		return stddeviation;
		
	}
	
	public static List<List<Float>> getBollingerBands(List<Float> vals, int periyot){

		List<Float> bollingerUpperBand = new ArrayList<Float>();
		List<Float> bollingerLowerBand = new ArrayList<Float>();
		List<Float> bollingerBandWidth = new ArrayList<Float>();
		
		List<Float> stdDev = getStdDev(vals, periyot);
		List<Float> sma = getSMA(vals, periyot);
		
		for(int i = 0;i<vals.size();i++){
			bollingerUpperBand.add(sma.get(i)+(stdDev.get(i)*2));
			bollingerLowerBand.add(sma.get(i)-(stdDev.get(i)*2));
			bollingerBandWidth.add(stdDev.get(i)*4);
			
		}
		
		List<List<Float>> bollinger = new ArrayList<List<Float>>();
		bollinger.add(bollingerUpperBand);
		bollinger.add(bollingerLowerBand);
		bollinger.add(bollingerBandWidth);
		
		return bollinger;
	}

	public static float getSum(Float[] vals){
		float val = 0f;
		for(Float f:vals){
			val = val + f;
		}
		return val;
	}
	
	public static List<Float> getCorrelation(List<Float> valsA, List<Float> valsB, int periyot){
		List<Float> correlation = new ArrayList<Float>();
		float meanA=0f;
		float meanB=0f;
		float curA = 0f;
		float curB = 0;
		Float[] sqrA = new Float[periyot];
		Float[] sqrB = new Float[periyot];
		Float[] AtimesB = new Float[periyot];
		

		for(int i=0;i<valsA.size();i++){
			if(i<periyot-1){
				correlation.add(0f);
			}else if(i>=periyot-1){
				meanA = getAverage(valsA, periyot, i);
				meanB = getAverage(valsB, periyot, i);
				for(int k = 0;k<periyot;k++){
					curA = meanA - valsA.get(i-k);
					curB = meanB - valsB.get(i-k);
					sqrA[k] = curA*curA;
					sqrB[k] = curB*curB;
					AtimesB[k] = curA*curB;
				}
				correlation.add((float) (getSum(AtimesB)/ Math.sqrt(getSum(sqrA)*getSum(sqrB))));
			}
		}
		return correlation;
	}
	
	public static List<Float> getStochasticK(List<Float> close, List<Float> highs, List<Float> mins, int periyot){
		List<Float> stochK = new ArrayList<Float>();
		float curMin, curMax;
		for(int i = 0;i<close.size();i++){
			if(i<periyot-1){
				stochK.add(0f);
			} else {
				curMin = getMin(mins,periyot,i);
				curMax = getMax(highs,periyot,i);
				stochK.add(((close.get(i)-curMin)/(curMax-curMin)) * 100);
			}
		}
		return stochK;
	}
	
	
}

