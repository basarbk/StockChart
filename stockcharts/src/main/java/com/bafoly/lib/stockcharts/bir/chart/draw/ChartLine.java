package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.MainIndicatorData;
import com.bafoly.lib.stockcharts.bir.chart.utils.FormatTools;


public class ChartLine extends ChartDraw {

	public ChartLine(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}

	private boolean isLinePaintLoaded = false;
	private boolean isThereThresholdColors = false;
	private boolean isLineThresholdPaintLoaded = false;
	
	private Path linePath, highAreaPath, lowAreaPath;
	private Paint lineColor;
	
	private Bitmap bitmapBGTop, bitmapBGBottom;
	private Paint paintBitmapArea;
	
	private PorterDuffXfermode xferMode;
	
	private Float[] benchVals;
	
	private void initLinePainting(){
		if(!isLinePaintLoaded){
			lineColor = new Paint();
			lineColor.setStyle(Paint.Style.STROKE);
			lineColor.setStrokeWidth(2);
			lineColor.setAntiAlias(true);
			lineColor.setStrokeJoin(Paint.Join.ROUND);
			lineColor.setStrokeCap(Paint.Cap.ROUND);
			lineColor.setPathEffect(new CornerPathEffect(5) );
			lineColor.setShadowLayer(1, 1, 1, Color.BLACK);
			isLinePaintLoaded = true;

		}
	}
	
	private void initLineThresholdPainting(){
		if(!isLineThresholdPaintLoaded){
			
			paintBitmapArea = new Paint();
			paintBitmapArea.setStyle(Paint.Style.FILL);
			paintBitmapArea.setColor(Color.BLACK);
			xferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
			isLineThresholdPaintLoaded = true;
		}
	}
	
	private Bitmap bitmapBG, bitmapMask, bitmapPath;
	private Canvas canvasBG, canvasMask, canvasPath;

	
	
	
	
	
	
	
	
	
	
	
	
	
	MainIndicatorData currentIndikatorData;
	
	@Override
	public void preChecks() {
		initLinePainting();
		isThereThresholdColors = false;
		
		if(mainData.getMainType()== Chart.MAIN_TYPE_INDICATOR){
			currentIndikatorData = (MainIndicatorData) mainData;
			if(currentIndikatorData.isHasThresholdValues()){
				isThereThresholdColors = true;
				initLineThresholdPainting();
				benchVals = currentIndikatorData.getThresholds();
			}
		}
		
		linePath = new Path();
		if(isThereThresholdColors){
			highAreaPath = new Path();
			lowAreaPath = new Path();
		}


	}
	
