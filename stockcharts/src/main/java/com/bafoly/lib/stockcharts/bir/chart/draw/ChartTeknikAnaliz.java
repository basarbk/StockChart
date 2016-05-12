package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.Coordinate;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.UserInputTechnicalAnalysisData;

public class ChartTeknikAnaliz extends ChartDraw {
	
	Paint line;
	int lineColor = Color.BLACK;
	float lineWidth = 3;
	
	boolean paintsLoaded = false;
	
	public ChartTeknikAnaliz(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}



	@Override
	public void preChecks() {
		if(!paintsLoaded){
			line = new Paint();
			line.setColor(lineColor);
			line.setStrokeWidth(lineWidth);
			line.setStyle(Paint.Style.STROKE);
			paintsLoaded = true;

		}

	}

	int sc;

	boolean scinit = false;
	
	@Override
	public void draw(Canvas canvas) {

	}
	
	public void draw(Canvas canvas, UserInputTechnicalAnalysisData technicalAnalysisData, boolean editMode) {
		preChecks();

		if (!scinit) {

			sc = canvas.saveLayer(
					chartPosition.getPaddingLeft(),
					chartPosition.getPaddingTop(),
					chartPosition.getViewWidth() - chartPosition.getPaddingRight(),
					chartPosition.getViewHeight() - chartPosition.getPaddingBottom(), null,
					Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
							| Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
							| Canvas.FULL_COLOR_LAYER_SAVE_FLAG
							| Canvas.CLIP_TO_LAYER_SAVE_FLAG);
			scinit = false;
		}
		
		line.setColor(technicalAnalysisData.getColor());

		Coordinate[] coordinates = technicalAnalysisData.getPoints(chartPosition, mainData);
		if (coordinates != null) {
			for (Coordinate c : coordinates) {
				canvas.drawLine(c.x1, c.y1, c.x2, c.y2, line);
			}
		}
		if(editMode){
			if(technicalAnalysisData.getAnalysisType()!= Chart.ANALYSIS_TYPE_SUPPORT_RESISTANCE){
				for(Rect r:technicalAnalysisData.getTouchingAreas()){
					canvas.drawCircle(r.exactCenterX(), r.centerY(), r.width()/2, line);
				}
			}
		}
		canvas.restoreToCount(sc);
	}
}