	int skip = -1;
	
	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}
	
	boolean first = true;
	int sc;

	boolean scinit = false;
	@Override
	public void draw(Canvas canvas) {
		preChecks();

		if(!scinit){
			
			sc = canvas.saveLayer(
					chartPosition.getPaddingLeft(), chartPosition.getPaddingTop(), 
					chartPosition.getViewWidth()-chartPosition.getPaddingRight(),
					chartPosition.getViewHeight()-chartPosition.getPaddingBottom(), null,
					Canvas.MATRIX_SAVE_FLAG |
					Canvas.CLIP_SAVE_FLAG |
					Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
					Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
					Canvas.CLIP_TO_LAYER_SAVE_FLAG);
			scinit = false;
		}
		
		
		first = true;
		lineColor.setColor(mainData.getColor());
		isThereThresholdColors =false;
		
		for (int i = chartPosition.getVisibleXbegin(); i < chartPosition.getVisibleXend(); i++) {
			if(i>skip && i<mainData.getChartData().getLineData().size()){
				chartValuePositionY = chartPosition.getPriceToPoisiton(mainData.getChartData().getLineData().get(i), mainData);
				chartIdxPositionX = chartPosition.getXChartIDXPosition(i, mainData.getMultiplierX());
				if (first) {
					first = false;
					linePath.moveTo(chartIdxPositionX, chartValuePositionY);
					if(isThereThresholdColors){
						highAreaPath.moveTo(chartIdxPositionX, chartPosition.getViewHeight());
						highAreaPath.lineTo(chartIdxPositionX, chartValuePositionY);
						lowAreaPath.moveTo(chartIdxPositionX, 0);
						lowAreaPath.lineTo(chartIdxPositionX, chartValuePositionY);
					}
				} else {
					linePath.lineTo(chartIdxPositionX, chartValuePositionY);
					if(isThereThresholdColors){						
						highAreaPath.lineTo(chartIdxPositionX, chartValuePositionY);
						lowAreaPath.lineTo(chartIdxPositionX, chartValuePositionY);
					}
				}
			}
		}
		if(isThereThresholdColors){
			highAreaPath.lineTo(chartIdxPositionX, chartPosition.getViewHeight());
			lowAreaPath.lineTo(chartIdxPositionX, 0);
			highAreaPath.close();
			lowAreaPath.close();
		}
		
		
		if(isThereThresholdColors){
			bitmapBGTop = createBG(highAreaPath, Color.RED, chartPosition.getPriceToPoisiton(benchVals[1], mainData), 0);
			bitmapBGBottom = createBG(lowAreaPath, Color.GREEN, chartPosition.getPriceToPoisiton(benchVals[0], mainData), chartPosition.getViewWidth());
			canvas.drawBitmap(bitmapBGTop, 0, 0, lineColor);
			canvas.drawBitmap(bitmapBGBottom, 0, 0, lineColor);
			drawYatayCizgi(canvas, benchVals[1], Color.MAGENTA, TEXT_LEFT);
			drawYatayCizgi(canvas, benchVals[0], Color.MAGENTA, TEXT_LEFT);
		}
		

		
		canvas.drawPath(linePath, lineColor);
		canvas.restoreToCount(sc);

	}

	
	
	
	
	
	
	
	
	
	private Bitmap createBG(Path p, int color, float y1, float y2){
//		bitmapBG = Bitmap.createBitmap(chartView.viewWidth-chartView.paddingLeft-chartView.paddingRight, chartView.viewHeight, Bitmap.Config.ARGB_8888);
//		bitmapMask = Bitmap.createBitmap(chartView.viewWidth-chartView.paddingLeft-chartView.paddingRight, chartView.viewHeight, Bitmap.Config.ARGB_8888);
//		bitmapPath = Bitmap.createBitmap(chartView.viewWidth-chartView.paddingLeft-chartView.paddingRight, chartView.viewHeight, Bitmap.Config.ARGB_8888);

		bitmapBG = Bitmap.createBitmap(chartPosition.getDrawWidht(), chartPosition.getViewHeight(), Bitmap.Config.ARGB_8888);
		bitmapMask = Bitmap.createBitmap(chartPosition.getDrawWidht(), chartPosition.getViewHeight(), Bitmap.Config.ARGB_8888);
		bitmapPath = Bitmap.createBitmap(chartPosition.getDrawWidht(), chartPosition.getViewHeight(), Bitmap.Config.ARGB_8888);

		
		
		canvasBG = new Canvas(bitmapBG);
		paintBitmapArea.setColor(color);

		for(int i = -chartPosition.getViewHeight();i<chartPosition.getViewWidth();i+=6){
			canvasBG.drawLine(i, 0, i+chartPosition.getViewHeight(), chartPosition.getViewHeight(), paintBitmapArea);
		}

		// maskeyi olusturduk
		
		canvasMask = new Canvas(bitmapMask);
		canvasMask.drawRect(0, y1, chartPosition.getViewWidth(), y2, paintBitmapArea);
		// cizgiyi bitmap olarak aktardik
		canvasMask.drawBitmap(bitmapMask, 0, 0, null);
		paintBitmapArea.setXfermode(xferMode);

		// cizgileri maskeledik
		canvasMask.drawBitmap(bitmapBG, 0, 0, paintBitmapArea);
		paintBitmapArea.setXfermode(null);

		canvasPath = new Canvas(bitmapPath);
		canvasPath.drawPath(p, paintBitmapArea);
		
		// path i ciziyoruz
		canvasPath.drawBitmap(bitmapPath, 0, 0, null);
		
		paintBitmapArea.setXfermode(xferMode);
		
		// ve ayikladigimiz ozellestirdigimiz maskeyle sekillendiriyoruz
		canvasPath.drawBitmap(bitmapMask, 0, 0, paintBitmapArea);
		paintBitmapArea.setXfermode(null);
		return bitmapPath;
//		return bitmapMask;
	}
	
	
	

	public static final int TEXT_LEFT = 0;
	public static final int TEXT_RIGHT = 1;
	public static final int TEXT_CENTER = 2;
	public static final int TEXT_NONE = 3;
	
	Paint singleLinePaint;
	float singleLineWidth = 1;
	boolean singleLinePaintLoaded = false;
	
	private void initYatayCizgiPaint(){
		if(!singleLinePaintLoaded){
			singleLinePaint = new Paint();
			singleLinePaint.setStyle(Paint.Style.STROKE);
			singleLinePaint.setStrokeWidth(singleLineWidth);
			singleLinePaint.setAntiAlias(true);
			singleLinePaintLoaded = true;
		}
	}
	
	float textWidth;
	Rect textBounds;
	float chartValuePositionY;

	String val;
	public void drawYatayCizgi(Canvas canvas, float x, int color, int textdirection){
		initYatayCizgiPaint();
		singleLinePaint.setColor(color);
		chartValuePositionY = chartPosition.getPriceToPoisiton(x, mainData);
		canvas.drawLine(chartPosition.getChartGraphArea().left, chartValuePositionY, chartPosition.getChartGraphArea().right, chartValuePositionY, singleLinePaint);
		if(textdirection!=TEXT_NONE){
			val = FormatTools.formatFloat(x);
			textWidth = singleLinePaint.measureText(val);
			if(textBounds==null){				
				textBounds = new Rect();
			}
			singleLinePaint.getTextBounds(val, 0, val.length(), textBounds);
			if(textdirection==TEXT_LEFT){
				canvas.drawText(val, chartPosition.getPaddingLeft(), chartValuePositionY+5+textBounds.height(), singleLinePaint);
			} else if (textdirection == TEXT_RIGHT){
				canvas.drawText(val, chartPosition.getViewWidth()-chartPosition.getPaddingRight()-textWidth, chartValuePositionY+5+textBounds.height(), singleLinePaint);
			} else if (textdirection == TEXT_CENTER){
				canvas.drawText(val, (chartPosition.getViewWidth()/2)-(textWidth/2), chartValuePositionY+5+textBounds.height(), singleLinePaint);
			}

		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
